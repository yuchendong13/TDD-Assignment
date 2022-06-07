package A4.G2.service;

import A4.G2.model.artwork.Art;
import A4.G2.model.artwork.Painting;
import A4.G2.model.artwork.Print;
import A4.G2.model.artwork.Sculpture;
import A4.G2.model.sale.Auction;
import A4.G2.model.sale.BuyNow;
import A4.G2.model.sale.Sale;
import A4.G2.model.users.Artist;

import java.util.ArrayList;
import java.util.List;

public class FilterArtService implements IFilterArtService{
    @Override
    public List<Sale> getAuctionItems(List<Sale> saleList) {
        List<Sale> auctionItemsList = new ArrayList<>();

        for (Sale sale : saleList) {
            if (sale instanceof Auction) {
                auctionItemsList.add(sale);
            }
        }
        return auctionItemsList;
    }

    @Override
    public List<Sale> getBuyNowItems(List<Sale> saleList) {
        List<Sale> buyNowItemsList = new ArrayList<>();

        for (Sale sale : saleList) {
            if (sale instanceof BuyNow) {
                buyNowItemsList.add(sale);
            }
        }
        return buyNowItemsList;
    }

    @Override
    public List<Sale> getPriceLowerThan(List<Sale> saleList, double max) {
        List<Sale> lowerPriceList = new ArrayList<>();

        for (Sale sale : saleList) {
            if ((sale instanceof BuyNow) && (Double.compare(sale.getPrice(), max) <= 0)) {
                lowerPriceList.add(sale);
            }
        }
        return lowerPriceList;
    }

    @Override
    public List<Sale> getPriceHigherThan(List<Sale> saleList, double min) {
        List<Sale> higherPriceList = new ArrayList<>();

        for (Sale sale : saleList) {
            if ((sale instanceof BuyNow) && (Double.compare(sale.getPrice(), min) >= 0)) {
                higherPriceList.add(sale);
            }
        }
        return higherPriceList;
    }

    @Override
    public List<Sale> getPriceBetween(List<Sale> saleList, double max, double min) {
        List<Sale> inRangeList = new ArrayList<>();

        for (Sale sale : saleList) {
            if ((sale instanceof BuyNow) && (Double.compare(sale.getPrice(), max) >= 0) &&
                    (Double.compare(sale.getPrice(), min) <= 0)) {
                inRangeList.add(sale);
            }
        }
        return inRangeList;
    }

    @Override
    public List<Sale> filterAuctionItemsUnderTime(List<Sale> saleList, int max) {
        List<Sale> lowerTimeRemainingList = new ArrayList<>();

        for (Sale sale : saleList) {
            if ((sale instanceof Auction) && (sale.getTimeRemaining() < max)) {
                lowerTimeRemainingList.add(sale);
            }
        }
        return lowerTimeRemainingList;
    }

    @Override
    public List<Sale> filterAuctionItemsOverTime(List<Sale> saleList, int min) {
        List<Sale> overTimeRemainingList = new ArrayList<>();

        for (Sale sale : saleList) {
            if ((sale instanceof Auction) && (sale.getTimeRemaining() > min)) {
                overTimeRemainingList.add(sale);
            }
        }
        return overTimeRemainingList;
    }

    @Override
    public List<Art> filterArtFromArtist(List<Art> artList, Artist artist1) {
        List<Art> artByArtistList = new ArrayList<>();

        for (Art art : artList) {
            if (art.getArtist().getArtistId().equals(artist1.getArtistId())) {
                artByArtistList.add(art);
            }
        }
        return artByArtistList;
    }

    @Override
    public List<Art> getArtPiecesByArtType(List<Art> artList, String artType) {
        List<Art> paintingTypeList = new ArrayList<>();
        List<Art> printTypeList = new ArrayList<>();
        List<Art> sculptureTypeList = new ArrayList<>();

        for (Art art : artList) {
            if (art instanceof Painting) {
                paintingTypeList.add(art);
            } else if (art instanceof Print) {
                printTypeList.add(art);
            } else if (art instanceof Sculpture) {
                sculptureTypeList.add(art);
            }
        }

        if (artType.equalsIgnoreCase("painting")) {
            return paintingTypeList;
        } else if (artType.equalsIgnoreCase("print")) {
            return printTypeList;
        } else if (artType.equalsIgnoreCase("sculpture")) {
            return sculptureTypeList;
        }
        return new ArrayList<>();
    }
}
