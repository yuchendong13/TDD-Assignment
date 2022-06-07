package A4.G2.model.sale;

import A4.G2.model.artwork.Art;

public abstract class Sale {
    private int saleID;
    private int timeRemaining;
    private double price;
    private double deliveryCost;
    private String deliveryTime;
    Art artPiece;

    public Sale(int saleID, double startingPrice, Art artPiece) {
        this.saleID = saleID;
        this.price = startingPrice;
        this.artPiece = artPiece;
    }

    public abstract String getSaleType();

    public int getSaleId() {
        return this.saleID;
    }

    public double getPrice() {
        return this.price;
    }

    public double getDeliveryCost() {
        return this.deliveryCost;
    }

    public String getDeliveryTime() {
        return this.deliveryTime;
    }

    public Art getArtPiece() {
        return this.artPiece;
    }

    public int getTimeRemaining() {
        return this.timeRemaining;
    }
}
