package A4.G2.model.artwork;

import A4.G2.model.Gallery;
import A4.G2.model.sale.Auction;
import A4.G2.model.sale.BuyNow;
import A4.G2.model.users.Artist;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mockito;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(Lifecycle.PER_CLASS)
public class testGetOnSaleArtsDetails {
    Gallery gallery;
    Image image;
    Artist artist;

    @BeforeAll
    public void setUp() throws IOException {
        gallery = Mockito.spy(new Gallery());
        image = ImageIO.read(new File("src/test/java/A4/G2/model/artwork/testImage.png"));
        artist = Mockito.mock(Artist.class);
        gallery.addArtForSale(Mockito.spy(new Auction(1,1.0,
                Mockito.spy(new Print(artist,"Art 1","",image,"")),
                1.0,1)));
        gallery.addArtForSale(Mockito.spy(new BuyNow(2,1.0,
                Mockito.spy(new Print(artist,"Art 2","",image,"")))));
    }

    @Test
    public void testGetSalesTitle(){
        for(int i=1;i<2;i++){
            assertEquals("Art "+Integer.toString(i+1),
                    gallery.getArtSalesList().get(i).getArtPiece().getTitle());
        }
    }

    @Test
    public void testGetSalesImage(){
        for(int i=1;i<2;i++){
            assertEquals(image, gallery.getArtSalesList().get(i).getArtPiece().getImage());
        }
    }

    @Test
    public void testGetSaleType(){
        assertEquals("Auction", gallery.getArtSalesList().get(0).getSaleType());
        assertEquals("Buy Now", gallery.getArtSalesList().get(1).getSaleType());
    }

    @Test
    public void testGetSaleID(){
        for(int i=1;i<2;i++){
            assertEquals(i+1, gallery.getArtSalesList().get(i).getSaleId());
        }
    }

    @Test
    public void testGetSalePrice(){
        for(int i=1;i<2;i++){
            assertEquals(1.0, gallery.getArtSalesList().get(i).getPrice());
        }
    }
}
