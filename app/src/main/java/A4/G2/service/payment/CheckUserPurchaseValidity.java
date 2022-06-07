package A4.G2.service.payment;

import A4.G2.model.users.User;

import java.util.Date;

public class CheckUserPurchaseValidity {

    public static void checkIfUserCanPurchaseArt(User user) throws NoPaymentDetailsException, UnregisteredUserPurchaseException,
            UnderAgePurchaseException {
        if (user == null) {
            throw new UnregisteredUserPurchaseException("User is not registered, please sign in to buy artwork.");
        }
        if (user.getPaymentDetails() == null) {
            throw new NoPaymentDetailsException("User has no payment details.");
        }

        //Check if user is over 16
        Date now = new Date();
        long difference_in_time = now.getTime() - user.getDateOfBirth().getTime();
        long years = (difference_in_time
                / (1000l * 60 * 60 * 24 * 365));


        if (years < 16) {
            throw new UnderAgePurchaseException("User is not old enough to buy artwork.");
        }
    }
}
