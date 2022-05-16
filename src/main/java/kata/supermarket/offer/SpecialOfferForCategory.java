package kata.supermarket.offer;

public interface SpecialOfferForCategory extends SpecialOffer {
    /**
     * special offer applies to this category of products only (e.g. cheese, veg, fruit, etc)
     *
     * @return category of the product the special offer applies to
     */
    String category();
}
