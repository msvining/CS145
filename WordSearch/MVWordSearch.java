import java.util.Scanner; //Import scanner class for user input

public class MVWordSearch {

  private static int size = 5; //variable for the ammount of words to use
  private static char grid[][] = new char[10][10]; //create a grid of characters
  private static String words[] = new String[size]; //create an array to store all words

  private static Scanner scanner = new Scanner(System.in); //define a scanner for user input

  public static void main(String args[]) {

    System.out.println("Please input 5 words"); //tell the user to input words

    for (int i = 0; i < size; i++) { //repeat for each word needed

      String word = ""; //define a variable for word
      while (word.length() > 9 || word.length() <= 0) { //while word is still invalid

        word = input("please input a word (less that 9 characters in length)"); //get a word from the user

      }

      words[i] = word; //add that word to the array

    }

    generate(); //generate the word search
    display(); //display the word search

  }

  static String input(String message) { //create an easy way to get user input
		System.out.println(message); //print prompt
		return scanner.nextLine(); // return user input
	}

  public static void generate() { //method to generate a word search

    for (int i = 0; i < grid.length; i++) {

      for (int j = 0; j < grid[i].length; j++) {

        grid[i][j] = '#'; //fill every space with #

      }

    }

    for (int i = 0; i < words.length; i++) {

      insert(words[i]); //insert words

    } 

  }

  public static void display() { //method to display the word search

    String stringGrid = ""; //create a string to display the grid
    for (int i = 0; i < grid.length; i++) {

      for (int j = 0; j < grid[i].length; j++) {

        if (Character.compare(grid[i][j], '#') == 0){ //fill all blank spaces with a random character
          int index = (int)(Math.random() * 26);
          char c = (char)(index + 'a');
          stringGrid += ("\u001B[40m" + c + " "); //color this character with a black background
        } else {
          stringGrid += ("\u001B[44m" + grid[i][j] + " "); //color every word character with a purple background
        }

      }

      stringGrid += "\n"; //add a line break at the end of every row

    }

    System.out.println(stringGrid.replace("\u001B[44m", "\u001B[40m")); //show the grid with no hilighting 

    System.out.println("\nwords:");
    for (int i = 0; i < size; i++) {
      System.out.println(words[i]); //print each word
    }

    input("press enter for answer key"); //wait for input
    System.out.println(stringGrid); //print the grid with hilighting

  }

  public static char[][] cloneGrid() { //method do clone the grid

    char copy[][] = new char[grid.length][grid[0].length]; //create a copy of the grid
    
    for (int i = 0; i < grid.length; i++) {

      for (int j = 0; j < grid[i].length; j++) {

        copy[i][j] = grid[i][j]; //copy each characrer

      }

    }

    return copy; //return the copy

  }

  private static void insert(String word) { //method to insert a word

    char clone[][] = cloneGrid(); //create a clone of the grid

    int offsetX = (int)(Math.random() * 2); //get a random offset for x and y
    int offsetY = (int)(Math.random() * 2);

    int y = (int)(Math.random() * clone[0].length - 1); //get a starting position for x and y
    if (offsetY == 1) {
      y = (int)(Math.random() * (clone[0].length - word.length()));
    }
    
    int x = (int)(Math.random() * clone.length);
    if (offsetX == 1){
      x = (int)(Math.random() * (clone.length - word.length()));
    }

    if (offsetX == 0 && offsetY == 0) {

      insert(word); //if both offsets are 0, redo and end function
      return;

    }

    int reversed = (int)(Math.random() * 2); //check if the word is reversed or not
       
    for (int j = 0; j < word.length(); j++) { //loop through every letter of the word

      if (reversed == 1) {
        if (Character.compare(clone[y + j * offsetY][x + j * offsetX], '#') == 0 || //if the letter is in a blank space or matches the one there
          Character.compare(clone[y + j * offsetY][x + j * offsetX], word.charAt(word.length() - j - 1)) == 0) { 

          clone[y + j * offsetY][x + j * offsetX] = word.charAt(word.length() - j - 1); //set the letter there to the letter in the word
                                                                                        
        } else { //if the spot is taken
          insert(word); //try again
          return;
        }


      } else {
        if (Character.compare(clone[y + j * offsetY][x + j * offsetX], '#') == 0 || //if the letter is in a blank space or matches the one there
          Character.compare(clone[y + j * offsetY][x + j * offsetX], word.charAt(j)) == 0) { 

          clone[y + j * offsetY][x + j * offsetX] = word.charAt(j); //set the letter there to the letter in the word
                                                                                        
        } else { //if the spot is taken
          insert(word); //try again
          return;
        }


      }
        
    }

    grid = clone; //set the grid to the clone if successful

  }

}
