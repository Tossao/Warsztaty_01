package src.zadanie2;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Zad2_Lottery {

    static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        lotto();
        scanner.close();
    }

    static void lotto() {
        Random random = new Random();
        int[] winNumbers = new int[6];
        for (int i = 0; i < 6; i++) {
            int number = random.nextInt(49) + 1;
            winNumbers[i] = number;
        }
        Arrays.sort(winNumbers);

        for (int i = 0; i < 5; i++) {
            while (true) {
                if (winNumbers[i] == winNumbers[i + 1]) {
                    int number = random.nextInt(49) + 1;
                    winNumbers[i] = number;
                    Arrays.sort(winNumbers);
                    i = 0;
                } else {
                    break;
                }
            }
        }

        int[] choice = getNumbers();
        int counter = 0;
        System.out.println("Maszyna losująca wylosowała liczby: " + Arrays.toString(winNumbers));
        for (int i = 0; i < 6; i++) {
            if (winNumbers[i] == choice[i]) {
                counter++;
            }
        }
        if (counter == 3) {
            System.out.println("Trafiłeś trójke!!!");
        } else if (counter == 4) {
            System.out.println("Trafiles czwórke!!!!");
        } else if (counter == 5) {
            System.out.println("Trafiles piątkę!!!!!");
        } else if (counter == 6) {
            System.out.println("Trafiłeś szóstkę!!!!!!");
        } else {
            System.out.println("Brak wygranej :(  Graj dalej!!!");
        }
    }

    static int[] getNumbers() {
        System.out.println("Podaj swoje szczęsliwe liczby: ");
        int[] luckyNumbers = new int[6];
        int count;
        for (int i = 0; i < 6; i++) {
            int n;
            while (true) {
                while (!scanner.hasNextInt()) {
                    System.out.println("To nie jest liczba!!!");
                    scanner.next();
                }
                n = scanner.nextInt();
                count = 0;
                for (int j = 0; j < i; j++) {
                    if (n == luckyNumbers[j]) {
                        count++;
                    }
                }
                if ((n < 1) || (n > 49)) {
                    System.out.println("Liczby z poza zakresu!!!");
                    scanner.nextLine();
                } else if (count > 0) {
                    System.out.println("Ta liczba juz zostala podana!!! Podaj inna liczbę...");
                    scanner.nextLine();
                } else {
                    luckyNumbers[i] = n;
                    break;
                }
            }
            System.out.println("Podałes: " + n);
            scanner.nextLine();
        }
        Arrays.sort(luckyNumbers);
        System.out.println("Twoje liczby to: " + Arrays.toString(luckyNumbers));
        return luckyNumbers;
    }
}

