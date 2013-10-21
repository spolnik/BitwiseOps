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
public class BitTranslatorShortTest {
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {(short)10,                    "0000000000001010"},
                {(short)0,                     "0000000000000000"},
                {(short)1,                     "0000000000000001"},
                {(short)232,                   "0000000011101000"},
                {Short.MAX_VALUE,              "0111111111111111"},
                {Short.MIN_VALUE,              "1000000000000000"},
                {(short)-1,                    "1111111111111111"},
                {(short)-10,                   "1111111111110110"},
                {(short)-323,                  "1111111010111101"},
        });
    }

    private short input;
    private String expected;

    public BitTranslatorShortTest(short input, String expected) {
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
