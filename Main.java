package bullscows;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static int codeSize;
    public static int numberOfSymbols;
    public static String createCode() {
        Random random = new Random();
        String pRD = "0123456789abcdefghijklmnopqrstuvwxyz";
        char[] pRDChar = pRD.toCharArray();
        StringBuilder code = new StringBuilder("");
        int i = numberOfSymbols -1;

        String stars = "";
        for (int j = 0; j < numberOfSymbols; j++) {
            stars = stars + "*";
        }
        String symbols = "";
        if (numberOfSymbols <= 10){
            symbols = 0 + "-" + pRDChar[numberOfSymbols - 1];
        } else {
            symbols = "0-9, " + pRDChar[10] + "-" + pRDChar[numberOfSymbols - 1];
        }
        while (code.length() < codeSize) {
            int rnd = random.nextInt(numberOfSymbols - 1);
            if (code.indexOf(Character.toString(pRD.charAt(rnd))) == -1) {
                code.append(pRD.charAt(rnd));
            }
        }
        System.out.printf("The secret is prepared: %s (%s).%n" , stars, symbols);
        return  code.toString();
    }


    public static void main(String[] args) {
        System.out.println("Input the length of the secret code:");
        Scanner scanner = new Scanner(System.in);
        String userInput1 = scanner.next();
        String userInput2 = scanner.next();
        try {
            codeSize = Integer.parseInt(userInput1);
        } catch (Exception e) {
            System.out.printf("Error: %s isn't valid number.%n", userInput1);
            return;
        }

        System.out.println("Input the number of possible symbols in the code:");

        try {
            numberOfSymbols = Integer.parseInt(userInput2);
        } catch (Exception e) {
            System.out.printf("Error: %s isn't valid number.%n", userInput2);
            return;
        }

        if (codeSize > 36 || codeSize <= 0) {
            System.out.printf("Error: can't generate a secret number with a length of %d because there aren't enough unique digits.", codeSize);
            return;
        } else if (numberOfSymbols > 36) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            return;
        } else if (codeSize > numberOfSymbols) {
            System.out.printf("Error: it's not possible to generate a code with a length of %d with %d unique symbols.", codeSize, numberOfSymbols);
            return;
        }


        playGame();
    }

    public static void printResults(int bulls, int cows) {
        if (bulls > 0 && cows > 0) {
            System.out.print("Grade: " + bulls + " bulls(s)" + cows + " cow(s).");
            return;
        }
        if (bulls > 0) {
            System.out.print("Grade: " + bulls + " bulls(s).");
            return;
        }
        if (cows > 0) {
            System.out.print("Grade: " + cows + " cow(s).");
            return;
        }
        System.out.print("Grade: None.");
    }

    public static void playGame() {
        int cows = 0;
        int bulls = 0;
        int turns = 1;
        String[] codeParts = createCode().split("");
        System.out.println("Okay, let's start a game!");
        while (bulls < codeSize) {
            System.out.printf("Turn %d:%n", turns);
            cows = 0;
            bulls = 0;
            turns++;
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            String[] inputParts = input.split("");


            for (int codeI = 0; codeI < codeParts.length; codeI++) {
                for (int inputI = 0; inputI < inputParts.length; inputI++) {
                    if (codeParts[codeI].equals(inputParts[inputI])) {
                        if (codeI == inputI) {
                            bulls++;
                        } else {
                            cows++;
                        }
                    }
                }
            }
            printResults(bulls, cows);
        }
        System.out.println("Congratulations! You guessed the secret code.");
    }

}