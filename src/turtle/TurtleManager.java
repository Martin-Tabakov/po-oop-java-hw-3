package turtle;

import enums.Nation;

import java.awt.*;
import java.util.*;

public class TurtleManager {
    protected ArrayList<Turtle> turtles;
    private ArrayList<Integer> generatedNums;

    /**
     * Constructor, believe it or not
     *
     * @param count the numbers of turtles to be created
     * @param range the furthest a turtle can be placed to the right
     */
    public TurtleManager(int count, int range) {
        this.generatedNums = new ArrayList<>();
        generatedNums.add(2);
        this.turtles = new ArrayList<>(count);
        this.turtles.add(createTurtle(range));
        this.turtles.add(createTurtle(range));
    }

    /**
     * Returns a turtle
     *
     * @param selectedTile The tile that a turtle should occupy
     * @return The found turtle instance, if not found - null
     */
    public Turtle getTurtle(Point selectedTile) {
        for (Turtle t : turtles) {
            if (t.getXPos() == selectedTile.x && t.getYPos() == selectedTile.y) return t;
        }
        return null;
    }

    /**
     * Creates a turtle instance
     *
     * @param maxPos the furthest a turtle can be placed to the right
     * @return The newly created turtle instance
     */
    private Turtle createTurtle(int maxPos) {
        return new Turtle(getDistinctPosition(maxPos), 2, Nation.NEUTRAL);
    }

    /**
     * Removes a turtle
     *
     * @param turtle The turtle to be removed
     * @return true if the removal is successful, false otherwise
     */
    public boolean remove(Turtle turtle) {
        return turtles.remove(turtle);
    }

    /**
     * Generates a distinct position for a turtle to occupy
     *
     * @param maxPos the furthest a turtle can be placed to the right
     * @return horizontal placement position for a turtle
     */
    private int getDistinctPosition(int maxPos) {
        Random random = new Random();
        do {
            int num = random.nextInt(maxPos);
            boolean exists = generatedNums.contains(num);
            if (!exists) {
                generatedNums.add(num);
                return num;
            }
        } while (true);
    }

    /**
     * Prints all turtles on the game board
     *
     * @param g Graphics component
     */
    public void paintTurtles(Graphics g) {
        for (Turtle t : turtles) t.render(g);
    }
}
