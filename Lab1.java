/*
Authors: Sam Clarke, Nick Ivancovich, Jeong Gyu Tak
Date: 1/9/2024
Class: CS 145
Assignment: Lab #1: Guessing Game
File: Lab1.java
Source: Deitel / Deitel
Purpose: Plays a number guessing game with the user
*/

import java.util.Scanner;
import java.util.Random;

public class Lab1
{
    public static void main(String[] args)
    {
        gameManager();
    }

    public static void gameManager()
    {
        //VARIABLES
        final int MAX_NUM = 100;
        final int MIN_NUM = 1;
        boolean playAgainFlag = true;
        int bestGame = 10000;
        int guesses = 0;
        int totalGuesses = 0;
        int gamesPlayed = 0;
        int randomNumber = 0;

        //OBJECTS
        Random rand = new Random();
        Scanner playAgainInput = new Scanner(System.in);
        Scanner playGameInput = new Scanner(System.in);

        gameIntro(MIN_NUM, MAX_NUM);

        while (playAgainFlag) //While the user still wants to play the game
        {
            randomNumber = rand.ints(1, MIN_NUM, MAX_NUM + 1).findFirst().getAsInt(); //Choose random number in a range between MIN_NUM and MAX_NUM

            guesses = playGame(playGameInput, guesses, randomNumber, MAX_NUM, MIN_NUM); //Call the play game method and store the number of guesses taken

            if (guesses < bestGame) //Checks if game is the new best game
            {
                bestGame = guesses;
            }
            totalGuesses += guesses; //Add to total number of guesses
            gamesPlayed++;
            guesses = 0; //Reset guesses variable
            playAgainFlag = playAgain(playAgainInput, " again"); //Ask user if they want to play again
        }
        if (gamesPlayed >= 1) //Makes sure at least one game has been played to avoid dividing by 0 in results method
        {
            printResults(totalGuesses, gamesPlayed, bestGame);
        }
    }

    public static void gameIntro(int MIN_NUM, int MAX_NUM)
    {
        System.out.printf("%s%n%s%n%d%s%n%s%n%s%n%s%n%n", 
        "This program allows you to play a guessing game.",             
                "I will think of a number between " + MIN_NUM + " and ", 
                MAX_NUM,
                " and will allow you to guess until", 
                "you get it. For each guess, I will tell you", 
                "whether the right answer is higher or lower", 
                "than your guess.");
        return;
    }

    public static boolean playAgain(Scanner input, String again) //Asks user if they want to play the game (again)
    {
        boolean flag = false;
        System.out.print("Do you want to play the game" + again + "? ");
        String answer = input.nextLine();
        System.out.print("\n");
        char firstLetter = answer.charAt(0); //Grabs and stores the first letter of the input

        switch(firstLetter)
        {
            //Checks if first letter is either 'y' or 'Y'
            case 'y':
            case 'Y':
                flag = true;
                break;
            //Checks if first letter is either 'n' or 'N'
            case 'n':
            case 'N':
                flag = false;
                break;
            default:
                flag = false;
                break;
        }
        return flag; //Returns answer as boolean
    }

    public static void printResults(int totalGuesses, int gamesPlayed, int bestGame) //Prints the final results of all the games played
    {
        double guessesPerGame = (double)totalGuesses/(double)gamesPlayed; //Finds the average number of guesses taken per game
        System.out.printf("%s%n%s%d%n%s%d%n%s%.1f%n%s%d%n", 
        "Overall results:", 
                "\ttotal games\t = ", 
                gamesPlayed, 
                "\ttotal guesses\t = ", 
                totalGuesses, 
                "\tguesses/games\t = ", 
                guessesPerGame, 
                "\tbest game\t = ", 
                bestGame);
        return;
    }

    public static int playGame(Scanner input, int guesses, int correctNumber, int MAX, int MIN) //Plays one game with the user
    {
        int guess = -1; //Initialize guess variable to a value outside the range of numbers
        String plural = "es";
        System.out.println("I'm thinking of a number between " + MIN + " and " + MAX + "...");

        while (guess != correctNumber) //Keep asking for guesses until right answer is found
        {
            System.out.print("Your guess? ");
            guess = input.nextInt();
            guesses++;

            if (guess > correctNumber)
            {
                System.out.println("It's lower.");
            }
            else if (guess < correctNumber)
            {
                System.out.println("It's higher.");
            }
            else
            {
                if (guesses == 1) //Checks if number was guessed on first try so as to print "guess" instead of "guesses"
                {
                    plural = "";
                }
                System.out.println("You got it right in " + guesses + " guess" + plural);
            }
        }
        return guesses; //Returns number of guesses for the game
    }
}