package org.pierrevertej;

public class Util {

    public static String toTitleCase(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }

        String titlecase = "";
        String[] words = str.split(" ");
        for (String word : words) {
            titlecase += word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase() + " ";
        }

        return titlecase.substring(0, titlecase.length() - 1);
    }
}
