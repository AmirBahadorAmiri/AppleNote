package com.amirbahadoramiri.applenotebook.tools.text;

public class REPL {

    public static String replaceWithPersianNumber(String text) {
        String[] persianNumbers = {"۰", "۱", "۲", "۳", "۴", "۵", "۶", "۷", "۸", "۹"};
        String[] englishNumbers = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        for (int i = 0; i < englishNumbers.length; i++) {
            text = text.replace(englishNumbers[i], persianNumbers[i]);
        }
        return text;
    }

}
