package board;

import Teams.TeamManager;
import enums.Colors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class GameBoard extends JFrame implements MouseListener {

    ArrayList<BoardTile> tiles;
    TeamManager teamManager;


    /**
     * Constructor used for setting the main window and all objects used in the game
     */
    public GameBoard() {
        this.teamManager = new TeamManager();
        this.tiles = setTiles();
        this.setSize(600, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.addMouseListener(this);
    }

    /**
     * Method, used for drawing the entities on the screen
     *
     * @param g Graphics base class
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        paintTiles(g);
        teamManager.paintTeams(g);
    }

    /**
     * Method, used for drawing the tiles on the screen
     *
     * @param g Graphics base class
     */
    private void paintTiles(Graphics g) {
        for (BoardTile tile : tiles) {
            tile.render(g);
        }
    }


    /**
     * Creates all columns in the board
     *
     * @return Initialised board
     */
    private ArrayList<BoardTile> setTiles() {
        ArrayList<BoardTile> tempTiles = new ArrayList<>();
        tempTiles.addAll(getTileColumn(0, false));
        tempTiles.addAll(getTileColumn(1, true));
        tempTiles.addAll(getCenterColumn());
        tempTiles.addAll(getTileColumn(3, true));
        tempTiles.addAll(getTileColumn(4, false));
        return tempTiles;
    }

    /**
     * Creates a column of tiles. Used for all columns, besides the center one
     *
     * @param xPos      Horizontal position on the gameBoard
     * @param isReverse Value indicating the orientation of the column
     * @return ArrayList with the tiles, contained in a column
     */
    private ArrayList<BoardTile> getTileColumn(int xPos, boolean isReverse) {
        int yPos = 0;
        int dir = (isReverse) ? 4 : 0;

        ArrayList<BoardTile> standardTileColumn = new ArrayList<>();

        standardTileColumn.add(new BoardTile(xPos, Math.abs(dir - yPos++), Colors.GRAPHITE.color, Colors.PEACH.color));
        standardTileColumn.add(new BoardTile(xPos, Math.abs(dir - yPos++), Colors.GRAPHITE.color, Colors.GRAY.color));
        standardTileColumn.add(new BoardTile(xPos, Math.abs(dir - yPos++), Colors.GRAPHITE.color, Colors.WHITE.color));
        standardTileColumn.add(new BoardTile(xPos, Math.abs(dir - yPos++), Colors.GRAPHITE.color, Colors.GRAY.color));
        standardTileColumn.add(new BoardTile(xPos, Math.abs(dir - yPos), Colors.GRAPHITE.color, Colors.GRAPHITE.color));

        return standardTileColumn;
    }

    /**
     * Sets the tiles in the center column of the board
     *
     * @return ArrayList with the tiles, contained in the center column
     */
    private ArrayList<BoardTile> getCenterColumn() {
        int yPos = 0;
        int xPos = 2;

        ArrayList<BoardTile> centerTileColumn = new ArrayList<>();

        centerTileColumn.add(new BoardTile(xPos, yPos++, Colors.GRAPHITE.color, Colors.WHITE.color));
        centerTileColumn.add(new BoardTile(xPos, yPos++, Colors.GRAPHITE.color, Colors.WHITE.color));
        centerTileColumn.add(new CenterTile(xPos, yPos++, Colors.GRAPHITE.color, Colors.WHITE.color, Colors.GRAPHITE.color, Colors.GRAY.color));
        centerTileColumn.add(new BoardTile(xPos, yPos++, Colors.GRAPHITE.color, Colors.WHITE.color));
        centerTileColumn.add(new BoardTile(xPos, yPos, Colors.GRAPHITE.color, Colors.WHITE.color));

        return centerTileColumn;
    }


    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (teamManager.movePawn(e)) this.repaint();
    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {

    }

    /**
     * Invoked when a mouse button has been released on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     * Invoked when the mouse enters a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * Invoked when the mouse exits a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }
}
