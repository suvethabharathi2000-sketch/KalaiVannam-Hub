
package com.kalaivannamhub.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kalaivannamhub.entity.ArtistProfile;
import com.kalaivannamhub.entity.Artwork;
import com.kalaivannamhub.entity.User;
import com.kalaivannamhub.repository.ArtistProfileRepository;
import com.kalaivannamhub.repository.ArtworkRepository;
import com.kalaivannamhub.repository.UserRepository;

@Service
public class ArtworkService {

    @Autowired
    private ArtworkRepository artworkRepository;

    @Autowired
    private ArtistProfileRepository artistProfileRepository;

    @Autowired
    private UserRepository userRepository;

    public Artwork addArtwork(Artwork artwork, String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        ArtistProfile artistProfile = artistProfileRepository
                .findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Artist profile not found"));

        artwork.setArtistProfile(artistProfile);

        return artworkRepository.save(artwork);
    }
    
    public List<Artwork> getMyArtworks(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        ArtistProfile artistProfile = artistProfileRepository
                .findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Artist profile not found"));

        return artworkRepository.findByArtistProfileId(artistProfile.getId());
    }
    
    public List<Artwork> getAllArtworks() {
        return artworkRepository.findAll();
    }
}