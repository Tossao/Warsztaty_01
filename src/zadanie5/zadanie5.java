package src.zadanie5;
/// NIE SKONCZONE!!!
/// NIE SKONCZONE!!!
/// NIE SKONCZONE!!!
/// NIE SKONCZONE!!!
/// NIE SKONCZONE!!!

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class zadanie5 {
    public static void main(String[] args) {
        mostPopularWords();
    }

    static void mostPopularWords() {
        String path1 = "popular_words.txt";
        String path2 = "most_popular_words.txt";
        createFile(path1, popularWords());
        createFile(path2, mostPopularWords(path1));
    }

    static String mostPopularWords(String str) {
        Path path = Paths.get(str);
        StringBuilder fileWords = new StringBuilder();

        File file = new File(path.toString());
        try {
            Scanner scanner = new Scanner(file);
            while (!scanner.hasNextLine()) {
                fileWords.append(scanner.nextLine() + ", ");
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku");
        }

        String[] tabPopular = strToTab(fileWords.toString());
        String mostPopular = tenWords(tabPopular);
        return mostPopular;
    }

    static String tenWords(String[] arr) {
        Arrays.sort(arr);
        StringBuilder strNoRepeat = new StringBuilder();
        for (int i = 0; i < arr.length + 1; i++) {
            if (!arr[i].equals(arr[i + 1])) {
                strNoRepeat.append(arr[i] + ", ");
            }
        }
        String diffrentWords = strNoRepeat.toString();
        String[] tabDiffrent = strToTab(diffrentWords);
        int numberDiffrent = tabDiffrent.length;
        int[] repeat = new int[numberDiffrent];

        for (int i = 0; i < numberDiffrent; i++) {
            int counter = 0;
            for (int j = 0; j < arr.length; j++) {
                if (tabDiffrent[i].equals(arr[j])) {
                    counter++;
                }
            }
            repeat[i] = counter;
        }

        String[][] counterWords = new String[numberDiffrent][2];
        for (int i = 0; i < numberDiffrent; i++) {
            counterWords[i][1] = tabDiffrent[i];
            counterWords[i][0] = Integer.toString(repeat[i]);
        }

        int[] repeatSort = repeat;
        Arrays.sort(repeatSort);
        System.out.println(Arrays.toString(repeatSort));
        int[] topTenTimes = new int[10];
        StringBuilder topWords = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            topTenTimes[i] = repeatSort[numberDiffrent - 1 - i];
        }
        System.out.println(Arrays.toString(topTenTimes));
        for (int i = 0; i < numberDiffrent; i++) {
            if (cont(topTenTimes, Integer.parseInt(counterWords[i][0]))) {
                System.out.println(counterWords[i][0] + "-" + counterWords[i][1]);
                topWords.append(counterWords[i][1] + ", ");
            }
        }

        String result = topWords.toString();
        System.out.println(result);
        return result;
    }

    static boolean cont(int[] array, int v) {
        boolean result = false;
        for (int i : array) {
            if (i == v) {
                result = true;
                break;
            }
        }
        return result;
    }

    static void createFile(String str, String inWords) {
        String strInWords = inWords;

        while (strInWords.charAt(0) == '[') {
            strInWords = strInWords.substring(1);
        }

        while (strInWords.charAt(strInWords.length() - 1) == ']') {
            strInWords = strInWords.substring(0, strInWords.length() - 2);
        }

        Path path = Paths.get(str);
        File f = new File(path.toString());
        try {
            f.createNewFile();
        } catch (IOException e) {
            System.out.println("Brak dostępu do pliku");
        }

        String[] tabWords = strToTab(strInWords);

        try {
            PrintWriter out = new PrintWriter(path.toString());
            for (String word : tabWords) {
                System.out.println(word);
            }
            out.close();
        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku");
        }
    }

    static String popularWords() {
        String[] popularSites = {"http://www.siatka.org", "http://www.sport.pl", "http://www.plusliga.pl"};
        StringBuilder words = new StringBuilder();
        for (String site : popularSites) {
            String[] temp = getTitles(site);
            words.append(Arrays.toString(temp)).append(", ");
        }
        String strPopularWords = words.toString();
        System.out.println(strPopularWords);
        return strPopularWords;
    }

    static String[] getTitles(String address) {
        StringBuilder titles = new StringBuilder();
        Connection connection = Jsoup.connect(address);
        try {
            Document document = connection.get();
            Elements links = document.select("span.title");
            for (Element element : links) {
                titles.append(element.text()).append(", ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] tabTitles = strToTab(titles.toString());
        return tabTitles;
    }

    static String[] strToTab(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, "!?\"\':-,.");
        StringBuilder words = new StringBuilder();

        while (stringTokenizer.hasMoreTokens()) {
            String temp = stringTokenizer.nextToken();
            if (temp.length() > 3) {
                words.append(temp).append("&");
            }
        }
        String[] tabWords = words.toString().split("&");
        return tabWords;
    }
}
