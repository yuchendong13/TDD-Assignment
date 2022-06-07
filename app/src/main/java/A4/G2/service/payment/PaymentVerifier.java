package A4.G2.service.payment;



public class PaymentVerifier {

    public static void  verifyPayment(String cardNumber,String cardHolder,String expiryDate,String CVV) throws InvalidPaymentException {
        //Throw an error if the card number length,CVV and expiry date isn't 16,3 and 5 respectively.
        if (cardNumber.length() != 16 || CVV.length() != 3 || expiryDate.length() !=5) {
            throw new InvalidPaymentException("Invalid Payment Details");
        }

        //Throw an error if the card number contains a letter.
        for (int i =0;i < cardNumber.length();i++) {
            if (!Character.isDigit(cardNumber.charAt(i))) {
                throw new InvalidPaymentException("Invalid Payment Details");
            }
        }

        //Throw an error if the CVV contains a letter.
        for (int i=0; i < CVV.length();i++) {
            if (!Character.isDigit(CVV.charAt(i))) {
                throw new InvalidPaymentException("Invalid Payment Details");
            }
        }

        if (!isExpiryDateValid(expiryDate)) {
            throw new InvalidPaymentException("Invalid Payment Details");
        }


    }

    private static boolean isExpiryDateValid(String expiryDate) {
        //Throw an error if the expiry date isn't in correct format
        if (expiryDate.charAt(2) != '/') {
            return false;
        }
        try {
            int month = Integer.parseInt(expiryDate.substring(0,2));
            int year = Integer.parseInt(expiryDate.substring(3,5));

            if (month < 0 || month > 12 || year < 0) {
                return false;
            }
        }
        catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        return true;
    }
}
