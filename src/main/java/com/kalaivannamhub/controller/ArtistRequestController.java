
package com.kalaivannamhub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kalaivannamhub.entity.ArtworkRequest;
import com.kalaivannamhub.service.ArtworkRequestService;

@RestController
@RequestMapping("/api/artist/requests")
public class ArtistRequestController {

    @Autowired
    private ArtworkRequestService artworkRequestService;

    @GetMapping
    public List<ArtworkRequest> getAllOpenRequests() {

        return artworkRequestService.getAllOpenRequests();

    }

}