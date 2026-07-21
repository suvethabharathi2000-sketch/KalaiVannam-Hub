
package com.kalaivannamhub.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kalaivannamhub.entity.ArtistProfile;

public interface ArtistProfileRepository extends JpaRepository<ArtistProfile, Long> {

    Optional<ArtistProfile> findByUserId(Long userId);

}