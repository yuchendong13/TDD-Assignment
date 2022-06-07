package A4.G2.service.account;

/**
 * An exception that is thrown when a user tries to choose a username that is already taken.
 */
public class UsernameTakenException extends Exception{
    public UsernameTakenException(String errorMessage) {
        super(errorMessage);
    }
}
