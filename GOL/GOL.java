public class GOL {

  private int[][] grid;
  private int sides[][] = {
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

    this.grid = grid;

  }

  public void update() {

    int clone[][] = clone(grid);


    for (int i = 0; i < grid.length; i++) {

      for (int j = 0; j < grid[i].length; j++) {

        int neighbors = checkSides(i, j);
        int nextType = makeMove(i, j, neighbors);

        clone[i][j] = nextType;

      }

    }

    grid = clone;
    
  }

    public int makeMove(int x, int y, int neighbors) {

    
    if (grid[x][y] == 1) {

      if(neighbors > 1 && neighbors < 4) {

        return 1;

      } else {
    
        return 0;

      }

    } else {

      if (neighbors == 3) {

        return 1;

      }

    }

    return 0;

  }

  public int[][] clone(int grid[][]) {

    int newGrid[][] = new int[grid.length][grid[0].length];
    
    for (int i = 0; i < grid.length; i++) {

      for (int j = 0; j < grid[i].length; j++) {

        newGrid[i][j] = grid[i][j];

      }

    }

    return newGrid;

  }

  public int checkSides(int x, int y) {

    int neighbors = 0;

    for (int i = 0; i < sides.length; i++) {

      int newX = x + sides[i][0];
      int newY = y + sides[i][1];

      if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[newX].length) {

        if(grid[newX][newY] == 1) {

          neighbors++;

        }

      }

    }

    return neighbors;

  }

  public String toString() {

    String display = "";

    for (int i = 0; i < grid.length; i++) {

      for (int j = 0; j < grid[i].length; j++) {

        if (grid[i][j] == 0) {

          display += "\u001B[40m  ";

        } else {

          display += "\u001B[47m  ";

        }

      }

      display += "\033[0m\n";

    }

    return display;

  }
 
}
