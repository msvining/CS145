import java.util.Scanner; 

public class MVGameOfLife {

  private static Scanner scanner = new Scanner(System.in);
  private static GOL game;
  private static int grid[][];

  private static int delay;

  public static void main(String[] args) {
   
    setup();
    setSpeed();

    while (grid != null) {
      
      System.out.print("\033[H\033[2J"); //erase the current stdout
      System.out.flush();

      System.out.println(game);
      pause(delay);
      game.update();    

    }

  }

  public static void setSpeed() {

    String type = input("Would you like to run the game at slow, medium, or fast?");

    switch(type) {

      case "slow":
        delay = 500;
        break;

      case "medium":
        delay = 250;
        break;

      case "fast":
        delay = 75;
        break;

      default:
        delay = 250;
        System.out.println("Invalid command. Running at medium speed.");
        break;

    }

  }

  public static void setup() {

    String type = input("Would you like to load a file or use random noise? (file/random)");

    if(type.equals("file")) {

      String fileName = input("please input a file name: (files located in examples/)");
      FileReader reader = new FileReader("examples/" + fileName);
      grid = reader.toGrid();

      if (grid.length == 1) {

        genRandom();

      }

    } else if (type.equals("random")) {

      genRandom();
      

    } else {

      System.out.println("invalid command");

    }

    game = new GOL(grid);

  }

  public static void genRandom() {

    String size = input("what size grid would you like?");
    int sizeInt = Integer.parseInt(size);

    if (sizeInt <= 1) {

      System.out.println("Grid too small");
      genRandom();
      return;

    }

    grid = new int[sizeInt][sizeInt];
    for (int i = 0; i < sizeInt; i++) {

      for (int j = 0; j < sizeInt; j++) {

        grid[i][j] = (int)(Math.random() * 2);

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
