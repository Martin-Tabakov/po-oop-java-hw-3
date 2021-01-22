package pawns;

import enums.Nation;

import java.awt.*;

public class Leader extends Pawn {
    /**
     * Constructor for a Leader object
     *
     * @param xPos   Horizontal position on the gameBoard
     * @param yPos   Vertical position on the gameBoard
     * @param nation The nation, to which the current instance belongs to
     */
    public Leader(int xPos, int yPos, Nation nation) {
        super(xPos, yPos, nation);
    }

    /**
     * Method for visualising the Leader on the gameBoard
     *
     * @param g Graphics base class
     */
    @Override
    public void render(Graphics g) {
        g.setColor(fillColor);
        g.fillRect(coordX + inTileOffset, coordY + inTileOffset, pawnSize, pawnSize);
    }
}
