//package src.zadanie2;
//
//import java.util.ArrayList;
//import java.util.Random;
//import java.util.Scanner;
//
///*
//pierwsza metoda pobierze dane od uzytkownika
//6 liczb bez powtorzen
//scanner do wprowadzania liczb
//
//do while az tablica bedzie miala 6 elementow
//counter elementow ktory zwiekszamy tylko gdy dodamy element do tablicy
//spr czy wprowadzone dane to liczba
//spr czy liczba jest z danego zakresu && wprowadzone dane nie sa juz w tablicy
//jesli poprawne to dodaj do tablicy[counter]
//konczymy gdy counter jest 6
//
//sortujemy typy uzytkownika
//druga metoda to getRandomNumbers
//wykona to ze slajdu
//pobierze pierwszych 6 elementow z pomieszanej kolekcji i zwroci metody
//sortujemy wyniki
//mamy dwie petle - osobna metoda!! - ktore sprawdza czy trafilismy conajmniej trojke jesli trafilismy z numerem
//to do licznika trafien dodaj +1
// */
//public class zadanie2 {
//    public static void main(String[] args) {
//        Random random = new Random();
//        int minRand = 1 ;
//        int maxRand = 49;
//
//        ArrayList<String> lottoNumber = new ArrayList<>();
//        String userNumber = "";
//
//        System.out.println("Wytypuj 6 liczb od 1 do 49: ");
//        Scanner scanner = new Scanner(System.in);
//
//        for ( int i = 0; i<6; i++) {
//            userNumber = scanner.next();
//            lottoNumber.add(userNumber);
//        }
//        scanner.close();
//
//        int counter = 0;
//        ArrayList<Integer> randomNumberList = new ArrayList<>();
//        Integer wylosowana = random.nextInt((maxRand - minRand)+1)+minRand;
//        randomNumberList.add(wylosowana);
//
//        for(int i = 0; i<5; i++){
//            do {
//                wylosowana = random.nextInt((maxRand-minRand)+1)+minRand;
//            } while (randomNumberList.contains(wylosowana))
//                randomNumberList.add(wylosowana);
//                System.out.println(wylosowana);
//            }
//
//        for ( int i = 0; i< randomNumberList.size(); i++){
//            for ( int j = 0; j < randomNumberList(); j++) {
//
//            }
//        }
//
//
//        }
//
//
//    }
//
//}
