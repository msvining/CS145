//class to simulate the Game of Life

public class GOL {

  private int[][] grid; //variable for the main grid
  private int sides[][] = { //vartable to store every space around a square
    {1, 0},
    {1, 1},
    {0, 1},
    {-1, 1},
    {-1, 0},
    {-1, -1},
    {0, -1},
    {1, -1}
  };

  public GOL(int[][] grid) {

    this.grid = grid; //create the GOL class

  }

  public void update() { //method to update the grid

    int clone[][] = clone(grid); //create a clone of the grid

    for (int i = 0; i < grid.length; i++) { 

      for (int j = 0; j < grid[i].length; j++) { //loop through every square in the grid

        int neighbors = checkSides(i, j); //check the surroundings of each square
        int nextType = makeMove(i, j, neighbors); //get the status of the square

        clone[i][j] = nextType; //update that point on the clone

      }

    }

    grid = clone; //set the current grid to the clone
    
  }

  public int makeMove(int x, int y, int neighbors) { //method to update a square
    
    if (grid[x][y] == 1) { //check if the square is a 1

      if(neighbors > 1 && neighbors < 4) {

        return 1; //if the square has the right amount of neighbors, it stays a 1

      } else {
    
        return 0; //if not, it becomes a 0

      }

    } else {

      if (neighbors == 3) {

        return 1; //if a 0 has exactly 3 neighbors, it becomes a 1

      }

    }

    return 0; //return 0

  }

  public int[][] clone(int grid[][]) { //method to clone a grid

    int newGrid[][] = new int[grid.length][grid[0].length];
    
    for (int i = 0; i < grid.length; i++) {

      for (int j = 0; j < grid[i].length; j++) {

        newGrid[i][j] = grid[i][j]; //loop through and copy every value over

      }

    }

    return newGrid; //return the new grid

  }

  public int checkSides(int x, int y) { //method to get the neighbor count

    int neighbors = 0; //variable to store neighbors

    for (int i = 0; i < sides.length; i++) { //loop through the surrounding area

      int newX = x + sides[i][0];
      int newY = y + sides[i][1];

      if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[newX].length) { //check if the new values are in the grid

        if(grid[newX][newY] == 1) {

          neighbors++; //if a nearby value is a 1, increment neighbors

        }

      }

    }

    return neighbors; //return neighbors

  }

  public String toString() { //method to display as a string

    String display = ""; //string to display

    for (int i = 0; i < grid.length; i++) {

      for (int j = 0; j < grid[i].length; j++) { //loop through the grid

        if (grid[i][j] == 0) {

          display += "\u001B[40m  "; //if 0, add a black square

        } else {

          display += "\u001B[47m  "; //if 1, return a white square

        }

      }

      display += "\033[0m\n"; //cleare the coloring at the end of every line

    }

    return display; //return the string

  }
 
}
