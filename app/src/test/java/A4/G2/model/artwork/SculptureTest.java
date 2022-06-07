package A4.G2.model.artwork;

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
public class SculptureTest {

	Artist artist;
	Image image;
	Sculpture sculpture;

	@BeforeAll
	public void setUp() throws IOException {
		artist = Mockito.mock(Artist.class);
		image = ImageIO.read(new File("src/test/java/A4/G2/model/artwork/testImage.png"));
		sculpture = Mockito.spy(new Sculpture(artist, "Art Title", "Art Description", image, "0.5x0.5x0.5m"));
	}

	@Test
	public void testSculpturePageLinkGeneration() {
		LinkGenerator sculptureLink = new LinkGenerator();
		String link = sculptureLink.generateArtLink(sculpture.getId());
		assertEquals("https://www.g2gallery.com/art/"+sculpture.getId(), link);
	}

}
