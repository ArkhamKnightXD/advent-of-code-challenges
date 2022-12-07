package knight.arkham;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Challenge {

    public static void caloriesChallenge() throws FileNotFoundException {

        File file = new File("calories.txt");
        Scanner scanner = new Scanner(file);

        List<String> calories = new ArrayList<>();

        while (scanner.hasNextLine())
            calories.add(scanner.nextLine());

        scanner.close();

        int actualSum = 0;
        int maxValue = 0;

        List<String> totalCaloriesOfEachElf = new ArrayList<>();

        for (String actualCalories : calories) {

            if (actualCalories.equals("")){

                if (actualSum > maxValue){
                    maxValue = actualSum;

                    totalCaloriesOfEachElf.add(String.valueOf(maxValue));
                }

                actualSum = 0;
            }

            else
                actualSum += Integer.parseInt(actualCalories);
        }

        int arraySize = totalCaloriesOfEachElf.size() - 1;

        int totalCaloriesOfTop3 = Integer.parseInt(totalCaloriesOfEachElf.get(arraySize)) +
                Integer.parseInt(totalCaloriesOfEachElf.get(arraySize-1)) +
                Integer.parseInt(totalCaloriesOfEachElf.get(arraySize-2));

        System.out.println("Respuesta Parte 1: " + maxValue + "\nRespuesta Parte 2: " + totalCaloriesOfTop3);
    }
}
