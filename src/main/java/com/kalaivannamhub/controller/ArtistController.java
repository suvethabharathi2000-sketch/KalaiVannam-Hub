
package com.kalaivannamhub.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.kalaivannamhub.entity.ArtistProfile;
import com.kalaivannamhub.service.ArtistProfileService;

@RestController
@RequestMapping("/api/artist")
public class ArtistController {

    @Autowired
    private ArtistProfileService artistProfileService;

    @PostMapping("/profile")
    public ArtistProfile saveProfile(@RequestBody ArtistProfile profile,
                                     Principal principal) {

        return artistProfileService.saveProfile(profile, principal.getName());
    }
    
    
    @GetMapping("/profile")
    public ArtistProfile getProfile(Principal principal) {

        return artistProfileService.getProfile(principal.getName());
    }
}