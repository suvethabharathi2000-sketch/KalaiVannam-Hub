
package com.kalaivannamhub.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.kalaivannamhub.entity.ArtworkRequest;
import com.kalaivannamhub.service.ArtworkRequestService;

@RestController
@RequestMapping("/api/customer/requests")
public class ArtworkRequestController {

    @Autowired
    private ArtworkRequestService artworkRequestService;

    @PostMapping
    public ArtworkRequest createRequest(@RequestBody ArtworkRequest request,
                                        Principal principal) {

        return artworkRequestService.createRequest(request, principal.getName());
    }
    
    @GetMapping
    public List<ArtworkRequest> getMyRequests(Principal principal) {

        return artworkRequestService.getMyRequests(principal.getName());
    }

}