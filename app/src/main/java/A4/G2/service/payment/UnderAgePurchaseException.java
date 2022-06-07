package A4.G2.service.payment;

/**
 * An exception is thrown when a user under the age of 16 tries to buy/bid
 * on artwork.
 */
public class UnderAgePurchaseException extends Exception {
    public UnderAgePurchaseException(String errorMessage) {
        super(errorMessage);
    }
}
