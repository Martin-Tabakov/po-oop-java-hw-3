package turtle;

import pawns.*;
import enums.*;

import java.awt.*;

public class Turtle extends Pawn {
    /**
     * Constructor for a Turtle object
     *
     * @param xPos   Horizontal position on the gameBoard
     * @param yPos   Vertical position on the gameBoard
     */
    public Turtle(int xPos, int yPos,Nation nation) {
        super(xPos, yPos,nation);

    }

    /**
     * Method, used by instances of derived classes
     *
     * @param g Graphics base class
     */
    @Override
    public void render(Graphics g) {

        int sizeDiff = 5;
        g.setColor(borderColor);
        g.fillOval(coordX + inTileOffset, coordY + inTileOffset, pawnSize, pawnSize);
        g.setColor(fillColor);
        g.fillOval(
                coordX + inTileOffset + sizeDiff,
                coordY + inTileOffset + sizeDiff,
                pawnSize - sizeDiff * 2,
                pawnSize - sizeDiff * 2);
    }
}
