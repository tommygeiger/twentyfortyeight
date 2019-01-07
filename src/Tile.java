import java.awt.*;

public class Tile {

    private int value;

    //Value of 0 means the tile is null, don't draw it
    public Tile (){
        value = 0;
    }


    public int getValue () {
        return value;
    }

    public void setValue (int v) {
        value = v;
    }

    public Color getColor () {
        if (value == 2)
            return new Color(238, 228,217);
        if (value == 4)
            return new Color(237, 224,201);
        if (value == 8)
            return new Color(241, 177,125);
        if (value == 16)
            return new Color(243, 149,104);
        if (value == 32)
            return new Color(244, 124,99);
        if (value == 64)
            return new Color(244, 95,67);
        if (value == 128)
            return new Color(236, 206,120);
        if (value == 256)
            return new Color(236, 203,105);
        if (value == 512)
            return new Color(236, 199,90);
        if (value == 1024)
            return new Color(236, 196,76);
        if (value == 2048)
            return new Color(236, 193,64);
        else
            return new Color(0, 0,0);
    }

}
