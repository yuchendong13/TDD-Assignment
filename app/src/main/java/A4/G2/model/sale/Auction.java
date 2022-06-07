package A4.G2.model.sale;

import A4.G2.model.artwork.Art;
import A4.G2.model.users.User;
import A4.G2.service.payment.NoPaymentDetailsException;
import A4.G2.service.payment.UnderAgePurchaseException;
import A4.G2.service.payment.UnregisteredUserPurchaseException;
import A4.G2.service.payment.CheckUserPurchaseValidity;

public class Auction extends  Sale {

    private User bidPerson;
    private int numBids;
    private double reservePrice;
    private int timeRemaining;
    private double currentBid;

    public Auction(int saleID, double startingPrice, Art artPiece, double reservePrice, int timeRemain) {
        super(saleID, startingPrice, artPiece);
        this.reservePrice = reservePrice;
        this.timeRemaining = timeRemain;
    }

    public double getReservePrice() {
        return this.reservePrice;
    }

    public int getTimeRemaining() {
        return this.timeRemaining;
    }

    public double getCurrentBid() {
        return this.currentBid;
    }

    public int getTotalBids() {
        return this.numBids;
    }

    public User getCurrentBidder() {
        return this.bidPerson;
    }

    public void placeBid(User user, int price) throws NoPaymentDetailsException, UnregisteredUserPurchaseException,
            UnderAgePurchaseException{
        CheckUserPurchaseValidity.checkIfUserCanPurchaseArt(user);
        this.bidPerson = user;
        this.currentBid = price;
        this.numBids++;
    }
  
    @Override
    public String getSaleType() {
        return "Auction";
    }
}
