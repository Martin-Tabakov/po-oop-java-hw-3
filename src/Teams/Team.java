package Teams;

import enums.Nation;
import pawns.Guard;
import pawns.Leader;
import pawns.Pawn;

import java.awt.*;
import java.util.ArrayList;

public class Team {

    private ArrayList<Pawn> members;

    /**
     * Constructor for creating a Team instance, containing information about one team
     *
     * @param nation        The team`s nation. Used For colouring of the team`s members
     * @param rowPosition   Specifies on which row the members to be positioned on the board
     * @param isLeaderFirst Indicates whether the position of the leader is on the left if true or right is false
     */
    public Team(Nation nation, int rowPosition, boolean isLeaderFirst) {
        this.members = setMembers(nation, rowPosition, isLeaderFirst);
    }

    /**
     * Initializes the members of the team instance
     *
     * @param nation        The team`s nation. Used For colouring of the team`s members
     * @param rowPosition   Specifies on which row the members to be positioned on the board
     * @param isLeaderFirst Indicates whether the position of the leader is on the left if true or right is false
     * @return List of all members in the current team
     */
    private ArrayList<Pawn> setMembers(Nation nation, int rowPosition, boolean isLeaderFirst) {
        ArrayList<Pawn> pawns = new ArrayList<>();

        int dir = (isLeaderFirst) ? 4 : 0;
        int i;
        for (i = 0; i < 4; i++) pawns.add(new Guard(Math.abs(dir - i), rowPosition, nation));
        pawns.add(new Leader(Math.abs(dir - i), rowPosition, nation));

        return pawns;
    }

    /**
     * Searches for a member on a specified position
     *
     * @param tile The tile on which the pawn is to be located
     * @return Reference to the pawn on the tile if found, otherwise null
     */
    protected Pawn getPawn(Point tile) {
        for (Pawn member : members) {
            if (member.getXPos() == tile.x && member.getYPos() == tile.y) return member;
        }
        return null;
    }

    /**
     * Removes a pawn from this team instance
     *
     * @param pawn The pawn to be removed
     * @return true if the removal is successful, otherwise - false
     */
    public boolean remove(Pawn pawn) {
        return members.remove(pawn);
    }

    /**
     * Renders all members of the current team on the board
     *
     * @param g Graphics component
     */
    public void render(Graphics g) {
        for (Pawn member : members) member.render(g);
    }
}
