package A4.G2.model.users;

import java.util.Date;
import java.util.UUID;

import A4.G2.model.Payment;

public class User {
    private String id;
    private String userName;
    private String password;
    private String email;
    private String phone;
    private String address;
    private Payment payment;
    private ShippingDetails shippingDetails;

    private Date dateOfBirth;


public User(String userName, String password, String email, String phone, String address,Date dateOfBirth) {
    this.userName = userName;
    this.password = password;
    this.email = email;
    this.phone = phone;
    this.address = address;
    this.id = this.id = UUID.randomUUID().toString();
    this.dateOfBirth = dateOfBirth;
}

	public void modifyPayment(Payment payment) {
	    this.payment = payment;
	}

    public Payment getPaymentDetails() {
        return this.payment;
    }

    public void deletePaymentDetails() {
        this.payment = null;
    }

    public Date getDateOfBirth() {
        return this.dateOfBirth;
    }

    public ShippingDetails getShippingDetails() { return this.shippingDetails; }

    public void setShippingDetails(ShippingDetails newShippingDetails) { this.shippingDetails = newShippingDetails; }

    public void setUsername(String newUsername) {
        userName = newUsername;
    }

    public void setPassword(String newPassword) {
        password = newPassword;
    }

    public String getPassword() {
        return password;
    }
}
