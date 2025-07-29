package com.WealthWay.WealthCalulater.Utility;

public class NumberToWordsIndian {

    private static final String[] units = {
        "", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", 
        "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", 
        "sixteen", "seventeen", "eighteen", "nineteen"
    };

    private static final String[] tens = {
        "", "", "twenty", "thirty", "forty", "fifty", 
        "sixty", "seventy", "eighty", "ninety"
    };

    public static String convert(int number) {
        if (number == 0) return "zero";
        return convertToWords(number).trim();
    }

    private static String convertToWords(int number) {
        if (number < 20)
            return units[number];
        if (number < 100)
            return tens[number / 10] + (number % 10 != 0 ? " " + units[number % 10] : "");
        if (number < 1000)
            return units[number / 100] + " hundred" + (number % 100 != 0 ? " and " + convertToWords(number % 100) : "");
        if (number < 100000)
            return convertToWords(number / 1000) + " thousand" + (number % 1000 != 0 ? " " + convertToWords(number % 1000) : "");
        if (number < 10000000)
            return convertToWords(number / 100000) + " lakh" + (number % 100000 != 0 ? " " + convertToWords(number % 100000) : "");
        return convertToWords(number / 10000000) + " crore" + (number % 10000000 != 0 ? " " + convertToWords(number % 10000000) : "");
    }

    
}

