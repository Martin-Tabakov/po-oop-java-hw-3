package pawns;

import enums.Nation;

import java.awt.*;

public class Guard extends Pawn {
    /**
     * Constructor for a Guard object
     *
     * @param xPos   Horizontal position on the gameBoard
     * @param yPos   Vertical position on the gameBoard
     * @param nation The nation, to which the current instance belongs to
     */
    public Guard(int xPos, int yPos, Nation nation) {
        super(xPos, yPos, nation);
    }

    /**
     * Method for visualising the Guard on the gameBoard
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
