package com.wordpress.nprogramming;

import java.util.Stack;

import static com.google.common.base.Preconditions.*;

public final class BitProcessorImpl implements BitProcessor {
    private final static Integer one = 1;
    private final static Integer zero = 0;

    private final static int numberOfBits = 32;

    @Override
    public String getBitRepresentation(Integer number) {
        checkNotNull(number);

        if (number == 0)
            return "00000000000000000000000000000000";
        else
            return analyzeNumber(number);
    }

    private String analyzeNumber(Integer number) {
        Stack<Integer> stack = new Stack<>();
        StringBuilder result = new StringBuilder();

        if (number > 0) {
            processPositiveNumber(number, stack);
            enrichWithLeadingNumber(stack, zero);
        } else {
            processNegativeNumber(number, stack);
            enrichWithLeadingNumber(stack, one);
        }

        while (!stack.isEmpty())
            result.append(stack.pop().toString());

        return result.toString();
    }

    private void processNegativeNumber(int x, Stack<Integer> stack) {
        x = -1 * x - 1;

        while (x > 0) {
            if (x % 2 == 1)
                stack.push(zero);
            else
                stack.push(one);
            x = x / 2;
        }
    }

    private void processPositiveNumber(int x, Stack<Integer> stack) {
        while (x > 0) {
            if (x % 2 == 0)
                stack.push(zero);
            else
                stack.push(one);
            x = x / 2;
        }
    }

    private void enrichWithLeadingNumber(Stack<Integer> stack, Integer leadingNumber) {
        int numberOfZerosToAdd = numberOfBits - stack.size();

        for (int i = 0; i < numberOfZerosToAdd; i++)
            stack.push(leadingNumber);
    }
}
