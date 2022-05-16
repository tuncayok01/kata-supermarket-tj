package kata.supermarket.service;

import kata.supermarket.domain.Item;
import kata.supermarket.offer.SpecialOffer;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class SpecialOfferServiceImpl implements SpecialOfferService {

    @Override
    public List<SpecialOffer> getSpecialOffers(List<Item> items) {
        //  TODO implement this method. Too complex and out of scope for this exercise.

        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    public BigDecimal getTotalDiscount(List<SpecialOffer> specialOffers) {
        return specialOffers.stream()
                .map(SpecialOffer::getDiscount)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO)
                .setScale(2, RoundingMode.HALF_UP);
    }
}
