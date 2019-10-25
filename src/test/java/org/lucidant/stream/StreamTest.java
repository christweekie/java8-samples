package org.lucidant.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.maxBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * To capture stream handling examples as I create them.
 */
class StreamTest
{
    final List<Transaction> transactions = new ArrayList<>();

    @BeforeEach
    void beforeEach()
    {
        transactions.add(new Transaction(200, "Dave"));
        transactions.add(new Transaction(499, "Dave"));
        transactions.add(new Transaction(210, "Chris"));
        transactions.add(new Transaction(199, "Chris"));
    }

    @DisplayName("Groups to Map. Shows a grouping by from Collectors.")
    @Test
    void testGroupToMap()
    {
        final Map<String, List<Transaction>> largeBySeller = transactions.stream()
                                                             .filter(t -> t.getAmount() > 200)
                                                             .collect(groupingBy(Transaction::getPerson));

        assertEquals(2, largeBySeller.size());
    }

    @DisplayName("Groups to Map. Shows a grouping by from Collectors and a reduction to max.")
    @Test
    void testGroupToMapReducingToMax()
    {
        final Map<String, Optional<Transaction>> largestBySeller = transactions.stream()
                                                                 .filter(t -> t.getAmount() > 200)
                                                                 .collect(groupingBy(Transaction::getPerson,
                                                                                     maxBy(comparing(Transaction::getAmount))));

        assertEquals(2, largestBySeller.size());
    }

    @Getter
    @RequiredArgsConstructor
    class Transaction
    {
        private final int amount;

        private final String person;
    }
}
