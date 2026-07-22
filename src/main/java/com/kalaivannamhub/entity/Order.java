
package com.kalaivannamhub.entity;

import java.time.LocalDate;

import com.kalaivannamhub.enums.OrderStatus;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "bid_id")
    private Bid bid;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private User artist;

    private Double totalAmount;

    private Double advanceAmount;

    private Double remainingAmount;
    
    private String finalArtworkUrl;
    
    private LocalDate completedDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.PENDING_ADVANCE_PAYMENT;

    private LocalDate createdAt = LocalDate.now();

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public Bid getBid() {
        return bid;
    }

    public void setBid(Bid bid) {
        this.bid = bid;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public User getArtist() {
        return artist;
    }

    public void setArtist(User artist) {
        this.artist = artist;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getAdvanceAmount() {
        return advanceAmount;
    }

    public void setAdvanceAmount(Double advanceAmount) {
        this.advanceAmount = advanceAmount;
    }

    public Double getRemainingAmount() {
        return remainingAmount;
    }

    public void setRemainingAmount(Double remainingAmount) {
        this.remainingAmount = remainingAmount;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }
    public String getFinalArtworkUrl() {
        return finalArtworkUrl;
    }

    public void setFinalArtworkUrl(String finalArtworkUrl) {
        this.finalArtworkUrl = finalArtworkUrl;
    }

    public LocalDate getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(LocalDate completedDate) {
        this.completedDate = completedDate;
    }
}