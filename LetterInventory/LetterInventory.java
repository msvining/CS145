//letter inventory class
public class LetterInventory {

    private int[] letters = new int[26]; //array to store the count of each letter
    private int sizeVal = 0; //an int for the total size
    private boolean empty = true; //a boolean to store if the inventory is empty
    public LetterInventory(String data) { //constructor method

        char[] chars = data.toCharArray(); //create an array from the string
        for (int i = 0; i < chars.length; i++) { //loop through the char list

            int index = Character.getNumericValue(chars[i]) - 10; //get the alphabet value

            if (index > 25 || index < 0) {

                throw new IllegalArgumentException("not an alphabet char"); //if not an alphabet char, throw an exception

            }

            letters[index]++; //increment the letter
            sizeVal++; //increment the size
            empty = false; //set to not empty

        }

    }

    public int get(char letter) { //method to get the value of a letter

        int index = Character.getNumericValue(letter) - 10; //get the index of the char

        if (index > 25 || index < 0) {

            throw new IllegalArgumentException("not an alphabet char"); //if not an alphabet character, throw an exception

        }

        return letters[index]; //return the value at the index

    }

    public void set(char letter, int value) { //method to set a value at an index

        int index = Character.getNumericValue(letter) - 10; //get the index of the character

        if (index > 25 || index < 0) {

            throw new IllegalArgumentException("not an alphabet char"); //if not an alphabet character, throw an exception

        }

        if (value < 0) {

            value = 0; //if the value is too low, set it to 0

        }

        if (value == 0) { //if the value is 0

            empty = true; //set empty to true
            for (int i = 0; i < letters.length; i++) { //check all values

                if (letters[i] != 0) { //if non 0 value in the list

                    empty = false; //set empty to false

                }

            }

        }

        int diff = value - letters[index]; //get the difference in values
        sizeVal += diff; //change the size

        letters[index] = value; //set the value

    }

    public int size() {

        return sizeVal; //method to return the size

    }

    public boolean isEmpty() {

        return empty; //method to return is empty

    }

    public String toString() { //method to convert to string

        String out = ""; //variable for string
        for (int i = 0; i < letters.length; i++) { //loop through the letters

            for (int j = 0; j < letters[i]; j++) { //for every letter in each

                out += (char) (i + 97); //add that letter to the string

            }

        }

        return out; //return out

    }

    public LetterInventory add(LetterInventory other) { //method to add 2 inventories

        LetterInventory added = new LetterInventory(""); //create an inventory to return

        for (int i = 0; i < letters.length; i++) { //loop through the letters

            char index = (char) (i + 97); //get the character for that value
            int value = get(index) + other.get(index); //add the values for both chars

            if (value < 0) {

                value = 0; //if the value is below 0, set it to 0

            }

            added.set(index, value); //set the value

        }

        return added; //return the inventory

    }

    public LetterInventory subtract(LetterInventory other) { //method to subtract 2 inventories

        LetterInventory subbed = new LetterInventory("");

        for (int i = 0; i < letters.length; i++) { //loop through the letters

            char index = (char) (i + 97); //get the character for that value
            int value = get(index) - other.get(index);//add the values for both chars

            if (value < 0) {

                value = 0; //if the value is below 0, set it to 0

            }

            subbed.set(index, value); //set the value

        }

        return subbed; //return the inventory

    }

}