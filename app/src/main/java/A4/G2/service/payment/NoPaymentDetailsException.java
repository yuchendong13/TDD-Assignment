package A4.G2.service.payment;

/**
 * An exception is thrown when the user tries to place a bid or buys an artpiece
 * with no payment details. The exception is used to notify the user.
 */
public class NoPaymentDetailsException extends Exception {
    public NoPaymentDetailsException(String errorMessage) {
        super(errorMessage);
    }
}
