package kata.supermarket.offer;

import kata.supermarket.domain.WeighedProduct;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PercentOffForACategoryOfWeighedProduct implements SpecialOfferForCategory {

    private final WeighedProduct product;
    private final BigDecimal offerWeightInKilos;
    private final BigDecimal discountMultiplier;
    private final String category;

    /**
     * A Special offer that allows you to represent offers such as
     * - Buy one kilo of vegetables for half price
     *
     * @param product            product that this special offer applies to
     * @param offerWeightInKilos how much the item must weigh in order for this offer to apply
     * @param discountMultiplier how much discount should be applied to the total, e.g 30% off would be 0.3
     * @param category           special offer applies to this category of products, e.g. vegetables, cheese, fruit, slicedBread
     */
    public PercentOffForACategoryOfWeighedProduct(WeighedProduct product, BigDecimal offerWeightInKilos, BigDecimal discountMultiplier, String category) {

        if (!product.categories().contains(category))
            throw new IllegalArgumentException("category of item does not match category of special offer");

        this.product = product;
        this.offerWeightInKilos = offerWeightInKilos;
        this.discountMultiplier = discountMultiplier;
        this.category = category;
    }

    @Override
    public BigDecimal getDiscount() {

        return product
                .pricePerKilo()
                .multiply(offerWeightInKilos)
                .multiply(discountMultiplier)
                .setScale(2, RoundingMode.HALF_UP);

    }

    @Override
    public String category() {
        return category;
    }
}
