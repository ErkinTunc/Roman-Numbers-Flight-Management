package org.example;

import java.util.Collection;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class RomanConverter {

    // Initialization flag to prevent recursive calls during construction
    public static boolean initialization = true;

    // Table des symboles
    private static final Collection<RomanNumber> SYMBOLS = new ArrayList<>();
    static {
        SYMBOLS.add(new RomanNumber(1000, "M"));
        SYMBOLS.add(new RomanNumber(900, "CM"));
        SYMBOLS.add(new RomanNumber(500, "D"));
        SYMBOLS.add(new RomanNumber(400, "CD"));
        SYMBOLS.add(new RomanNumber(100, "C"));
        SYMBOLS.add(new RomanNumber(90, "XC"));
        SYMBOLS.add(new RomanNumber(50, "L"));
        SYMBOLS.add(new RomanNumber(40, "XL"));
        SYMBOLS.add(new RomanNumber(10, "X"));
        SYMBOLS.add(new RomanNumber(9, "IX"));
        SYMBOLS.add(new RomanNumber(5, "V"));
        SYMBOLS.add(new RomanNumber(4, "IV"));
        SYMBOLS.add(new RomanNumber(1, "I"));

        initialization = false;
    }

    // Expression régulière de validation | (Regex for validation)
    private static final Pattern VALIDATION_RE = Pattern // Regex for numbers and roman symboles
            .compile("^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$");

    /**
     * Converts a normal number into a ROman number.
     * 
     * @param a it is a normal number like 1, 2, 3, ...
     * @return a Roman number like I, II, III, IV, ...
     * @throws IllegalArgumentException if input is not between 1 and 3999
     *                                  (inclusive)
     */
    public static String getRomanFromNumber(int a) throws IllegalArgumentException { // toRoman
        if (a < 1 || a > 3999) {
            throw new IllegalArgumentException("Number must be between 1 and 3999");
        }

        String resultat = "";
        for (RomanNumber symbol : SYMBOLS) {
            while (a >= symbol.getValue()) {
                resultat += symbol.getRoman();
                a -= symbol.getValue();
            }
        }
        return resultat; // Get Roman Nomber
    }

    /**
     * Converts a Roman number into a normal number.
     * 
     * @param a the Roman number to convert
     * @return a normal number like 1, 2, 3, ...
     * @throws IllegalArgumentException if input is not valid to regex or empty
     */
    public static int getNumberFromRoman(String a) throws IllegalArgumentException { // fromRoman
        if (a == null || a.isEmpty() || !VALIDATION_RE.matcher(a).matches()) { // Checks if the string is valid( is
                                                                               // empty? , matches the regex?)
            throw new IllegalArgumentException("Invalid Roman number");
        }
        /**
         * Matcher Class in Java : it is part of the java.util.regex
         * package is used to perform matching operations on an input string using a
         * compiled regular expression (Pattern)
         * URL : https://www.geeksforgeeks.org/java/matcher-class-in-java/
         */

        int resultat = 0;
        int index = 0;

        for (RomanNumber symbol : SYMBOLS) {
            while (a.startsWith(symbol.getRoman(), index)) {
                resultat += symbol.getValue();
                index += symbol.getRoman().length();
            }
        }

        return resultat; // Get Number
    }
}