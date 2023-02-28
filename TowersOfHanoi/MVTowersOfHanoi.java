// Marlen Vining
// 16/02/23
// Lab #6
//
//This program will do the following:
//Solve the towers of hanoi puzzle

import java.util.ArrayList; //import arraylists

public class MVTowersOfHanoi { //main class

  //even without a time delay, 64 takes a very long time and cannot be properly displayed without a vary small font size, the size is set to 16 for demonstration but will still work at 64
  private static int size = 16; //set size of towers

  private static ArrayList<Integer> peg1 = new ArrayList<Integer>(); //create arraylist for each tower
  private static ArrayList<Integer> peg2 = new ArrayList<Integer>();
  private static ArrayList<Integer> peg3 = new ArrayList<Integer>();

  public static void main(String args[]) {

    for (int i = 0; i < size; i++) { 

      peg1.add(size - i); //fill the first tower

    }

    move(peg1.size(), peg1, peg3, peg2); //start moving the discs

  }

  private static void printRow(ArrayList<Integer> tower, int i) { //method to print a row

      if (i >= tower.size()) { //if higher than the ammount of pegs
   
        for (int j = 0; j < size * 2; j++) { //for size * 2

          System.out.print(" "); //print spaces

        }
        System.out.print("|"); //print divider line
        return; //end the function early

      }

      int val = tower.get(i) * 2; //get the width of the disc at i
      int spaces = (size * 2 - val) / 2; //get the spaces needed to center it

      for (int j = 0; j < spaces; j++) { 

        System.out.print(" "); //print out the spaces

      }

      for (int j = 0; j < val; j++) {

        System.out.print("@"); //print out the disc

      }

      for (int j = 0; j < spaces; j++) {

        System.out.print(" "); //print out the spaces on the other side

      }

      System.out.print("|"); //print out the divider line

  }

  private static void display() { //method to display pegs
    
    System.out.print("\033[H\033[2J"); //erase the current stdout
    System.out.flush();

    for(int i = 0; i < size; i++) { //loop through each section

      System.out.print("|"); //print out the divider line
      printRow(peg1, size - i - 1); //display each section of each peg
      printRow(peg2, size - i - 1);
      printRow(peg3, size - i - 1);
      System.out.println(""); //newline
    
    }

    pause(10); //pause for 10 ms

  }

  private static void move(int n, ArrayList source, ArrayList target, ArrayList aux){ //method to move discs

    if (n > 0) { //if not done

      move(n - 1, source, aux, target); //call itself with aux and target switched 

      target.add(source.remove(source.size() - 1)); //move the top of source to tge top of target

      display(); //display the pegs

      move(n - 1, aux, target, source); //call itself with order reversed

    }

  }

  public static void pause(int ms) { //method to delay the program
    try {
        Thread.sleep(ms); //wait for miliseconds
    } catch (InterruptedException e) {
        System.err.format("IOException: %s%n", e); //if error, report
    }
  }

}
