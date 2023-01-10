#include <iostream>
#include <string>
#include <time.h>

//define game vars
int max = 100;
int guesses = 0;
int games = 0;

bool checkNumber(std::string str) { //checks if an input is a valid string

  for (int i = 0; i < str.length(); i++){ //for every letter in the string, check if it is a digit
    if (isdigit(str[i]) == false) {
      return false; //if any character is not a number, return false
    }
  }
  return true; //if every character is a number return true
}

bool guess(int val) { //function to get and process a guess

  int guess = -1; //define guess var

  while (guess == -1) { //while no valid guess
    std::string buffer; //create and set var for string input
    std::cin >> buffer;

    int bufferInt; //create a var to store the int input
    bool valid = checkNumber(buffer); //check if the input is an int
    if (valid) {
      bufferInt = stoi(buffer); //if it is, set bufferInt to the value
    } else {
      bufferInt = -1; //if not, the guess is invalid
    }

    if (bufferInt >= 1 && bufferInt <= max) { //if the guess is between 1 and max, set the guess to bufferInt
      guess = bufferInt;
    } else {
      std::cout << "please input a number between 1 and " << max << "\n"; //if not, tell the user
    }

  }

  if (guess > val) {
    std::cout << "guess too high\n"; //if the guess is too high, tell the user
  }
  if (guess < val) {
    std::cout << "guess too low\n"; //if the guess is too low, tell the user
  }

  if (guess == val){ //if the guess is right, return false to end the loop, if not, return true
    return false;
  }else {
    return true;
  }
}

void game() { //function to play a game
 
  srand(time(NULL)); //set seed

  std::cout << "I'm thinking of a number between 1 and " << max << "\n"; //print a game message

  games++; //increment games
  int randVal = rand() % 100 + 1;
  int gameGuesses = 0;

  bool running = true;
  while (running) { //start main game loop
    running = guess(randVal); //get guesses until correct
    gameGuesses++;
  }

  std::cout << "you got the number in " << gameGuesses << "\n"; //tell the user they won
  guesses += gameGuesses; //increment total guesses by game guesses
  std::cout << "you take an average of " << guesses / games << " to win\n"; //tell the user the average

}

void intro() { //function to print an intro
  std::cout << "This program allows you to play a guessing game.\n";
  std::cout << "I will think of a number between 1 and\n";
  std::cout << max << " and will allow you to guess until\n";
  std::cout << "you get it. For each guess, I will tell you\n";
  std::cout << "whether the right answer is higher or lower\n";
  std::cout << "than your guess.\n\n";
}

int main () { //main function

  intro(); //print intro

  while (true) { //while game is running
    
    std::cout << "Would you like to play a game? (Y/N)\n"; //prompt user for command

    std::string input; //define input and set it to user input
    std::cin >> input;
    char firstChar = toupper(input[0]); //get the first char

    if (firstChar == 'Y') { //if Y, play a game
      game(); 
    } else if (firstChar == 'N') { //if N, quit
      break;
    } else {
      std::cout << "err"; //if neither, tell the user
    }
  } 

  return 0; //return 0 for no errors
}
