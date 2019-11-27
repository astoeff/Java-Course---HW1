package bg.sofia.uni.fmi.mjt.shopping.portal;

import bg.sofia.uni.fmi.mjt.shopping.portal.offer.Offer;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class PriceStatistic {
    private String productName;
    private LocalDate statisticDate;
    List<Offer> allOffers;

    public PriceStatistic(String productName, List<Offer> offers) {
        this.productName = productName;
        this.statisticDate = LocalDate.now();
        this.allOffers = offers;
    }

    /**
     * Returns the date for which the statistic is
     * collected.
     */
    public LocalDate getDate() throws UnsupportedOperationException {
        if (statisticDate == null) {
            throw new UnsupportedOperationException();
        }
        return statisticDate;
    }

    /**
     * Returns the lowest total price from the offers
     * for this product for the specific date.
     */
    public double getLowestPrice() throws UnsupportedOperationException {
        if (allOffers == null) {
            throw new UnsupportedOperationException();
        }
        double lowestPrice = -1;
        for (int i = 0; i < allOffers.size(); i++) {
            if (allOffers.get(i).getTotalPrice() < lowestPrice || lowestPrice == -1) {
                lowestPrice = allOffers.get(i).getTotalPrice();
            }
        }
        return lowestPrice;
    }

    /**
     * Return the average total price from the offers
     * for this product for the specific date.
     */
    public double getAveragePrice() throws UnsupportedOperationException {
        if (allOffers == null) {
            throw new UnsupportedOperationException();
        }
        double totalPrice = 0;
        for (int i = 0; i < allOffers.size(); i++) {
            totalPrice += allOffers.get(i).getTotalPrice();
        }
        return totalPrice / allOffers.size();
    }

}
