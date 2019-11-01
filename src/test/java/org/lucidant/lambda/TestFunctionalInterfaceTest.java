package org.lucidant.lambda;

import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TestFunctionalInterfaceTest
{

    @Test
    void testFunction()
    {
        TestFunctionalInterface funcInter = (String text) -> System.out.println(text);

        funcInter.printIt("hello");
        TestFunctionalInterface.printItToSystemOut("goodbye");
    }

    @Test
    void testWithIsolation()
    {
        // Because the method is simply a consumer,
        final AtomicReference<String> atomicReference = new AtomicReference("");

        // So the supplier gets passed to the timed() method and gets executed by supplier.get();
        // timed() returns the value from supplier.
        final Double costs = TestFunctionalInterface.timed("Cost calculation", this::calculateCosts, atomicReference::set);

        // So we told the timed method that the consumer, instead of the "normal" System.out, is the set method of the atomicReference
        // So instead of checking that the logging happened, we are now checking that the Consumer was called
        assertTrue(atomicReference.get().contains("Cost calculation took"));
    }

    private Double calculateCosts()
    {
        pretendToWorkHard();
        return 12345.6;
    }

    private static final Random random = new Random();
    private static final Integer MAX_WORK_TIME_MS = 200;
    private static void pretendToWorkHard() {
        try {
            Thread.sleep(random.nextInt(MAX_WORK_TIME_MS));
        } catch (InterruptedException e) {
        }
    }
}
