package A4.G2.model;

import A4.G2.model.users.Artist;
import A4.G2.service.LinkGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(Lifecycle.PER_CLASS)
public class ArtistTest {
	Artist artist;

	@BeforeAll
	public void setUp() {
		 artist = new Artist();
	}

	@Test
	public void testArtistPageLinkGeneration() {
		LinkGenerator artistLink = new LinkGenerator();
		String link = artistLink.generateArtistLink(artist.getArtistId());
		assertEquals("https://www.g2gallery.com/"+artist.getArtistId(), link);
	}

	@Test
	public void testCheckArtistIdNotEmpty(){
		assertTrue(artist.getArtistId().matches("^[\\da-f]{8}-[\\da-f]{4}-[\\da-f]{4}-[\\da-f]{4}-[\\da-f]{12}$"));

	}

}