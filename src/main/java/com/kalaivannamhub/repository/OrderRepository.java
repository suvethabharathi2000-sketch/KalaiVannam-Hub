
package com.kalaivannamhub.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kalaivannamhub.entity.Bid;
import com.kalaivannamhub.entity.Order;
import com.kalaivannamhub.entity.User;

public interface OrderRepository extends JpaRepository<Order, Long> {

    // Customer Orders
    List<Order> findByCustomer(User customer);

    // Artist Orders
    List<Order> findByArtist(User artist);
    
    Optional<Order> findByBid(Bid bid);

}