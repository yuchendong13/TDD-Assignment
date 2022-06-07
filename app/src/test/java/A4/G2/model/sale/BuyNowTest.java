package A4.G2.model.sale;


import A4.G2.helpers.DateGenerator;
import A4.G2.model.Payment;
import A4.G2.model.artwork.Painting;
import A4.G2.model.users.Artist;
import A4.G2.model.users.User;
import A4.G2.service.payment.NoPaymentDetailsException;
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
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BuyNowTest {
	Artist artist;
	Image image;
	Painting painting;
	BuyNow buyNow;
	int saleId;
	double price;

	@BeforeAll
	public void setUp() throws IOException {
		artist = Mockito.mock(Artist.class);
		image = ImageIO.read(new File("src/test/java/A4/G2/model/artwork/testImage.png"));
		painting = Mockito.spy(new Painting(artist, "Art Title", "Art Description", image, "2x1m"));

		saleId = 1;
		price = 111.11;
		buyNow = Mockito.spy(new BuyNow(saleId, price, painting));
	}

	@Test
	public void testGetBuyNowSaleId() {
		Mockito.doReturn(saleId).when(buyNow).getSaleId();
		assertEquals(saleId, buyNow.getSaleId());
	}

	@Test
	public void testGetBuyNowPrice() {
		Mockito.doReturn(price).when(buyNow).getPrice();
		assertEquals(price, buyNow.getPrice());
	}

	@Test
	public void testGetBuyNowDeliveryCost() {
		Mockito.doReturn(15.00).when(buyNow).getDeliveryCost();
		assertEquals(15.00, buyNow.getDeliveryCost());
	}

	@Test
	public void testGetBuyNowDeliveryTime() {
		Mockito.doReturn("5 working days").when(buyNow).getDeliveryTime();
		assertEquals("5 working days", buyNow.getDeliveryTime());
	}

	@Test
	public void testGetBuyNowGstCalculation() {
		double gst = price * 0.15;
		Mockito.doReturn(gst).when(buyNow).getGst();
		assertEquals(gst, buyNow.getGst());
	}

	@Test
	public void testGetArtwork() {
		Mockito.doReturn(painting).when(buyNow).getArtPiece();
		assertEquals(painting, buyNow.getArtPiece());
	}

	@Test
	public void testGetSaleTypeBuyNow() {
		assertEquals("Buy Now", buyNow.getSaleType());
	}

	@Test
	public void testUnregisteredUserBuyNow() {
		User unregisteredUser = null;
		try {
			buyNow.buyArtPiece(unregisteredUser);
			fail("This should have thrown an exception");
		}
		catch(UnregisteredUserPurchaseException ex) {
			assertEquals(ex.getMessage(),"User is not registered, please sign in to buy artwork.");
		}
		catch(NoPaymentDetailsException | UnderAgePurchaseException ex) {
			//User should be first checked if registered before checking payments.
			fail("This should have thrown an UnregisteredUserPurchaseException.");
		}
	}

	@Test
	public void testBidUnder16Fails() {
		User underAgeUser = Mockito.spy(new User("Luxman", "Luxman", "luxman.gmail.com",
				"0222222222", "9 Narnia Land", DateGenerator.getSampleDateUnder16()));

		Payment payment = new Payment("5555555555554444","Luxman","02/23","333");
		underAgeUser.modifyPayment(payment);

		try {
			buyNow.buyArtPiece(underAgeUser);
			fail("User is underage and shouldn't be able to bid.");
		}
		catch (NoPaymentDetailsException | UnregisteredUserPurchaseException e) {
			fail("User has payment details and is regsitered.");
		}
		catch(UnderAgePurchaseException ex) {
			assertEquals(ex.getMessage(),"User is not old enough to buy artwork.");
		}
	}

}
