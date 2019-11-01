package org.lucidant.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.lucidant.stream.random.RandomSale;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.maxBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * To capture stream handling examples as I create them.
 */
class StreamTest
{
    private final List<Transaction> transactions = new ArrayList<>();

    /**
     * Can be used as an accumulator in a "reduce" operation where we
     * can keep everything iteratively collected in "other" and just return last
     * each time from the operator.
     */
    private static final BinaryOperator<String> LAST = (other, last) -> last;

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

    @DisplayName("Groups to Map. Shows a grouping by from Collectors and a the entry coming from Collectors.summarizing")
    @Test
    void testGroupToMapStoreByTotalSales()
    {
        // So we go straight in collecting on the store as the Map key and the entry from a C
        final Map<Store, DoubleSummaryStatistics> storeStatistics = saleStream().collect(
            Collectors.groupingBy(sale -> sale.store, // First argument to the groupingBy is a Function
                                  Collectors.summarizingDouble(Sale::total)));

        assertTrue(storeStatistics.get(Store.ST_LOUIS).getAverage() > 0);
    }

    @DisplayName("Example with joining ")
    @Test
    void testJoining() {
        final List<String> list = Arrays.asList("a", "b", "c");

        final String result = list.stream().collect(Collectors.joining(","));

        assertEquals("a,b,c", result);

        System.out.println(result);
    }

    private static BinaryOperator<String> joinOn(final String divider) {
        return (allSoFar, oneMore) ->
            allSoFar + divider + oneMore;
    }

    /**
     * Reduce is a way of combining multiple values and ending up with one.
     *
     * T reduce(T identity, BinaryOperator<T> accumulator);
     * Where, identity is initial value of type T and accumulator is a function for combining two values.
     * The binary operator takes two inputs, the first a basket of accumulated objects and the second a new value
     * to add.
     */
    @DisplayName("A reduce operation. Take an array of strings, take last word from each stream and  ")
    @Test
    void testReduce()
    {
        // A function which takes a phrase , splits by string and gets the last word
        final Function<String,String> lastWord = (String phrase) ->
            Arrays.asList(phrase.split(" "))
                  .stream()
                  .reduce(LAST)// this is the accumulating Binary operator
                  .orElse("");

        final String[] foods = new String[] {
            "Crunchy carrots",
            "Golden-hued bananas",
            "",
            "Bright orange pumpkins",
            "Little trees of broccoli",
            "meat"
        };

        final String actual = Arrays.asList(foods).stream()
                         .filter(s -> !s.isEmpty()) // skip empty array elements
                         .map(lastWord) // A function which internally uses a stream to get last word from each
                        .reduce(joinOn(" & ")) // A BinaryOperator acting as an accumulator
                        .orElse("");

        assertEquals("carrots & bananas & pumpkins & broccoli & meat", actual);
    }

    // Doing this here because need to use a stream repeatedly. If put in a variable, the stream
    // would be dead after first usage and if we tried to use again, we'd get a RuntimeException
    static Stream<Sale> saleStream() {
        return RandomSale.streamOf(10000);
    }

    @DisplayName("Test summary statistics")
    @Test
    void testSummaryStatistics()
    {
        // total is a method in Sale which adds together all the items sold.
        // So saleStream gives us "totalStream" which as a supplier we can call get() on
        final Supplier<DoubleStream> totalStream = () -> saleStream().mapToDouble(Sale::total);

        // maximum sale amount?
        final DoubleSummaryStatistics stats = totalStream.get().summaryStatistics();
        assertTrue(stats.getMax() > 100.0d);
        assertTrue(stats.getAverage() > stats.getMin());
    }

    /**
     * So here we want  a stream of the Items within the Sale so we're going for a list in a list. Tricky, eh.
     */
    @DisplayName("Test stream with a flatMap")
    @Test
    void testFlatMap(){
        // Could try this and this would be a natural inclination, use a map transformer
        // BUT THIS WOULD GIVE US A STREAM OF A LIST OF ITEMS
        // Supplier<Stream<Item>> itemStream = () -> saleStream().map(sale -> sale.items)

        // Could try this and this would be a natural inclination, use a map transformer
        // BUT THIS WOULD GIVE US A STREAM OF A STREAM OF ITEMS
        // Supplier<Stream<Item>> itemStream = () -> saleStream().map(sale -> sale.items.stream())

        // Solution is to use flatMap - is a function which takes a stream and returns a stream in this case
        final Supplier<Stream<Item>> itemStream = () -> saleStream().flatMap(sale-> sale.items.stream());

        // Recall that itemStream is a supplier so has a getto pull the stream which we can simply count
        assertTrue(itemStream.get().count() > 2);
    }

    @Getter
    @RequiredArgsConstructor
    class Transaction
    {
        private final int amount;

        private final String person;
    }
}
