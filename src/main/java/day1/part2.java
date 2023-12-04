package day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

public class part2 {
    private static final Map<String, Integer> numberMap = new HashMap<>();

    static {
        numberMap.put("one", 1);
        numberMap.put("two", 2);
        numberMap.put("three", 3);
        numberMap.put("four", 4);
        numberMap.put("five", 5);
        numberMap.put("six", 6);
        numberMap.put("seven", 7);
        numberMap.put("eight", 8);
        numberMap.put("nine", 9);
    }

    public static void main(String[] args) {
        String puzzleInput = "./src/main/java/day1/puzzle_input";
        int totalSum = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(puzzleInput))) {
            String line;
            while ((line = reader.readLine()) != null) {
                totalSum += Integer.parseInt(addUpSum(line));
                System.out.println("Total Sum: " + totalSum);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String addUpSum(String input) {
        int firstNum = findFirstNumber(input);
        System.out.println("first number: " + findFirstNumber(input));
        int lastNum = findLastNumber(input);
        System.out.println("last number: " + findLastNumber(input));
        return firstNum + String.valueOf(lastNum);
    }

    private static int findFirstNumber(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (Character.isDigit(input.charAt(i))) {
                return Character.getNumericValue(input.charAt(i));
            } else {
                for (Map.Entry<String, Integer> entry : numberMap.entrySet()) {
                    int wordLength = entry.getKey().length();
                    if (i + wordLength <= input.length() && input.substring(i, i + wordLength).equals(entry.getKey())) {
                        return entry.getValue();
                    }
                }
            }
        }
        return 0;
    }

    private static int findLastNumber(String input) {
        for (int i = input.length() - 1; i >= 0; i--) {
            if (Character.isDigit(input.charAt(i))) {
                return Character.getNumericValue(input.charAt(i));
            } else {
                for (Map.Entry<String, Integer> entry : numberMap.entrySet()) {
                    int wordLength = entry.getKey().length();
                    if (i - wordLength + 1 >= 0 && input.substring(i - wordLength + 1, i + 1).equals(entry.getKey())) {
                        return entry.getValue();
                    }
                }
            }
        }
        return 0;
    }
}