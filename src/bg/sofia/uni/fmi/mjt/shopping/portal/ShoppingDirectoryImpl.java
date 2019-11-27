package bg.sofia.uni.fmi.mjt.shopping.portal;

import bg.sofia.uni.fmi.mjt.shopping.portal.exceptions.NoOfferFoundException;
import bg.sofia.uni.fmi.mjt.shopping.portal.exceptions.OfferAlreadySubmittedException;
import bg.sofia.uni.fmi.mjt.shopping.portal.exceptions.ProductNotFoundException;
import bg.sofia.uni.fmi.mjt.shopping.portal.offer.Offer;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.TreeSet;

public class ShoppingDirectoryImpl implements ShoppingDirectory {
    private HashMap<String, TreeSet<Offer>> offersCollection;
    private HashMap<String, TreeMap<LocalDate, PriceStatistic>> priceStatisticCollection;

    public ShoppingDirectoryImpl() {
        this.offersCollection = new HashMap<>();
        this.priceStatisticCollection = new HashMap<>();
    }

    /**
     * Returns a collection of offers submitted in the last 30 days
     * for the product with name @productName sorted by total price
     * in ascending order.
     *
     * @throws ProductNotFoundException if there is no product with name @productName
     * @throws IllegalArgumentException if @productName is null
     */
    @Override
    public Collection<Offer> findAllOffers(String productName) throws ProductNotFoundException {
        if (productName == null) {
            throw new IllegalArgumentException();
        }
        if (offersCollection.containsKey(productName)) {
            TreeSet<Offer> result = offersCollection.get(productName);
            return result;
        } else {
            throw new ProductNotFoundException();
        }
    }

    /**
     * Returns the most favorable offer for the product with name @productName
     * submitted in the last 30 days - the one with lowest total price.
     *
     * @throws ProductNotFoundException if there is no product with name @productName
     * @throws NoOfferFoundException    if there is no offer submitted in the last 30
     *                                  days for the product with name @productName
     * @throws IllegalArgumentException if @productName is null
     */
    @Override
    public Offer findBestOffer(String productName) throws ProductNotFoundException,
            NoOfferFoundException, IllegalArgumentException {
        if (productName == null) {
            throw new IllegalArgumentException();
        }
        if (offersCollection.containsKey(productName)) {
            TreeSet<Offer> allOffersForProduct = offersCollection.get(productName);
            Offer result = allOffersForProduct.iterator().next();
            return result;
        } else {
            throw new ProductNotFoundException();
        }
    }

    /**
     * Returns a collection of price statistics for the product with name @productName
     * sorted by date in descending order.
     *
     * @throws ProductNotFoundException if there is no product with name @productName
     * @throws IllegalArgumentException if @productName is null
     */
    @Override
    public Collection<PriceStatistic> collectProductStatistics(String productName) throws ProductNotFoundException {
        if (productName == null) {
            throw new IllegalArgumentException();
        }
        if (priceStatisticCollection.containsKey(productName)) {
            Collection<PriceStatistic> result = priceStatisticCollection.get(productName).values();
            return result;
        } else {
            throw new ProductNotFoundException();
        }
    }

    /**
     * Submits a new offer.
     *
     * @throws OfferAlreadySubmittedException if an identical @offer has already been submitted
     * @throws IllegalArgumentException       if @offer is null
     */
    @Override
    public void submitOffer(Offer offer) throws OfferAlreadySubmittedException, IllegalArgumentException {
        if (offer == null) {
            throw new IllegalArgumentException();
        }
        if (offersCollection.get(offer.getProductName()) != null) {
            if (offersCollection.get(offer.getProductName()).contains(offer)) {
                throw new OfferAlreadySubmittedException();
            } else {
                offersCollection.get(offer.getProductName()).add(offer);
            }
        } else {
            TreeSet<Offer> offers = new TreeSet<>();
            offers.add(offer);
            offersCollection.put(offer.getProductName(), offers);
        }
    }
}
