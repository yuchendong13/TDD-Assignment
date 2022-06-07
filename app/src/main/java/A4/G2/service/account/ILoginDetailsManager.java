package A4.G2.service.account;

import A4.G2.model.users.User;

public interface ILoginDetailsManager {

    boolean changeUsername(User user, String newUsername) throws UsernameTakenException;

    boolean changePassword(User user, String oldPassword, String newPassword, String newPasswordRetyped) throws WeakPasswordException, IncorrectPasswordException;
}
