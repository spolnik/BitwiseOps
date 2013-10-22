package com.wordpress.nprogramming.utils;

import com.wordpress.nprogramming.BitOperation;
import com.wordpress.nprogramming.BitTranslator;

public final class DisplayHelper {
    private BitTranslator bitTranslator;

    public DisplayHelper(BitTranslator bitTranslator) {

        this.bitTranslator = bitTranslator;
    }

    public void processResultAsBits(Long a, Long b, BitOperation operation) {
        dashline();
        System.out.println("-- BIT REPRESENTATION --");
        dashline();

        NumberSize numberSize = isShiftOp(operation)
                ? NumberSize.LONG
                : getMaxSize(a > b ? a : b);

        displayNumberAsBits(a, numberSize);
        displayNumberAsBits(b, numberSize);
        displayOperation(operation);
        dashline();
        displayResultAsBits(a, b, operation, numberSize);
    }

    private boolean isShiftOp(BitOperation operation) {
        return operation == BitOperation.LSHIFT || operation == BitOperation.RSHIFT;
    }

    private NumberSize getMaxSize(Long x) {
        if (x <= Byte.MAX_VALUE && x >= Byte.MIN_VALUE) {
            return NumberSize.BYTE;
        } else if (x <= Short.MAX_VALUE && x >= Short.MIN_VALUE) {
            return NumberSize.SHORT;
        } else if (x <= Integer.MAX_VALUE && x >= Integer.MIN_VALUE) {
            return NumberSize.INT;
        } else {
            return NumberSize.LONG;
        }
    }

    public void processResult(Long a, Long b, BitOperation operation) {
        dashline();
        System.out.println("-- DECIMAL REPRESENTATION --");
        dashline();

        displayNumber(a);
        displayNumber(b);
        displayOperation(operation);
        dashline();
        displayResult(a, b, operation);
    }

    private void displayOperation(BitOperation operation) {
        System.out.println(operation.getSymbol());
    }

    private void displayNumberAsBits(Long x, NumberSize numberSize) {
        String bitRepresentation;

        switch (numberSize) {

            case INT:
                bitRepresentation = bitTranslator.getBitRepresentation(x.intValue());
                break;
            case SHORT:
                bitRepresentation = bitTranslator.getBitRepresentation(x.shortValue());
                break;
            case BYTE:
                bitRepresentation = bitTranslator.getBitRepresentation(x.byteValue());
                break;
            default:
                bitRepresentation = bitTranslator.getBitRepresentation(x);
                break;
        }

        System.out.println(bitRepresentation);
    }

    private void dashline() {
        System.out.println("----------------");
    }

    private void displayNumber(Long x) {
        System.out.println(x);
    }

    private void displayResultAsBits(Long a, Long b, BitOperation operation, NumberSize numberSize) {
        Long result = operation.calculate(a, b);

        displayNumberAsBits(result, numberSize);
    }

    private void displayResult(Long a, Long b, BitOperation operation) {
        Long result = operation.calculate(a, b);
        System.out.println(result);
    }

    enum NumberSize {LONG, INT, SHORT, BYTE}
}
