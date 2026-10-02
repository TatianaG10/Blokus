package View.Panels.Layout;

import Controller.ActionListenerCoup;
import Controller.Configuration;
import Model.Jeu;
import View.ComposantsGraphiques.BoutonResizable;

import javax.swing.*;
import java.awt.*;

public class PanelGestionCoup extends JPanel {
    private BoutonResizable rejouer, annuler;
    private Jeu game;

    public PanelGestionCoup(Jeu jeu) {
        game = jeu;
        setOpaque(false);
        setLayout(new GridLayout(3,1,0,5));
        setBorder(BorderFactory.createEmptyBorder(5,0,5,0));

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,2,5,0));
        panel.setOpaque(false);

        annuler = generateBouton(ActionListenerCoup.REWIND);
        rejouer = generateBouton(ActionListenerCoup.UNWIND);

        panel.add(annuler);
        panel.add(rejouer);

        add(panel);
    }
    public void setTimer(Timer timer) {
        annuler.addActionListener(new ActionListenerCoup(ActionListenerCoup.REWIND,game, timer));
        rejouer.addActionListener(new ActionListenerCoup(ActionListenerCoup.UNWIND, game, timer));
    }

    private BoutonResizable generateBouton(int mode){
        BoutonResizable bouton;
        if (mode == ActionListenerCoup.REWIND){
            Image logo = Configuration.AnnuleLogo;
            Image logoHover = Configuration.AnnuleLogoHover;
            bouton = new BoutonResizable(BoutonResizable.SQUARE, logo, logoHover);

        }else if  (mode == ActionListenerCoup.UNWIND){
            Image logo = Configuration.RejouerLogo;
            Image logoHover = Configuration.RejouerLogoHover;
            bouton = new BoutonResizable(BoutonResizable.SQUARE, logo, logoHover);
        }else{
            return null;
        }
        bouton.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        return bouton;
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
        int offsetX = x + (width-w) / 2;
        int offsetY = y + (height - h) / 2;

        super.setBounds(offsetX, offsetY, w, h);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
