
package com.kalaivannamhub.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kalaivannamhub.entity.ArtworkRequest;
import com.kalaivannamhub.entity.User;
import com.kalaivannamhub.enums.RequestStatus;
import com.kalaivannamhub.repository.ArtworkRequestRepository;
import com.kalaivannamhub.repository.UserRepository;

@Service
public class ArtworkRequestService {

    @Autowired
    private ArtworkRequestRepository artworkRequestRepository;

    @Autowired
    private UserRepository userRepository;
    public ArtworkRequest createRequest(ArtworkRequest request,
            String email) {

User customer = userRepository.findByEmail(email)
.orElseThrow(() -> new RuntimeException("Customer not found"));

request.setCustomer(customer);

request.setStatus(RequestStatus.OPEN);

return artworkRequestRepository.save(request);
}
    
    public List<ArtworkRequest> getMyRequests(String email) {

        User customer = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        return artworkRequestRepository.findByCustomer(customer);
    }
    
    public List<ArtworkRequest> getAllOpenRequests() {

        return artworkRequestRepository.findByStatus(RequestStatus.OPEN);

    }
    

}