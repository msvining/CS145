// Marlen Vining
// 1/11/22
// Lab #2
//
//This program will do the following:
//Simulate a simple environment where 
//critters fight for control
//

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Orca extends Critter {
    private int counter; //define counter to count infects
    private boolean left; //define left to decide direction

    public Orca() {
        counter = 0; //set counter to 0
        
        if (Math.random() >= 0.5) {
            left = true; // 50% chance of starting as true
        } else {
            left = false; // 50% chance of starting false
        }
    }

    public Action getMove(CritterInfo info) { //method to get the next move

        if (info.getFront() == Neighbor.OTHER) { //allways check if other is in front first
            counter++; //if so increment counter
            
            if (counter > 4) {
                counter = 0; //if counter is too high, loop back around
            }
            
            if (Math.random() >= 0.4) {
                left = !left; //radnomly decide to change direction
            }
            return Action.INFECT; //infect the critter in front


        } else if (info.getFront() == Neighbor.EMPTY) { //if possible, hop
            return Action.HOP;

        } else {
            
            if (left) { //finaly, either turn right or left based off of the value of left
                return Action.LEFT;
            } else {
                return Action.RIGHT;
            }

        }

    }

    public Color getColor() { //method to get color

        switch (counter) { //based off of counter, set color
            case 0:
                return Color.RED;

            case 1:
                return Color.ORANGE;

            case 2:
                return Color.YELLOW;

            case 3:
                return Color.GREEN;

            default:
                return Color.BLUE;
        }
    }

    public String toString() {
        return "MV"; //allways return MV as string
    }
}
