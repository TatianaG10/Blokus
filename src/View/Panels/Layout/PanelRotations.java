package View.Panels.Layout;

import Controller.ActionListenerRotation;
import Controller.Configuration;
import Controller.Joueurs.Humain;
import Model.Jeu;
import View.ComposantsGraphiques.BoutonResizable;
import View.Panels.DragAndDrop.DragGlassPanel;

import javax.swing.*;
import java.awt.*;

public class PanelRotations extends JPanel {
    private BoutonResizable gaucheB, droiteB, symHB, symVB;
    private ActionListenerRotation gauche, droite, horizontal, vertical;

    public PanelRotations(DragGlassPanel glassPanel) {
        setOpaque(false);
        setLayout(new GridLayout(3, 1,0,5));

        Image logo, logoHover;

        JPanel panelHaut = new JPanel();
        panelHaut.setOpaque(false);
        panelHaut.setLayout(new GridLayout(1,2,5,0));

        /// --- Bouton Rotation Gauche ---
        logo = Configuration.RotationGauche;
        logoHover = Configuration.RotationGaucheHover;
        //add options and config on button here
        gaucheB = generateBouton(logo, logoHover, BoutonResizable.SQUARE);
        gauche = new ActionListenerRotation(0, glassPanel);
        gaucheB.addActionListener(gauche);

        panelHaut.add(gaucheB);

        /// --- Bouton Rotation Droite ---
        logo = Configuration.RotationDroite;
        logoHover = Configuration.RotationDroiteHover;
        //add options and config on button here
        droiteB = generateBouton(logo, logoHover, BoutonResizable.SQUARE);
        droite = new ActionListenerRotation(1, glassPanel);
        droiteB.addActionListener(droite);

        panelHaut.add(droiteB);

        add(panelHaut);


        /// --- Bouton Rotation par Symétrie Horizontal ---

        //add options and config on button here
        logo = Configuration.SymetrieHorizontale;
        logoHover = Configuration.SymetrieHorizontaleHover;
        //add options and config on button here
        symHB = generateBouton(logo, logoHover, BoutonResizable.RECTANGLE);
        horizontal = new ActionListenerRotation(2, glassPanel);
        symHB.addActionListener(horizontal);
        add(symHB);

        /// --- Bouton Rotation par Symétrie Verticale ---
        //add options and config on button here
        logo = Configuration.SymetrieVerticale;
        logoHover = Configuration.SymetrieVerticaleHover;
        //add options and config on button here
        symVB = generateBouton(logo, logoHover, BoutonResizable.RECTANGLE);

        vertical = new ActionListenerRotation(3, glassPanel);
        symVB.addActionListener(vertical);
        add(symVB);
    }

    private BoutonResizable generateBouton(Image logo, Image logoHover, int mode){
        BoutonResizable bouton = new BoutonResizable(mode , logo, logoHover);
        bouton.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        return bouton;
    }

    private void setPlayer(Humain player){
        gauche.setPlayer(player);
        droite.setPlayer(player);
        horizontal.setPlayer(player);
        vertical.setPlayer(player);
    }

    public void update(Jeu jeu){
        if (jeu.getJoueurCourrant() instanceof Humain){
            setPlayer((Humain) jeu.getJoueurCourrant());
        }
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension size = super.getPreferredSize();

        // Calcule la hauteur maximale en fonction de la largeur disponible pour garder le ratio 2:3
        int maxHeight = size.height;
        int maxWidth = size.width;

        // Calcule hauteur et largeur dans le respect du ratio
        int height = Math.min(maxHeight, (int)(maxWidth * 1.5));
        int width = (int)(height * (2.0 / 3.0));

        return new Dimension(width, height);
    }

    @Override
    public void setBounds(int x, int y, int width, int height) {
        int h = Math.min(height, (int)(width * 1.5));
        int w = (int)(h * (2.0 / 3.0));
        int offsetX = x + (width - w) / 2;
        int offsetY = y + (height - h) / 2;

        super.setBounds(offsetX, offsetY, w, h);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
