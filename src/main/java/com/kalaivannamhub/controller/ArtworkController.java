

	package com.kalaivannamhub.controller;

	import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.web.bind.annotation.*;

	import com.kalaivannamhub.entity.Artwork;
	import com.kalaivannamhub.service.ArtworkService;

	@RestController
	@RequestMapping("/api/artworks")
	public class ArtworkController {

	    @Autowired
	    private ArtworkService artworkService;

	    @PostMapping
	    public Artwork addArtwork(@RequestBody Artwork artwork,
	                              Principal principal) {

	        return artworkService.addArtwork(artwork, principal.getName());
	    }
	    
	    @GetMapping("/my")
	    public List<Artwork> getMyArtworks(Principal principal) {

	        return artworkService.getMyArtworks(principal.getName());
	    }
	    
	    @GetMapping
	    public List<Artwork> getAllArtworks() {
	        return artworkService.getAllArtworks();
	    }
	}
