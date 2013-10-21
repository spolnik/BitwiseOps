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
public class BitTranslatorIntTest {
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {10,                    "00000000000000000000000000001010"},
                {0,                     "00000000000000000000000000000000"},
                {1,                     "00000000000000000000000000000001"},
                {232,                   "00000000000000000000000011101000"},
                {Integer.MAX_VALUE,     "01111111111111111111111111111111"},
                {Integer.MIN_VALUE,     "10000000000000000000000000000000"},
                {-1,                    "11111111111111111111111111111111"},
                {-10,                   "11111111111111111111111111110110"},
                {-323,                  "11111111111111111111111010111101"},
        });
    }

    private int input;
    private String expected;

    public BitTranslatorIntTest(int input, String expected) {
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
