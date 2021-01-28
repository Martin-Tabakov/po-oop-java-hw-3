package application;

import java.awt.*;

public abstract class Entity {

    public static final int entitySize = 100;
    public static final int offset = 50;

    protected int xPos;
    protected int yPos;
    protected int coordX;
    protected int coordY;

    protected Color fillColor;
    protected Color borderColor;

    /**
     * Constructor for a base class, used to initialise positions.
     *
     * @param xPos Horizontal position on the gameBoard
     * @param yPos Vertical position on the gameBoard
     */
    protected Entity(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.coordX = xPos * entitySize + xPos + offset;
        this.coordY = yPos * entitySize + yPos + offset;
    }

    /**
     * Returns the entity`s current position on the board.
     *
     * @return Horizontal position on board
     */
    public int getXPos() {
        return this.xPos;
    }

    /**
     * Returns the entity`s current position on the board.
     *
     * @return Vertical position on board
     */
    public int getYPos() {
        return this.yPos;
    }

    /**
     * Sets the entity`s horizontal position.
     * Updates the horizontal coordinate where the current entity is positioned.
     *
     * @param newPos New horizontal position
     */
    public void setXPos(int newPos) {
        this.xPos = newPos;
        this.coordX = xPos * entitySize + xPos + offset;
    }

    /**
     * Sets the entity`s vertical position.
     * Updates the vertical coordinate where the current entity is positioned.
     *
     * @param newPos New vertical position
     */
    public void setYPos(int newPos) {
        this.yPos = newPos;
        this.coordY = yPos * entitySize + yPos + offset;
    }
    /**
     * Method, used by instances of derived classes
     *
     * @param g Graphics base class
     */
    public abstract void render(Graphics g);
}
