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
public class PrintTest {

	Artist artist;
	Image image;
	Print print;

	@BeforeAll
	public void setUp() throws IOException {
		artist = Mockito.mock(Artist.class);
		image = ImageIO.read(new File("src/test/java/A4/G2/model/artwork/testImage.png"));
		print = Mockito.spy(new Print(artist, "Art Title", "Art Description", image, "1920x1080px"));
	}

	@Test
	public void testPrintPageLinkGeneration() {
		LinkGenerator printLink = new LinkGenerator();
		String link = printLink.generateArtLink(print.getId());
		assertEquals("https://www.g2gallery.com/art/"+print.getId(), link);
	}

}