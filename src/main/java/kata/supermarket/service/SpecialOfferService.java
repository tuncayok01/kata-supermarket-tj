package kata.supermarket.service;

import kata.supermarket.domain.Item;
import kata.supermarket.offer.SpecialOffer;

import java.math.BigDecimal;
import java.util.List;

public interface SpecialOfferService {

    /**
     * Service will fetch all special offers from the DB for the given items and return those offers that are applicable
     * to the given items.
     * <p>
     * If the items have multiple special offer types, it will filter those that give the best value for the customer.
     *
     * @param items basket items that special offers will be applied for
     * @return list of special offers applicable to the basket items. List will contain duplicate special offers if an offer
     * can be applied multiple times for the basket items.
     */
    List<SpecialOffer> getSpecialOffers(List<Item> items);

    /**
     * calculate the total discount for the given special offers
     *
     * @param specialOffers to calculate discounts on
     * @return total monetary value of discount
     */
    BigDecimal getTotalDiscount(List<SpecialOffer> specialOffers);
}
