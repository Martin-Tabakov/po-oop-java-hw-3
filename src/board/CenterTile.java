package board;

import java.awt.*;

public class CenterTile extends BoardTile {

    Color circleFill;
    Color circleBorder;

    /**
     * Constructor, used for instancing the center tile of the board
     *
     * @param xPos         Horizontal position on the gameBoard
     * @param yPos         Vertical position on the gameBoard
     * @param borderColor  Color, used for the border of the tile
     * @param fillColor    Color, used for filling the tile
     * @param circleBorder Color, used for the border of the circle within the tile
     * @param circleFill   Color, used for filling the circle within the tile
     */
    public CenterTile(int xPos, int yPos, Color borderColor, Color fillColor, Color circleBorder, Color circleFill) {
        super(xPos, yPos, borderColor, fillColor);
        this.circleBorder = circleBorder;
        this.circleFill = circleFill;
    }

    /**
     * Method, used for drawing the tile on the screen
     *
     * @param g Graphics base class
     */
    @Override
    public void render(Graphics g) {
        super.render(g);
        int ovalSize = entitySize / 4;
        g.setColor(circleBorder);
        g.fillOval(coordX + ovalSize - 1, coordY + ovalSize - 1, ovalSize * 2 + 2, ovalSize * 2 + 2);
        g.setColor(circleFill);
        g.fillOval(coordX + ovalSize, coordY + ovalSize, ovalSize * 2, ovalSize * 2);
    }

}