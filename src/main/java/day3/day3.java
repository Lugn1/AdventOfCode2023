package day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class day3 {

    public static void main(String[] args) {
        String fileName = "./src/main/java/day3/sample_input";
        List<String> schematic = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                schematic.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Part 1
        System.out.println("Sum of part numbers: " + sumPartNumbers(schematic));
        // Part 2
        System.out.println("Sum of gear ratios: " + sumGearRatios(schematic));
    }

    public static int sumPartNumbers(List<String> schematic) {
        int sum = 0;
        Set<String> countedNumbers = new HashSet<>();
        for (int i = 0; i < schematic.size(); i++) {
            String line = schematic.get(i);
            for (int j = 0; j < line.length(); j++) {
                if (Character.isDigit(line.charAt(j)) && !countedNumbers.contains(i + "," + j)) {
                    int[] numberInfo = extractNumber(schematic, i, j);
                    if (isAdjacentToSymbol(schematic, i, j, numberInfo[1])) {
                        sum += numberInfo[0];
                        for (int k = 0; k < numberInfo[1]; k++) {
                            countedNumbers.add(i + "," + (j + k));
                        }
                    }
                    j += numberInfo[1] - 1;
                }
            }
        }
        return sum;
    }

    private static boolean isAdjacentToSymbol(List<String> schematic, int i, int startJ, int length) {
        for (int j = startJ; j < startJ + length; j++) {
            for (int row = i - 1; row <= i + 1; row++) {
                for (int col = j - 1; col <= j + 1; col++) {
                    if (row >= 0 && row < schematic.size() && col >= 0 && col < schematic.get(row).length()) {
                        if (row == i && col == j) {
                            continue;
                        }
                        char c = schematic.get(row).charAt(col);
                        if (c != '.' && !Character.isDigit(c)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private static int[] extractNumber(List<String> schematic, int i, int j) {
        StringBuilder number = new StringBuilder();
        int length = 0;
        String line = schematic.get(i);
        while (j < line.length() && Character.isDigit(line.charAt(j))) {
            number.append(line.charAt(j));
            j++;
            length++;
        }
        int num = Integer.parseInt(number.toString());
        return new int[]{num, length};
    }

    // Part 2

    private static List<Integer> getAdjacentPartNumbers(List<String> schematic, int i, int j) {
        Set<Integer> partNumbers = new HashSet<>();
        Map<Integer, String> numberCoordinates = new HashMap<>(); // Map to store number and its starting coordinates

        for (int row = i - 1; row <= i + 1; row++) {
            for (int col = j - 1; col <= j + 1; col++) {
                if (isValidCell(schematic, row, col) && (row != i || col != j)) {
                    char c = schematic.get(row).charAt(col);
                    if (Character.isDigit(c)) {
                        int[] numberInfo = extractNumber(schematic, row, col);
                        int num = numberInfo[0];
                        String coordKey = row + "," + col;

                        // Check if the number's starting coordinate has already been encountered
                        if (!numberCoordinates.containsKey(num) || !numberCoordinates.get(num).equals(coordKey)) {
                            partNumbers.add(num);
                            numberCoordinates.put(num, coordKey);
                        }
                    }
                }
            }
        }

        return partNumbers.size() == 2 ? new ArrayList<>(partNumbers) : Collections.emptyList();
    }

    private static boolean isValidCell(List<String> schematic, int row, int col) {
        return row >= 0 && row < schematic.size() && col >= 0 && col < schematic.get(row).length();
    }

    public static int sumGearRatios(List<String> schematic) {
        int sum = 0;

        for (int i = 0; i < schematic.size(); i++) {
            String line = schematic.get(i);
            for (int j = 0; j < line.length(); j++) {
                if (line.charAt(j) == '*') {
                    List<Integer> adjacentPartNumbers = getAdjacentPartNumbers(schematic, i, j);
                    if (adjacentPartNumbers.size() == 2) {
                        sum += adjacentPartNumbers.get(0) * adjacentPartNumbers.get(1); // Multiply the two part numbers
                    }
                }
            }
        }

        return sum;
    }
}