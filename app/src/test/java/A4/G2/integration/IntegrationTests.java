package A4.G2.integration;

import A4.G2.helpers.DateGenerator;
import A4.G2.model.Gallery;
import A4.G2.model.Payment;
import A4.G2.model.artwork.Painting;
import A4.G2.model.sale.BuyNow;
import A4.G2.model.users.Artist;
import A4.G2.model.users.User;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.fail;

public class IntegrationTests {

    @Test
    public void testBuyArtInGalleryWithNoPaymentDetails() throws IOException {
        User user = new User("username", "password", "email.gmail", "0248195812", "123 address", DateGenerator.getSampleDateOver16());

        Artist artist = new Artist();
        Image image = ImageIO.read(new File("src/test/java/A4/G2/model/artwork/testImage.png"));
        Painting painting = new Painting(artist, "title", "description", image, "2x1m");
        BuyNow buyNow = new BuyNow(1, 15.99, painting);

        Gallery gallery = new Gallery();
        gallery.addArtForSale(buyNow);

        if (gallery.getArtSalesList().get(0) instanceof BuyNow) {
            try{
                ((BuyNow) gallery.getArtSalesList().get(0)).buyArtPiece(user);
                fail("Expected exception not thrown");
            } catch (Exception e) {
                assert(e.getMessage().equals("User has no payment details."));
            }

        } else {
            fail("Art not added correctly to gallery");
        }
    }

    @Test
    public void testBuyArtInGalleryWithPaymentDetails() throws IOException {
        User user = new User("username", "password", "email.gmail", "0248195812", "123 address",DateGenerator.getSampleDateOver16() );
        Payment payment = new Payment("1234567890123456", "User User", "01/20", "123");
        user.modifyPayment(payment);

        Artist artist = new Artist();
        Image image = ImageIO.read(new File("src/test/java/A4/G2/model/artwork/testImage.png"));
        Painting painting = new Painting(artist, "title", "description", image, "2x1m");
        BuyNow buyNow = new BuyNow(1, 15.99, painting);

        Gallery gallery = new Gallery();
        gallery.addArtForSale(buyNow);

        if (gallery.getArtSalesList().get(0) instanceof BuyNow) {
            try{
                ((BuyNow) gallery.getArtSalesList().get(0)).buyArtPiece(user);
            } catch (Exception e) {
                fail("Expected exception not thrown");
            }

        } else {
            fail("Art not added correctly to gallery");
        }
    }

    @Test
    public void testBuyArtInGalleryWithNonRegisteredUser() throws IOException {
        Artist artist = new Artist();
        Image image = ImageIO.read(new File("src/test/java/A4/G2/model/artwork/testImage.png"));
        Painting painting = new Painting(artist, "title", "description", image, "2x1m");
        BuyNow buyNow = new BuyNow(1, 15.99, painting);

        Gallery gallery = new Gallery();
        gallery.addArtForSale(buyNow);

        if (gallery.getArtSalesList().get(0) instanceof BuyNow) {
            try{
                ((BuyNow) gallery.getArtSalesList().get(0)).buyArtPiece(null);
                fail("Expected exception not thrown");
            } catch (Exception e) {
                assert(e.getMessage().equals("User is not registered, please sign in to buy artwork."));
            }

        } else {
            fail("Art not added correctly to gallery");
        }
    }


}
