package A4.G2.service;

import A4.G2.model.artwork.Painting;
import A4.G2.model.users.Artist;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LinkGeneratorTest {

	@Test
	public void testArtistPageLinkGenerator() {
		String uuid = UUID.randomUUID().toString();
		LinkGenerator artistLink = new LinkGenerator();
		Artist artist = Mockito.mock(Artist.class);
		Mockito.doReturn(uuid).when(artist).getArtistId();
		String link = artistLink.generateArtistLink(artist.getArtistId());
		assertEquals("https://www.g2gallery.com/"+uuid, link);
	}

	@Test
	public void testArtPageLinkGenerator() {
		String uuid = UUID.randomUUID().toString();
		LinkGenerator artLinkGenerator = new LinkGenerator();
		String link = artLinkGenerator.generateArtLink(uuid);
		assertEquals("https://www.g2gallery.com/art/"+uuid, link);
	}

}