/*
 * Nathanael Obrey & James McKeann
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

    // Scanner userInput = new Scanner(System.in); // removed duplicate declaration
        boolean playAgain = true;
        Scanner userInput = new Scanner(System.in);
        int reshufflesLeft = 3;
        while (playAgain) {
            boolean restartRound = false;
            do {
                restartRound = false;
                reshufflesLeft = 3; // Reset reshuffles on restart or win
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
                String exchangeAnswer = "";
                while (true) {
                    System.out.println("Would you like to exchange one of your letters? (Y/N), /s to reshuffle all letters (" + reshufflesLeft + " left), /q to quit, /r to restart");
                    exchangeAnswer = userInput.nextLine().trim();
                    if (exchangeAnswer.equalsIgnoreCase("/q")) {
                        System.out.println("Quitting game. Goodbye!");
                        userInput.close();
                        return;
                    }
                    if (exchangeAnswer.equalsIgnoreCase("/r")) {
                        System.out.println("Restarting round with new letters.\n");
                        restartRound = true;
                        break;
                    }
                    if (exchangeAnswer.equalsIgnoreCase("/s")) {
                        if (reshufflesLeft > 0) {
                            reshufflesLeft--;
                            for (int i = 0; i < 4; i++) {
                                int rand = (int) (Math.random() * 26) + 65;
                                chars[i] = Character.toString((char) rand);
                            }
                            System.out.println("Letters reshuffled. Your new characters are: ");
                            for (String c : chars) {
                                System.out.print(c + " ");
                            }
                            System.out.println();
                            System.out.println("Reshuffles left: " + reshufflesLeft);
                        } else {
                            System.out.println("No reshuffles left this round.");
                        }
                        continue;
                    }
                    exchangeAnswer = exchangeAnswer.toUpperCase();
                    if (exchangeAnswer.equals("Y") || exchangeAnswer.equals("YES")) {
                        System.out.println("Enter the letter you want to exchange (one of the shown characters, or /q to quit, /r to restart, /s to reshuffle): ");
                        String toReplace = userInput.nextLine().toUpperCase().trim();
                        if (toReplace.equalsIgnoreCase("/Q")) {
                            System.out.println("Quitting game. Goodbye!");
                            userInput.close();
                            return;
                        }
                        if (toReplace.equalsIgnoreCase("/R")) {
                            System.out.println("Restarting round with new letters.\n");
                            restartRound = true;
                            break;
                        }
                        if (toReplace.equalsIgnoreCase("/S")) {
                            if (reshufflesLeft > 0) {
                                reshufflesLeft--;
                                for (int i = 0; i < 4; i++) {
                                    int rand = (int) (Math.random() * 26) + 65;
                                    chars[i] = Character.toString((char) rand);
                                }
                                System.out.println("Letters reshuffled. Your new characters are: ");
                                for (String c : chars) {
                                    System.out.print(c + " ");
                                }
                                System.out.println();
                                System.out.println("Reshuffles left: " + reshufflesLeft);
                            } else {
                                System.out.println("No reshuffles left this round.");
                            }
                            continue;
                        }
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
                        break;
                    } else if (exchangeAnswer.equals("N") || exchangeAnswer.equals("NO")) {
                        break;
                    } else {
                        System.out.println("Please enter 'Y' or 'N'.");
                    }
                }
                if (restartRound) continue;
                String userWord;
                int index = -1;
                do {
                    System.out.println("Please enter a word using these characters (or type /q to quit, /r to restart, /s to reshuffle): ");
                    userWord = userInput.nextLine().toUpperCase().trim();
                    if (userWord.equalsIgnoreCase("/Q")) {
                        System.out.println("Quitting game. Goodbye!");
                        userInput.close();
                        return;
                    }
                    if (userWord.equalsIgnoreCase("/R")) {
                        System.out.println("Restarting round with new letters.\n");
                        restartRound = true;
                        break;
                    }
                    if (userWord.equalsIgnoreCase("/S")) {
                        if (reshufflesLeft > 0) {
                            reshufflesLeft--;
                            for (int i = 0; i < 4; i++) {
                                int rand = (int) (Math.random() * 26) + 65;
                                chars[i] = Character.toString((char) rand);
                            }
                            System.out.println("Letters reshuffled. Your new characters are: ");
                            for (String c : chars) {
                                System.out.print(c + " ");
                            }
                            System.out.println();
                            System.out.println("Reshuffles left: " + reshufflesLeft);
                        } else {
                            System.out.println("No reshuffles left this round.");
                        }
                        continue;
                    }

                    // ensure the user only uses the provided characters
                    if (userWord.length() == 0) {
                        System.out.println("You must enter a word. Try again.");
                        continue;
                    }
                    if (!usesOnlyGivenLetters(userWord, chars)) {
                        System.out.println("Your word uses letters not in the given characters or uses a letter too many times. Try again.");
                        continue;
                    }

                    //check if the word is in the dictionary using binary search
                    index = binarySearch(dictionary, userWord);
                    if (index != -1) {
                        System.out.println("Congratulations! You found a valid word: " + dictionary.get(index).getWord());
                        System.out.println("Definition: " + dictionary.get(index).getDefinition());
                        reshufflesLeft = 3; // Reset reshuffles after a win
                    } else {
                        System.out.println("Sorry, that word is not in the dictionary. Try again.");
                    }
                } while (index == -1 && !restartRound);
            } while (restartRound);

            // Ask if the user wants to play again
            String againAnswer = "";
            while (true) {
                System.out.println("Would you like to play again? (Y/N)");
                againAnswer = userInput.nextLine().trim();
                if (againAnswer.equalsIgnoreCase("/q")) {
                    System.out.println("Quitting game. Goodbye!");
                    userInput.close();
                    return;
                }
                if (againAnswer.equalsIgnoreCase("/r")) {
                    System.out.println("Restarting round with new letters.\n");
                    break;
                }
                againAnswer = againAnswer.toUpperCase();
                if (againAnswer.equals("Y") || againAnswer.equals("YES")) {
                    playAgain = true;
                    break;
                } else if (againAnswer.equals("N") || againAnswer.equals("NO")) {
                    playAgain = false;
                    System.out.println("Thanks for playing!");
                    break;
                } else {
                    System.out.println("Please enter 'Y' or 'N'.");
                }
            }
        }
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

    //check that the target word can be constructed from the given characters (respecting counts)
    public static boolean usesOnlyGivenLetters(String target, String[] chars) {
        int[] counts = new int[26];
        for (String c : chars) {
            if (c != null && c.length() > 0) {
                counts[c.charAt(0) - 'A']++;
            }
        }

        for (int i = 0; i < target.length(); i++) {
            char ch = target.charAt(i);
            if (ch < 'A' || ch > 'Z') return false; // only allow A-Z
            int idx = ch - 'A';
            counts[idx]--;
            if (counts[idx] < 0) return false; // used more times than available
        }
        return true;
    }
}
