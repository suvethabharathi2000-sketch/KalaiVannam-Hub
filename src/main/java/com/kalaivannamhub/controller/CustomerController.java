
package com.kalaivannamhub.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.security.Principal;
import com.kalaivannamhub.entity.CustomerProfile;
import com.kalaivannamhub.service.CustomerProfileService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
	
	@Autowired
	private CustomerProfileService customerProfileService;

    @GetMapping("/home")
    public String home() {
        return "Welcome Customer!";
    }
    
    @PostMapping("/profile")
    public CustomerProfile saveProfile(@RequestBody CustomerProfile profile,
                                       Principal principal) {

        return customerProfileService.saveProfile(profile, principal.getName());
    }
    
    @GetMapping("/profile")
    public CustomerProfile getProfile(Principal principal) {

        return customerProfileService.getProfile(principal.getName());
    }

}