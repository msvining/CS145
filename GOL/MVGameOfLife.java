//main class

import java.util.Scanner; //import scanner for user input 

public class MVGameOfLife {

  private static Scanner scanner = new Scanner(System.in); //scanner for user input
  private static GOL game; //game 
  private static int grid[][]; //grid

  private static int delay; //variable to store the delay

  public static void main(String[] args) {
   
    setup(); //set up variables
    setSpeed(); //set the delay

    while (grid != null) {
      
      System.out.print("\033[H\033[2J"); //erase the current stdout
      System.out.flush();

      System.out.println(game); // display the grid
      pause(delay); //wait for a delay
      game.update(); //update the grid  

    }

  }

  public static void setSpeed() { //method to set the speed to run at

    String type = input("Would you like to run the game at slow, medium, or fast?"); //get user input

    switch(type) {

      case "slow":
        delay = 500; //if slow, set the delay to 500ms
        break;

      case "medium":
        delay = 250; //if medium, set the delay to 250ms
        break;

      case "fast":
        delay = 75; //if fast, set the delay to 75ms
        break;

      default:
        setSpeed(); //if none, try again
        break;

    }

  }

  public static void setup() { //method to set up the base variables

    String type = input("Would you like to load a file or use random noise? (file/random)"); //get user input

    if(type.equals("file")) { //if the command is file

      String fileName = input("please input a file name: (files located in examples/)"); //get the filename
      FileReader reader = new FileReader("examples/" + fileName); //then open the file
      grid = reader.toGrid(); //get the grid from thre reader

      if (grid.length <= 1) {

        genRandom(); //if the length is one or less, something is wrong, generate random instead

      }

    } else if (type.equals("random")) { //if random, generate random grid

      genRandom();
      

    } else {

      System.out.println("invalid command");
      setup(); //if none, tell the user and start over
      return;

    }

    game = new GOL(grid); //create a new GOL instance

  }

  public static void genRandom() { //method to generate a random grid

    String size = input("what size grid would you like?");
    int sizeInt = Integer.parseInt(size); //get the size of grid to generate

    if (sizeInt <= 1) {

      System.out.println("Grid too small");
      genRandom(); //if too small, start over
      return;

    }

    grid = new int[sizeInt][sizeInt]; //create a new grid
    for (int i = 0; i < sizeInt; i++) {

      for (int j = 0; j < sizeInt; j++) {

        grid[i][j] = (int)(Math.random() * 2); //set every value randomly

      } 

    }

  }

  public static void pause(int ms) { //method to delay the program
    try {
        Thread.sleep(ms); //wait for miliseconds
    } catch (InterruptedException e) {
        System.err.format("IOException: %s%n", e); //if error, report
    }
  }

  static String input(String message) { //create an easy way to get user input
		System.out.println(message); //print prompt
		return scanner.nextLine(); // return user input
	}
}
