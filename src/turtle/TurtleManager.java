package turtle;

import enums.Nation;

import java.awt.*;
import java.util.*;

public class TurtleManager {
    protected ArrayList<Turtle> turtles;
    private ArrayList<Integer> generatedNums;

    public TurtleManager(int count, int range) {
        this.generatedNums = new ArrayList<>();
        generatedNums.add(2);
        this.turtles = new ArrayList<>(count);
        this.turtles.add(createTurtle(range));
        this.turtles.add(createTurtle(range));
    }

    public Turtle getTurtle(Point selectedTile) {
        for (Turtle t: turtles) {
            if(t.getXPos() == selectedTile.x && t.getYPos() == selectedTile.y) return t;
        }
        return null;
    }

    private Turtle createTurtle(int maxPos) {
        return new Turtle(getDistinctPosition(maxPos),2, Nation.NEUTRAL);
    }
    public boolean remove(Turtle turtle){
        return turtles.remove(turtle);
    }
    private int getDistinctPosition(int maxPos) {
        Random random = new Random();
        do{
            int num = random.nextInt(maxPos);
            boolean exists = generatedNums.contains(num);
            if(!exists){
                generatedNums.add(num);
                return num;
            }
        }while(true);
    }

    public void paintTurtles(Graphics g){
        for (Turtle t: turtles) t.render(g);
    }
}
