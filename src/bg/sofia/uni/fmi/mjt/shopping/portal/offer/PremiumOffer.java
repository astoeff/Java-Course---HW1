package bg.sofia.uni.fmi.mjt.shopping.portal.offer;

import java.time.LocalDate;


public class PremiumOffer extends RegularOffer {

    private double offerDiscount;
    private static final int ROUND_PARAMETER = 100;

    public PremiumOffer(String productName, LocalDate date, String description,
                        double price, double shippingPrice, double discount) {
        super(productName, date, description, price, shippingPrice);
        this.offerDiscount = Math.round(discount * ROUND_PARAMETER) / ROUND_PARAMETER;
        //double totalPrice=getTotalPrice();
        //setTotalPrice(totalPrice);
    }

    public double getOfferDiscount() {
        return this.offerDiscount;
    }

    @Override
    public double getTotalPrice() {
        double totalPrice = super.getTotalPrice();
        totalPrice -= totalPrice * (offerDiscount / ROUND_PARAMETER);
        return totalPrice;
    }
}
