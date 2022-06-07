package A4.G2.model.artwork;

import A4.G2.model.Gallery;
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
public class testGetArtsDisplayOnly {
    Gallery gallery;
    Image[] images;
    Artist artist;

    @BeforeAll
    public void setUp() throws IOException {
        gallery = Mockito.spy(new Gallery());
        artist = Mockito.mock(Artist.class);
        images=new Image[3];
        for(int i=0;i<3;i++){
            images[i]=ImageIO.read(new File("src/test/java/A4/G2/model/artwork/testImage"+Integer.toString(i+1)+".png"));
        }
        gallery.addArt(Mockito.spy(new Painting(artist,"Art 1","",images[0],"")));
        gallery.addArt(Mockito.spy(new Print(artist,"Art 2","",images[1],"")));
        gallery.addArt(Mockito.spy(new Sculpture(artist,"Art 3","",images[2],"")));
    }

    @Test
    public void testGetImageFromGallery(){
        for(int i=1;i<3;i++){
            assertEquals(images[i], gallery.getArtList().get(i).getImage());
        }
    }
}
