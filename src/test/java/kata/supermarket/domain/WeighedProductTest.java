package kata.supermarket.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WeighedProductTest {

    @ParameterizedTest
    @MethodSource
    void itemFromWeighedProductHasExpectedUnitPrice(String pricePerKilo, String weightInKilos, String expectedPrice) {
        final WeighedProduct weighedProduct = new WeighedProduct(1, Set.of("dairy"), new BigDecimal(pricePerKilo));
        final Item weighedItem = weighedProduct.weighing(new BigDecimal(weightInKilos));
        assertEquals(new BigDecimal(expectedPrice), weighedItem.price());
    }

    static Stream<Arguments> itemFromWeighedProductHasExpectedUnitPrice() {
        return Stream.of(
                Arguments.of("100.00", "1.00", "100.00"),
                Arguments.of("100.00", "0.33333", "33.33"),
                Arguments.of("100.00", "0.33335", "33.34"),
                Arguments.of("100.00", "0", "0.00")
        );
    }

    @Test
    void weighedProduct_returnsExpectedProductAttributes_whenGivenCorrectValuesOnInstantiation() {
        Set<String> categories = Set.of("dairy", "cheese");
        BigDecimal pricePerKilo = new BigDecimal("2.49");
        WeighedProduct dairyProduct = new WeighedProduct(1, categories, pricePerKilo);

        assertEquals(1, dairyProduct.id());
        assertEquals(categories, dairyProduct.categories());
        assertEquals(pricePerKilo, dairyProduct.pricePerKilo());
    }

}