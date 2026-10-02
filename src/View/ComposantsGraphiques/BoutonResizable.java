package View.ComposantsGraphiques;

import javax.swing.*;
import java.awt.*;

public class BoutonResizable extends JButton {
    public static final int SQUARE=0;
    public static final int RECTANGLE=1;

    private Dimension dimension;
    private Image logo, logoHover;
    private int mode;

    public BoutonResizable(int mode, Image logo, Image logoHover) {
        super();
        this.mode = mode;
        this.logo = logo;
        this.logoHover = logoHover;
        setFocusPainted(false);  // Supprime l'effet de focus
        setContentAreaFilled(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR)); //main comme curseur (indique clickable)
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension size = super.getPreferredSize();
        int dim = Math.min(size.width, size.height);
        if (mode==SQUARE){
            dimension = new Dimension(dim, dim);
        }else{
            dimension = new Dimension(dim*2 + 5,dim);
        }
        return dimension;
    }

    @Override
    public void setBounds(int x, int y, int width, int height) {
        int size = Math.min(width, height);
        if (mode == 0) {
            super.setBounds(x + (width - size) / 2, y + (height - size) / 2, size, size);
        } else {
            int w = size * 2 + 5;
            int h = size;
            int offsetX = x + (width - w) / 2;
            int offsetY = y + (height - h) / 2;
            super.setBounds(offsetX, offsetY, w, h);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image img = getModel().isRollover() ? logoHover : logo;
        if (img != null) {
            g.drawImage(img, 0, 0, getWidth()-1, getHeight()-1, this);
        }
    }
}
