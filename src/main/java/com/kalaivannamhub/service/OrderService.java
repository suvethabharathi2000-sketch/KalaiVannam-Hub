
package com.kalaivannamhub.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kalaivannamhub.entity.Bid;
import com.kalaivannamhub.entity.Order;
import com.kalaivannamhub.entity.User;
import com.kalaivannamhub.enums.OrderStatus;
import com.kalaivannamhub.repository.BidRepository;
import com.kalaivannamhub.repository.OrderRepository;
import com.kalaivannamhub.repository.UserRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BidRepository bidRepository;

    @Autowired
    private UserRepository userRepository;
    
    public Order createOrder(Long bidId) {

        Bid bid = bidRepository.findById(bidId)
                .orElseThrow(() -> new RuntimeException("Bid not found"));
        if (orderRepository.findByBid(bid).isPresent()) {
            throw new RuntimeException("Order already exists for this bid");
        }

        Order order = new Order();

        order.setBid(bid);

        order.setCustomer(bid.getArtworkRequest().getCustomer());

        order.setArtist(bid.getArtist());

        order.setTotalAmount(bid.getBidAmount());

        order.setAdvanceAmount(bid.getBidAmount() * 0.30);

        order.setRemainingAmount(bid.getBidAmount() * 0.70);

        order.setStatus(OrderStatus.PENDING_ADVANCE_PAYMENT);

        return orderRepository.save(order);

    }
    
    public Order payAdvance(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (order.getStatus() != OrderStatus.PENDING_ADVANCE_PAYMENT) {
            throw new RuntimeException("Advance payment already completed.");
        }

        order.setStatus(OrderStatus.ADVANCE_PAID);

        return orderRepository.save(order);
    }

    public Order startWork(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (order.getStatus() != OrderStatus.ADVANCE_PAID) {
            throw new RuntimeException("Customer has not paid the advance.");
        }

        order.setStatus(OrderStatus.IN_PROGRESS);

        return orderRepository.save(order);
    }
    
    public Order uploadFinalArtwork(Long orderId, String artworkUrl) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (order.getStatus() != OrderStatus.IN_PROGRESS) {
            throw new RuntimeException("Work has not started yet.");
        }

        order.setFinalArtworkUrl(artworkUrl);

        order.setCompletedDate(LocalDate.now());

        order.setStatus(OrderStatus.ARTWORK_UPLOADED);

        return orderRepository.save(order);
    }
}