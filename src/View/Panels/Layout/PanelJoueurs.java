package View.Panels.Layout;

import Controller.Configuration;
import Controller.Joueurs.Joueur;
import Model.Jeu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelJoueurs extends JPanel {
    public static final int nbJoueurs = Configuration.nbJoueurs;

    private int joueurCourrant;
    private int joueurDisplay;
    private final JButton[] boutonsJoueurs;

    private PanelPlateau plateauP;
    private Jeu game;

    public PanelJoueurs(PanelPlateau plat, Jeu jeu){
        joueurCourrant=0;
        joueurDisplay=0;
        boutonsJoueurs=new JButton[Configuration.nbJoueurs];
        plateauP=plat;
        game=jeu;

        setOpaque(false);
        setLayout(new GridLayout(Configuration.nbJoueurs,1 , 0, 5));

        for (int i = 0; i < Configuration.nbJoueurs; i++) {
            JButton joueur = createButton(i); // extraire la création de bouton dans une méthode
            boutonsJoueurs[i] = joueur;
            add(joueur);
        }
    }

    private JButton createButton(int index){
            JButton joueur = new JButton("Joueur "+(index+1)+" : "+0);
            //add options and config on button here
            joueur.setBackground(Configuration.ordreJoueurs[index]);
            joueur.setFocusPainted(false);
            joueur.setCursor(new Cursor(Cursor.HAND_CURSOR));
            joueur.setForeground(Color.BLACK);

            updateButtonStyle(joueur, index);

            joueur.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    joueur.setBorder(BorderFactory.createLineBorder(Configuration.bordureJoueurs[index], 2));
                    joueur.setForeground(Color.WHITE);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    updateButtonStyle(joueur, index);
                }
            });
            joueur.addActionListener(e -> {
                joueurDisplay=index;
                PanelPieces.setJoueurs(joueurDisplay);
                updateAllButtons();
                plateauP.updateFeedForward(game, joueurDisplay);
            });

            return joueur;
    }

    private void updateButtonStyle(JButton bouton, int index) {
        bouton.setForeground(index == joueurDisplay ? Color.WHITE : Color.BLACK);
        int borderWidth = (index == joueurCourrant) ? 4 : 2;
        bouton.setBorder(BorderFactory.createLineBorder(Configuration.bordureJoueurs[index], borderWidth));
        if (game.getCoupsJouables(index).isEmpty()){
            bouton.setBackground(Configuration.GRAY_DARK);
        }else{
            bouton.setBackground(Configuration.ordreJoueurs[index]);
        }
    }

    private void updateAllButtons() {
        for (int i = 0; i < nbJoueurs; i++) {
            updateButtonStyle(boutonsJoueurs[i], i);
        }
    }

    public void update(Jeu game){
        joueurCourrant=game.getIndexJoueurCourrant();
        joueurDisplay=joueurCourrant;

        Joueur[] joueurs = game.getJoueurs();
        for (int i=0;i<joueurs.length;i++) {
            boutonsJoueurs[i].setText(joueurs[i].getPseudo() + ": " + joueurs[i].getScore());
        }
        updateAllButtons();
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

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }
}
