
package com.kalaivannamhub.entity;

import java.time.LocalDate;

import com.kalaivannamhub.enums.BidStatus;

import jakarta.persistence.*;

@Entity
@Table(name = "bids")
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double bidAmount;

    private Integer deliveryDays;

    @Column(length = 1000)
    private String message;

    @Enumerated(EnumType.STRING)
    private BidStatus status = BidStatus.PENDING;

    private LocalDate createdAt = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private User artist;

    @ManyToOne
    @JoinColumn(name = "request_id")
    private ArtworkRequest artworkRequest;

    public Bid() {
    }

    public Long getId() {
        return id;
    }

    public Double getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(Double bidAmount) {
        this.bidAmount = bidAmount;
    }

    public Integer getDeliveryDays() {
        return deliveryDays;
    }

    public void setDeliveryDays(Integer deliveryDays) {
        this.deliveryDays = deliveryDays;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BidStatus getStatus() {
        return status;
    }

    public void setStatus(BidStatus status) {
        this.status = status;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public User getArtist() {
        return artist;
    }

    public void setArtist(User artist) {
        this.artist = artist;
    }

    public ArtworkRequest getArtworkRequest() {
        return artworkRequest;
    }

    public void setArtworkRequest(ArtworkRequest artworkRequest) {
        this.artworkRequest = artworkRequest;
    }
}