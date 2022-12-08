package knight.arkham;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Challenge {

    private static List<String> loadFileData(String filename) {
        var file = new File(filename);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        var fileValues = new ArrayList<String>();

        if (scanner != null) {

            while (scanner.hasNextLine())
                fileValues.add(scanner.nextLine());

            scanner.close();
        }

        return fileValues;
    }

    public static void backpackOrganizationChallenge() {

        int totalSumOfItemsPriority = 0;

        List<String> backpacksContent = loadFileData("backpacks.txt");

        for (String content : backpacksContent) {

            System.out.println(content);

//            getPriorityBadgeSum(content);
            totalSumOfItemsPriority += getPriorityItemSum(content);
        }

        printChallengeResults(3, totalSumOfItemsPriority, 0);
    }

//    private static void getPriorityBadgeSum(String backpackContent){
//
//    }

//    To help prioritize item rearrangement, every item type can be converted to a priority:
//    Lowercase item types a through z have priorities 1 through 26.
//    Uppercase item types A through Z have priorities 27 through 52.
    private static int getPriorityItemSum(String backpackContent) {

        var firstCompartment = backpackContent.substring(0, backpackContent.length() / 2);
        var secondCompartment = backpackContent.substring(backpackContent.length() / 2);

        var firstCompartmentItems = new char[firstCompartment.length()];
        var secondCompartmentItems = new char[secondCompartment.length()];

        for (var i = 0; i < firstCompartment.length(); i++)
            firstCompartmentItems[i] = firstCompartment.charAt(i);

        for (var i = 0; i < secondCompartment.length(); i++)
            secondCompartmentItems[i] = secondCompartment.charAt(i);

        var itemRepeatCounter = 0;

        var sumOfItemsPriority = 0;

        var itemsPriority = getItemsPriorityMap();

        for (char firstCompartmentItem : firstCompartmentItems) {

            for (char secondCompartmentItem : secondCompartmentItems) {

                if (firstCompartmentItem == secondCompartmentItem && itemRepeatCounter == 0) {

                    itemRepeatCounter++;
                    var itemPriorityValue = itemsPriority.get(String.valueOf(firstCompartmentItem));

                    sumOfItemsPriority += itemPriorityValue;
                }
            }
        }

        return sumOfItemsPriority;
    }

    private static HashMap<String, Integer> getItemsPriorityMap() {

        var itemsPriority = new HashMap<String, Integer>();

        int priorityValue = 1;

        for (var letter = 'a'; letter <= 'z'; ++letter){

            itemsPriority.put(String.valueOf(letter), priorityValue);
            priorityValue++;
        }

        for (var letter = 'A'; letter <= 'Z'; ++letter){

            itemsPriority.put(String.valueOf(letter), priorityValue);
            priorityValue++;
        }

        return itemsPriority;
    }


    public static void rockPaperAndScissorsChallenge() {

        var tournamentRoundValues = loadFileData("tournament.txt");

        int totalScorePart1 = 0;
        int totalScorePart2 = 0;

        for (var roundValue : tournamentRoundValues) {

            totalScorePart1 = getTournamentTotalScore(totalScorePart1, roundValue, true);
            totalScorePart2 = getTournamentTotalScore(totalScorePart2, roundValue, false);
        }

        printChallengeResults(2, totalScorePart1, totalScorePart2);
    }

    private static int getTournamentTotalScore(int totalScore, String roundValue, boolean isPart1) {

        var rockScore = 1;
        var paperScore = 2;
        var scissorsScore = 3;

        var lost = 0;
        var draw = 3;
        var win = 6;

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

        var calories = loadFileData("calories.txt");

        int actualSum = 0;
        int maxValue = 0;

        var totalCaloriesOfEachElf = new ArrayList<String>();

        for (var actualCalories : calories) {

            if (actualCalories.equals("")) {

                if (actualSum > maxValue) {
                    maxValue = actualSum;

                    totalCaloriesOfEachElf.add(String.valueOf(maxValue));
                }

                actualSum = 0;
            } else
                actualSum += Integer.parseInt(actualCalories);
        }

        var arraySize = totalCaloriesOfEachElf.size() - 1;

        var totalCaloriesOfTop3 = Integer.parseInt(totalCaloriesOfEachElf.get(arraySize)) +
                Integer.parseInt(totalCaloriesOfEachElf.get(arraySize - 1)) +
                Integer.parseInt(totalCaloriesOfEachElf.get(arraySize - 2));

        printChallengeResults(1, maxValue, totalCaloriesOfTop3);
    }

    private static void printChallengeResults(int challengeNumber, int challenge1, int challenge2) {
        System.out.println("\nReto #" + challengeNumber + " Completado");
        System.out.println("Respuesta Parte 1: " + challenge1 + "\nRespuesta Parte 2: " + challenge2);
    }
}
