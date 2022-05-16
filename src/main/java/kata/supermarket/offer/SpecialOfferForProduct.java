package kata.supermarket.offer;

import kata.supermarket.domain.Product;

public interface SpecialOfferForProduct extends SpecialOffer {
    /**
     * this special offer applies to this product only
     *
     * @return product
     */
    Product product();
}
