// Marlen Vining
// 2/6/23
// Lab #3
//
//This program will do the following:
//show off a simple inventory of alphabet letters
//
public class MVLetterInventory {

    public static void main(String[] args) { //main method

        LetterInventory letter = new LetterInventory("helLo"); //create a letter inventory
        letter.set('l', 6); //set the inventory's amount of "l"s to 6
        System.out.println(letter.get('L')); //check the value of l
        System.out.println(letter.size()); //get the size of the inventory
        System.out.println(letter); //print the string value of the inventory

        LetterInventory other = new LetterInventory("aaa"); //create another inventory
        LetterInventory added = letter.add(other); //add the first 2 together

        System.out.println(added); //print the output of the add
        System.out.println(added.size()); //print the size of the add

        LetterInventory other2 = new LetterInventory("aalll"); //create a third inventory
        LetterInventory subbed = added.subtract(other2); //subtract this from the added value

        System.out.println(subbed); //print the string output of this

    }

}
