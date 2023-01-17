import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Orca extends Critter {
    private int counter;
    private boolean left;

    public Orca() {
        counter = 0;
        
        if (Math.random() >= 0.5) {
            left = true;
        } else {
            left = false;
        }
    }

    public Action getMove(CritterInfo info) {
        counter++;
            
        if (counter > 4) {
            counter = 0;
        }


        if (info.getFront() == Neighbor.OTHER) {
            
            if (Math.random() >= 0.4) {
                left = !left;
            }
            return Action.INFECT;


        } else if (info.getFront() == Neighbor.EMPTY) {
            return Action.HOP;
        } else {
            
            if (left) {
                return Action.LEFT;
            } else {
                return Action.RIGHT;
            }

        }

    }

    public Color getColor() {

        switch (counter) {
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
        return "MV";
    }
}
