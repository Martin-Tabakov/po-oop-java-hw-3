package board;

import application.Entity;

import java.awt.*;

public class BoardTile extends Entity {
    /**
     * Constructor for the tiles, constructing the gameBoard
     *
     * @param xPos        Horizontal position on the gameBoard
     * @param yPos        Vertical position on the gameBoard
     * @param borderColor Color, used for the border of the tile
     * @param fillColor   Color, used for filling the tile
     */
    public BoardTile(int xPos, int yPos, Color borderColor, Color fillColor) {
        super(xPos, yPos);
        this.borderColor = borderColor;
        this.fillColor = fillColor;
    }

    /**
     * Method, used for drawing the tile on the screen
     *
     * @param g Graphics base class
     */
    public void render(Graphics g) {
        g.setColor(borderColor);
        g.drawRect(coordX - 1, coordY - 1, entitySize + 1, entitySize + 1);
        g.setColor(fillColor);
        g.fillRect(coordX, coordY, entitySize, entitySize);
    }
}
