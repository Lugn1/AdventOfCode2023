package day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class part1 {
    public static void main(String[] args) {
        String puzzleInput = "./src/main/java/day2/puzzle_input";
        int possibleGamesTotal = 0;


        try {
            BufferedReader reader = new BufferedReader(new FileReader(puzzleInput));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                System.out.println(possibleGamesTotal);
                checkPossibility(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void checkPossibility(String input) {
        Map<String, Integer> gemCount = new HashMap<>();

        String gameData = input.substring(input.indexOf(':') + 1).trim();
        String[] parts = gameData.split(";");


        for (String part : parts) {
            String[] gems = part.split(",");
            for (String gem : gems) {
                String[] gemDetails = gem.split(" ");
                if (gemDetails.length == 2) {
                    int count = Integer.parseInt(gemDetails[0]);
                    String color = gemDetails[1];

                    gemCount.put(color, gemCount.getOrDefault(color, 0) + count);
                }
            }
        }
        for (String color : gemCount.keySet()) {
            System.out.println(color + ": " + gemCount.get(color));
        }
    }
}

