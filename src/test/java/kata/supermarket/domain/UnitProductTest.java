package kata.supermarket.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UnitProductTest {

    @Test
    void unitProduct_returnsExpectedProductAttributes_whenGivenCorrectValuesOnInstantiation() {
        Set<String> categories = Set.of("dairy", "cheese");
        BigDecimal pricePerUnit = new BigDecimal("2.49");
        UnitProduct dairyProduct = new UnitProduct(1, categories, pricePerUnit);

        assertEquals(1, dairyProduct.id());
        assertEquals(categories, dairyProduct.categories());
        assertEquals(pricePerUnit, dairyProduct.oneOf().price());
    }
}