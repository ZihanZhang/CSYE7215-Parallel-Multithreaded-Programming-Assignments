package Zihan;


/**
 * Class provided for ease of test. This will not be used in the project 
 * evaluation, so feel free to modify it as you like.
 */ 
public class Simulation
{
    public static void main(String[] args) throws InterruptedException {
        int nrSellers = 50;
        int nrBidders = 20;
        
        Thread[] sellerThreads = new Thread[nrSellers];
        Thread[] bidderThreads = new Thread[nrBidders];
        Seller[] sellers = new Seller[nrSellers];
        Bidder[] bidders = new Bidder[nrBidders];

        System.out.println("Please wait for 5 seconds");

        // Start the sellers
        for (int i=0; i<nrSellers; ++i)
        {
            sellers[i] = new Seller(
            		AuctionServer.getInstance(), 
            		"Seller"+i, 
            		100, 50, i
            );
            sellerThreads[i] = new Thread(sellers[i]);
            sellerThreads[i].start();
        }
        
        // Start the buyers
        for (int i=0; i<nrBidders; ++i)
        {
            bidders[i] = new Bidder(
            		AuctionServer.getInstance(), 
            		"Buyer"+i, 
            		1000, 20, 150, i
            );
            bidderThreads[i] = new Thread(bidders[i]);
            bidderThreads[i].start();
        }
        
        // Join on the sellers
        for (int i=0; i<nrSellers; ++i)
        {
            try
            {
                sellerThreads[i].join();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        
        // Join on the bidders
        for (int i=0; i<nrBidders; ++i)
        {
            try
            {
//                sellerThreads[i].join();
                  bidderThreads[i].join();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }

        // TODO: Add code as needed to debug
//        System.out.println(AuctionServer.getInstance().soldItemsCount());
//        System.out.println(AuctionServer.getInstance().revenue());
//
//        for (Item i : AuctionServer.getInstance().getItems()) {
//            System.out.println(i);
//        }
//        Thread.sleep(3000);
        System.out.println("**************************************");
        System.out.println("Total Revenue: " + AuctionServer.getInstance().revenue());
        System.out.println("Total Items: " + AuctionServer.getInstance().soldItemsCount());


        System.out.println("**************************************");
    }
}