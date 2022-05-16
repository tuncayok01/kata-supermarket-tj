package kata.supermarket.offer;

import kata.supermarket.domain.UnitProduct;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PercentOffForUnitProductTest {

    @ParameterizedTest
    @MethodSource
    void getDiscount_shouldReturnExpectedValues(String pricePerUnit, int offerQuantity, String discountMultiplier, String expectedDiscount) {

        UnitProduct product = packOfChewingGum(pricePerUnit);
        PercentOffForUnitProduct specialOffer = new PercentOffForUnitProduct(product, offerQuantity, new BigDecimal(discountMultiplier));

        BigDecimal discount = specialOffer.getDiscount();

        assertEquals(new BigDecimal(expectedDiscount), discount);
    }

    static Stream<Arguments> getDiscount_shouldReturnExpectedValues() {
        return Stream.of(
                //  pricePerUnit, offerQuantity, discountMultiplier, expectedDiscount
                Arguments.of("100.00", 2, "0.5", "100.00"),     //  Buy one, get one free
                Arguments.of("0.99", 3, "0.333", "0.99"),       //  Buy three items for the price of two
                Arguments.of("100.00", 5, "0.2", "100.00"),     //  Buy 4 items and get one free
                Arguments.of("100.00", 5, "0.4", "200.00")      //  Buy 5 items, get 2 free
        );
    }

    private static UnitProduct packOfChewingGum(String price) {
        return new UnitProduct(1, Set.of("sweet"), new BigDecimal(price));
    }
}