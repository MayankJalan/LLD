package BiddingSystem;

import java.time.LocalDateTime;

public class Bid {
    private String id;
    private User bidder;
    private double amount;
    private final LocalDateTime timestamp;
    public Bid(String id, User bidder, double amount) {
        this.id = id;
        this.bidder = bidder;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }

    // Getters and setters

    public double getAmount() {
        return amount;
    }

    public User getBidder() {
        return bidder;
    }

}
