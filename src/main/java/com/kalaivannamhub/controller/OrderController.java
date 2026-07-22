
package com.kalaivannamhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.kalaivannamhub.entity.Order;
import com.kalaivannamhub.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PutMapping("/{orderId}/pay-advance")
    public Order payAdvance(@PathVariable Long orderId) {

        return orderService.payAdvance(orderId);

    }
    
    @PutMapping("/{orderId}/start-work")
    public Order startWork(@PathVariable Long orderId) {

        return orderService.startWork(orderId);

    }

}