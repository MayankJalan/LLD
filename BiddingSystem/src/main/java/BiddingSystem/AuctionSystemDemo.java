package BiddingSystem;

import java.util.List;

public class AuctionSystemDemo {

    public static void main(String[] args) {
        AuctionSystem auctionSystem=AuctionSystem.getInstance();

        User user1=new User("001","Mayank","mnk@gmail.com");
        User user2=new User("002","Kush","kush@gmail.com");
        auctionSystem.registerUser(user1);
        auctionSystem.registerUser(user2);

        AuctionListing auctionListing1=new AuctionListing("001","item1","Descrption1",10000,60000,user1);
        AuctionListing auctionListing2=new AuctionListing("002","item2","Descrption2",20000,60000,user2);

        auctionSystem.createAuctionListing(auctionListing1);
        auctionSystem.createAuctionListing(auctionListing2);

        List<AuctionListing> searchResults = auctionSystem.searchAuctionListings("item");
        System.out.println("Search Results:");
        for (AuctionListing listing : searchResults) {
            System.out.println(listing.getItemName());
        }

        Bid bid1=new Bid("1",user1,25000);
        Bid bid2=new Bid("2",user2,15000);

        auctionSystem.placeBid(auctionListing1.getId(), bid2);
        auctionSystem.placeBid(auctionListing1.getId(), bid1);

    }
}
