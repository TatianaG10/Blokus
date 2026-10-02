package View.Panels.Menu;

import Controller.ActionListenerMenu;
import Controller.Configuration;
import View.Windows.MenuPrincipal;

import javax.swing.*;
import java.awt.*;

public class Panel2Joueurs extends JPanel {

    private final JPanel playerContainer;
    private final JButton ajout;
    private final JButton remove;
    private final BoutonTypeJoueur[] boutonsJoueurs;

    public int nbJoueur;
    private ActionListenerMenu actionListener;
    private MenuPrincipal menuPrincipal;
    private ColorManager colorManager;

    public Panel2Joueurs(MenuPrincipal windowP) {
        menuPrincipal = windowP;
        playerContainer = new JPanel();
        boutonsJoueurs = new BoutonTypeJoueur[4];

        setLayout(new BorderLayout());
        setOpaque(false);


        // CENTER PANEL
        JPanel center = new JPanel();
        center.setOpaque(false);
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setBorder(BorderFactory.createEmptyBorder(20, 50, 0, 50));

        playerContainer.setLayout(new BoxLayout(playerContainer, BoxLayout.Y_AXIS));
        playerContainer.setOpaque(false);
        playerContainer.setAlignmentX(Component.CENTER_ALIGNMENT);
        center.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Bouton Ajout joueur
        ajout = new JButton("+");
        ajout.setPreferredSize(new Dimension(30,30));
        ajout.setFocusable(false);
        ajout.setFocusPainted(false);
        ajout.setBorderPainted(true);
        ajout.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        ajout.setCursor(new Cursor(Cursor.HAND_CURSOR));
        ajout.setAlignmentX(Component.CENTER_ALIGNMENT);
        ajout.addActionListener(e -> {
            ajouterJoueur();
        });

        //Bouton Remove Joueur
        remove = new JButton("-");
        remove.setPreferredSize(new Dimension(30,30));
        remove.setFocusable(false);
        remove.setFocusPainted(false);
        remove.setBorderPainted(true);
        remove.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        remove.setCursor(new Cursor(Cursor.HAND_CURSOR));
        remove.setAlignmentX(Component.CENTER_ALIGNMENT);
        remove.addActionListener(e -> {
            retirerJoueur();
        });

        init();

        center.add(playerContainer);
        JPanel boutons = new JPanel();
        boutons.setOpaque(false);
        boutons.setLayout(new FlowLayout(FlowLayout.CENTER));
        boutons.add(ajout);
        boutons.add(remove);

        center.add(boutons);
        add(center, BorderLayout.CENTER);

        // TOP PANEL
        JPanel top = new JPanel();
        top.setLayout(new FlowLayout(FlowLayout.LEFT));
        top.setOpaque(false);

        JButton retour = new JButton("");
        retour.setCursor(new Cursor(Cursor.HAND_CURSOR));
        retour.setIcon(new ImageIcon(Configuration.Back.getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        retour.setPreferredSize(new Dimension(60,30));
        retour.setFocusable(false);
        retour.setFocusPainted(false);
        retour.setBorderPainted(true);
        retour.setCursor(new Cursor(Cursor.HAND_CURSOR));
        retour.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        retour.addActionListener(e -> {
            init();
            windowP.afficherCarte("Menu");
        });

        top.add(retour);
        add(top, BorderLayout.NORTH);

        // BOTTOM PANEL
        JPanel bottom = new JPanel();
        bottom.setOpaque(false);
        bottom.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton go = new JButton("Lancer");
        go.setPreferredSize(new Dimension(60,30));
        go.setFocusable(false);
        go.setFocusPainted(false);
        go.setBorderPainted(true);
        go.setCursor(new Cursor(Cursor.HAND_CURSOR));
        go.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        go.addActionListener(actionListener);
        bottom.add(go);
        add(bottom, BorderLayout.SOUTH);
    }

    public void ajouterJoueur() {
        if (nbJoueur < 4) {
            nbJoueur++;
            actionListener.setNbJoueurs(nbJoueur);
            JPanel p = new JPanel();
            p.setMaximumSize(new Dimension(300, 40));
            p.setOpaque(false);
            JLabel j = new JLabel("J" + nbJoueur + " :");
            BoutonTypeJoueur bot = new BoutonTypeJoueur(nbJoueur==1);
            boutonsJoueurs[nbJoueur-1] = bot;
            BoutonCouleur colo = new BoutonCouleur(nbJoueur - 1, colorManager);
            colo.setFocusable(false);
            p.add(j);
            p.add(bot);
            p.add(colo);

            playerContainer.add(p);
            playerContainer.add(Box.createRigidArea(new Dimension(0, 5)));
            playerContainer.revalidate();
            playerContainer.repaint();
            if (nbJoueur == 4) {
                ajout.setVisible(false);
            }
            remove.setVisible(true);
        }
    }

    public void retirerJoueur() {
        int componentCount = playerContainer.getComponentCount();
        if (nbJoueur > 0) {
            boutonsJoueurs[nbJoueur-1] = null;
            nbJoueur--;
            actionListener.setNbJoueurs(nbJoueur);
            playerContainer.remove(componentCount - 1); // la rigid area
            playerContainer.remove(componentCount - 2); // le JPanel du joueur
            playerContainer.revalidate();
            playerContainer.repaint();
            if (nbJoueur == 1) {
                remove.setVisible(false);
            }
            ajout.setVisible(true);
        }
    }


    public void init() {
        nbJoueur=0;
        playerContainer.removeAll();
        Configuration.resetColors();
        colorManager = new ColorManager(); // Remettre à zéro les couleurs
        actionListener = new ActionListenerMenu(menuPrincipal, colorManager, boutonsJoueurs);
        ajouterJoueur();
        ajout.setVisible(true);
        remove.setVisible(false);
        revalidate();
        repaint();
    }
}
