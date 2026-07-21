
package com.kalaivannamhub.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kalaivannamhub.entity.CustomerProfile;

public interface CustomerProfileRepository extends JpaRepository<CustomerProfile, Long> {

    Optional<CustomerProfile> findByUserId(Long userId);

}