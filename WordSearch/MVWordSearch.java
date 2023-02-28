import java.util.Scanner; //Import scanner class for user input

public class MVWordSearch {

  private static int size = 5;
  private static char grid[][] = new char[10][10];
  private static String words[] = new String[size];

  private static Scanner scanner = new Scanner(System.in); //define a scanner for user input

  public static void main(String args[]) {

    System.out.println("Please input 5 words");

    for (int i = 0; i < size; i++) {

      String word = "";
      while (word.length() > 9 || word.length() <= 0) {

        word = input("please input a word (less that 9 characters in length)");

      }

      words[i] = word;

    }

    generate();
    display();

  }

  static String input(String message) { //create an easy way to get user input
		System.out.println(message); //print prompt
		return scanner.nextLine(); // return user input
	}

  public static void generate() {

    for (int i = 0; i < grid.length; i++) {

      for (int j = 0; j < grid[i].length; j++) {

        grid[i][j] = '#';

      }

    }

    for (int i = 0; i < words.length; i++) {

      insert(words[i]);

    } 

  }

  public static void display() {

    String stringGrid = "";
    for (int i = 0; i < grid.length; i++) {

      for (int j = 0; j < grid[i].length; j++) {

        if (Character.compare(grid[i][j], '#') == 0){
          int index = (int)(Math.random() * 26);
          char c = (char)(index + 'a');
          stringGrid += ("\u001B[40m" + c + " ");
        } else {
          stringGrid += ("\u001B[44m" + grid[i][j] + " ");
        }

      }

      stringGrid += "\n";

    }

    System.out.println(stringGrid.replace("\u001B[44m", "\u001B[40m"));

    System.out.println("\nwords:");
    for (int i = 0; i < size; i++) {
      System.out.println(words[i]);
    }

    input("press enter for answer key");
    System.out.println(stringGrid);

  }

  public static char[][] cloneGrid() {

    char copy[][] = new char[grid.length][grid[0].length];
    
    for (int i = 0; i < grid.length; i++) {

      for (int j = 0; j < grid[i].length; j++) {

        copy[i][j] = grid[i][j]; 

      }

    }

    return copy;

  }

  private static void insert(String word) {

    char clone[][] = cloneGrid(); 

    int offsetX = (int)(Math.random() * 2);
    int offsetY = (int)(Math.random() * 2);

    int y = (int)(Math.random() * clone[0].length - 1);
    if (offsetY == 1) {
      y = (int)(Math.random() * (clone[0].length - word.length()));
    }
    
    int x = (int)(Math.random() * clone.length);
    if (offsetX == 1){
      x = (int)(Math.random() * (clone.length - word.length()));
    }

    if (offsetX == 0 && offsetY == 0) {

      insert(word);
      return;

    }

    int reversed = (int)(Math.random() * 2);
       
    for (int j = 0; j < word.length(); j++) {

      if (Character.compare(clone[y + j * offsetY][x + j * offsetX], '#') == 0) {
        if (reversed == 1) {

          clone[y + j * offsetY][x + j * offsetX] = word.charAt(word.length() - j - 1);

        } else {
        
          clone[y + j * offsetY][x + j * offsetX] = word.charAt(j);
        
        }
      } else {
        insert(word);
        return;
      }
        
    }

    grid = clone;

  }

}
