package com.example.BE_Library.common.util;

public class StringUtils {
    public static boolean isEmpty(String string) {
        return !isNotEmpty(string);
    }
    public static boolean isNotEmpty(String string) {
        return org.springframework.util.StringUtils.hasText(string);
    }

    public static String toSearchableText(String input) {
        if (isEmpty(input)) {
            return input;
        }
        return removeSpace(removeSpecialCharacters(input));
    }

    public static String removeSpace(String input) {
        if (isEmpty(input)) {
            return input;
        }
        return input
                .replaceAll("\n", "")
                .replaceAll("\t", "")
                .replaceAll(" ", "");
    }

    public static String removeSpecialCharacters(String input) {
        if (isEmpty(input)) {
            return input;
        }
        return input.replaceAll("[^a-zA-Z0-9 \n\t]", "");
    }
}
