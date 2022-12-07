package knight.arkham;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Challenge {

    private static List<String> loadFileData(String filename) {
        File file = new File(filename);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        List<String> fileValues = new ArrayList<>();

        if (scanner != null) {

            while (scanner.hasNextLine())
                fileValues.add(scanner.nextLine());

            scanner.close();
        }

        return fileValues;
    }

    public static void backpackOrganizationChallenge() {
//        Lowercase item types a through z have priorities 1 through 26.
//        Uppercase item types A through Z have priorities 27 through 52.


        List<String> backpacksContent = loadFileData("backpacks.txt");

        for (String content : backpacksContent) {

            getCommonItem(content);
        }
    }

    private static void getCommonItem(String backpackContent){

        var firstCompartment = backpackContent.substring(0, backpackContent.length()/2);
        var secondCompartment = backpackContent.substring(backpackContent.length()/2);

        var firstCompartmentItems = new char[firstCompartment.length()];
        var secondCompartmentItems = new char[secondCompartment.length()];

        System.out.println("first compartment: " + firstCompartment);
        System.out.println("second compartment: " + secondCompartment);

        for (int i = 0; i < firstCompartment.length(); i++)
            firstCompartmentItems[i] = firstCompartment.charAt(i);

        for (int i = 0; i < secondCompartment.length(); i++)
            secondCompartmentItems[i] = secondCompartment.charAt(i);

//        for (int i = 0; i < firstCompartment.length(); i++){
//            System.out.println(firstCompartmentItems[i]);
//            System.out.println(secondCompartmentItems[i]);
//        }

    }


    public static void rockPaperAndScissorsChallenge() {

        List<String> roundValues = loadFileData("tournament.txt");

        int totalScorePart1 = 0;
        int totalScorePart2 = 0;

        for (String roundValue : roundValues) {

            totalScorePart1 = getTournamentTotalScore(totalScorePart1, roundValue, true);
            totalScorePart2 = getTournamentTotalScore(totalScorePart2, roundValue, false);
        }

        printChallengeResults(2, totalScorePart1, totalScorePart2);
    }

    private static int getTournamentTotalScore(int totalScore, String roundValue, boolean isPart1) {

        int rockScore = 1;
        int paperScore = 2;
        int scissorsScore = 3;

        int lost = 0;
        int draw = 3;
        int win = 6;

        //Rival variables: A = Rock, B = Paper, C = Scissor
        //My Variables Part 1: X = Rock, Y = Paper, Z = Scissor
        //My Variables Part 2: X = You need to lose, Y = You need to draw, Z = You need to Win
        switch (roundValue) {
            case "A X":
                totalScore += isPart1 ? (rockScore + draw) : (scissorsScore + lost);
                break;
            case "A Y":
                totalScore += isPart1 ? (paperScore + win) : (rockScore + draw);
                break;
            case "A Z":
                totalScore += isPart1 ? (scissorsScore + lost) : (paperScore + win);
                break;
            case "B X":
                totalScore += (rockScore + lost);
                break;
            case "B Y":
                totalScore += (paperScore + draw);
                break;
            case "B Z":
                totalScore += (scissorsScore + win);
                break;
            case "C X":
                totalScore += isPart1 ? (rockScore + win) : (paperScore + lost);
                break;
            case "C Y":
                totalScore += isPart1 ? (paperScore + lost) : (scissorsScore + draw);
                break;
            case "C Z":
                totalScore += isPart1 ? (scissorsScore + draw) : (rockScore + win);
                break;
        }

        return totalScore;
    }

    public static void caloriesChallenge() {

        List<String> calories = loadFileData("calories.txt");

        int actualSum = 0;
        int maxValue = 0;

        List<String> totalCaloriesOfEachElf = new ArrayList<>();

        for (String actualCalories : calories) {

            if (actualCalories.equals("")) {

                if (actualSum > maxValue) {
                    maxValue = actualSum;

                    totalCaloriesOfEachElf.add(String.valueOf(maxValue));
                }

                actualSum = 0;
            } else
                actualSum += Integer.parseInt(actualCalories);
        }

        int arraySize = totalCaloriesOfEachElf.size() - 1;

        int totalCaloriesOfTop3 = Integer.parseInt(totalCaloriesOfEachElf.get(arraySize)) +
                Integer.parseInt(totalCaloriesOfEachElf.get(arraySize - 1)) +
                Integer.parseInt(totalCaloriesOfEachElf.get(arraySize - 2));

        printChallengeResults(1, maxValue, totalCaloriesOfTop3);
    }

    private static void printChallengeResults(int challengeNumber, int maxValue, int totalCaloriesOfTop3) {
        System.out.println("\nReto #"+challengeNumber+ " Completado");
        System.out.println("Respuesta Parte 1: " + maxValue + "\nRespuesta Parte 2: " + totalCaloriesOfTop3);
    }
}
