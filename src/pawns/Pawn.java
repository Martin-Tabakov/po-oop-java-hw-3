package pawns;

import application.Entity;
import enums.Nation;


public abstract class Pawn extends Entity{

    protected final int sizeScale = 2;
    protected final int offsetScale = 2;
    protected int inTileOffset;
    protected int pawnSize;
    public final Nation nation;

    /**
     * @param xPos   Horizontal position on the gameBoard
     * @param yPos   Vertical position on the gameBoard
     * @param nation Enum value indicating the side to which the current pawn belongs to
     */
    protected Pawn(int xPos, int yPos, Nation nation) {
        super(xPos, yPos);
        this.nation = nation;
        this.borderColor = nation.borderColor;
        this.fillColor = nation.fillColor;
        this.inTileOffset = offset / offsetScale;
        this.pawnSize = entitySize / sizeScale;
    }


}
