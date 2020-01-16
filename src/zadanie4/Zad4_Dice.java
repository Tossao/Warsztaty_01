package src.zadanie4;

import org.jsoup.internal.StringUtil;

import java.util.Random;
import java.util.Scanner;

public class Zad4_Dice {
    public static void main(String[] args) {

        int result = 0;
        int numberOfRolls = 0;
        int dice = 0;
        int mathOperation = 0;
        char mark = ' ';
        boolean loop = true;

        while (loop) {
            numberOfRolls = 1;
            dice = 0;
            mathOperation = 0;
            mark = ' ';
            loop = false;
            String command = "";
            System.out.println("Rzuć kością używając schematu xDy+z \n"
                    + "gdzie: \n"
                    + "y – rodzaj kostek, których należy użyć (np. D6, D10), \n"
                    + "x – liczba rzutów kośćmi (jeśli rzucamy raz, ten parametr jest pomijalny), \n"
                    + "z – (opcjonalnie) liczba, którą należy dodać (lub odjąć) do wyniku rzutów.");
            System.out.println("Wybierz kości: D3, D4, D6, D8, D10, D12, D20, D100");
            Scanner scanner = new Scanner(System.in);
            command = scanner.nextLine();
            String[] partFirst = command.split("D");
            if (!command.contains("D")) {
                System.out.println("Źle wpisana komenda! Brakuje 'D'");
                loop = true;
            } else if (partFirst.length != 2) {
                System.out.println("Źle wpisana komenda!");
                loop = true;
            } else if (!partFirst[0].equals("")) {
                try {
                    numberOfRolls = Integer.parseInt(partFirst[0]);
                } catch (NumberFormatException ex) {
                    System.out.println("Przed 'D' musi być liczba !");
                    loop = true;
                }
            }
            if (numberOfRolls < 1) {
                System.out.println("Przynajmniej raz trzeba rzucic kostka!");
                loop = true;
            }
            if (!loop) {
                if (partFirst[1].contains("+")) {
                    String[] partSecond = partFirst[1].split("\\+");
                    mark = '+';
                    try {
                        dice = Integer.parseInt(partSecond[0]);
                    } catch (NumberFormatException e) {
                        System.out.println("Żle wpisany rodzaj kostki! Popraw błąd");
                        loop = true;
                    }
                    try {
                        mathOperation = Integer.parseInt(partSecond[1]);
                    } catch (NumberFormatException e) {
                        System.out.println("Źle wpisana (opcjonalna) liczba! Popraw Błąd");
                        loop = true;
                    }
                } else if (partFirst[1].contains("-")) {
                    String[] partSecond = partFirst[1].split("-");
                    mark = '-';
                    try {
                        dice = Integer.parseInt(partSecond[0]);
                    } catch (NumberFormatException e) {
                        System.out.println("Żle wpisany rodzaj kostki! Popraw błąd ");
                        loop = true;
                    }
                    try {
                        mathOperation = Integer.parseInt(partSecond[1]);
                    } catch (NumberFormatException e) {
                        System.out.println("Źle wpisana(opcjonalna) liczba! Popraw błąd.");
                        loop = true;
                    }

                } else {
                    try {
                        dice = Integer.parseInt(partFirst[1]);
                    } catch (NumberFormatException e) {
                        System.out.println("Żle wpisany rodzaj kostki! Popraw błąd");
                        loop = true;
                    }
                }
                if (dice == 3 || dice == 4 || dice == 6 || dice == 8 || dice == 10 || dice == 12 || dice == 20 || dice == 100) {

                } else {
                    System.out.println("Zły rodzaj kostki!");
                    loop = true;
                }
            }
        }
        for (int i = 0; i < numberOfRolls; i++) {
            Random random = new Random();
            int number = random.nextInt(dice) + 1;
            result += number;
        }
        if (mark == '+') {
            result += mathOperation;
        } else if (mark == '-') {
            result -= mathOperation;
        }
        System.out.println("Ilość rzutów: " + numberOfRolls);
        System.out.println("Typ kostki: D" + dice);
        System.out.println("Opcjonalne plus/minus: " + mark + mathOperation);
        System.out.println();
        System.out.println("Wynik: " + result);
    }
}
