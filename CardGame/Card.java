//method to represent a card

public class Card {

    private String suit; //define variable for suit
    private int face; //define variable for the face value
    private static String[] faces; //define array for face strings

    public Card(String suit, int face) {
        faces = new String[]{"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Oueen", "King"}; //set the global vars
        
        this.suit = suit;
        this.face = face;

    }

    public void display() { //method to display card data

        System.out.printf("%s of %s\n", faces[getFace() - 1], getSuit()); //print the card's face and suit

    }

    public String getSuit() { //method to get the suit

        return suit; //return the suit value

    }

    public int getFace() { //method to get the face 

        return face; //return the face value

    }

}
