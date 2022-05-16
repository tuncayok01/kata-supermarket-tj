package kata.supermarket.domain;

import kata.supermarket.offer.SpecialOffer;
import kata.supermarket.service.SpecialOfferService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Basket {
    private final SpecialOfferService specialOfferService;
    private final List<Item> items;

    public Basket(SpecialOfferService specialOfferService) {
        this.specialOfferService = specialOfferService;
        this.items = new ArrayList<>();
    }

    public void add(final Item item) {
        this.items.add(item);
    }

    List<Item> items() {
        return Collections.unmodifiableList(items);
    }

    public BigDecimal total() {
        return new TotalCalculator().calculate();
    }

    private class TotalCalculator {
        private final List<Item> items;

        TotalCalculator() {
            this.items = items();
        }

        private BigDecimal subtotal() {
            return items.stream().map(Item::price)
                    .reduce(BigDecimal::add)
                    .orElse(BigDecimal.ZERO)
                    .setScale(2, RoundingMode.HALF_UP);
        }

        private BigDecimal discounts() {
            List<SpecialOffer> specialOffers = specialOfferService.getSpecialOffers(items);

            return specialOfferService.getTotalDiscount(specialOffers);
        }

        private BigDecimal calculate() {
            return subtotal().subtract(discounts());
        }
    }
}
