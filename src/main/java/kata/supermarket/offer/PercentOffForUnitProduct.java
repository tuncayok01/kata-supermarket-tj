package kata.supermarket.offer;

import kata.supermarket.domain.UnitProduct;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PercentOffForUnitProduct implements SpecialOffer {

    private final UnitProduct product;
    private final int quantity;
    private final BigDecimal discountMultiplier;

    /**
     * A Special offer that allows you to represent offers such as
     * - Buy one, get one free
     * - Buy three items for the price of two
     *
     * @param product            product that this special offer applies to
     * @param quantity           number of items that must be purchased in order for this special offer to apply
     * @param discountMultiplier how much discount should be applied to the total, e.g 30% off would be 0.3
     */
    public PercentOffForUnitProduct(UnitProduct product, int quantity, BigDecimal discountMultiplier) {
        this.product = product;
        this.quantity = quantity;
        this.discountMultiplier = discountMultiplier;
    }


    @Override
    public BigDecimal getDiscount() {
        return BigDecimal.valueOf(product.pricePerUnit().floatValue() * quantity * discountMultiplier.floatValue())
                .setScale(2, RoundingMode.HALF_UP);
    }

}
