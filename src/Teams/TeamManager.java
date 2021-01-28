package Teams;

import application.Entity;
import enums.Nation;
import pawns.Pawn;
import turtle.Turtle;
import turtle.TurtleManager;

import java.awt.*;
import java.awt.event.MouseEvent;

public class TeamManager {

    Team greenTeam;
    Team yellowTeam;
    Pawn selectedPawn = null;
    TurtleManager turtleManager;


    /**
     * Constructor for a TeamManager instance.Used for team interactions. Initializes two teams - Yellow and Green
     */
    public TeamManager() {
        this.yellowTeam = new Team(Nation.YELLOW, 0, false);
        this.greenTeam = new Team(Nation.GREEN, 4, true);
        this.turtleManager = new TurtleManager(2, 5);
    }

    /**
     * Renders the two teams on the board
     *
     * @param g Graphics component
     */
    public void paintTeams(Graphics g) {
        yellowTeam.render(g);
        greenTeam.render(g);
        turtleManager.paintTurtles(g);
    }

    /**
     * Controls the mouse click interaction with the pawns on the board.
     *
     * @param e the event to be processed
     * @return true if a move is successfully made, otherwise false
     */
    public boolean movePawn(MouseEvent e) {
        Point selectedTile = getPosition(e);

        if (!isInBoundary(selectedTile)) return false;

        Pawn result = getPawnOnSelectedTile(selectedTile);

        if (movePawn(result, selectedTile)) return true;

        if (selectedPawn != null && result != null)
            if (calculateCollision(selectedTile)) {
                selectedPawn = null;
                return true;
            }

        return changeSelectedPawn(result);

    }

    /**
     * Determinate if there is a winner in the game
     * @return The Leader that occupies the winning spot
     */
    public Pawn getWinner() {
        Pawn winner = getPawnOnSelectedTile(new Point(2, 2));
        if (winner == null) return null;
        if (winner.getClass().getSimpleName().equals("Leader")) return winner;
        else return null;
    }

    /**
     * Contains logic for the collision between a guard and a turtle
     * @param selectedTile The destination of the guard
     * @return true if a removal is made, if not - false
     */
    private boolean calculateCollision(Point selectedTile) {
        if (selectedPawn.getClass().getSimpleName().equals("Guard"))
            if (turtleManager.getTurtle(selectedTile) != null) {
                turtleManager.remove(turtleManager.getTurtle(selectedTile));
                removePawn(selectedPawn);
                System.out.println("Should have removed turtle");
                return true;
            }
        return false;
    }

    /**
     * Removes a pawn from whichever team it belongs to
     * @param selectedPawn The pawn to be removed
     */
    private void removePawn(Pawn selectedPawn) {
        if (!yellowTeam.remove(selectedPawn)) greenTeam.remove(selectedPawn);
    }

    /**
     * Moves a pawn if possible
     * @param result The pawn
     * @param selectedTile The move-to tile
     * @return true if a board modification is made
     */
    private boolean movePawn(Pawn result, Point selectedTile) {
        if (result == null && selectedPawn != null) {
            if (validateMove(selectedTile, selectedPawn)) {
                movePawn(selectedTile, selectedPawn);
                selectedPawn = null;
                return true;
            }
        }
        return false;
    }

    /**
     * Changes the currently selected pawn
     * @param result the newly selected pawn
     * @return false, because no board modification is made
     */
    private boolean changeSelectedPawn(Pawn result) {
        if (result != null) {
            selectedPawn = result;
            System.out.printf("Selected %s %s\n", selectedPawn.nation, selectedPawn.getClass().getSimpleName());
        }
        return false;
    }

    /**
     * Changes the position of a pawn to a new position on the board
     *
     * @param selectedTile The position of the board for the pawn to be moved on
     * @param selectedPawn The pawn whose position will be changed
     */
    private void movePawn(Point selectedTile, Pawn selectedPawn) {
        System.out.println("Moving pawn");
        selectedPawn.setXPos(selectedTile.x);
        selectedPawn.setYPos(selectedTile.y);
    }

    /**
     * Validates a new position for a pawn to be placed on
     *
     * @param selectedTile The tile that will be checked whether it is a valid move-to position for a pawn
     * @param selectedPawn The pawn whose new position will be checked as valid or invalid
     * @return true if the new position for the pawn is valid, otherwise - false
     */
    private boolean validateMove(Point selectedTile, Pawn selectedPawn) {
        return switch (selectedPawn.getClass().getSimpleName()) {
            case "Leader" -> isValidLeaderMove(selectedTile, selectedPawn);
            case "Guard" -> isValidGuardMove(selectedTile, selectedPawn);
            case "Turtle" -> isValidTurtleMove();
            default -> false;
        };
    }

    /**
     * Checks whether a turtle can move. It can`t
     * @return false, because a turtle cant move
     */
    private boolean isValidTurtleMove() {
        System.out.println("Turtle can`t move!");
        return false;
    }

    /**
     * Checks whether a new position for a Leader pawn is valid
     *
     * @param selectedTile The position to be checked
     * @param selectedPawn The leader pawn
     * @return true if the new position is a valid one, otherwise - false
     */
    private boolean isValidLeaderMove(Point selectedTile, Pawn selectedPawn) {


        if (selectedTile.x == selectedPawn.getXPos()) {
            if (selectedTile.y < selectedPawn.getYPos()) return verifyDestUp(selectedTile, selectedPawn);
            if (selectedTile.y > selectedPawn.getYPos()) return verifyDestDown(selectedTile, selectedPawn);
        }
        if (selectedTile.y == selectedPawn.getYPos()) {
            if (selectedTile.x < selectedPawn.getXPos()) return verifyDestLeft(selectedTile, selectedPawn);
            if (selectedTile.x > selectedPawn.getXPos()) return verifyDestRight(selectedTile, selectedPawn);
        }
        return false;
    }

    /**
     * Verifies if a move upwards for a Leader pawn is valid
     *
     * @param selectedTile The position to be checked
     * @param selectedPawn The leader pawn
     * @return true if the new position is a valid one, otherwise - false
     */
    private boolean verifyDestUp(Point selectedTile, Pawn selectedPawn) {
        Point possibleDest = null;
        int i;

        for (i = selectedPawn.getYPos() - 1; i > -1; i--) {
            if (getPawnOnSelectedTile(new Point(selectedPawn.getXPos(), i)) != null) {
                possibleDest = new Point(selectedPawn.getXPos(), ++i);
                break;
            }
        }
        if (possibleDest == null) return selectedTile.y == 0;

        return possibleDest.y == selectedTile.y;
    }

    /**
     * Verifies if a move downwards for a Leader pawn is valid
     *
     * @param selectedTile The position to be checked
     * @param selectedPawn The leader pawn
     * @return true if the new position is a valid one, otherwise - false
     */
    private boolean verifyDestDown(Point selectedTile, Pawn selectedPawn) {
        Point possibleDest = null;
        int i;

        for (i = selectedPawn.getYPos() + 1; i < 5; i++) {
            if (getPawnOnSelectedTile(new Point(selectedPawn.getXPos(), i)) != null) {
                possibleDest = new Point(selectedPawn.getXPos(), --i);
                break;
            }
        }
        if (possibleDest == null) return selectedTile.y == 4;

        return possibleDest.y == selectedTile.y;
    }

    /**
     * Verifies if a move to the right of the current position for a Leader pawn is valid
     *
     * @param selectedTile The position to be checked
     * @param selectedPawn The leader pawn
     * @return true if the new position is a valid one, otherwise - false
     */
    private boolean verifyDestRight(Point selectedTile, Pawn selectedPawn) {
        Point possibleDest = null;
        int i;

        for (i = selectedPawn.getXPos() + 1; i < 5; i++) {
            if (getPawnOnSelectedTile(new Point(i, selectedPawn.getYPos())) != null) {
                possibleDest = new Point(--i, selectedPawn.getYPos());
                break;
            }
        }
        if (possibleDest == null) return selectedTile.x == 4;

        return possibleDest.x == selectedTile.x;
    }

    /**
     * Verifies if a move to the left of the current position for a Leader pawn is valid
     *
     * @param selectedTile The position to be checked
     * @param selectedPawn The leader pawn
     * @return true if the new position is a valid one, otherwise - false
     */
    private boolean verifyDestLeft(Point selectedTile, Pawn selectedPawn) {
        Point possibleDest = null;
        int i;

        for (i = selectedPawn.getXPos() - 1; i > -1; i--) {
            if (getPawnOnSelectedTile(new Point(i, selectedPawn.getYPos())) != null) {
                possibleDest = new Point(++i, selectedPawn.getYPos());
                break;
            }
        }
        if (possibleDest == null) return selectedTile.x == 0;

        return possibleDest.x == selectedTile.x;
    }

    /**
     * Checks whether a new position for a Guard pawn is valid
     *
     * @param selectedTile The position to be checked
     * @param selectedPawn The guard pawn
     * @return true if the new position is a valid one, otherwise - false
     */
    private boolean isValidGuardMove(Point selectedTile, Pawn selectedPawn) {
        return isHorizontallyAdjacent(selectedTile, selectedPawn) ||
                isVerticallyAdjacent(selectedTile, selectedPawn);
    }

    /**
     * Checks whether a horizontal reposition for a Guard pawn is valid
     *
     * @param selectedTile The position to be checked
     * @param selectedPawn The guard pawn
     * @return true if the new position is a valid one, otherwise - false
     */
    private boolean isHorizontallyAdjacent(Point selectedTile, Pawn selectedPawn) {
        return Math.abs(selectedTile.x - selectedPawn.getXPos()) == 1 &&
                Math.abs(selectedTile.y - selectedPawn.getYPos()) == 0;
    }

    /**
     * Checks whether a vertical reposition for a Guard pawn is valid
     *
     * @param selectedTile The position to be checked
     * @param selectedPawn The guard pawn
     * @return true if the new position is a valid one, otherwise - false
     */
    private boolean isVerticallyAdjacent(Point selectedTile, Pawn selectedPawn) {
        return Math.abs(selectedTile.x - selectedPawn.getXPos()) == 0 &&
                Math.abs(selectedTile.y - selectedPawn.getYPos()) == 1;
    }

    /**
     * Checks whether a pawn is located on a specified tile on the board
     *
     * @param selectedTile Position of the pawn
     * @return Reference to the pawn instance if a pawn is located on the specified position. If no pawn is found returns null
     */
    private Pawn getPawnOnSelectedTile(Point selectedTile) {
        Pawn resYellow = yellowTeam.getPawn(selectedTile);
        Pawn resGreen = greenTeam.getPawn(selectedTile);
        Turtle turtle = turtleManager.getTurtle(selectedTile);

        if (resYellow != null) {
            return resYellow;
        }
        if (resGreen != null) {
            return resGreen;
        }
        return turtle;
    }

    /**
     * Checks whether a click is made within the boundaries of the board
     *
     * @param selection Position where the click was made
     * @return true if the click is on a board tile, false if the click is made outside of the board
     */
    private boolean isInBoundary(Point selection) {
        return (selection.y > -1 && selection.y < 5) &&
                (selection.x > -1 && selection.x < 5);
    }

    /**
     * Converts a click position in tile location from pixel coordinates
     *
     * @param e e the event to be processed
     * @return The converted tile position of the click that was made.
     * Note: A invalid position could be returned if the click was made outside the board
     */
    private Point getPosition(MouseEvent e) {
        Point point = e.getPoint();
        return new Point(
                (point.x + Entity.offset) / Entity.entitySize - 1,
                (point.y + Entity.offset) / Entity.entitySize - 1);
    }
}
