package org.example;

public class RomanNumber extends Number implements Comparable<Number> {

    // Current Roman Value
    private String roman;

    // Current Normal Value
    private int value;

    // ------------------ Constructors ------------

    /**
     * Default constructor
     */
    public RomanNumber() {
        // Ignored
    }

    /**
     * Converts a Roman number into a normal number.
     * 
     * @param roman the Roman number to convert
     * @return a normal number like 1, 2, 3, ...
     * @throws IllegalArgumentException if input is not valid
     * @param roman
     */
    public RomanNumber(String roman) {
        this.roman = roman;
        this.value = RomanConverter.getNumberFromRoman(this.roman);
    }

    /**
     * Converts a normal number into a Roman number.
     * 
     * @param value it is a normal number like 1, 2, 3, ...
     */
    public RomanNumber(int value) {
        this.value = value;
        this.roman = RomanConverter.getRomanFromNumber(this.value);

    }

    /**
     * Puts values into this class
     * 
     * @param value it is a normal number like 1, 2, 3, ...
     * @param roman a Roman number like I, II, III, IV, ...
     */
    public RomanNumber(int value, String roman) {

        if (roman == null) {
            throw new IllegalArgumentException("Roman number cannot be null");
        }
        if (value <= 0 || value >= 4000) {
            throw new IllegalArgumentException("Value is out of range (1-3999)");
        }

        String normalizedRoman = roman.trim();

        // TODO : SOLVE INCONSISTENCY BUG
        if (!RomanConverter.initialization) {
            int parsedValue = RomanConverter.getNumberFromRoman(normalizedRoman);

            if (value != parsedValue) {
                throw new IllegalArgumentException("Inconsistent value and roman number");
            }
        }

        this.value = value;
        this.roman = normalizedRoman;

    }

    // ----------------- Getters and Setters -------------
    public String getRoman() {
        return this.roman;
    }

    public int getValue() {
        return this.value;
    }

    public void setRoman(String roman) {
        if (roman == null) {
            throw new IllegalArgumentException("Roman number cannot be null");
        }

        String normalizedRoman = roman.trim();
        int parsedValue = RomanConverter.getNumberFromRoman(normalizedRoman);

        this.roman = normalizedRoman;
        this.value = parsedValue;
    }

    public void setValue(int value) {
        if (value <= 0 || value >= 4000) {
            throw new IllegalArgumentException("Value is out of range (1-3999)");
        }
        this.value = value;
        this.roman = RomanConverter.getRomanFromNumber(this.value);
    }

    // ------------------ Methods -------------------

    /**
     * @{inheritDoc}
     */
    @Override
    public double doubleValue() {
        return (double) this.value;
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public float floatValue() {
        return (float) this.value;
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public int intValue() {
        return (int) this.value;
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public long longValue() {
        return (long) this.value;
    }

    @Override
    public String toString() {
        return this.roman;
    }

    @Override
    public int compareTo(Number other) {
        if (other == null) {
            throw new NullPointerException();
        }
        return Integer.compare(this.value, other.intValue());
    }
}