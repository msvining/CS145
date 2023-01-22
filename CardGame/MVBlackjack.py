# Marlen Vining
# 1/11/22
# Lab #4
#
#This program will do the following:
#Play a simple Blackjack game
#
# python version

from random import randint #import random int library

#method to represent a card
class Card:

    def __init__(self, suit, face):
        self.faces = "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Oueen", "King" #set the global vars
        
        self.suit = suit
        self.face = face

    def display(self): #method to display card data

        print(f"{self.faces[self.getFace() - 1]} of {self.getSuit()}") #print the card's face and suit

    def getSuit(self): #method to get the suit

        return self.suit #return the suit value

    def getFace(self): #method to get the face 

        return self.face #return the face value

#class to represent a user's hand
class Hand:

    def __init__(self, name): #base method
         
        self.cards = [] #set the global vars
        self.name = name

    def add(self, card): #method to add a card to hand

        self.cards.append(card) #add a card to the hand

    def display(self): #method to display the hand

        print(f"The cards in {self.name} hand are:\n") #print message for the hand

        for i in self.cards: #loop through the cards in the hand
            
            i.display() #display each card

        print(f"The total value of this hand is {self.getValue()}\n\n") #print the total value of the hand

    def getValue(self): #method to get the total value of the hand

        value = 0 #define variable for total value

        for i in self.cards: #loop through cards in hand

            cardValue = i.getFace() #get the value of that card
            
            if (cardValue > 10):

                cardValue = 10 #if the card value is higher than 10 (A face card) set the value to 10

            if (cardValue == 1): #if the card value is 1 (An ace), set the card value to 11

                cardValue = 11
            value += cardValue #add the card value to the total value

        return value #return the total value

class MVBlackjack:
    
    def __init__(self): 

        self.suits = {"Spades", "Hearts", "Clubs", "Diamonds"} #set card suits

        self.maxFace = 13 #set max face value
        self.games = 0 #set games and wins to 0
        self.wins = 0

        self.deck = [] #create stack for deck

        self.shuffle() #randomly place cards in deck

        while (True):  #main loop

            playCmd = input("would you like to play a game? (Y/N)\n") #ask if the user would like to play a game

            if (playCmd == "N"):  #if no, quit

                break

            elif (playCmd == "Y"):  #if yes, increment games and continue
                
                self.games += 1
                self.game()

                print(f"You win an average of {(self.wins/self.games) * 100}% games\n") #after the game, print stats

            else: 

                print("Please input a valid command") #if none, tell the user

    def game(self):  #method to play a game

        playerHand = Hand("your") #define the player and house hand
        houseHand = Hand("the house's")

        playerStand = False #define variables for standing
        houseStand = False

        playerHand.add(self.draw()) #give each player 2 cards
        houseHand.add(self.draw())
        playerHand.add(self.draw()) #I tried this with a loop and it was too long and messy
        houseHand.add(self.draw())

        playerHand.display() #display both hands
        houseHand.display()

        while (True):  #game loop
            
            if (not playerStand):
                cmd = input("Would you like to hit, stand, or look?\n") #ask for an input command

                if (cmd != "look"):  #if the command isn't look, check for game commands
                    
                    if (cmd == "hit"):  #if hit, run the hit method for the player

                        self.hit("You", playerHand)
            
                    elif (cmd == "stand"): 

                        print("You stood") #if stand, tell the player and set standing to true
                        playerStand = True

                    else:

                        print("Please input a valid cmd") #if neither, tell the player
                        continue

                    if (playerHand.getValue() == 21):  #if the players cards are equal to 21, they win
    
                        print("Blackjack!!! You won!")
                        self.wins += 1
                        break

                    elif (playerHand.getValue() > 21):  #if higher, they lose

                        print("Break. You lost.")
                        break

                else:  # if the command is look, display the cards in both hands

                    playerHand.display()
                    houseHand.display()
                    continue #skip the rest of the loop

            if (not houseStand):  #if the house has not stood, make a move

                if (houseHand.getValue() >= 17 and houseHand.getValue() > playerHand.getValue()):

                    print("The house stood") #if the house is above 17 and above the value of the player hand, stand
                    houseStand = True

                else:

                    self.hit("The house", houseHand) #if not, hit

                if (houseHand.getValue() == 21):  #if the house's hand has a value of 21, you lose

                    print("Blackjack. You lost.")
                    break

                elif (houseHand.getValue() > 21):  #if the house's hand is higher than 21, you win

                    print("Break!!! You won!")
                    self.wins += 1
                    break

            if (houseStand and playerStand):  #if both players stand the one with the higher cards won

                if (houseHand.getValue() >= playerHand.getValue()):  #if the house has the same or higher cards, they win

                    print("The house has higher cards. You lost.")
                    break

                else:  #if not, you win

                    print("You have higher cards!!! You won!")
                    self.wins += 1 #increment wins
                    break

    def hit(self, name, hand):  #method to hit
                                                     
        if (len(self.deck) < 1):  #if the deck is empty, shuffle

            print("Deck empty, shuffling")
            self.shuffle()      

        print(f"{name} hit\n") #print who hit

        next = self.draw() #get the next card from the deck
        print(f"{name} drew a ", end="") #print who drew
        next.display() #display the card

        hand.add(next) #add the card to the hand
        print(f"The value of this hand is now {hand.getValue()}") #print the value of the hand  

    def draw(self):  #method to draw a card
    
        card = self.deck.pop(len(self.deck) - 1) #get the top card of the deck
        return card

    def shuffle(self):  #method to shuffle a deck

        cards = [] #create an arraylist for cards

        for i in self.suits:  #loop through suits

            for j in range(self.maxFace): #loop through faces

                card = Card(i, j + 1) #create a new card
                cards.append(card) #add that card to the arraylist

        for i in range(51): #loop through all the cards
            
            try:
                index = randint(0, len(cards) - 1)#pick a random value
            except:
                index = 0

            card = cards.pop(index) #remove that from the card list
            self.deck.append(card) #add it to the deck

if __name__ == "__main__":
    cardGame = MVBlackjack()
