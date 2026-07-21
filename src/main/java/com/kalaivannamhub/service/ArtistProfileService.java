
	package com.kalaivannamhub.service;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Service;

	import com.kalaivannamhub.entity.ArtistProfile;
	import com.kalaivannamhub.entity.User;
	import com.kalaivannamhub.repository.ArtistProfileRepository;
	import com.kalaivannamhub.repository.UserRepository;

	@Service
	public class ArtistProfileService {

	    @Autowired
	    private ArtistProfileRepository artistProfileRepository;

	    @Autowired
	    private UserRepository userRepository;

	    public ArtistProfile saveProfile(ArtistProfile profile, String email) {

	        User user = userRepository.findByEmail(email)
	                .orElseThrow(() -> new RuntimeException("User not found"));

	        profile.setUser(user);

	        return artistProfileRepository.save(profile);
	    }
	    
	    public ArtistProfile getProfile(String email) {

	        User user = userRepository.findByEmail(email)
	                .orElseThrow(() -> new RuntimeException("User not found"));

	        return artistProfileRepository.findByUserId(user.getId())
	                .orElseThrow(() -> new RuntimeException("Artist profile not found"));
	    }
	}


