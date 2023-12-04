package day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class part1 {
    public static void main(String[] args) {
        String puzzleInput = "./src/main/java/day1/puzzle_input";
        int totalSum = 0;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(puzzleInput));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                System.out.println(totalSum);
                totalSum += addUpSum(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(totalSum);
    }

    private static int addUpSum(String input) {
        int firstNum = 0;
        int lastNum = 0;

        for (int i = 0; i < input.length(); i++) {
            if (Character.isDigit(input.charAt(i))) {
                System.out.println(input.charAt(i));
                firstNum = Character.getNumericValue(input.charAt(i));
                System.out.println("first sum " + firstNum);
                break;
            }
        }
        for (int i = input.length() - 1; i >= 0; i--) {
            if (Character.isDigit(input.charAt(i))) {
                System.out.println(input.charAt(i));
                lastNum = Character.getNumericValue(input.charAt(i));
                System.out.println("last sum: " + lastNum);
                break;
            }
        }
        return Integer.parseInt(firstNum + String.valueOf(lastNum));
    }
}

