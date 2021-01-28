package enums;

import java.awt.*;

public enum Nation {
    YELLOW(Colors.GREEN.color, Colors.YELLOW.color),
    GREEN(Colors.YELLOW.color, Colors.GREEN.color),
    NEUTRAL(Colors.RED.color, Colors.TRANSPARENT.color);

    public Color borderColor;
    public Color fillColor;

    /**
     * Constructor, used for setting all enum values
     *
     * @param borderColor Color class instance
     * @param fillColor   Color class instance
     */
    Nation(Color borderColor, Color fillColor) {
        this.borderColor = borderColor;
        this.fillColor = fillColor;
    }
}
