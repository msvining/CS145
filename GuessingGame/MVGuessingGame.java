// Marlen Vining
// 1/11/22
// Lab #1
//
//This program will do the following:
//Play a simple number guessing game
//

import java.util.Scanner; //Import scanner class for user input

public class MVGuessingGame {
	private static Scanner scanner = new Scanner(System.in); //creates a scanner to read user input
	
	//set variables for the game
	private static int max = 100;
	private static double games = 0.0;
	private static double guesses = 0.0;
	
	public static void main(String[] args) {
		intro(); //display the intro message
		while (true) { //main loop
			char cmd = input("would you like to play a game? (Y/N)").charAt(0); //ask if the user wants to play a game
			cmd = Character.toUpperCase(cmd); //process the first character
			if (Character.compare(cmd, 'N') == 0) {
				break; //if N, exit the game
			} else if (Character.compare(cmd, 'Y') == 0) {
				games += 1; //if Y, play a game
				game();
			} else {
				System.out.println("Please input a valid answer"); //if neither, prompt the user
			}
		}
	}
	
	static void game() { //method to play a game
		System.out.printf("I'm thinking of a number between 1 and %d\n", max); //print game message
		int rand = (int)(Math.random() * max) + 1; //generate random number
		int roundGuesses = 0;
		
		boolean running = true; //start the game loop
		while (running) {
			int guessInt = -1; //define the guess
			while (guessInt == -1) {//while the guess is not set
				String guess = input("guess a number"); //prompt the user to guess
				try {
					guessInt = Integer.valueOf(guess); //get the int value of guess
					
					if (guessInt > max || guessInt < 0) {
						guessInt = -1; //if too high or low , set to -1
					}
				} catch (Exception e) {
					guessInt = -1; //if error, set to -1
				}
				if (guessInt == -1) {
					System.out.println("Please input a valid number"); //if -1, something must be wrong, prompt the user
				}
			}
			guesses += 1; //increment guesses and round guesses
			roundGuesses += 1;
			int checkVal = check(guessInt, rand); //check the value
			switch (checkVal) { 
				case -1:
					System.out.println("Guess too low"); //if too low, tell the user
					break;
					
				case 1:
					System.out.println("Guess too high"); //if too high, tell the user
					break;
					
				case 0:
					System.out.println("You guessed the number"); //if equal, tell the user and exit to main menu
					System.out.printf("You got it in %d guesses\n", roundGuesses);
					running = false;
					break;
			}
		}
		stats();
	}
	
	static void stats() { //method to print stats
		if (games != 0) {
			System.out.printf("You take an average of %d guesses to win\n", (int)(guesses / games)); //print average guesses per game
		} else {
			System.out.println("Please play at least one game to see your average score"); //don't divide by 0
		}
	}
	
	static int check(int input, int value) { //method to check a guess
		if (input == value) { //return value based on guess and number
			return 0;
		} else if (input > value) {
			return 1;
		} else {
			return -1;
		}
	}
	
	static void intro() { //method to print intro message
		System.out.println("This program allows you to play a guessing game.");
		System.out.println("I will think of a number between 1 and");
		System.out.printf("%d and will allow you to guess until\n", max);
		System.out.println("you get it. For each guess, I will tell you");
		System.out.println("whether the right answer is higher or lower");
		System.out.println("than your guess.");
	}
	
	static String input(String message) { //create an easy way to get user input
		System.out.println(message); //print prompt
		return scanner.next(); // return user input
	}
}