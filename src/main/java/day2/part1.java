package day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class part1 {
    public static void main(String[] args) {
        String puzzleInput = "./src/main/java/day2/puzzle_input";
        int sumOfGameIDs = 0;
        int sumOfPowers = 0;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(puzzleInput));
            String line;
            while ((line = reader.readLine()) != null) {
                int gameId = Integer.parseInt(line.substring(5, line.indexOf(':')));
                if (checkPossibility(line)) {
                    sumOfGameIDs += gameId;
                }
                Cubes cubes = cubesAndPower(line);
                sumOfPowers += cubes.getPower();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Sum of IDs for possible games: " + sumOfGameIDs);
        System.out.println("Sum of powers: " + sumOfPowers);
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

    // Part 2
    static class Cubes {
        int red;
        int green;
        int blue;

        public Cubes(int red, int green, int blue) {
            this.red = red;
            this.green = green;
            this.blue = blue;
        }

        int getPower() {
            return red * green * blue;
        }
    }


    public static Cubes cubesAndPower(String input) {
        int highestRed = 0;
        int highestGreen = 0;
        int highestBlue = 0;
        String gameData = input.substring(input.indexOf(':') + 1).trim();
        String[] parts = gameData.split(";");

        for (String part : parts) {
            int localRed = 0, localGreen = 0, localBlue = 0;
            String[] gems = part.split(",");
            for (String gem : gems) {
                gem = gem.trim();
                String[] gemDetails = gem.split(" ");
                if (gemDetails.length == 2) {
                    int count = Integer.parseInt(gemDetails[0]);
                    String color = gemDetails[1].toLowerCase();

                    if (color.equals("red")) localRed = count;
                    if (color.equals("green")) localGreen = count;
                    if (color.equals("blue")) localBlue = count;

                    highestRed = Math.max(highestRed, localRed);
                    highestGreen = Math.max(highestGreen, localGreen);
                    highestBlue = Math.max(highestBlue, localBlue);
                }
            }
        }
        return new Cubes(highestRed, highestGreen, highestBlue);
    }
}

