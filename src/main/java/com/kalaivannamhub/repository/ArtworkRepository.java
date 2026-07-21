
package com.kalaivannamhub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kalaivannamhub.entity.Artwork;

public interface ArtworkRepository extends JpaRepository<Artwork, Long> {

    List<Artwork> findByArtistProfileId(Long artistProfileId);

}