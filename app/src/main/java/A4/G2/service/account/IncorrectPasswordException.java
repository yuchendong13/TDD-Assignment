package A4.G2.service.account;

/**
 * An exception that is thrown when the password a user enters is incorrect.
 */
public class IncorrectPasswordException extends Exception{
    public IncorrectPasswordException(String errorMessage) {
        super(errorMessage);
    }
}