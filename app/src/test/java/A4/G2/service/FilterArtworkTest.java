package A4.G2.service;

import A4.G2.model.artwork.Art;
import A4.G2.model.artwork.Painting;
import A4.G2.model.artwork.Print;
import A4.G2.model.artwork.Sculpture;
import A4.G2.model.sale.Auction;
import A4.G2.model.sale.BuyNow;
import A4.G2.model.sale.Sale;
import A4.G2.model.users.Artist;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * USER STORY: Filter artwork
 *
 * As a user, I would like a way to filter both artwork for display and for sale by their type, price(auction and
 * buy now if applicable), for auction or buy now, auction time left(if applicable) and artist so that I can view
 * the pieces of artwork that are of interest to me.
 *
 * ACs
 * - A user should be able to filter art so that they can see all pieces on auction
 * - A user should be able to filter art so that they can see all pieces that can be directly bought (have a
 *   ‘buy now’)
 * - A user should be able to filter art based on a price range (for items with a ‘buy now’)
 * - A user should be able to filter art being sold on auction based on the time remaining on the auction
 * - A user should be able to filter art so that they can see all the art from a specific artist
 * - A user should be able to filter art to view a specific type of art, such as sculptures, paintings, prints etc.
 */

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FilterArtworkTest {

    IFilterArtService filterService;

    Artist artist1;
    List<Sale> saleList = new ArrayList<>();
    List<Art> artList = new ArrayList<>();

    @BeforeAll
    public void setup() {
        artist1 = Mockito.mock(Artist.class);
        Mockito.when(artist1.getArtistId()).thenReturn("a1");

        Artist artist2 = Mockito.mock(Artist.class);
        Mockito.when(artist2.getArtistId()).thenReturn("a2");

        Sale buyNow1 = Mockito.mock(BuyNow.class);
        Sale buyNow2 = Mockito.mock(BuyNow.class);
        Sale buyNow3 = Mockito.mock(BuyNow.class);

        Sale auction1 = Mockito.mock(Auction.class);
        Sale auction2 = Mockito.mock(Auction.class);

        Mockito.when(buyNow1.getPrice()).thenReturn(120.00);
        Mockito.when(buyNow2.getPrice()).thenReturn(101.00);
        Mockito.when(buyNow3.getPrice()).thenReturn(99.0);

        Mockito.when(auction1.getTimeRemaining()).thenReturn(90000);
        Mockito.when(auction2.getTimeRemaining()).thenReturn(7200);

        saleList.add(buyNow1);
        saleList.add(buyNow2);
        saleList.add(buyNow3);

        saleList.add(auction1);
        saleList.add(auction2);

        Art painting1 = Mockito.mock(Painting.class);
        Art painting2 = Mockito.mock(Painting.class);
        Art print1 = Mockito.mock(Print.class);

        Mockito.when(painting1.getArtist()).thenReturn(artist1);
        Mockito.when(print1.getArtist()).thenReturn(artist1);
        Mockito.when(painting2.getArtist()).thenReturn(artist2);

        artList.add(painting1);
        artList.add(print1);
        artList.add(painting2);

        filterService = new FilterArtService();
    }

    @Test
    public void testFilterOnlyAuctionPieces() {
        List<Sale> actual = filterService.getAuctionItems(saleList);
        assertEquals(2, actual.size());

        for (Sale sale : actual) {
            assertTrue(sale instanceof Auction);
        }
    }

    @Test
    public void testFilterOnlyBuyNowPieces() {
        List<Sale> actual = filterService.getBuyNowItems(saleList);
        assertEquals(3, actual.size());

        for (Sale sale : actual) {
            assertTrue(sale instanceof BuyNow);
        }
    }

    @Test
    public void testFilterOnlyBuyNowPiecesLowerThan$100() {
        List<Sale> actual = filterService.getPriceLowerThan(saleList, 100.0);

        assertEquals(1, actual.size());

        for (Sale sale : actual) {
            assertTrue(Double.compare(sale.getPrice(), 100.0) <= 0);
        }
    }

    @Test
    public void testFilterOnlyBuyNowPiecesHigherThan$100() {
        List<Sale> actual = filterService.getPriceHigherThan(saleList, 100.0);

        assertEquals(2, actual.size());
        for (Sale sale : actual) {
            assertTrue(sale.getPrice() >= 100);
        }
    }

    @Test
    public void testFilterOnlyBuyNowPiecesBetween$100and$110() {
        List<Sale> actual = filterService.getPriceBetween(saleList, 100.0, 110.0);

        assertEquals(1, actual.size());
        for (Sale sale : actual) {
            assertTrue((Double.compare(sale.getPrice(), 100.0) >= 0) &&
                    (Double.compare(sale.getPrice(), 110.0) <= 0));
        }
    }

    @Test
    public void testOnlyRetrievingBuyNowPiecesWhenFilteringByPrice() {
        List<Sale> actual = filterService.getPriceHigherThan(saleList, 0.0);

        for (Sale sale : actual) {
            assertTrue(sale instanceof BuyNow);
        }
    }

    @Test
    public void testFilterOnlyAuctionPiecesUnder1DayLeft() {
        List<Sale> actual = filterService.filterAuctionItemsUnderTime(saleList, 86400);

        assertEquals(1, actual.size());
        for (Sale sale : actual) {
            assertTrue(sale.getTimeRemaining() < 86400);
        }
    }

    @Test
    public void testFilterOnlyAuctionOver2DaysLeft() {
        List<Sale> actual = filterService.filterAuctionItemsOverTime(saleList, 86400);

        assertEquals(1, actual.size());
        for (Sale sale : actual) {
            assertTrue(sale.getTimeRemaining() > 86400);
        }
    }

    @Test
    public void testOnlyRetrievingAuctionItemsWhenFilteringByPrice() {
        List<Sale> actual = filterService.filterAuctionItemsOverTime(saleList, 86400);

        for (Sale sale : actual) {
            assertTrue(sale instanceof Auction);
        }
    }

    @Test
    public void testArtistArtworksRetrievalWhenListNonEmpty() {
        List<Art> actual = filterService.filterArtFromArtist(artList, artist1);

        assertEquals(2, actual.size());
        for (Art art : actual) {
            assertTrue(art.getArtist().getArtistId().equalsIgnoreCase(artist1.getArtistId()));
        }
    }

    @Test
    public void testArtistArtworksRetrievalWhenListEmpty() {
        Artist artist3 = Mockito.mock(Artist.class);
        Mockito.when(artist3.getArtistId()).thenReturn("jeff");

        List<Art> actual = filterService.filterArtFromArtist(artList, artist3);

        assertEquals(0, actual.size());
    }

    @Test
    public void testFilterArtByTypeSculptureWithNoResults() {
        List<Art> actual = filterService.getArtPiecesByArtType(artList, "sculpture");

        assertEquals(0, actual.size());
    }

    @Test
    public void testFilterArtByTypePrintOnlyWithResults() {
        List<Art> actual = filterService.getArtPiecesByArtType(artList, "print");

        assertEquals(1, actual.size());

        for (Art art : actual) {
            assertTrue(art instanceof Print);
        }
    }
}
