package day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class part1 {
    public static void main(String[] args) {
        String puzzleInput = "./src/main/java/day2/puzzle_input";
        int sumOfGameIDs = 0;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(puzzleInput));
            String line;
            while ((line = reader.readLine()) != null) {
                int gameId = Integer.parseInt(line.substring(5, line.indexOf(':')));
                if (checkPossibility(line)) {
                    sumOfGameIDs += gameId;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Sum of IDs for possible games: " + sumOfGameIDs);
    }

    public static boolean checkPossibility(String input) {
        int maxRed = 12;
        int maxGreen = 13;
        int maxBlue = 14;

        String gameData = input.substring(input.indexOf(':') + 1).trim();
        String[] parts = gameData.split(";");

        for (String part : parts) {
            String[] gems = part.split(",");
            for (String gem : gems) {
                gem = gem.trim();
                String[] gemDetails = gem.split(" ");
                if (gemDetails.length == 2) {
                    int count = Integer.parseInt(gemDetails[0]);
                    String color = gemDetails[1].toLowerCase();

                    if ((color.equals("red") && count > maxRed) ||
                            (color.equals("green") && count > maxGreen) ||
                            (color.equals("blue") && count > maxBlue)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}





