package com.alina;

import java.util.Scanner;
import java.util.Arrays;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println(calc(input));
    }
    public static String calc(String input) throws Exception {
        String[] romanNumbers = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        String romanRegex = "M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})";
        String arabicRegex = "\\d+";
        input = input.replaceAll("\\s+","");
        String[] args = input.split("[\\+,\\-,\\/,\\*]");

        if (args.length != 2) { throw new Exception("invalid expression"); }

        int a, b, result;
        boolean isRoman = false;

        if (Pattern.matches(romanRegex, args[0]) && Pattern.matches(romanRegex, args[1])) {
            a = Arrays.asList(romanNumbers).indexOf(args[0]) + 1;
            b = Arrays.asList(romanNumbers).indexOf(args[1]) + 1;
            isRoman = true;
        } else if (Pattern.matches(arabicRegex, args[0]) && Pattern.matches(arabicRegex, args[1])){
            a = Integer.parseInt(args[0]);
            b = Integer.parseInt(args[1]);
        } else {
            throw new Exception("input only in Arabic or Roman numbers");
        }

        if(a<1 || a>10 || b<1 || b>10 ){
            throw new Exception("Incorrect values");
        }

        if (input.contains("+")) {
            result = a+b;
        } else if (input.contains("-")) {
            result = a-b;
        } else if (input.contains("*")) {
            result = a*b;
        } else if (input.contains("/")) {
            result = a/b;
        } else {
            throw new Exception("Incorrect operator");
        }

        if (isRoman) {
            return intToRoman(result);
        } else {
            return Integer.toString(result);
        }
    }

    public static String intToRoman(int num) throws Exception {
        if (num < 1) { throw new Exception("Result is less than one"); }
        int[] values = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] romanLetters = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        StringBuilder roman = new StringBuilder();
        for(int i=0;i<values.length;i++)
        {
            while(num >= values[i])
            {
                num = num - values[i];
                roman.append(romanLetters[i]);
            }
        }
        return roman.toString();
    }

}



