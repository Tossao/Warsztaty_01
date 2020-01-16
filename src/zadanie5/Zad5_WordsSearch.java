package src.zadanie5;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.print.Doc;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class Zad5_WordsSearch {
    public static void main(String[] args) {
        String[] addressUrlTab = {"http://www.onet.pl/", "http://sport.pl/", "http://www.siatka.org/"};
        String[] wordsExcludedTab = {"oraz", "co", "ponieważ", "coś", "kto", "jeżeli", "bo", "gdyż", "po", "gdzie", "za", "na"};
        String popularWordsFile = "popular_words.txt";
        String filteredWordsFile = "filtered_popular_words.txt";
        writePopularWords(addressUrlTab, popularWordsFile);
        writeFilteredWordsFile(wordsExcludedTab, popularWordsFile, filteredWordsFile);
    }

    static void writePopularWords(String[] urlTab, String filename) {
        String popularWords = "";

        for (String urlAddress : urlTab) {
            Connection connect = Jsoup.connect(urlAddress);
            StringBuilder stringBuilder = new StringBuilder();

            try {
                Document document = connect.get();
                org.jsoup.select.Elements links = document.select("span.title");
                for (Element element : links) {
                    stringBuilder.append(element.text()).append(" ");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            popularWords = stringBuilder.toString();
            String onePopularWordTemp = "";
            Path path = Paths.get(filename);
            if (Files.exists(path) == false) {
                try {
                    Files.createFile(path);
                } catch (IOException ex) {
                    System.out.println("Błąd IOException");
                }
            }

            StringTokenizer popularWordsTok = new StringTokenizer(popularWords);
            try {
                FileWriter fileWriter = new FileWriter(filename, true);
                while (popularWordsTok.hasMoreTokens()) {
                    onePopularWordTemp = popularWordsTok.nextToken(",.!:?;'- \n\"");
                    if (onePopularWordTemp.length() > 3) {
                        fileWriter.append(onePopularWordTemp + " ");
                    }
                }
                fileWriter.close();
            } catch (IOException ex) {
                System.out.println("Błąd zapisu do pliku");
            }
        }
        System.out.println("Zapisano do pliku: " + filename);
    }


    static void writeFilteredWordsFile(String[] excludedWordsTab, String filename, String filteredFilename) {
        File popularWordFile = new File(filename);
        filteredFilename = "filtered_popular_words.txt";
        Path filteredPath = Paths.get(filteredFilename);
        boolean isIOException = false;
        if (Files.exists(filteredPath) == false) {
            try {
                Files.createFile(filteredPath);
            } catch (IOException ex) {
                System.out.println("Błąd IOException");
                isIOException = true;
            }
        }

        if (isIOException == false) {
            try {
                Scanner scanner = new Scanner(popularWordFile);
                String oneLinePopularWords = "";
                boolean isExcludedWord = false;
                while (scanner.hasNextLine()) {
                    oneLinePopularWords = scanner.nextLine();
                    String[] oneLineTab = oneLinePopularWords.split(" ");
                    StringBuilder oneLineBuild = new StringBuilder();
                    for (int i = 0; i < oneLineTab.length; i++) {
                        isExcludedWord = false;
                        oneLineTab[i] = oneLineTab[i].toLowerCase();
                        for (int j = 0; j < excludedWordsTab.length; j++) {
                            if (oneLineTab[i].equals(excludedWordsTab[j])) {
                                isExcludedWord = true;
                                break;
                            }
                        }
                        if (isExcludedWord == false) {
                            oneLineBuild = oneLineBuild.append(oneLineTab[i]).append(" ");
                        }
                    }
                    oneLinePopularWords = oneLineBuild.toString();
                    try {
                        FileWriter fileWriter = new FileWriter(filteredFilename, true);
                        fileWriter.append(oneLinePopularWords).append(' ');
                        fileWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("Nie znaleziono pliku popular_word.txt");
            }
        }
        System.out.println("Zapisano plik " + filteredFilename);
    }
}
