
package com.kalaivannamhub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kalaivannamhub.entity.ArtworkRequest;
import com.kalaivannamhub.entity.Bid;
import com.kalaivannamhub.entity.User;

public interface BidRepository extends JpaRepository<Bid, Long> {

    // Get all bids submitted by an artist
    List<Bid> findByArtist(User artist);

    // Get all bids for a particular artwork request
    List<Bid> findByArtworkRequest(ArtworkRequest artworkRequest);

}