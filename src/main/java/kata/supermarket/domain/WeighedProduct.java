package kata.supermarket.domain;

import java.math.BigDecimal;
import java.util.Set;

public class WeighedProduct implements Product {

    private final Integer id;
    private final Set<String> categories;
    private final BigDecimal pricePerKilo;

    public WeighedProduct(Integer id, Set<String> categories, final BigDecimal pricePerKilo) {
        this.id = id;
        this.categories = categories;
        this.pricePerKilo = pricePerKilo;
    }

    public BigDecimal pricePerKilo() {
        return pricePerKilo;
    }

    public Item weighing(final BigDecimal kilos) {
        return new ItemByWeight(this, kilos);
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
