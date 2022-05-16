package kata.supermarket.offer;

import java.math.BigDecimal;

public interface SpecialOffer {
    /**
     * get discount gained from this offer
     *
     * @return amount of discount
     */
    BigDecimal getDiscount();
}
