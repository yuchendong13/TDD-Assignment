package A4.G2.service.payment;

/**
 * An exception is thrown when a user tries to purcahse or bid on an item when they
 * are not registered. The exception is used to notify to the users(can be caught and run client
 * side code for notification.)
 */
public class UnregisteredUserPurchaseException extends Exception {
    public UnregisteredUserPurchaseException(String errorMessage) {
        super(errorMessage);
    }
}
