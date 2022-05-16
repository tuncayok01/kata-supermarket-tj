package kata.supermarket.domain;

import kata.supermarket.offer.SpecialOffer;
import kata.supermarket.service.SpecialOfferService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BasketTest {

    @Mock
    private SpecialOfferService mockSpecialOfferService;

    @Mock
    private SpecialOffer mockSpecialOffer;

    private Basket basket;

    @BeforeEach
    void setUp() {
        basket = new Basket(mockSpecialOfferService);
    }


    @DisplayName("basket provides its total value when containing...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void basketProvidesTotalValue(String description, String expectedDiscount, String expectedTotal, Iterable<Item> items) {

        List<Item> itemsList = new ArrayList<>();
        items.forEach(itemsList::add);

        List<SpecialOffer> specialOffers = List.of(mockSpecialOffer, mockSpecialOffer);
        when(mockSpecialOfferService.getSpecialOffers(itemsList)).thenReturn(specialOffers);

        when(mockSpecialOfferService.getTotalDiscount(specialOffers)).thenReturn(new BigDecimal(expectedDiscount));

        items.forEach(basket::add);
        assertEquals(new BigDecimal(expectedTotal), basket.total());
    }

    static Stream<Arguments> basketProvidesTotalValue() {
        return Stream.of(
                noItems(),
                aSingleItemPricedPerUnit(),
                multipleItemsPricedPerUnit(),
                aSingleItemPricedByWeight(),
                multipleItemsPricedByWeight()
        );
    }

    private static Arguments aSingleItemPricedByWeight() {
        return Arguments.of("a single weighed item", "0.00", "1.25", Collections.singleton(twoFiftyGramsOfAmericanSweets()));
    }

    private static Arguments multipleItemsPricedByWeight() {
        return Arguments.of("multiple weighed items", "0.10", "1.75",
                asList(twoFiftyGramsOfAmericanSweets(), twoHundredGramsOfPickAndMix())
        );
    }

    private static Arguments multipleItemsPricedPerUnit() {
        return Arguments.of("multiple items priced per unit", "1.00", "1.04",
                asList(aPackOfDigestives(), aPintOfMilk()));
    }

    private static Arguments aSingleItemPricedPerUnit() {
        return Arguments.of("a single item priced per unit", "0.00", "0.49", Collections.singleton(aPintOfMilk()));
    }

    private static Arguments noItems() {
        return Arguments.of("no items", "0.00", "0.00", Collections.emptyList());
    }

    private static Item aPintOfMilk() {
        return new UnitProduct(1, Set.of("dairy"), new BigDecimal("0.49")).oneOf();
    }

    private static Item aPackOfDigestives() {
        return new UnitProduct(4, Set.of("biscuit"), new BigDecimal("1.55")).oneOf();
    }

    private static WeighedProduct aKiloOfAmericanSweets() {
        return new WeighedProduct(2, Set.of("sweet"), new BigDecimal("4.99"));
    }

    private static Item twoFiftyGramsOfAmericanSweets() {
        return aKiloOfAmericanSweets().weighing(new BigDecimal(".25"));
    }

    private static WeighedProduct aKiloOfPickAndMix() {
        return new WeighedProduct(3, Set.of("sweet"), new BigDecimal("2.99"));
    }

    private static Item twoHundredGramsOfPickAndMix() {
        return aKiloOfPickAndMix().weighing(new BigDecimal(".2"));
    }
}