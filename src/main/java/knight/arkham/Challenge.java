package knight.arkham;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Challenge {

    private static Scanner loadFileData(String filename) {
        var file = new File(filename);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return scanner;
    }

    public static void campCleanupChallenge(){

        var cleaningSections = loadFileData("cleaning-pairs.txt");

        int counter = 0;

        while (cleaningSections.hasNextLine()){

            var sections = cleaningSections.nextLine();

            counter = calculateOverlapsSections(counter, sections);

        }

        System.out.println("Respuesta: "+ counter);

        cleaningSections.close();
    }

    private static int calculateOverlapsSections(int counter, String sections) {
        var elements = sections.split(",");

        var element1 = elements[0];
        var simpleElement1 = element1.split("-")[0];
        var simpleElement2 = element1.split("-")[1];

        var value1 = Integer.parseInt(simpleElement1);
        var value2 = Integer.parseInt(simpleElement2);

        var element2 = elements[1];
        var simpleElementV2 = element2.split("-")[0];
        var simpleElementV2Part2 = element2.split("-")[1];

        var value3 = Integer.parseInt(simpleElementV2);
        var value4 = Integer.parseInt(simpleElementV2Part2);

        if (value1 <= value3 && value2 >= value4 || value1 >= value3 && value2 <= value4)
            counter++;
        return counter;
    }


    public static void backpackOrganizationChallenge() {

        var backpacksContent = loadFileData("backpacks.txt");

        int totalSumOfItemsPriority = 0;
        int totalSumOfBadgePriority = 0;

        var itemsPriority = getItemsPriorityMap();

        var actualElfGroup = new ArrayList<String>();

        while (backpacksContent.hasNextLine()){

            var content = backpacksContent.nextLine();

            actualElfGroup.add(content);

            if (actualElfGroup.size() == 3){

                totalSumOfBadgePriority += getPriorityBadgeSum(actualElfGroup, itemsPriority);

                actualElfGroup.clear();
            }

            totalSumOfItemsPriority += getPriorityItemSum(content, itemsPriority);
        }

        backpacksContent.close();

        printChallengeResults(3, totalSumOfItemsPriority, totalSumOfBadgePriority);
    }

    private static int getPriorityBadgeSum(List<String> elfGroup, HashMap<String, Integer> itemsPriority){

        var backpackGroupMember1 = elfGroup.get(0);
        var backpackGroupMember2 = elfGroup.get(1);
        var backpackGroupMember3 = elfGroup.get(2);

        var backpackItems1 = convertStringToCharArray(backpackGroupMember1);
        var backpackItems2 = convertStringToCharArray(backpackGroupMember2);
        var backpackItems3 = convertStringToCharArray(backpackGroupMember3);

        int itemRepeatCounter = 0;
        int sumOfBadgePriority = 0;

        for (char content1 :backpackItems1) {

            for (char content2 :backpackItems2) {

                for (char content3 :backpackItems3) {

                    if ((content1 == content2 && content2 == content3) && itemRepeatCounter == 0){

                        var itemPriorityValue = itemsPriority.get(String.valueOf(content1));

                        sumOfBadgePriority += itemPriorityValue;
                        itemRepeatCounter++;
                    }
                }
            }
        }

        return sumOfBadgePriority;
    }

    private static int getPriorityItemSum(String backpackContent, HashMap<String, Integer> itemsPriority) {

        var firstCompartment = backpackContent.substring(0, backpackContent.length() / 2);
        var secondCompartment = backpackContent.substring(backpackContent.length() / 2);

        var firstCompartmentItems = convertStringToCharArray(firstCompartment);
        var secondCompartmentItems = convertStringToCharArray(secondCompartment);

        int itemRepeatCounter = 0;
        int sumOfItemsPriority = 0;

        for (char firstCompartmentItem : firstCompartmentItems) {

            for (char secondCompartmentItem : secondCompartmentItems) {

                if (firstCompartmentItem == secondCompartmentItem && itemRepeatCounter == 0) {

                    var itemPriorityValue = itemsPriority.get(String.valueOf(firstCompartmentItem));

                    sumOfItemsPriority += itemPriorityValue;
                    itemRepeatCounter++;
                }
            }
        }

        return sumOfItemsPriority;
    }

    private static char[] convertStringToCharArray(String backpackContent) {

        var backpackItems = new char[backpackContent.length()];

        for (int i = 0; i < backpackContent.length(); i++)
            backpackItems[i] = backpackContent.charAt(i);

        return backpackItems;
    }

    // To help prioritize item rearrangement, every item type can be converted to a priority:
//    Lowercase item types a through z have priorities 1 through 26.
//    Uppercase item types A through Z have priorities 27 through 52.
    private static HashMap<String, Integer> getItemsPriorityMap() {

        var itemsPriorityMap = new HashMap<String, Integer>();

        int priorityValue = 1;

        for (var letter = 'a'; letter <= 'z'; letter++){

            itemsPriorityMap.put(String.valueOf(letter), priorityValue);
            priorityValue++;
        }

        for (var letter = 'A'; letter <= 'Z'; letter++){

            itemsPriorityMap.put(String.valueOf(letter), priorityValue);
            priorityValue++;
        }

        return itemsPriorityMap;
    }


    public static void rockPaperAndScissorsChallenge() {

        var tournamentRoundValues = loadFileData("tournament.txt");

        int totalScorePart1 = 0;
        int totalScorePart2 = 0;

        while (tournamentRoundValues.hasNextLine()){

            var roundValue = tournamentRoundValues.nextLine();

            totalScorePart1 = getTournamentTotalScore(totalScorePart1, roundValue, true);
            totalScorePart2 = getTournamentTotalScore(totalScorePart2, roundValue, false);
        }

        tournamentRoundValues.close();

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

        while (calories.hasNextLine()){

            var actualCalories = calories.nextLine();

            if (actualCalories.equals("")) {

                if (actualSum > maxValue) {
                    maxValue = actualSum;

                    totalCaloriesOfEachElf.add(String.valueOf(maxValue));
                }

                actualSum = 0;
            } else
                actualSum += Integer.parseInt(actualCalories);
        }

        calories.close();

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
