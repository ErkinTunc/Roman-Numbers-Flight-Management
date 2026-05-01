package org.example;

public class Start {

    // Start class
    public static void main(String[] args) {

        System.out.println("=== Roman Converter ===");

        RomanNumber roman = new RomanNumber("IV");
        System.out.println(roman.getValue());
        System.out.println(roman.getRoman());

	System.out.println("--------------------");

	RomanNumber rom = new RomanNumber("MMXX");

        System.out.println("--------------------");

        System.out.println(rom.getValue());
        System.out.println(rom.getRoman());

        System.out.println("--------------------");

        System.out.println(RomanConverter.getRomanFromNumber(14)); // XIV

        System.out.println("--------------------");

        System.out.println(RomanConverter.getNumberFromRoman("CD")); // 400

        System.out.println("--------------------");

        try{
            RomanNumber bad = new RomanNumber("IIII");
        } catch (Exception e){
            System.out.println("Error detected " + e.getMessage());
        }

    }

}
