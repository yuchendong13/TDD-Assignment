package A4.G2.service;

public class LinkGenerator implements ILinkGenerator {
	private final String site = "https://www.g2gallery.com/";

	public String generateArtistLink(String artistId) {
		String link;
		link = site + artistId;
		return link;
	}

	public String generateArtLink(String artId) {
		String link;
		link = site + "art/" + artId;
		return link;
	}
}
