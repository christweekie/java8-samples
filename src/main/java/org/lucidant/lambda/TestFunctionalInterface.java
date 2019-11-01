package org.lucidant.lambda;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * One unimplemented method.
 */
@FunctionalInterface
public interface TestFunctionalInterface
{
    void printIt(String text);

    default public void printUtf8To(String text, OutputStream outputStream){
        try {
            outputStream.write(text.getBytes("UTF-8"));
        } catch (IOException e) {
            throw new RuntimeException("Error writing String as UTF-8 to OutputStream", e);
        }
    }

    static void printItToSystemOut(String text){
        System.out.println(text);
    }

    /**
     * Achieve timing console logging around the execution of the supplier passed in.
     *
     * @param description
     * @param code  the code to be accepted
     * @param consumer a consumer to accept the String and presumably do something with it, like output it to the console
     * @param <A>
     * @return
     */
    static <A> A timed(final String description, final Supplier<A> code, final Consumer<String> consumer) {
        final Date before = new Date();
        final A result = code.get();
        final Long duration = new Date().getTime() - before.getTime();
        consumer.accept(description + " took " + duration + " ms");
        return result;
    }
}
