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
public class BitTranslatorByteTest {
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {(byte)10,          "00001010"},
                {(byte)0,           "00000000"},
                {(byte)1,           "00000001"},
                {(byte)32,          "00100000"},
                {Byte.MAX_VALUE,    "01111111"},
                {Byte.MIN_VALUE,    "10000000"},
                {(byte)-1,          "11111111"},
                {(byte)-10,         "11110110"},
                {(byte)-23,         "11101001"},
        });
    }

    private byte input;
    private String expected;

    public BitTranslatorByteTest(byte input, String expected) {
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
