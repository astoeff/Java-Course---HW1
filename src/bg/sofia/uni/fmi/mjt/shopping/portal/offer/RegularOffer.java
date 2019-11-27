package bg.sofia.uni.fmi.mjt.shopping.portal.offer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class RegularOffer implements Offer {
    private LocalDate offerDate;
    private String productName;
    private String offerDescription;
    private double productPrice;
    private double productShippingPrice;

    private static final int HASHCODE_BASE = 17;
    private static final int MULTIPLIER = 31;
    private static final int SHIFT_NUMBER = 32;
    //private double totalPrice;

    public RegularOffer(String productName, LocalDate date, String description, double price, double shippingPrice) {
        this.offerDate = date;
        this.productName = productName;
        this.offerDescription = description;
        this.productPrice = price;
        this.productShippingPrice = shippingPrice;
    }


    /*public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
*/
    @Override
    public String getProductName() {
        return this.productName;
    }

    @Override
    public LocalDate getDate() {
        return this.offerDate;
    }

    @Override
    public String getDescription() {
        return this.offerDescription;
    }

    @Override
    public double getPrice() {
        return this.productPrice;
    }

    @Override
    public double getShippingPrice() {
        return this.productShippingPrice;
    }

    @Override
    public double getTotalPrice() {
        return productPrice + productShippingPrice;
    }

    @Override
    public boolean equals(Object obj) {
        if (getClass() != obj.getClass()) {
            return false;
        }

        Offer objectCastToOffer = (RegularOffer) obj;
        String objectCastToOfferName = objectCastToOffer.getProductName();
        boolean areProductNamesEqual = (productName.toLowerCase() == objectCastToOfferName.toLowerCase());
        boolean areDatesEqual = (this.offerDate.equals((objectCastToOffer.getDate())));
        boolean isTotalPriceEqual = (this.getTotalPrice() == objectCastToOffer.getTotalPrice());

        return areProductNamesEqual && areDatesEqual && isTotalPriceEqual;
    }

    // @Override
    public int hashcode() {
        int result = HASHCODE_BASE;

        result = MULTIPLIER * result + this.offerDate.hashCode();
        result = MULTIPLIER * result + this.productName.hashCode();
        result = MULTIPLIER * result + this.offerDescription.hashCode();
        long priceLong = Double.doubleToLongBits(this.productPrice);
        result = MULTIPLIER * result + (int) (priceLong ^ (priceLong >>> SHIFT_NUMBER));
        long shippingPriceLong = Double.doubleToLongBits(this.productShippingPrice);
        result = MULTIPLIER * result + (int) (shippingPriceLong ^ (shippingPriceLong >>> SHIFT_NUMBER));
        return result;
    }


    @Override
    public int compareTo(Object object) {
        RegularOffer objectToOffer = (RegularOffer) object;
        return Double.compare(getTotalPrice(), objectToOffer.getTotalPrice());
    }
}
