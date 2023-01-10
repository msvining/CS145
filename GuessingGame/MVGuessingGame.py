# Marlen Vining
# 1/11/22
# Lab #1
#
#This program will do the following:
#Play a simple number guessing game

from random import randint #import random integer

class GuessingGame: #define main class
    def __init__(self, max): #setup function

        #define game variables
        self.max = max

        self.games = 0
        self.guesses = 0

        self.intro() # display intro message

        while True: # main loop
            play = input("Would you like to play a game? (Y/N)?\n") #prompt user to play a game

            if play[0].upper() == "Y": # if yes play game
                self.game()
            elif play[0].upper() == "N": # if no quit
                break
            else:
                print("please input a valid answer") # if neither, tell the user

    def intro(self): #frunction to print an intro message
        print("This program allows you to play a guessing game.")
        print("I will think of a number between 1 and")
        print(f"{max} and will allow you to guess until")
        print("you get it. For each guess, I will tell you")
        print("whether the right answer is higher or lower")
        print("than your guess.")


    def game(self): #function to play a game
        print(f"I'm thinking of a number between 1 and {self.max}") #start message
        randVal = randint(1, self.max) #generate a random number

        self.games += 1 #increment total games
        gameGuesses = 0
        running = True
        while running: #while running play game
            running = self.guess(randVal) #get a guess from user
            gameGuesses += 1

        print(f"game completed in {gameGuesses} guesses") # print stats
        print(f"you take an average of {int(self.guesses / self.games)} to win")

    def guess(self, val): #function to process a guess
        guess = -1 #define guess
        self.guesses += 1 # increment guesses
        while guess == -1: #loop until valid guess
            try:
                guess = int(input("guess a number:\n")) # try to get an int from input
            except:
                print("not a number") # if not, tell the user

            if guess > self.max or guess < 1:
                print(f"please input a number between 1 and {self.max}") #if too high or low, tell the user
                guess = -1

        if guess > val:
            print("guess too high") #if the guess is above the random value, tell the user

        if guess < val: 
            print("guess too low") #if the guess is below, tell the user

        if guess == val: # if the guess is correct, return false to stop the game loop
            return False

        else: #if incorrect, return true to continue the game loop
            return True

if __name__ == "__main__":
    game = GuessingGame(100) # if main, start a game with max value of 100
