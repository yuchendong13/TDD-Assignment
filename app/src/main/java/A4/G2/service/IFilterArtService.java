package A4.G2.service;

import A4.G2.model.artwork.Art;
import A4.G2.model.artwork.Sculpture;
import A4.G2.model.sale.Sale;
import A4.G2.model.users.Artist;

import java.util.List;

public interface IFilterArtService {
    List<Sale> getAuctionItems(List<Sale> saleList);

    List<Sale> getBuyNowItems(List<Sale> saleList);

    List<Sale> getPriceLowerThan(List<Sale> saleList, double v);

    List<Sale> getPriceHigherThan(List<Sale> saleList, double v);

    List<Sale> getPriceBetween(List<Sale> saleList, double v, double v1);

    List<Sale> filterAuctionItemsUnderTime(List<Sale> saleList, int i);

    List<Sale> filterAuctionItemsOverTime(List<Sale> saleList, int i);

    List<Art> filterArtFromArtist(List<Art> artList, Artist artist1);

    List<Art> getArtPiecesByArtType(List<Art> artList, String artType);
}
