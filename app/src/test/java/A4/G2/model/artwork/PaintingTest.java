package A4.G2.model.artwork;


import A4.G2.model.sale.BuyNow;
import A4.G2.model.users.Artist;
import A4.G2.service.LinkGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PaintingTest {

	Artist artist;
	Image image;
	Painting painting;

	@BeforeAll
	public void setUp() throws IOException {
		artist = Mockito.mock(Artist.class);
		image = ImageIO.read(new File("src/test/java/A4/G2/model/artwork/testImage.png"));
		painting = Mockito.spy(new Painting(artist, "Art Title", "Art Description", image, "2x1m"));
	}

	@Test
	public void testPaintingPageLinkGeneration() {
		LinkGenerator paintingLink = new LinkGenerator();
		String link = paintingLink.generateArtLink(painting.getId());
		assertEquals("https://www.g2gallery.com/art/"+painting.getId(), link);
	}

}
