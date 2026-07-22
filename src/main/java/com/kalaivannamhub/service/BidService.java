
package com.kalaivannamhub.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kalaivannamhub.entity.ArtworkRequest;
import com.kalaivannamhub.entity.Bid;
import com.kalaivannamhub.entity.User;
import com.kalaivannamhub.enums.BidStatus;
import com.kalaivannamhub.enums.RequestStatus;
import com.kalaivannamhub.repository.ArtworkRequestRepository;
import com.kalaivannamhub.repository.BidRepository;
import com.kalaivannamhub.repository.UserRepository;

@Service
public class BidService {

    @Autowired
    private BidRepository bidRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ArtworkRequestRepository artworkRequestRepository;
    
    @Autowired
    private OrderService orderService;
    
    public Bid submitBid(Long requestId, Bid bid, String email) {

        User artist = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Artist not found"));

        ArtworkRequest artworkRequest = artworkRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Artwork Request not found"));

        bid.setArtist(artist);

        bid.setArtworkRequest(artworkRequest);

        bid.setStatus(BidStatus.PENDING);

        return bidRepository.save(bid);
    }
    
    public List<Bid> getBidsForRequest(Long requestId) {

        ArtworkRequest artworkRequest = artworkRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Artwork Request not found"));

        return bidRepository.findByArtworkRequest(artworkRequest);

    }
    
    public Bid selectBid(Long bidId) {

        Bid selectedBid = bidRepository.findById(bidId)
                .orElseThrow(() -> new RuntimeException("Bid not found"));
        
        if (selectedBid.getStatus() == BidStatus.SELECTED) {
            throw new RuntimeException("This bid has already been selected.");
        }

        ArtworkRequest artworkRequest = selectedBid.getArtworkRequest();

        List<Bid> allBids = bidRepository.findByArtworkRequest(artworkRequest);

        for (Bid bid : allBids) {

            if (bid.getId().equals(bidId)) {
                bid.setStatus(BidStatus.SELECTED);
            } else {
                bid.setStatus(BidStatus.REJECTED);
            }

            bidRepository.save(bid);
        }

        artworkRequest.setStatus(RequestStatus.ASSIGNED);

        artworkRequestRepository.save(artworkRequest);
        
        //Automatically create Order
        orderService.createOrder(selectedBid.getId());
        
        return selectedBid;
    }

}