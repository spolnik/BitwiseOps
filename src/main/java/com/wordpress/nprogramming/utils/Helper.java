package com.wordpress.nprogramming.utils;

public final class Helper {
    public static boolean isNumber(String x) {
        try {
            Long.parseLong(x);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
