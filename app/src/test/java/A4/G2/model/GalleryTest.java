package A4.G2.model;

import A4.G2.model.artwork.Art;
import A4.G2.model.sale.BuyNow;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GalleryTest {

    Gallery gallery;
    Art art;
    BuyNow buyNow;

    @BeforeEach
    public void setUp() {
        gallery = new Gallery();
        art = Mockito.mock(Art.class);
        buyNow = Mockito.mock(BuyNow.class);
    }

    @Test
    public void testAddingArtToGalleryArtList() {
        gallery.addArt(art);
        assertEquals(gallery.getArtList().size(), 1);
    }

    @Test
    public void testAddingArtToGallerySalesList() {
        gallery.addArtForSale(buyNow);
        assertEquals(gallery.getArtSalesList().size(), 1);
    }

    @Test
    public void testRemoveArtFromGalleryArtList() {
        gallery.addArt(art);
        gallery.removeArt(art);
        assertEquals(gallery.getArtList().size(), 0);
    }

    @Test
    public void testRemoveArtFromGallerySalesList() {
        gallery.addArtForSale(buyNow);
        gallery.removeArtForSale(buyNow);
        assertEquals(gallery.getArtSalesList().size(), 0);
    }
}
