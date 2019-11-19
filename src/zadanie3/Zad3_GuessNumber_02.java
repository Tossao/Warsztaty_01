package src.zadanie3;

import java.util.Scanner;

public class Zad3_GuessNumber_02 {

    static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        guessNumber();
        scanner.close();
    }

    static void guessNumber() {
        System.out.println("Pomyśl liczbę od 0 do 1000 a ja ją zgadnę w max. 10 próbach");
        int min = 0;
        int max = 1000;

        while (true) {
            int guess = ((max - min) / 2) + min;
            System.out.println("Zgaduję: " + guess);
            String userGuess = userGuessing();
            if (userGuess.equals("za duzo")) {
                max = guess;
            } else if (userGuess.equals("za malo")) {
                min = guess;
            } else if (userGuess.equals("zgadles")) {
                System.out.println("Wygrałem!!! ");
                break;
            } else {
                System.out.println("Nie oszukuj!!!");
            }
        }

    }

    static String userGuessing() {
        System.out.println("Podaj odpowiedź: za duzo / za malo / zgadles");
        String str = scanner.nextLine();
        return str;
    }
}
