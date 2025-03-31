package BiddingSystem;

import java.util.*;
import java.util.stream.Collectors;

public class AuctionSystem {
    private static AuctionSystem instance;

    Map<String, User> users;
    Map<String, AuctionListing> auctionListings;

    public AuctionSystem() {
        users = new HashMap<>();
        auctionListings = new HashMap<>();
    }

    public static synchronized AuctionSystem getInstance() {
        if (instance == null) {
            instance = new AuctionSystem();
        }
        return instance;
    }

    public void registerUser(User user) {
        users.put(user.getId(), user);
    }

    public User getUser(String userId) {
        return users.get(userId);
    }

    public void placeBid(String auctionListingId, Bid bid) {
        AuctionListing auctionListing=auctionListings.get(auctionListingId);
        if(auctionListing != null)
            auctionListing.placeBid(bid);

    }

    public void createAuctionListing(AuctionListing auctionListing) {
        auctionListings.put(auctionListing.getId(), auctionListing);
        startAuctionTimer(auctionListing);

    }

    public List<AuctionListing> searchAuctionListings(String keyword) {
        return auctionListings.values().
                                stream().
                                filter(item -> item.getItemName().contains(keyword) ||
                                        item.getDescription().contains(keyword)).collect(Collectors.toList());
    }


        private void startAuctionTimer(AuctionListing auctionListing) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                auctionListing.closeAuction();
            }
        },auctionListing.getDuration());
    }

}
