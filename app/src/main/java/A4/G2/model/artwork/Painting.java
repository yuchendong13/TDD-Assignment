package A4.G2.model.artwork;

import A4.G2.model.users.Artist;

import java.awt.*;

public class Painting extends Art {

    public Painting(Artist artist, String title, String description, Image photo, String dimensions) {
        super(artist, title, description, photo, dimensions);

    }

    @Override
    public String getArtType() {
        return "Painting";
    }

}
