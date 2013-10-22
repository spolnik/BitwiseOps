package com.wordpress.nprogramming;

import static com.google.common.base.Preconditions.checkArgument;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.wordpress.nprogramming.utils.DisplayHelper;
import com.wordpress.nprogramming.utils.Helper;

public class BitProcessor {

    public static void main(String[] args) {
        validateInput(args);

        BitOperation operation = BitOperation.valueOf(args[2].toUpperCase());

        Long a = Long.parseLong(args[0]);
        Long b = Long.parseLong(args[1]);

        DisplayHelper displayHelper = new DisplayHelper(getBitTranslator());

        displayHelper.processResultAsBits(a, b, operation);
        System.out.println();
        displayHelper.processResult(a, b, operation);
    }

    private static void validateInput(String[] args) {
        checkArgument(args.length == 3, "You have to specify 3 arguments, <number A>, <number B> <operation>");

        checkArgument(Helper.isNumber(args[0]), "First argument has to be number.");
        checkArgument(Helper.isNumber(args[1]), "Second argument has to be number.");
    }

    private static BitTranslator getBitTranslator() {
        Injector injector = Guice.createInjector(new BitProcessorModule());
        return injector.getInstance(BitTranslator.class);
    }
}
