package A4.G2.model.users;

import A4.G2.helpers.DateGenerator;
import A4.G2.model.Payment;
import A4.G2.model.artwork.Painting;
import A4.G2.model.sale.Auction;
import A4.G2.model.sale.BuyNow;
import A4.G2.model.users.Artist;
import A4.G2.model.users.User;
import A4.G2.service.payment.InvalidPaymentException;
import A4.G2.service.payment.NoPaymentDetailsException;
import A4.G2.service.payment.PaymentVerifier;
import A4.G2.service.payment.UnderAgePurchaseException;
import A4.G2.service.payment.UnregisteredUserPurchaseException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PaymentTest {
	Artist artist;
	User user;
	Image image;
	Painting painting;
	Auction auction;
	int saleId;
	double price;
	double reservePrice;
	int timeRemaining;
	BuyNow buyNow;

	@BeforeAll
	public void setUp() throws IOException {
		artist = Mockito.mock(Artist.class);
		image = ImageIO.read(new File("src/test/java/A4/G2/model/artwork/testImage.png"));
		painting = Mockito.spy(new Painting(artist, "Art Title", "Art Description", image, "2x1m"));

		saleId = 2;
		price = 50.00; // starting price
		reservePrice = 200.00;
		timeRemaining = 7; // days
		auction = Mockito.spy(new Auction(saleId, price, painting, reservePrice, timeRemaining));

		user = Mockito.spy(new User("Luxman", "Luxman", "luxman.gmail.com", "0222222222", "9 Narnia Land", DateGenerator.getSampleDateOver16()));
		buyNow = Mockito.spy(new BuyNow(saleId, 100, painting));
	}

	@Test
	public void testValidCreatePayment() {
		Payment payment = getValidPayment();
		assertEquals(payment.getCardNumber(),"5555555555554444");
		assertEquals(payment.getCardHolder(),"Luxman");
		assertEquals(payment.getExpiryDate(),"02/24");
		assertEquals(payment.getCVV(),"333");
	}

	@Test
	public void testInvalidCreatePayment() {
		try {
			String invalidCardNumber = "5";
			String cardHolder = "Luxman";
			String expiryDate = "02/21";
			String invalidCVV = "3";
			PaymentVerifier.verifyPayment(invalidCardNumber,cardHolder,expiryDate,invalidCVV);
			fail("This should have thrown an exception");

		} catch (InvalidPaymentException e) {
			assertEquals(e.getMessage(),"Invalid Payment Details");
		}
	}

	@Test
	public void testFailAuctionNoPayment() {
		user.deletePaymentDetails();
		try {
			auction.placeBid(user,60);
			fail("This should have thrown an exception");
		}
		catch (NoPaymentDetailsException e) {
			assertEquals(e.getMessage(),"User has no payment details.");
		}
		catch(UnregisteredUserPurchaseException | UnderAgePurchaseException ex) {
			fail("This should have thrown a NoPaymentDetailsException.");
		}

	}

	@Test
	public void testFailBuyNoPayment() {
		user.deletePaymentDetails();
		try {
			buyNow.buyArtPiece(user);
			fail("This should have thrown an exception");
		}
		catch(UnregisteredUserPurchaseException | UnderAgePurchaseException ex) {
			fail("This should have thrown a NoPaymentDetailsException.");
		}
		catch (NoPaymentDetailsException e) {
			assertEquals(e.getMessage(),"User has no payment details.");
		}
	}

	@Test
	public void testAddPayment() {
		Payment payment = getValidPayment();
		user.modifyPayment(payment);
		assertEquals(payment, user.getPaymentDetails());
	}

	@Test
	public void modifyPayment() {
		User user = getUserWithPayment();
		Payment payment = new Payment("5557553552554446",
				"Harry","02/21","743");
		user.modifyPayment(payment);
		assertEquals(payment, user.getPaymentDetails());
	}

	@Test
	public void deletePayment() {
		User user = getUserWithPayment();
		user.deletePaymentDetails();
		assertEquals(null,user.getPaymentDetails());
	}

	private Payment getValidPayment() {
		String cardNumber = "5555555555554444";
		String cardHolder = "Luxman";
		String expiryDate = "02/24";
		String CVV = "333";
		return new Payment(cardNumber,cardHolder,expiryDate,CVV);
	}

	private User getUserWithPayment() {
		Payment payment = getValidPayment();
		user.modifyPayment(payment);
		return user;
	}
}

