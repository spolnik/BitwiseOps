package com.wordpress.nprogramming;

import java.util.Stack;

import static com.google.common.base.Preconditions.*;

final class BitTranslatorImpl implements BitTranslator {
    private final static Byte one = 1;
    private final static Byte zero = 0;

    @Override
    public String getBitRepresentation(Long number) {
        checkNotNull(number);

        if (number == 0)
            return "0000000000000000000000000000000000000000000000000000000000000000";
        else
            return analyzeNumber(number, 64);
    }

    @Override
    public String getBitRepresentation(Integer number) {
        checkNotNull(number);

        if (number == 0)
            return "00000000000000000000000000000000";
        else
            return analyzeNumber(Long.valueOf(number), 32);
    }

    @Override
    public String getBitRepresentation(Short number) {
        checkNotNull(number);

        if (number == 0)
            return "0000000000000000";
        else
            return analyzeNumber(Long.valueOf(number), 16);
    }

    @Override
    public String getBitRepresentation(Byte number) {
        checkNotNull(number);

        if (number == 0)
            return "00000000";
        else
            return analyzeNumber(Long.valueOf(number), 8);
    }

    private String analyzeNumber(Long number, int numberOfBits) {
        Stack<Number> stack = new Stack<>();

        if (number > 0) {
            processPositiveNumber(number, stack);
            enrichWithLeadingByte(stack, zero, numberOfBits);
        } else {
            processNegativeNumber(number, stack);
            enrichWithLeadingByte(stack, one, numberOfBits);
        }

        return getResult(stack);
    }

    private String getResult(Stack<Number> stack) {
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty())
            result.append(stack.pop().toString());

        return result.toString();
    }

    private void processNegativeNumber(long x, Stack<Number> stack) {
        x = -1 * x - 1;

        while (x > 0) {
            if ((x & 1) == 1)
                stack.push(zero);
            else
                stack.push(one);
            x = x >> 1;
        }
    }

    private void processPositiveNumber(long x, Stack<Number> stack) {
        while (x > 0) {
            if ((x & 1) == 0)
                stack.push(zero);
            else
                stack.push(one);
            x = x >> 1;
        }
    }

    private void enrichWithLeadingByte(Stack<Number> stack, Byte leadingByte, int numberOfBits) {
        int numberOfZerosToAdd = numberOfBits - stack.size();

        for (int i = 0; i < numberOfZerosToAdd; i++)
            stack.push(leadingByte);
    }
}
