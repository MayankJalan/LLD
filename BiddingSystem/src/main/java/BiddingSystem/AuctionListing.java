package BiddingSystem;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class AuctionListing {
    private String id;
    private String itemName;
    private String description;
    private AuctionStatus status;
    private User seller;
    private double startingPrice;
    private double currentHighestBid;
    private User currentHighestBidder;
    private long duration;
    private List<Bid> bids;

    public AuctionListing(String id, String itemName, String description, double startingPrice, long duration, User seller) {
        this.id = id;
        this.itemName = itemName;
        this.description = description;
        this.startingPrice = startingPrice;
        this.duration = duration;
        this.seller = seller;
        this.status = AuctionStatus.ACTIVE;
        this.currentHighestBid = startingPrice;
        this.currentHighestBidder = null;
        this.bids = new CopyOnWriteArrayList<>();
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public String getItemName() {
        return itemName;
    }

    public String getDescription() {
        return description;
    }

    public long getDuration() {
        return duration;
    }

    private void notifyObservers() {
        // Notify observers (bidders) about the updated highest bid or auction closure
        // ...
    }
    public synchronized void placeBid(Bid bid){
        if(status.equals(AuctionStatus.ACTIVE) && currentHighestBid<=bid.getAmount()){
            currentHighestBid=bid.getAmount();
            currentHighestBidder=bid.getBidder();
            bids.add(bid);
            notifyObservers();
        }
        bids.add(bid);
        notifyObservers();
    }
    public synchronized void closeAuction() {
        if (status == AuctionStatus.ACTIVE) {
            status = AuctionStatus.CLOSED;
            notifyObservers();
        }
    }





}
