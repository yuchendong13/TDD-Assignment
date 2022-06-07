package A4.G2.service.payment;

/**
 * An exception is thrown when the card number is invalid.
 * The exception is used to notify the user their payment details is incorrect.
 */
public class InvalidPaymentException extends Exception {
    public InvalidPaymentException(String errorMessage) {
        super(errorMessage);
    }
}
