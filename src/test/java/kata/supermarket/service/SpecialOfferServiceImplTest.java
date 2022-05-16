package kata.supermarket.service;

import kata.supermarket.offer.SpecialOffer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SpecialOfferServiceImplTest {

    private SpecialOfferServiceImpl specialOfferService;

    @BeforeEach
    void setUp() {
        specialOfferService = new SpecialOfferServiceImpl();
    }

    @DisplayName("service returns expected total discount when...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void getTotalDiscount_shouldReturnExpectedResults(String description, String expectedTotal, List<SpecialOffer> specialOffers) {
        BigDecimal totalDiscount = specialOfferService.getTotalDiscount(specialOffers);
        assertEquals(new BigDecimal(expectedTotal), totalDiscount);

    }

    static Stream<Arguments> getTotalDiscount_shouldReturnExpectedResults() {
        return Stream.of(
                noSpecialOfferes(),
                singleSpecialOffer(),
                multipleSpecialOffers()
        );
    }

    private static Arguments noSpecialOfferes() {
        return Arguments.of("no special offers", "0.00", new ArrayList<SpecialOffer>());
    }

    private static Arguments singleSpecialOffer() {

        SpecialOffer bogofOffer = bogofOffer();

        return Arguments.of("single fixed price special offer for a product", "1.00", singletonList(bogofOffer));
    }

    private static Arguments multipleSpecialOffers() {

        return Arguments.of("multiple special offers", "3.00", asList(bogofOffer(), bogofOffer(), bogofOffer()));
    }

    private static SpecialOffer bogofOffer() {
        SpecialOffer mockBogofOffer = mock(SpecialOffer.class);

        when(mockBogofOffer.getDiscount()).thenReturn(new BigDecimal("1.00"));

        return mockBogofOffer;
    }
}