package org.lucidant.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import static java.util.stream.Collectors.groupingBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * To capture stream handling examples as I create them.
 */
class StreamTest
{

    @DisplayName("Groups into a map. Shows a grouping by.")
    @Test
    void testGroupToMap()
    {
        final List<Transaction> txns = new ArrayList<>();
        txns.add(new Transaction(200, "Dave"));
        txns.add(new Transaction(499, "Dave"));
        txns.add(new Transaction(210, "Chris"));
        txns.add(new Transaction(199, "Chris"));

        final Map<String, List<Transaction>> bigTxnbySeller = txns.stream()
                                                             .filter(t -> t.getAmount() > 200)
                                                             .collect(groupingBy(Transaction::getPerson));

        assertEquals(2, bigTxnbySeller.size());
    }

    @Getter
    @RequiredArgsConstructor
    class Transaction
    {
        private final int amount;

        private final String person;
    }
}
