package A4.G2.model.users;

import A4.G2.helpers.DateGenerator;
import A4.G2.service.account.IncorrectPasswordException;
import A4.G2.service.account.UsernameTakenException;
import A4.G2.service.account.WeakPasswordException;
import A4.G2.service.dao.UserDaoService;
import A4.G2.service.account.LoginDetailsManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

/**
 * USER STORY: Manage login details
 *
 * As a user, I would like to be able to change my login details such as my username and password, so that my account is
 * more secure and memorable.
 *
 * ACs
 * - A logged in user should be able to click on their profile to reach an ‘edit profile’ section
 * - In the ‘edit profile’ section the user should be able to directly edit their username
 * - In the ‘edit profile’ section the user should be able to also change their password by entering their new password
 *   and confirming it by entering it again (as is the convention)
 * - If the user’s new password entries don’t match then notify the user to try again
 * - If the user’s new password is not secure enough, then notify the user to try again
 * - If the user’s new password is sufficiently secure and the two entries match, change this particular account’s
 *   password
 */
public class ManageLoginsDetailsTest {

    LoginDetailsManager loginDetailsManager;
    User user;

    @BeforeEach
    public void setup() {
        UserDaoService userDaoService = Mockito.mock(UserDaoService.class);
        Mockito.when(userDaoService.checkIfUsernameTaken("Geoff")).thenReturn(false);
        Mockito.when(userDaoService.checkIfUsernameTaken("Steve")).thenReturn(true);

        loginDetailsManager = new LoginDetailsManager(userDaoService);

        user = Mockito.spy(new User("jeff", "Qwerty30", "jeff.com", "123456",
                "jeff house", DateGenerator.getSampleDateOver16()));
    }

    @Test
    public void testUserChangesUsernameToUniqueUsername() {
        try {
            assertTrue(loginDetailsManager.changeUsername(user, "Geoff"));
        } catch (Exception exception) {
            fail("This should have not thrown an exception");
        }
        Mockito.verify(user, times(1)).setUsername("Geoff");
    }

    @Test
    public void testUserChangesUsernameToTakenUsername() {
        try {
            loginDetailsManager.changeUsername(user, "Steve");
            fail("This should have thrown an exception");
        } catch (UsernameTakenException e) {
            Mockito.verify(user, times(0)).setUsername("Steve");
            assertEquals(e.getMessage(), "'Steve' already taken as a username");
        }
    }

    @Test
    public void testUserChangesPasswordToWeakPassword_noNumbers() {
        try {
            loginDetailsManager.changePassword(user, "Qwerty30", "Password", "Password");
            fail("This should have thrown an exception");
        } catch (WeakPasswordException | IncorrectPasswordException e) {
            Mockito.verify(user, times(0)).setPassword("Password");
            assertEquals(e.getMessage(), "Weak password: no digits in password");
        }
    }

    @Test
    public void testUserChangesPasswordToWeakPassword_noCapitalLetters() {
        try {
            loginDetailsManager.changePassword(user, "Qwerty30", "password9", "password9");
            fail("This should have thrown an exception");
        } catch (WeakPasswordException | IncorrectPasswordException e) {
            Mockito.verify(user, times(0)).setPassword("password9");
            assertEquals(e.getMessage(), "Weak password: no capital letters in password");
        }
    }

    @Test
    public void testUserChangesPasswordToWeakPassword_lessThan6Chars() {
        try {
            loginDetailsManager.changePassword(user, "Qwerty30", "Hi5", "Hi5");
            fail("This should have thrown an exception");
        } catch (WeakPasswordException | IncorrectPasswordException e) {
            Mockito.verify(user, times(0)).setPassword("Hi5");
            assertEquals(e.getMessage(), "Weak password: password less than 6 characters");
        }
    }

    @Test
    public void testUserChangesPassword_oldPasswordIncorrect() {
        try {
            loginDetailsManager.changePassword(user, "qwerty30", "Password9", "Password9");
            fail("This should have thrown an exception");
        } catch (IncorrectPasswordException | WeakPasswordException e) {
            Mockito.verify(user, times(1)).getPassword();
            Mockito.verify(user, times(0)).setPassword("Password9");
            assertEquals(e.getMessage(), "Incorrect password: provided old password was incorrect");
        }
    }

    @Test
    public void testUserIncorrectlyRetypesNewPassword() {
        try {
            loginDetailsManager.changePassword(user, "Qwerty30", "Password9", "Qassword9");
            fail("This should have thrown an exception");
        } catch (IncorrectPasswordException | WeakPasswordException e) {
            Mockito.verify(user, times(0)).setPassword("Password9");
            assertEquals(e.getMessage(), "Incorrect password: retyped new passwords don't match");
        }
    }

    @Test
    public void testUserChangesPasswordToStrongPasswordSuccessfully() {
        try {
            boolean result = loginDetailsManager.changePassword(user, "Qwerty30", "Password9", "Password9");

            assertTrue(result);
            Mockito.verify(user, times(1)).getPassword();
            Mockito.verify(user, times(1)).setPassword("Password9");

        } catch (IncorrectPasswordException | WeakPasswordException e) {
            fail("This should not have thrown an exception");
        }
    }
}
