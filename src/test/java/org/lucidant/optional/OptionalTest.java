package org.lucidant.optional;

import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.lucidant.stream.Sale;
import org.lucidant.stream.random.RandomSale;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OptionalTest
{

    static Stream<Sale> saleStream() {
        return RandomSale.streamOf(3);
    }

    static Optional<Sale> findSaleOf(final String itemName) {
        return saleStream().filter(sale -> sale.items.stream()
                                                     .anyMatch(item -> item.identity.equals(itemName)))
                           .findFirst();
    }

    @DisplayName("Shows Optional with flatMap - in a stream, don't have to worry about null if keep passing Optional")
    @Test
    void testFlatMapWithOptional() {

        final Optional<String> customer = findSaleOf("carrot").flatMap(sale -> sale.customer);

        assertTrue(customer.orElse("HELLO").length() > 0);
    }
}
