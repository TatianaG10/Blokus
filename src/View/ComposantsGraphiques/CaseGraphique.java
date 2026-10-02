package View.ComposantsGraphiques;

import Controller.Configuration;

import javax.swing.*;
import java.awt.*;

public class CaseGraphique extends JComponent {
    Color couleur;
    Color couleurOriginale;

    boolean isMarqued;
    Color colorMarquer;
    Color colorBordure;

    public CaseGraphique(Color couleur, Color bordure) {
        couleurOriginale = couleur;
        this.couleur = couleur;
        isMarqued = false;
        colorMarquer = Color.BLACK;
        colorBordure = bordure;
        if (colorBordure != null){
            setBorder(BorderFactory.createLineBorder(colorBordure, 1));
        }
    }

    public void paintComponent(Graphics g){
        g.setColor(couleur);
        g.fillRect(0,0,getWidth(),getHeight());
        if (isMarqued && colorMarquer != null) {
            int taille = (int) (getWidth() * 0.4);
            int marge = (getWidth() - taille) / 2;

            g.setColor(colorMarquer);
            g.fillOval(marge, marge, taille, taille);
            g.setColor(couleur);
        }
    }

    public void marquer(Color color){
        colorMarquer = color;
        isMarqued = true;
        repaint();
    }
    public void unMarquer(){
        isMarqued = false;
        colorMarquer = null;
        repaint();
    }
    public void setColor(Color color){
        couleur = color;
        repaint();
    }
    public void setBordure(Color color){
        if (color!=null){
            setBorder(BorderFactory.createLineBorder(color, 1));
        }else{
            setBorder(BorderFactory.createLineBorder(colorBordure, 1));
        }
        repaint();
    }

    public void setSelected(boolean isSelected){
        if (isSelected){
            setBorder(BorderFactory.createLineBorder(Configuration.GRAY, 1));
        }else{
            setBorder(BorderFactory.createLineBorder(colorBordure, 1));
        }
        repaint();
    }

    public void setPlayed(boolean isPlayed, Color color){
        if (isPlayed){
            couleur = color;
        }
        repaint();
    }

    public void setUnPlayable(boolean isUnPlayable){
        if (isUnPlayable){
            couleur = Configuration.GRAY_DARK;
        }else{
            couleur = couleurOriginale;
        }
        repaint();
    }

    public Color getColor(){
        return couleur;
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension size = super.getPreferredSize();
        int dim = Math.min(size.width, size.height);
        return new Dimension(dim, dim);
    }

    @Override
    public void setBounds(int x, int y, int width, int height) {
        int size = Math.min(width, height);
        // Centrer dans l'espace alloué
        int offsetX = x + (width - size) / 2;
        int offsetY = y + (height - size) / 2;
        super.setBounds(offsetX, offsetY, size, size);
    }
}
