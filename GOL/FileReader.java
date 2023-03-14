import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class FileReader {

  String lines[];

  public FileReader(String fileName) {

    String text = "";

    try {

      File myObj = new File(fileName);
      Scanner myReader = new Scanner(myObj);

      while (myReader.hasNextLine()) {
      
        String data = myReader.nextLine();
        text += data + "\n"; 
      
      }

      myReader.close();

    } catch (FileNotFoundException e) {
      
      System.out.println("File could not be opened. Generating random");
      lines = new String[0];
    
    }

    lines = text.split("\n");
  }

  public int[][] toGrid(){

    int[][] grid = new int[lines.length][lines[0].length()];

    for(int i = 0; i < lines.length; i++) {

      for (int j = 0; j < lines[0].length(); j++) {

        char c = lines[i].charAt(j);
        int val = Character.getNumericValue(c);

        grid[i][j] = val;

      }

    }

    return grid;

  }

}
