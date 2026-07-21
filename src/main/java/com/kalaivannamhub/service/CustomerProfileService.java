
package com.kalaivannamhub.service;

import org.springframework.beans.factory.annotation.Autowired;
import com.kalaivannamhub.entity.User;
import com.kalaivannamhub.repository.UserRepository;
import org.springframework.stereotype.Service;

import com.kalaivannamhub.entity.CustomerProfile;
import com.kalaivannamhub.repository.CustomerProfileRepository;

@Service
public class CustomerProfileService {
	
	@Autowired
	private UserRepository userRepository;

    @Autowired
    private CustomerProfileRepository customerProfileRepository;

    public CustomerProfile saveProfile(CustomerProfile profile, String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        profile.setUser(user);

        return customerProfileRepository.save(profile);
    }
    
    
    public CustomerProfile getProfile(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return customerProfileRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Profile not found"));
    }

}
