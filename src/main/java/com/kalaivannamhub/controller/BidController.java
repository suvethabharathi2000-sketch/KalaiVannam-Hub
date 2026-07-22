
package com.kalaivannamhub.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.kalaivannamhub.entity.Bid;
import com.kalaivannamhub.service.BidService;

@RestController
@RequestMapping("/api/artist/bids")
public class BidController {

    @Autowired
    private BidService bidService;

    @PostMapping("/{requestId}")
    public Bid submitBid(@PathVariable Long requestId,
                         @RequestBody Bid bid,
                         Principal principal) {

        return bidService.submitBid(requestId, bid, principal.getName());

    }
    
    @GetMapping("/request/{requestId}")
    public List<Bid> getBidsForRequest(@PathVariable Long requestId) {

        return bidService.getBidsForRequest(requestId);

    }
    
    @PutMapping("/select/{bidId}")
    public Bid selectBid(@PathVariable Long bidId) {

        return bidService.selectBid(bidId);

    }

}
