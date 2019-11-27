package bg.sofia.uni.fmi.mjt.shopping.portal;

import bg.sofia.uni.fmi.mjt.shopping.portal.offer.Offer;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class PriceStatistic {

//    private int  numberOfAllOffersInStatistic;

    private String productName;
    private LocalDate statisticDate;
    List<Offer> offers;

    public PriceStatistic(String productName, List<Offer> offers) {
        this.productName = productName;
        this.statisticDate = LocalDate.now();
        this.offers = offers;
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
        if (offers == null) {
            throw new UnsupportedOperationException();
        }
        double lowestPrice = -1;
        for (int i = 0; i < offers.size(); i++) {
            if (offers.get(i).getTotalPrice() < lowestPrice || lowestPrice == -1) {
                lowestPrice = offers.get(i).getTotalPrice();
            }
        }
        return lowestPrice;
    }

    /**
     * Return the average total price from the offers
     * for this product for the specific date.
     */
    public double getAveragePrice() throws UnsupportedOperationException {
        if (offers == null) {
            throw new UnsupportedOperationException();
        }
        double totalPrice = 0;
        for (int i = 0; i < offers.size(); i++) {
            totalPrice += offers.get(i).getTotalPrice();
        }
        return totalPrice / offers.size();
    }

}
