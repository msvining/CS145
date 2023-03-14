//class for file processor

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class FileReader {

  String lines[]; //variable to store the lines in a file

  public FileReader(String fileName) {

    String text = ""; //variable to store the text in the file

    try {

      File fileReader = new File(fileName); //open the file
      Scanner scanner = new Scanner(fileReader); //create a reader

      while (scanner.hasNextLine()) { //loop through the lines in the file
      
        String data = myReader.nextLine(); //add each line to the string
        text += data + "\n"; 
      
      }

      myReader.close(); //close the reader

    } catch (FileNotFoundException e) {
      
      System.out.println("File could not be opened. Generating random");
      lines = new String[0]; //if nothing is found, generate random instead
    
    }

    lines = text.split("\n"); //split the string into lines
  }

  public int[][] toGrid(){ //method to convert the lines to a grid

    int[][] grid = new int[lines.length][lines[0].length()]; //create an empty grid of ints

    for(int i = 0; i < lines.length; i++) {

      for (int j = 0; j < lines[0].length(); j++) { //loop through every character

        char c = lines[i].charAt(j);
        int val = Character.getNumericValue(c); //set each value in the grid to the int value of the character

        grid[i][j] = val;

      }

    }

    return grid; //return the grid

  }

}
