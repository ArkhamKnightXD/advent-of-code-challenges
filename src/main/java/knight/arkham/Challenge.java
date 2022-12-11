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

    public static void supplyStacksChallenge() {

        var cargoInstructions = loadFileData("supply-stacks.txt");

        var stack1 = new Stack<String>();
        var stack2 = new Stack<String>();
        var stack3 = new Stack<String>();
        var stack4 = new Stack<String>();
        var stack5 = new Stack<String>();
        var stack6 = new Stack<String>();
        var stack7 = new Stack<String>();
        var stack8 = new Stack<String>();
        var stack9 = new Stack<String>();

        var list1 = new ArrayList<String>();
        var list2 = new ArrayList<String>();
        var list3 = new ArrayList<String>();
        var list4 = new ArrayList<String>();
        var list5 = new ArrayList<String>();
        var list6 = new ArrayList<String>();
        var list7 = new ArrayList<String>();
        var list8 = new ArrayList<String>();
        var list9 = new ArrayList<String>();

        int lineCounter = 0;

        while (cargoInstructions.hasNextLine() && lineCounter < 8) {

            lineCounter++;

            var instruction = cargoInstructions.nextLine();

//            Hay 4 es char entre cada letra del stack
            if (!String.valueOf(instruction.charAt(1)).trim().isEmpty())
                list1.add(String.valueOf(instruction.charAt(1)));

            if (!String.valueOf(instruction.charAt(5)).trim().isEmpty())
                list2.add(String.valueOf(instruction.charAt(5)));

            if (!String.valueOf(instruction.charAt(9)).trim().isEmpty())
                list3.add(String.valueOf(instruction.charAt(9)));

            if (!String.valueOf(instruction.charAt(13)).trim().isEmpty())
                list4.add(String.valueOf(instruction.charAt(13)));

            if (!String.valueOf(instruction.charAt(17)).trim().isEmpty())
                list5.add(String.valueOf(instruction.charAt(17)));

            if (!String.valueOf(instruction.charAt(21)).trim().isEmpty())
                list6.add(String.valueOf(instruction.charAt(21)));

            if (!String.valueOf(instruction.charAt(25)).trim().isEmpty())
                list7.add(String.valueOf(instruction.charAt(25)));

            if (!String.valueOf(instruction.charAt(29)).trim().isEmpty())
                list8.add(String.valueOf(instruction.charAt(29)));

            var finalCargoElement = String.valueOf(instruction.charAt(instruction.length() - 2));

            if (!finalCargoElement.trim().isEmpty())
                list9.add(finalCargoElement);
        }

        fillTheStackFromAnList(stack1, list1);
        fillTheStackFromAnList(stack2, list2);
        fillTheStackFromAnList(stack3, list3);
        fillTheStackFromAnList(stack4, list4);
        fillTheStackFromAnList(stack5, list5);
        fillTheStackFromAnList(stack6, list6);
        fillTheStackFromAnList(stack7, list7);
        fillTheStackFromAnList(stack8, list8);
        fillTheStackFromAnList(stack9, list9);

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

        System.out.println("Before: " + cargoStackMap.values());

        while (cargoInstructions.hasNextLine()) {

            lineCounter++;

            var instruction = cargoInstructions.nextLine();

            if (lineCounter > 10){

                System.out.println("Data: " + instruction);

                var instructionLength = instruction.length();

                var destinationKey = String.valueOf(instruction.charAt(instruction.length()-1));

                if (instructionLength == 18){

                    int elementsToMove = Integer.parseInt(String.valueOf(instruction.charAt(5)));

                    var sourceKey = String.valueOf(instruction.charAt(12));

                    var sourceStack = cargoStackMap.get(sourceKey);
                    var destinationStack = cargoStackMap.get(destinationKey);

                    moveElementsBetweenStacks(elementsToMove, sourceStack, destinationStack);
                }

                else{

                    int elementsToMove = Integer.parseInt(String.valueOf(instruction.charAt(5)) + instruction.charAt(6));

                    System.out.println("elements To Move: " + elementsToMove);

                    var sourceKey = String.valueOf(instruction.charAt(13));

                    System.out.println("source Key: " + sourceKey);
                    System.out.println("destination Key: " + destinationKey);

                    var sourceStack = cargoStackMap.get(sourceKey);
                    var destinationStack = cargoStackMap.get(destinationKey);

                    moveElementsBetweenStacks(elementsToMove, sourceStack, destinationStack);
                }
            }
        }

        System.out.println("after: " + cargoStackMap.values());


        for (var actualStack :cargoStackMap.values())
            System.out.println(actualStack.peek());
    }

    private static void fillTheStackFromAnList(Stack<String> stack, ArrayList<String> list) {
        Collections.reverse(list);

        for (var element : list)
            stack.push(element);
    }

    private static void moveElementsBetweenStacks(int elementsToMove, Stack<String> source, Stack<String> destination) {

        if (elementsToMove == 1) {

            var elementToMove = source.pop();

            destination.push(elementToMove);
        } else {

            var auxList = new ArrayList<String>();

            var stackElements = source.iterator();

            while (stackElements.hasNext() && auxList.size() < elementsToMove)
                auxList.add(stackElements.next());

//        Revierto el orden los elementos de la lista debido a que el orden no es correcto para el stack
            Collections.reverse(auxList);

            for (var element : auxList) {

                destination.push(element);
                source.pop();
            }
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
}
