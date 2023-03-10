// Marlen Vining
// 1/11/22
// Lab #4
//
//This program will do the following:
//Play a simple Blackjack game
//

import java.util.*; //import libraries
import java.util.Scanner; //Import scanner class for user input

public class MVBlackjack {
    
    private static String[] suits; //define array for card suits
    private static int maxFace; //define int for max face value

    private static Stack<Card> deck; //define stack for the deck

    private static Scanner scanner; //define a scanner for user input

    private static double games; //define variable for total games
    private static double wins; //define variable for total wins

    public static void main(String[] args) {

        suits = new String[]{"Spades", "Hearts", "Clubs", "Diamonds"}; //set card suits
        scanner = new Scanner(System.in); //create a scanner

        maxFace = 13; //set max face value
        games = 0.0; //set games and wins to 0
        wins = 0.0;

        deck = new Stack<>(); //create stack for deck

        shuffle(); //randomly place cards in deck

        while (true) { //main loop

            String playCmd = input("would you like to play a game? (Y/N)"); //ask if the user would like to play a game

            if (playCmd.equals("N")) { //if no, quit

                break;

            } else if (playCmd.equals("Y")) { //if yes, increment games and continue
                
                games++;
                game();

                System.out.printf("You win an average of %d%% games\n", (int)((wins/games) * 100)); //after the game, print stats

            } else {

                System.out.println("Please input a valid command"); //if none, tell the user

            }

        }

    }

    public static void game() { //method to play a game

        Hand playerHand = new Hand("your"); //define the player and house hand
        Hand houseHand = new Hand("the house's");

        boolean playerStand = false; //define variables for standing
        boolean houseStand = false;

        playerHand.add(draw()); //give each player 2 cards
        houseHand.add(draw());
        playerHand.add(draw()); //I tried this with a loop and it was too long and messy
        houseHand.add(draw());

        playerHand.display(); //display both hands
        houseHand.display();

        while (true) { //game loop
            
            if (!playerStand) {
                String cmd = input("Would you like to hit, stand, or look?"); //ask for an input command

                if (!cmd.equals("look")) { //if the command isn't look, check for game commands
                    
                    if (cmd.equals("hit")) { //if hit, run the hit method for the player

                        hit("You", playerHand);
            
                    } else if (cmd.equals("stand")) {

                        System.out.println("You stood"); //if stand, tell the player and set standing to true
                        playerStand = true;

                    } else {

                        System.out.println("Please input a valid cmd"); //if neither, tell the player
                        continue;

                    }

                    if (playerHand.getValue() == 21) { //if the players cards are equal to 21, they win
    
                        System.out.println("Blackjack!!! You won!");
                        wins++;
                        break;

                    } else if (playerHand.getValue() > 21) { //if higher, they lose

                        System.out.println("Break. You lost.");
                        break;

                    }


                } else { // if the command is look, display the cards in both hands

                    playerHand.display();
                    houseHand.display();
                    continue; //skip the rest of the loop

                }

            }

            if (!houseStand) { //if the house has not stood, make a move

                if (houseHand.getValue() >= 17 && houseHand.getValue() > playerHand.getValue()) {

                    System.out.println("The house stood"); //if the house is above 17 and above the value of the player hand, stand
                    houseStand = true;

                } else {

                    hit("The house", houseHand); //if not, hit

                }

                if (houseHand.getValue() == 21) { //if the house's hand has a value of 21, you lose

                    System.out.println("Blackjack. You lost.");
                    break;

                } else if (houseHand.getValue() > 21) { //if the house's hand is higher than 21, you win

                    System.out.println("Break!!! You won!");
                    wins++;
                    break;

                }

            }

            if (houseStand && playerStand) { //if both players stand the one with the higher cards won

                if (houseHand.getValue() >= playerHand.getValue()) { //if the house has the same or higher cards, they win

                    System.out.println("The house has higher cards. You lost.");
                    break;

                } else { //if not, you win

                    System.out.println("You have higher cards!!! You won!");
                    wins++; //increment wins
                    break;

                }

            }

        }

    }

    public static void hit(String name, Hand hand) { //method to hit
                                                     
        if (deck.size() < 1) { //if the deck is empty, shuffle

            System.out.println("Deck empty, shuffling");
            shuffle();

        }

        System.out.printf("%s hit\n", name); //print who hit

        Card next = draw(); //get the next card from the deck
        System.out.printf("%s drew a ", name); //print who drew
        next.display(); //display the card

        hand.add(next); //add the card to the hand
        System.out.printf("The value of this hand is now %d\n", hand.getValue()); //print the value of the hand

    }

    public static Card draw() { //method to draw a card
    
        Card card = deck.pop(); //get the top card of the deck
        return card;

    }

    private static void shuffle() { //method to shuffle a deck

        List<Card> cards = new ArrayList<>(); //create an arraylist for cards

        for (int suit = 0; suit < suits.length; suit++) { //loop through suits

            for (int face = 1; face <= maxFace; face++) { //loop through faces

                Card card = new Card(suits[suit], face); //create a new card
                cards.add(card); //add that card to the arraylist

            }

        }

        for (int i = 0; i < 52; i++) { //loop through all the cards

            int index = (int)(Math.random() * cards.size()); //pick a random value
            Card card = cards.remove(index); //remove that from the card list
            deck.push(card); //add it to the deck

        }

    }

    static String input(String message) { //create an easy way to get user input
		System.out.println(message); //print prompt
		return scanner.nextLine(); // return user input
	}

}
