package knight.arkham;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

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

    public static void tuningTroubleChallenge(){

        var signal = loadFileData("communication.txt");

        var communication = convertStringToCharArray(signal.nextLine());

        int repetitionCounter = 0;

        int firstPacketMarker = 0;

        var set = new HashSet<String>();

        for (char initialLetter : communication) {

            repetitionCounter = 0;

            for (char letter : communication) {

                repetitionCounter++;

                if (initialLetter != letter && repetitionCounter == 3){


                    System.out.println("Test");
                }
            }
        }

//        Todo comparar el elemento con cada caracter delante de el y si el
//         elemento es diferente 3 veces entonces el index del sigte caracter es el marker
//        System.out.println("Set: " + set);


//        for (int i = 0; i < communication.length-3; i++) {
//
//            var firstElement = (communication[i] != communication[i+1] && communication[i] != communication[i+2] && communication[i] != communication[i+3]);
//            var secondElement = (communication[i+1] != communication[i+2] && communication[i+1] != communication[i+3]);
//            var thirdElement = (communication[i+2] != communication[i+3]);
//
//            if (firstElement && secondElement && thirdElement && repetitionCounter == 0){
//
//                firstPacketMarker = i+4;
//                repetitionCounter++;
//            }
//        }
//
//        System.out.println("Respuesta: " + firstPacketMarker);
    }

    public static void supplyStacksChallenge() {

        var cargoInstructions = loadFileData("supply-stacks.txt");

        var initialValuesStack1 = new ArrayList<String>();
        var initialValuesStack2 = new ArrayList<String>();
        var initialValuesStack3 = new ArrayList<String>();
        var initialValuesStack4 = new ArrayList<String>();
        var initialValuesStack5 = new ArrayList<String>();
        var initialValuesStack6 = new ArrayList<String>();
        var initialValuesStack7 = new ArrayList<String>();
        var initialValuesStack8 = new ArrayList<String>();
        var initialValuesStack9 = new ArrayList<String>();

        int lineCounter = 0;

        while (cargoInstructions.hasNextLine() && lineCounter < 8) {

            lineCounter++;

            var instruction = cargoInstructions.nextLine();

//            Hay 4 char entre cada letra del stack
            if (!String.valueOf(instruction.charAt(1)).trim().isEmpty())
                initialValuesStack1.add(String.valueOf(instruction.charAt(1)));

            if (!String.valueOf(instruction.charAt(5)).trim().isEmpty())
                initialValuesStack2.add(String.valueOf(instruction.charAt(5)));

            if (!String.valueOf(instruction.charAt(9)).trim().isEmpty())
                initialValuesStack3.add(String.valueOf(instruction.charAt(9)));

            if (!String.valueOf(instruction.charAt(13)).trim().isEmpty())
                initialValuesStack4.add(String.valueOf(instruction.charAt(13)));

            if (!String.valueOf(instruction.charAt(17)).trim().isEmpty())
                initialValuesStack5.add(String.valueOf(instruction.charAt(17)));

            if (!String.valueOf(instruction.charAt(21)).trim().isEmpty())
                initialValuesStack6.add(String.valueOf(instruction.charAt(21)));

            if (!String.valueOf(instruction.charAt(25)).trim().isEmpty())
                initialValuesStack7.add(String.valueOf(instruction.charAt(25)));

            if (!String.valueOf(instruction.charAt(29)).trim().isEmpty())
                initialValuesStack8.add(String.valueOf(instruction.charAt(29)));

            var finalCargoElement = String.valueOf(instruction.charAt(instruction.length() - 2));

            if (!finalCargoElement.trim().isEmpty())
                initialValuesStack9.add(finalCargoElement);
        }

        var stack1 = new Stack<String>();
        var stack2 = new Stack<String>();
        var stack3 = new Stack<String>();
        var stack4 = new Stack<String>();
        var stack5 = new Stack<String>();
        var stack6 = new Stack<String>();
        var stack7 = new Stack<String>();
        var stack8 = new Stack<String>();
        var stack9 = new Stack<String>();

        fillTheStackFromAnList(stack1, initialValuesStack1);
        fillTheStackFromAnList(stack2, initialValuesStack2);
        fillTheStackFromAnList(stack3, initialValuesStack3);
        fillTheStackFromAnList(stack4, initialValuesStack4);
        fillTheStackFromAnList(stack5, initialValuesStack5);
        fillTheStackFromAnList(stack6, initialValuesStack6);
        fillTheStackFromAnList(stack7, initialValuesStack7);
        fillTheStackFromAnList(stack8, initialValuesStack8);
        fillTheStackFromAnList(stack9, initialValuesStack9);

        var cargoStackMap = new HashMap<String, Stack<String>>();

        cargoStackMap.put("1", stack1);
        cargoStackMap.put("2", stack2);
        cargoStackMap.put("3", stack3);
        cargoStackMap.put("4", stack4);
        cargoStackMap.put("5", stack5);
        cargoStackMap.put("6", stack6);
        cargoStackMap.put("7", stack7);
        cargoStackMap.put("8", stack8);
        cargoStackMap.put("9", stack9);

        while (cargoInstructions.hasNextLine()) {

            lineCounter++;

            var instruction = cargoInstructions.nextLine();

            if (lineCounter > 10)
                rearrangeCargoStacks(cargoStackMap, instruction, false);
        }

        var answerChallenge1 = new ArrayList<String>();

        for (var actualStack :cargoStackMap.values())
            answerChallenge1.add(actualStack.peek());

        cargoInstructions.close();

//        Para obtener la respuesta del challenge2 debo de cambiar el boolean de false a true.
        printChallengeResults(5, answerChallenge1, initialValuesStack1);
    }

    private static void rearrangeCargoStacks(HashMap<String, Stack<String>> cargoStackMap, String instruction, boolean isPart2) {

        var instructionLength = instruction.length();

        var destinationKey = String.valueOf(instruction.charAt(instructionLength-1));

        if (instructionLength == 18){

            int elementsToMove = Integer.parseInt(String.valueOf(instruction.charAt(5)));

            var sourceKey = String.valueOf(instruction.charAt(12));

            var sourceStack = cargoStackMap.get(sourceKey);
            var destinationStack = cargoStackMap.get(destinationKey);

            moveElementsBetweenStacks(elementsToMove, sourceStack, destinationStack, isPart2);
        }

        else{

            int elementsToMove = Integer.parseInt(String.valueOf(instruction.charAt(5)) + instruction.charAt(6));

            var sourceKey = String.valueOf(instruction.charAt(13));

            var sourceStack = cargoStackMap.get(sourceKey);
            var destinationStack = cargoStackMap.get(destinationKey);

            moveElementsBetweenStacks(elementsToMove, sourceStack, destinationStack, isPart2);
        }
    }

    private static void fillTheStackFromAnList(Stack<String> stack, ArrayList<String> initialStackValues) {

        Collections.reverse(initialStackValues);

        for (var element : initialStackValues)
            stack.push(element);

        initialStackValues.clear();
    }

    private static void moveElementsBetweenStacks(int elementsToMove, Stack<String> source, Stack<String> destination, boolean isPart2) {

        if (elementsToMove == 1) {

            var elementToMove = source.pop();

            destination.push(elementToMove);
        } else {

            var temporalList = new ArrayList<String>();

            while (temporalList.size() < elementsToMove){

                var element = source.pop();
                temporalList.add(element);
            }

            if (isPart2)
                Collections.reverse(temporalList);

            for (var element : temporalList)
                destination.push(element);
        }
    }

    public static void campCleanupChallenge() {

        var cleaningSections = loadFileData("cleaning-pairs.txt");

        int part1Counter = 0;
        int part2Counter = 0;

        while (cleaningSections.hasNextLine()) {

            var sectionPairs = cleaningSections.nextLine().split(",");

            var sectionPair1 = sectionPairs[0];
            int minValueSection1 = Integer.parseInt(sectionPair1.split("-")[0]);
            int maxValueSection1 = Integer.parseInt(sectionPair1.split("-")[1]);

            var sectionPair2 = sectionPairs[1];
            int minValueSection2 = Integer.parseInt(sectionPair2.split("-")[0]);
            int maxValueSection2 = Integer.parseInt(sectionPair2.split("-")[1]);

            boolean isSection1OverlappingSection2 = (minValueSection1 <= minValueSection2 && maxValueSection1 >= maxValueSection2);
            boolean isSection2OverlappingSection1 = (minValueSection1 >= minValueSection2 && maxValueSection1 <= maxValueSection2);
            boolean isPartialOverlapping = (minValueSection1 <= maxValueSection2 && maxValueSection1 >= minValueSection2);

            if (isSection1OverlappingSection2 || isSection2OverlappingSection1)
                part1Counter++;

            if (isSection1OverlappingSection2 || isSection2OverlappingSection1 || isPartialOverlapping)
                part2Counter++;
        }

        cleaningSections.close();

        printChallengeResults(4, part1Counter, part2Counter);
    }

    public static void backpackOrganizationChallenge() {

        var backpacksContent = loadFileData("backpacks.txt");

        int totalSumOfItemsPriority = 0;
        int totalSumOfBadgePriority = 0;

        var itemsPriority = getItemsPriorityMap();

        var actualElfGroup = new ArrayList<String>();

        while (backpacksContent.hasNextLine()) {

            var content = backpacksContent.nextLine();

            actualElfGroup.add(content);

            if (actualElfGroup.size() == 3) {

                totalSumOfBadgePriority += getPriorityBadgeSum(actualElfGroup, itemsPriority);

                actualElfGroup.clear();
            }

            totalSumOfItemsPriority += getPriorityItemSum(content, itemsPriority);
        }

        backpacksContent.close();

        printChallengeResults(3, totalSumOfItemsPriority, totalSumOfBadgePriority);
    }

    private static int getPriorityBadgeSum(List<String> elfGroup, HashMap<String, Integer> itemsPriority) {

        var backpackGroupMember1 = elfGroup.get(0);
        var backpackGroupMember2 = elfGroup.get(1);
        var backpackGroupMember3 = elfGroup.get(2);

        var backpackItems1 = convertStringToCharArray(backpackGroupMember1);
        var backpackItems2 = convertStringToCharArray(backpackGroupMember2);
        var backpackItems3 = convertStringToCharArray(backpackGroupMember3);

        int itemRepeatCounter = 0;
        int sumOfBadgePriority = 0;

        for (char content1 : backpackItems1) {

            for (char content2 : backpackItems2) {

                for (char content3 : backpackItems3) {

                    if ((content1 == content2 && content2 == content3) && itemRepeatCounter == 0) {

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

        for (var letter = 'a'; letter <= 'z'; letter++) {

            itemsPriorityMap.put(String.valueOf(letter), priorityValue);
            priorityValue++;
        }

        for (var letter = 'A'; letter <= 'Z'; letter++) {

            itemsPriorityMap.put(String.valueOf(letter), priorityValue);
            priorityValue++;
        }

        return itemsPriorityMap;
    }


    public static void rockPaperAndScissorsChallenge() {

        var tournamentRoundValues = loadFileData("tournament.txt");

        int totalScorePart1 = 0;
        int totalScorePart2 = 0;

        while (tournamentRoundValues.hasNextLine()) {

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

        while (calories.hasNextLine()) {

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

    private static void printChallengeResults(int challengeNumber, List<String> challenge1, List<String> challenge2) {
        System.out.println("\nReto #" + challengeNumber + " Completado");
        System.out.println("Respuesta Parte 1: " + challenge1 + "\nRespuesta Parte 2: " + challenge2);
    }
}
