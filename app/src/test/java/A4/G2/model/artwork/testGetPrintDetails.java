package A4.G2.model.artwork;

import A4.G2.model.users.Artist;
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
public class testGetPrintDetails {
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
	public void testGetPrintArtist() {
		assertEquals(artist, print.getArtist());
	}

	@Test
	public void testGetPrintTitle() {
		assertEquals("Art Title", print.getTitle());
	}

	@Test
	public void testGetPrintDescription() {
		assertEquals("Art Description", print.getDescription());
	}

	@Test
	public void testGetPrintImage() {
		assertEquals(image, print.getImage());
	}

	@Test
	public void testGetPrintDimensions() {
		assertEquals("1920x1080px", print.getDimensions());
	}

	@Test
	public void testGetArtTypePrint() {
		assertEquals("Print", print.getArtType());
	}
}
