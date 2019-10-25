package org.lucidant.lambda;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestFunctionalInterfaceTest
{

    @Test
    void testFunction()
    {
        TestFunctionalInterface funcInter = (String text) -> System.out.println(text);

        funcInter.printIt("hello");
        TestFunctionalInterface.printItToSystemOut("goodbye");
    }
}