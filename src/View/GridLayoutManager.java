package View;

import java.awt.*;

public class GridLayoutManager extends GridBagConstraints {
    public GridLayoutManager(){
        super();
    }

    public void updateGBC(int x, int y, double wX, double wY, Insets insets, int anchor, int fill) {
        gridx = x;
        gridy = y;
        weightx = wX;
        weighty = wY;
        this.insets = insets;
        this.anchor = anchor;
        this.fill = fill;
    }
}
