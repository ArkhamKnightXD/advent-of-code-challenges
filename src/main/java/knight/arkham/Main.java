package knight.arkham;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        chooseChallenge();
    }

    private static void chooseChallenge() {
        String userChoice;

        do {
            var scanner = new Scanner(System.in);

            System.out.println("\n Seleccione una de las siguientes opciones.");
            System.out.println("Presione cualquier tecla para salir 0 para Salir.");
            System.out.println("Presione 1 para ejecutar reto del día 1.");
            System.out.println("Presione 2 para ejecutar reto del día 2.");
            System.out.println("Presione 3 para ejecutar reto del día 3.");
            System.out.println("Presione 4 para ejecutar reto del día 4.");
            System.out.println("Presione 5 para ejecutar reto del día 5.");
            System.out.println("Presione 6 para ejecutar reto del día 6.");
            System.out.println("Presione 7 para ejecutar reto del día 7.");

            userChoice = scanner.nextLine();  // Read user input

            switch (userChoice) {

                case "1":
                    Challenge.caloriesCountingChallenge();
                    break;

                case "2":
                    Challenge.rockPaperAndScissorsChallenge();
                    break;

                case "3":
                    Challenge.backpackOrganizationChallenge();
                    break;

                case "4":
                    Challenge.campCleanupChallenge();
                    break;

                case "5":
                    Challenge.supplyStacksChallenge();
                    break;

                case "6":
                    Challenge.tuningTroubleChallenge();
                    break;

                case "7":
                    Challenge.noSpaceLeftChallenge();
                    break;

                default:
                    userChoice = "0";
            }
        } while (!userChoice.equals("0"));
    }

}