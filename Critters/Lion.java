import java.awt.*;

public class Lion extends Critter {
    private int colorInt;

    public Lion() {
        colorInt = (int) (Math.random() * 3.0);
    }

    public Action getMove(CritterInfo info) {
        if (info.getFront() == Neighbor.OTHER) {
            return Action.INFECT;
        } else if (info.getFront() == Neighbor.WALL || info.getRight() == Neighbor.WALL) {
            return Action.LEFT;
        }else if (info.getFront() == Neighbor.SAME) {
            return Action.LEFT;
        } else {
            return Action.HOP;
        }
    }

    public Color getColor() {

        if (colorInt == 0) {
            return Color.RED;
        } else if (colorInt == 1) {
            return Color.GREEN;
        }else {
            return Color.BLUE;
        }
    }

    public String toString() {
        return "L";
    }
}