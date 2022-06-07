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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AuctionTest {
	Artist artist;
	User user;
	Image image;
	Painting painting;
	Auction auction;
	int saleId;
	double price;
	double reservePrice;
	int timeRemaining;

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

		user = Mockito.mock(User.class);
	}

	@Test
	public void testGetAuctionSaleId() {
		Mockito.doReturn(saleId).when(auction).getSaleId();
		assertEquals(saleId, auction.getSaleId());
	}

	@Test
	public void testGetAuctionStartingPrice() {
		Mockito.doReturn(price).when(auction).getPrice();
		assertEquals(price, auction.getPrice());
	}

	@Test
	public void testGetAuctionReservePrice() {
		Mockito.doReturn(reservePrice).when(auction).getReservePrice();
		assertEquals(reservePrice, auction.getReservePrice());
	}

	@Test
	public void testGetAuctionTimeRemaining() {
		Mockito.doReturn(timeRemaining).when(auction).getTimeRemaining();
		assertEquals(timeRemaining, auction.getTimeRemaining());
	}

	@Test
	public void testGetAuctionCurrentBid() {
		Mockito.doReturn(75.00).when(auction).getCurrentBid();
		assertEquals(75.00, auction.getCurrentBid());
	}

	@Test
	public void testGetAuctionTotalBids() {
		Mockito.doReturn(3).when(auction).getTotalBids();
		assertEquals(3, auction.getTotalBids());
	}

	@Test
	public void testGetAuctionCurrentBidder() {
		Mockito.doReturn(user).when(auction).getCurrentBidder();
		assertEquals(user, auction.getCurrentBidder());
	}

	@Test
	public void testGetAuctionDeliveryCost() {
		Mockito.doReturn(15.00).when(auction).getDeliveryCost();
		assertEquals(15.00, auction.getDeliveryCost());
	}

	@Test
	public void testGetAuctionDeliveryTime() {
		Mockito.doReturn("5 working days").when(auction).getDeliveryTime();
		assertEquals("5 working days", auction.getDeliveryTime());
	}

	@Test
	public void testGetArtPiece() {
		Mockito.doReturn(painting).when(auction).getArtPiece();
		assertEquals(painting, auction.getArtPiece());
	}

	@Test
	public void testGetSaleTypeAuction() {
		assertEquals("Auction", auction.getSaleType());
	}

	@Test
	public void testBidUnder16Fails() {
		User underAgeUser = Mockito.spy(new User("Luxman", "Luxman", "luxman.gmail.com",
				"0222222222", "9 Narnia Land", DateGenerator.getSampleDateUnder16()));

		Payment payment = new Payment("5555555555554444","Luxman","02/23","333");
		underAgeUser.modifyPayment(payment);

		try {
			auction.placeBid(underAgeUser,80);
			fail("User is underage and shouldn't be able to bid.");
		}
		 catch (NoPaymentDetailsException | UnregisteredUserPurchaseException e) {
			fail("User has payment details and is regsitered.");
		}
		catch(UnderAgePurchaseException ex) {
			assertEquals(ex.getMessage(),"User is not old enough to buy artwork.");
		}
	}
	@Test
	public void testUnregisteredUserAuction() {
		User unregisteredUser = null;
		try {
			auction.placeBid(unregisteredUser, 80);
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
}
