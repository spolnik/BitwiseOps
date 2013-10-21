package com.wordpress.nprogramming;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class BitTranslatorLongTest {
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {10L,                    "0000000000000000000000000000000000000000000000000000000000001010"},
                {0L,                     "0000000000000000000000000000000000000000000000000000000000000000"},
                {1L,                     "0000000000000000000000000000000000000000000000000000000000000001"},
                {232L,                   "0000000000000000000000000000000000000000000000000000000011101000"},
                {Long.MAX_VALUE,         "0111111111111111111111111111111111111111111111111111111111111111"},
                {Long.MIN_VALUE,         "1000000000000000000000000000000000000000000000000000000000000000"},
                {-1L,                    "1111111111111111111111111111111111111111111111111111111111111111"},
                {-10L,                   "1111111111111111111111111111111111111111111111111111111111110110"},
                {-323L,                  "1111111111111111111111111111111111111111111111111111111010111101"},
        });
    }

    private long input;
    private String expected;

    public BitTranslatorLongTest(long input, String expected) {
        this.input = input;
        this.expected = expected;
    }

    @Test
    public void testBitRepresentation() throws Exception {
        Injector injector = Guice.createInjector(new BitProcessorModule());
        BitTranslator bitProcessor = injector.getInstance(BitTranslator.class);

        String bitRepresentation = bitProcessor.getBitRepresentation(input);

        assertThat(bitRepresentation, is(expected));
    }
}
