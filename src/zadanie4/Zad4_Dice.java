package src.zadanie4;

import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

// NIE SKONCZONE !!!
// NIE SKONCZONE !!!
// NIE SKONCZONE !!!
public class Zad4_Dice {

    static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        dice(input());
        scanner.close();
    }

    static int dice(String str) {
        int x = 1;
        int y;
        int z = 0;

        StringBuilder builder = new StringBuilder();
        if (times(str) == 0) {
            String strDice = str.substring(1);
            builder.append(strDice);
            if (mod(strDice) == 10) {
                StringTokenizer tokenizer = new StringTokenizer(strDice, "+");
                String str1 = tokenizer.nextToken();
                String str2 = tokenizer.nextToken();
                y = diceType(str1);
                z = modifier(str2);
            } else if (mod(strDice) == 20) {
                StringTokenizer tokenizer = new StringTokenizer(strDice, "-");
                String str1 = tokenizer.nextToken();
                String str2 = tokenizer.nextToken();
                y = diceType(str1);
                z = modifier(str2);
            } else {
                y = diceType(strDice);
            }

        } else if (times(str) == 1) {
            char charX = str.charAt(0);
            x = modifier(Character.toString(charX));

            String strDice = str.substring(2);
            builder.append(strDice);
            if (mod(strDice) == 10) {
                StringTokenizer tokenizer = new StringTokenizer(strDice, "+");
                String str1 = tokenizer.nextToken();
                String str2 = tokenizer.nextToken();
                y = diceType(str1);
                z = modifier(str2);
            } else if (mod(strDice) == 20) {
                StringTokenizer tokenizer = new StringTokenizer(strDice, "-");
                String str1 = tokenizer.nextToken();
                String str2 = tokenizer.nextToken();
                y = diceType(str1);
                z = modifier(str2);
            } else {
                y = diceType(strDice);
            }
        } else {
            char x1 = str.charAt(0);
            char x2 = str.charAt(1);
            String xPos = Character.toString(x1) + x2;
            x = modifier(xPos);

            String strDice = str.substring(3);
            builder.append(strDice);
            if (mod(strDice) == 10) {
                StringTokenizer tokenizer = new StringTokenizer(strDice, "+");
                String str1 = tokenizer.nextToken();
                String str2 = tokenizer.nextToken();
                y = diceType(str1);
                z = modifier(str2);
                System.out.println(z);
            } else if (mod(strDice) == 20) {
                StringTokenizer tokenizer = new StringTokenizer(strDice, "-");
                String str1 = tokenizer.nextToken();
                String str2 = tokenizer.nextToken();
                y = diceType(str1);
                z = modifier(str2);
            } else {
                y = diceType(strDice);
            }
        }
        System.out.println("Liczba rzutów: " + x + " Rodzaj kostek: D" + y + " Mod: " + z);
        Random random = new Random();
        int roll = 0;
        for (int i = 0; i < x; i++) {
            int d = random.nextInt(y) + 1;
            roll = roll + d;
        }
        int result;
        String strDice = builder.toString();
        if (mod(strDice) == 10) {
            result = roll + z;
        } else if (mod(strDice) == 20) {
            result = roll - z;
        } else {
            result = roll;
        }
        System.out.println(result);
        return result;
    }

    static String input() {
        System.out.println("Wprowadz: xDy +/- z," + "\n" + "gdzie x - ilosc rzutow, y - rodzaj kostki, z - (opcjonalnie) liczba ktora mozna dodac(lub odjac) do wyniku rzutow");
        StringBuilder temp = new StringBuilder();
        while (true) {
            String txt = scanner.nextLine();
            if (times(txt) < 3) {
                temp.append(txt);
                break;
            }
            System.out.println("Błąd!!! Wprowadz jeszcze raz: ");
        }
        String str = temp.toString();
        return str;
    }

    static int mod(String inCode) {
        int sign = 0;
        for (int i = 0; i < inCode.length() - 1; i++) {
            try {
                char charAtI = inCode.charAt(i);
                @SuppressWarnings("Nie uzywane")
                int temp = Integer.parseInt(Character.toString(charAtI));
                sign++;
            } catch (NumberFormatException ex) {
                break;
            }
        }
        if (sign + 1 < inCode.length()) {
            if (inCode.charAt(sign) == '+') {
                return 10;
            } else if (inCode.charAt(sign) == '-') {
                return 20;
            } else {
                return 30;
            }
        } else {
            return 30;
        }
    }

    static int times(String inCode) {
        int dDice = -1;
        for (int i = 0; i < inCode.length() - 1; i++) {
            if ((inCode.charAt(i) == 'D') || (inCode.charAt(i) == 'd')) {
                dDice = i;
                break;
            }
        }
        return dDice;
    }

    static int modifier(String str) {
        int num = 1;
        try {
            num = Integer.parseInt(str);
        } catch (NumberFormatException ex) {
            System.out.println("Źle wprowadzono dane! Wprowadz jeszcze raz:");
            dice(input());
        }
        return num;
    }

    static int diceType(String str) {
        int num = 1;
        try {
            num = Integer.parseInt(str);
            if ((num == 3) || (num == 4) || (num == 6) || (num == 8) || (num == 10) || (num == 12) || (num == 20) || (num == 100)) {

            } else {
                System.out.println("Zły rodzaj kostki!!! Wprowadz jeszcze raz: ");
                dice(input());
            }
        } catch (NumberFormatException ex) {
            System.out.println("Błąd");
            dice(input());
        }
        return num;
    }
}
