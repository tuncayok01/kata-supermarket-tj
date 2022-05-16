package kata.supermarket.offer;

import kata.supermarket.domain.WeighedProduct;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PercentOffForACategoryOfWeighedProductTest {

    @ParameterizedTest
    @MethodSource
    void getDiscount_shouldReturnExpectedValues(String pricePerKilo, String offerWeightInKilos, String discountMultiplier, String expectedDiscount) {

        WeighedProduct potatoes = potatoes(pricePerKilo);

        PercentOffForACategoryOfWeighedProduct specialOffer = new PercentOffForACategoryOfWeighedProduct(potatoes, new BigDecimal(offerWeightInKilos), new BigDecimal(discountMultiplier), "veg");
        BigDecimal discount = specialOffer.getDiscount();

        assertEquals(new BigDecimal(expectedDiscount), discount);
    }

    static Stream<Arguments> getDiscount_shouldReturnExpectedValues() {
        return Stream.of(
                //  pricePerKilo, offerWeightInKilos, discountMultiplier, expectedDiscount
                Arguments.of("100.00", "1.0", "0.5", "50.00"),   //  Buy one kilo of vegetables for half price (100.00 * 1.0 * 0.5)
                Arguments.of("1.97", "4.0", "0.25", "1.97"),     //  Buy 4 kilos of potatoes and get 25% off (1.97 * 4.0 * 0.25)
                Arguments.of("1.99", "1.0", "0.5", "1.00")       //  Buy 1 kilo of potatoes and get 50% off  (1.99 * 1.0 * 0.5)

        );
    }

    @Test
    void categoryForSpecialOffer_shouldReturnExpectedValue() {
        WeighedProduct potatoes = potatoes("1.2");
        PercentOffForACategoryOfWeighedProduct aKiloOfVegForHalfPrice = new PercentOffForACategoryOfWeighedProduct(potatoes, new BigDecimal("1.0"), new BigDecimal("0.5"), "veg");

        assertEquals("veg", aKiloOfVegForHalfPrice.category());
    }

    public static WeighedProduct potatoes(String pricePerKilo) {
        return new WeighedProduct(1, Set.of("veg"), new BigDecimal(pricePerKilo));
    }
}