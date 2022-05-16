package kata.supermarket.domain;

import java.math.BigDecimal;
import java.util.Set;

public class UnitProduct implements Product {

    private final Integer id;
    private final Set<String> categories;
    private final BigDecimal pricePerUnit;

    public UnitProduct(Integer id, Set<String> categories, final BigDecimal pricePerUnit) {
        this.id = id;
        this.categories = categories;
        this.pricePerUnit = pricePerUnit;
    }

    public BigDecimal pricePerUnit() {
        return pricePerUnit;
    }

    public Item oneOf() {
        return new ItemByUnit(this);
    }

    @Override
    public Integer id() {
        return id;
    }

    @Override
    public Set<String> categories() {
        return categories;
    }
}
