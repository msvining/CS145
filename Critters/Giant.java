import java.awt.*;

public class Giant extends Critter {
    private int counter;

    public Giant() {
        counter = 0;
    }

    public Action getMove(CritterInfo info) {
        counter++;

        if (info.getFront() == Neighbor.OTHER) {
            return Action.INFECT;
        } else if (info.getFront() ==Neighbor.EMPTY) {
            return Action.HOP;
        } else {
            return Action.RIGHT;
        }
    }

    public Color getColor() {
        return Color.GRAY;
    }

    public String toString() {

        if (counter <= 6) {
            return "fee";
        } else if (counter <= 12) {
            return "fie";
        } else if (counter <= 18){
            return "foe";
        } else {
            if (counter == 24) {
                counter = 0;
            }
            return "fum";
        }
    }
}