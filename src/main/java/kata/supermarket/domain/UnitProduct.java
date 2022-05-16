package kata.supermarket.domain;

import java.math.BigDecimal;

public class UnitProduct {

    private final BigDecimal pricePerUnit;

    public UnitProduct(final BigDecimal pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    BigDecimal pricePerUnit() {
        return pricePerUnit;
    }

    public Item oneOf() {
        return new ItemByUnit(this);
    }
}
