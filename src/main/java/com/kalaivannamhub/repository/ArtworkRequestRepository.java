
package com.kalaivannamhub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kalaivannamhub.entity.ArtworkRequest;
import com.kalaivannamhub.entity.User;
import com.kalaivannamhub.enums.RequestStatus;

public interface ArtworkRequestRepository extends JpaRepository<ArtworkRequest, Long> {

    // Get all requests posted by a particular customer
    List<ArtworkRequest> findByCustomer(User customer);

    // Get all requests based on status (OPEN, COMPLETED...)
    List<ArtworkRequest> findByStatus(RequestStatus status);

}