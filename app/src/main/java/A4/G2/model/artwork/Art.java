package A4.G2.model.artwork;

import A4.G2.model.users.Artist;
import A4.G2.model.sale.Sale;

import java.awt.*;
import java.util.UUID;

public abstract class Art {
    private String id;
    private Artist artist;
    private String title;
    private String description;
    private Image image;
    private String dimensions;
    private Sale sale;

    public Art(Artist artist, String title, String description, Image image, String dimensions) {
        this.id = UUID.randomUUID().toString();
        this.artist = artist;
        this.title = title;
        this.description = description;
        this.image = image;
        this.dimensions = dimensions;
        boolean onSale = false;
    }

    public abstract String getArtType();
    public String getId() {
        return this.id;
    }

    public Artist getArtist() {
        return this.artist;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public Image getImage() {
        return this.image;
    }

    public String getDimensions() {
        return this.dimensions;
    }
}
