package src.zadanie1;
/*
Gra w zgadywanie liczb
Napisz prostą grę w zgadywanie liczb. Komputer ma wylosować liczbę w zakresie od 1 do 100. Następnie:
wypisać: "Zgadnij liczbę" i pobrać liczbę z klawiatury;
sprawdzić, czy wprowadzony napis, to rzeczywiście liczba i w razie błędu wyświetlić komunikat:
 "To nie jest liczba", po czym wrócić do pkt. 1;
jeśli liczba podana przez użytkownika jest mniejsza niż wylosowana, wyświetlić komunikat:
"Za mało!", po czym wrócić do pkt. 1;
jeśli liczba podana przez użytkownika jest większa niż wylosowana, wyświetlić komunikat:
"Za dużo!", po czym wrócić do pkt. 1;
jeśli liczba podana przez użytkownika jest równa wylosowanej, wyświetlić komunikat:
"Zgadłeś!", po czym zakończyć działanie programu.
Gra w zgadywanie liczb
Przykład:
Zgadnij liczbę: cześć
To nie jest liczba.
Zgadnij liczbę: 50
Za mało!
Zgadnij liczbę: 75
Za dużo!
Zgadnij liczbę: 63
Zgadłeś!
-----------------------
Dwie petle while,
jedna powinna sprawdzac czy wprowadzony numer jest zgodny z wylosowanym
Druga powinna zawierac logike pobierajaca i przeksztalcajaca wprowadzone dane na liczbe z obsluga niestandardowych
sytulacji
Jesli uzyjeszcz do while to lapiac wyjatek piszesz continue

 */

import java.util.Random;
import java.util.Scanner;

public class Zad1_GuessNumber {
    public static void main(String[] args) throws NumberFormatException {
        guessNumber();
    }

    static void guessNumber() throws NumberFormatException {

        Random random = new Random();
        int number = random.nextInt(100);
        System.out.println(number);
        System.out.println("Zgaduj liczbe:");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            while (!scanner.hasNextInt()) {
                scanner.next();
                System.out.println("To nie jest liczba! Zgaduj dalej...");
            }
            int guess = scanner.nextInt();
            if (number > guess) {
                System.out.println("Zle, liczba za mala! Zgaduj dalej...");

            } else if (number < guess) {
                System.out.println("Zle, liczba za duza! Zgaduj dalej...");

            } else {
                System.out.println("Brawo zgadles! szukana liczba to: " + number);
                break;
            }
        }

    }

}