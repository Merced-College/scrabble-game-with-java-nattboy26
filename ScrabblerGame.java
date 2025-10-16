/*
 * Nathanael Obrey & James McKean
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ScrabblerGame {

    //adata structure to hold the dictionary of words
    private static List<Word> dictionary = new ArrayList<Word>();

    public static void main(String[] args) {
        
        //read in my text file of words and definitions into my arraylist
        Scanner in = null;
        try {
            in = new Scanner(new File("wordsWithDefs.txt"));
            while (in.hasNextLine()) {
                String word = in.next();
                String def = in.nextLine().trim();
                dictionary.add(new Word(word, def));
            }
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }

        //print out the dictionary
        /*
        for (Word w : dictionary) {
            System.out.println(w);
        }
        */
        //have the game choose 4 random characters using ascii, then output these to the user
        String[] chars = new String[4];
        for (int i = 0; i < 4; i++) {
            int rand = (int) (Math.random() * 26) + 65; //ascii A-Z
            chars[i] = Character.toString((char) rand);
        }
        System.out.println("Your characters are: ");
        for (String c : chars) {
            System.out.print(c + " ");
        }
        System.out.println();

        //allow the user to exchange one of their letters before playing
        Scanner userInput = new Scanner(System.in);
        System.out.println("Would you like to exchange one of your letters? (Y/N)");
        String exchangeAnswer = userInput.nextLine().trim().toUpperCase();
        if (exchangeAnswer.equals("Y") || exchangeAnswer.equals("YES")) {
            System.out.println("Enter the letter you want to exchange (one of the shown characters): ");
            String toReplace = userInput.nextLine().toUpperCase().trim();
            if (toReplace.length() > 0) {
                String letter = toReplace.substring(0, 1);
                boolean replaced = false;
                for (int i = 0; i < chars.length; i++) {
                    if (chars[i].equals(letter)) {
                        int rand = (int) (Math.random() * 26) + 65; //ascii A-Z
                        chars[i] = Character.toString((char) rand);
                        replaced = true;
                        break;
                    }
                }
                if (replaced) {
                    System.out.println("Letter exchanged. Your new characters are: ");
                    for (String c : chars) {
                        System.out.print(c + " ");
                    }
                    System.out.println();
                } else {
                    System.out.println("The letter you entered was not found among your characters. No exchange made.");
                }
            }
        }
    String userWord;
        int index = -1;
        do {
            System.out.println("Please enter a word using these characters: ");
            userWord = userInput.nextLine().toUpperCase().trim();

            //check if the word is in the dictionary using binary search
            index = binarySearch(dictionary, userWord);
            if (index != -1) {
                System.out.println("Congratulations! You found a valid word: " + dictionary.get(index).getWord());
                System.out.println("Definition: " + dictionary.get(index).getDefinition());
            } else {
                System.out.println("Sorry, that word is not in the dictionary. Try again.");
            }
        } while (index == -1);
        userInput.close();
    }//end main

    //binary search for a word in the dictionary
    public static int binarySearch(List<Word> list, String target) {
        int left = 0;
        int right = list.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int cmp = list.get(mid).getWord().compareTo(target);

            if (cmp == 0) {
                return mid; // found
            } else if (cmp < 0) {
                left = mid + 1; // search right half
            } else {
                right = mid - 1; // search left half
            }
        }
        return -1; // not found
    }
}