package View.Panels;

import Controller.Configuration;

import javax.swing.*;
import java.awt.*;


public class BackgroundPanel extends JPanel {
    private final Image backgroundImage;

    public BackgroundPanel(Image backgroundImage) {
        setOpaque(false);
        this.backgroundImage = backgroundImage;
        if ( backgroundImage == Configuration.BackGroundMenuFullScreenBg){
            setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 5));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (backgroundImage != null) {
            int imgWidth = backgroundImage.getWidth( this);
            int imgHeight = backgroundImage.getHeight( this);
            int panelWidth = getWidth();
            int panelHeight = getHeight();

            // Calculer le point (x, y) en haut à gauche du rectangle source (à centrer)
            int sx = (imgWidth - panelWidth) / 2;
            int sy = (imgHeight - panelHeight) / 2;

            // S'assurer de ne pas dépasser les bords
            sx = Math.max(0, sx);
            sy = Math.max(0, sy);
            int sx2 = Math.min(sx + panelWidth, imgWidth);
            int sy2 = Math.min(sy + panelHeight, imgHeight);

            // Ajuster la destination (dans le panel)
            int dx2 = sx2 - sx;
            int dy2 = sy2 - sy;

            g.drawImage(backgroundImage, 0, 0, dx2, dy2, sx, sy, sx2, sy2, this);
        }
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(4));
        g.drawRect( 0, 0, getWidth() - 1, getHeight() - 1);
    }
}
