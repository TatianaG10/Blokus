package View.Windows;

import Controller.Configuration;
import Controller.Joueurs.Humain;
import Controller.Joueurs.Joueur;
import Model.Jeu;
import View.GridLayoutManager;
import View.Panels.*;
import View.Panels.DragAndDrop.DragGlassPanel;
import View.Panels.Layout.*;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends Window{
    private boolean enChangementPleinEcran = false;
    private boolean portrait;
    private boolean fullscreen;
    private PanelPlateau panelPlateau;
    private PanelPieces panelPiecesJ1;
    private PanelGestionCoup panelGestionCoup;
    private PanelPieces panelPiecesJ2;
    private PanelPieces panelPiecesJ3;
    private PanelPieces panelPiecesJ4;
    private PanelJoueurs panelJoueurs;
    private PanelRotations panelRotations;
    private PanelBoutons panelBoutons;
    private DragGlassPanel glassPane;

    public GameWindow(Boolean port, Jeu jeu) {
        super();
        portrait = port;
        fullscreen = false;

        initialiserPanels(jeu);

        // Passe le glassPane à écouteur de souris et pièce
        for (Joueur joueur : jeu.getJoueurs()) {
            if (joueur instanceof Humain) {
                addMouseMotionListener((Humain) joueur);
                addMouseListener((Humain) joueur);
                ((Humain) joueur).setGlassPane(glassPane);
            }
        }

        organiserComposants();     // ajoute tous les panels
        afficherWindow();          // pack + centrer
    }

    private void initialiserPanels(Jeu game) {
        panelPlateau = new PanelPlateau();
        panelPiecesJ1 = new PanelPieces(game.getJoueurs()[0], PanelPieces.LANDSCAPE);
        panelPiecesJ2 = new PanelPieces(game.getJoueurs()[1], PanelPieces.PORTRAIT);
        panelPiecesJ3 = new PanelPieces(game.getJoueurs()[2], PanelPieces.REVERSE_LANDSCAPE);
        panelPiecesJ4 = new PanelPieces(game.getJoueurs()[3], PanelPieces.REVERSE_PORTRAIT);
        panelJoueurs = new PanelJoueurs(panelPlateau,game);
        panelBoutons = new PanelBoutons(this, game);
        panelGestionCoup = new PanelGestionCoup(game);
        glassPane = new DragGlassPanel(this);
        panelRotations = new PanelRotations(glassPane);
    }

    private void afficherWindow() {
        getContentPane().setPreferredSize(Configuration.dimensionGameWindow);
        setPreferredSize(Configuration.dimensionGameWindow);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void organiserComposants() {
        getContentPane().removeAll(); // enlève tous les composants
        BackgroundPanel background = new BackgroundPanel(Configuration.BackGroundGameFullScreen);
        background.setLayout(new GridBagLayout());
        setContentPane(background);

        GridLayoutManager gbcMain = new GridLayoutManager();

        JPanel upPanel = new JPanel();
        JPanel midPanel = new JPanel();
        JPanel downPanel = new JPanel();

        upPanel.setLayout(new GridBagLayout());
        upPanel.setOpaque(false);

        midPanel.setLayout(new GridBagLayout());
        midPanel.setOpaque(false);

        downPanel.setLayout(new GridBagLayout());
        downPanel.setOpaque(false);

        GridLayoutManager gbcUp = new GridLayoutManager();
        GridLayoutManager gbcMid = new GridLayoutManager();
        GridLayoutManager gbcDown = new GridLayoutManager();

        /// UP Panel
        if (portrait){
            gbcUp.updateGBC(0, 0, 0.2, 1.0, new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, GridBagConstraints.BOTH);
            upPanel.add(Box.createHorizontalBox(),gbcUp);

            //--- Panel Joueurs en haut à gauche ---
            gbcUp.updateGBC( 1, 0, 0.1, 1.0, new Insets(5, 0, 5, 0), GridBagConstraints.CENTER, GridBagConstraints.BOTH);
            upPanel.add(panelJoueurs, gbcUp);

            gbcUp.updateGBC( 2, 0, 0.5, 1.0, new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, GridBagConstraints.BOTH);
            upPanel.add(Box.createHorizontalBox(),gbcUp);

            //--- Boutons ---
            gbcUp.updateGBC( 3, 0, 0.1, 1.0, new Insets(5, 0, 5, 0), GridBagConstraints.CENTER, GridBagConstraints.BOTH);
            upPanel.add(panelBoutons, gbcUp);

            gbcUp.updateGBC( 4, 0, 0.1, 1.0, new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, GridBagConstraints.BOTH);
            upPanel.add(Box.createHorizontalBox(),gbcUp);
        }else{
            gbcUp.updateGBC(0, 0, 0.1, 1.0, new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, GridBagConstraints.BOTH);
            upPanel.add(Box.createHorizontalBox(),gbcUp);

            //--- Panel Joueurs en haut à gauche ---
            gbcUp.updateGBC( 1, 0, 0.1, 1.0, new Insets(5, 0, 5, 0), GridBagConstraints.CENTER, GridBagConstraints.BOTH);
            upPanel.add(panelJoueurs, gbcUp);

            gbcUp.updateGBC( 2, 0, 0.05, 1.0, new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, GridBagConstraints.BOTH);
            upPanel.add(Box.createHorizontalBox(),gbcUp);

            gbcUp.updateGBC( 3, 0, 0.5, 1.0, new Insets(5, 0, 5, 0), GridBagConstraints.EAST, GridBagConstraints.BOTH);
            upPanel.add(panelPiecesJ3,gbcUp);

            gbcUp.updateGBC( 4, 0, 0.05, 1.0, new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, GridBagConstraints.BOTH);
            upPanel.add(Box.createHorizontalBox(),gbcUp);

            //--- Boutons ---
            gbcUp.updateGBC( 5, 0, 0.1, 1.0, new Insets(5, 0, 5, 0), GridBagConstraints.CENTER, GridBagConstraints.BOTH);
            upPanel.add(panelBoutons, gbcUp);

            gbcUp.updateGBC( 6, 0, 0.1, 1.0, new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, GridBagConstraints.BOTH);
            upPanel.add(Box.createHorizontalBox(),gbcUp);
        }


        /// Mid Panel
        if (portrait){
            gbcMid.updateGBC(0, 0, 0.15, 1.0, new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, GridBagConstraints.BOTH);
            midPanel.add(Box.createHorizontalBox(),gbcMid);

            // --- PanelPlateau au centre ---
            gbcMid.updateGBC( 1, 0, 0.8, 1.0, new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, GridBagConstraints.BOTH);
            midPanel.add(panelPlateau, gbcMid);

            gbcMid.updateGBC( 2, 0, 0.15, 1.0, new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, GridBagConstraints.BOTH);
            midPanel.add(Box.createHorizontalBox(),gbcMid);
        }else{
            gbcMid.updateGBC( 0, 0, 0.1, 1.0, new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, GridBagConstraints.BOTH);
            midPanel.add(Box.createHorizontalBox(),gbcMid);

            // --- PanelPiece a gauche J2 ---
            gbcMid.updateGBC( 1, 0, 0.1, 1.0, new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, GridBagConstraints.BOTH);
            midPanel.add(panelPiecesJ2, gbcMid);

            // --- PanelPlateau au centre ---
            gbcMid.updateGBC( 2, 0, 0.6, 1.0, new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, GridBagConstraints.BOTH);
            midPanel.add(panelPlateau, gbcMid);

            // --- PanelPiece a gauche J4 ---
            gbcMid.updateGBC( 3, 0, 0.1, 1.0, new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, GridBagConstraints.BOTH);
            midPanel.add(panelPiecesJ4, gbcMid);

            gbcMid.updateGBC( 4, 0, 0.1, 1.0, new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, GridBagConstraints.BOTH);
            midPanel.add(Box.createHorizontalBox(),gbcMid);
        }

        /// Down Panel

        //--- Panel Gestion Historique ---
        gbcDown.updateGBC(0,0,0.2,1.0,new Insets(0,0,0,0),  GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        downPanel.add(panelGestionCoup,gbcDown);

        // --- PanelPieces ---
        gbcDown.updateGBC(2, 0, 0.6, 1.0, new Insets(5, 0, 5, 0), GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        downPanel.add(panelPiecesJ1, gbcDown);

        //--- Panel Rotations ---
        gbcDown.updateGBC( 4, 0, 0.2, 1.0, new Insets(5, 0, 5, 0), GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        downPanel.add(panelRotations, gbcDown);


        /// Main Panel
        gbcMain.updateGBC(0, 0, 1.0, 0.06, new Insets(0, 0, 0, 0),GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        add(upPanel, gbcMain);
        gbcMain.updateGBC(0, 1, 1.0, 0.75, new Insets(0, 0, 0, 0),GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        add(midPanel, gbcMain);
        gbcMain.updateGBC(0, 2, 1.0, 0.19, new Insets(0, 0, 0, 0),GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        add(downPanel, gbcMain);
    }

    public PanelPlateau getPanelPlateau() {
        return panelPlateau;
    }
    public PanelJoueurs getPanelJoueurs() {
        return panelJoueurs;
    }
    public PanelBoutons getPanelBoutons() {
        return panelBoutons;
    }
    public PanelRotations getPanelRotations() {
        return panelRotations;
    }
    public PanelGestionCoup getPanelGestionCoup() {
        return panelGestionCoup;
    }

    public PanelPieces[] getPanelPieces() {
        PanelPieces[] panelPieces = new PanelPieces[4];
        panelPieces[0]=panelPiecesJ1;
        panelPieces[1]=panelPiecesJ2;
        panelPieces[2]=panelPiecesJ3;
        panelPieces[3]=panelPiecesJ4;
        return panelPieces;
    }

    public void reinitialise(){
        Joueur.nbJoueurs=0;
        PanelPieces.reinitialise();
    }

    public void updateGraphicsJoueurs(Jeu jeu){
        panelJoueurs.update(jeu);
    }
    public void updateGraphicsPlateau(Jeu jeu){
        panelPlateau.update(jeu);
    }
    public void updateGraphicsPieces(Jeu jeu, int joueur) {
        panelRotations.update(jeu);
        PanelPieces.update(jeu);
        panelPlateau.updateFeedForward(jeu, joueur);
    }

    public void updateAllGraphics(Jeu jeu, int joueur){
        updateGraphicsJoueurs(jeu);
        updateGraphicsPlateau(jeu);
        updateGraphicsPieces(jeu, joueur);
        if (jeu.getJoueurs()[joueur] instanceof Humain){
            glassPane.setPlayer((Humain) jeu.getJoueurs()[joueur]);
        }
    }
}
