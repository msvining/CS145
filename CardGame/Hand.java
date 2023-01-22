//class to represent a user's hand

import java.util.*; //import libraries

public class Hand {

    private List<Card> cards; //define list for cards in hand
    private String name; // define string for hand owner name

    public Hand(String name) { //base method
         
        cards = new ArrayList<>(); //set the global vars
        this.name = name;

    }

    public void add(Card card) { //method to add a card to hand

        cards.add(card); //add a card to the hand

    }

    public void display() { //method to display the hand

        System.out.printf("The cards in %s hand are:\n", name); //print message for the hand

        for (int i = 0; i < cards.size(); i++) { //loop through the cards in the hand
            
            Card card = cards.get(i);
            card.display(); //display each card
        }

        System.out.printf("The total value of this hand is %d\n\n", getValue()); //print the total value of the hand

    }

    public int getValue() { //method to get the total value of the hand

        int value = 0; //define variable for total value

        for (int i = 0; i < cards.size(); i++) { //loop through cards in hand

            Card card = cards.get(i); //get the card at this index
            int cardValue = card.getFace(); //get the value of that card
            
            if (cardValue > 10) {

                cardValue = 10; //if the card value is higher than 10 (A face card) set the value to 10

            }

            if (cardValue == 1) { //if the card value is 1 (An ace), set the card value to 11

                cardValue = 11;

            }

            value += cardValue; //add the card value to the total value

        }

        return value; //return the total value

    }

}
