package A4.G2.model;

import A4.G2.service.payment.InvalidPaymentException;
import A4.G2.service.payment.PaymentVerifier;

public class Payment {
    private String cardNumber;
    private String cardHolder;
    private String expiryDate;
    private String CVV;

    public Payment(String cardNumber,String cardHolder,String expiryDate,String CVV){
        try {
            PaymentVerifier.verifyPayment(cardNumber,cardHolder,expiryDate,CVV);
        }
        catch (InvalidPaymentException ex) {
        }

        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.expiryDate = expiryDate;
        this.CVV = CVV;
    }

    public String getCardNumber() {
        return this.cardNumber;
    }

    public String getCardHolder() {
        return this.cardHolder;
    }

    public String getExpiryDate() {
        return this.expiryDate;
    }

    public String getCVV() {
        return this.CVV;
    }
}
