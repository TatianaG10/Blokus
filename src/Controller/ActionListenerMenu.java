package Controller;

import Controller.Joueurs.Humain;
import Controller.Joueurs.IA.IA_alea;
import Controller.Joueurs.IA.IA_intermediare;
import Controller.Joueurs.Joueur;
import Model.Jeu;
import View.Panels.Menu.BoutonTypeJoueur;
import View.Panels.Menu.ColorManager;
import View.Windows.FinDeJeu;
import View.Windows.GameWindow;
import View.Windows.Window;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionListenerMenu implements ActionListener {
    private final Window windowMenu;
    private final ColorManager colorManager;

    private Jeu game;
    private int nbJoueurs;
    private BoutonTypeJoueur[] boutonsType;
    private Timer timer;

    public ActionListenerMenu(Window windowMenu, ColorManager colorManager, BoutonTypeJoueur[] boutonsJoueurs) {
        game=null;
        this.windowMenu = windowMenu;
        this.colorManager = colorManager;
        this.boutonsType = boutonsJoueurs;
        nbJoueurs=0;
        for (int i = 0; i < boutonsJoueurs.length; i++) {
            if (boutonsType[i]!=null) nbJoueurs++;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //assignation de couleur à chaque joueur avant lancement
        Configuration.setupConfigurationJeu(20, 4);
        colorManager.defineColors();
        windowMenu.dispose();
        Joueur joueur1 = null;
        Joueur joueur2 = null;
        Joueur joueur3 = null;
        Joueur joueur4 = null;
        switch(nbJoueurs){
            case 1:
                if (boutonsType[0].getChoix()) joueur1 = new Humain("Joueur 1");
                else joueur1 = new IA_intermediare("IA 1");
                joueur2 = new IA_intermediare("IA 2");
                joueur3 = new IA_intermediare("IA 3");
                joueur4 = new IA_intermediare("IA 4");
                Configuration.setupConfigurationJeu(Configuration.taillePlateau, 4);
                game = new Jeu(joueur1,joueur2,joueur3,joueur4,Configuration.taillePlateau); // 1 joueur (precise)  et 3IA (non precise)
                break;
            case 2:
                if (boutonsType[0].getChoix()) joueur1 = new Humain("Joueur 1");
                else joueur1 = new IA_intermediare("IA 1");
                if (boutonsType[1].getChoix()) joueur2 = new Humain("Joueur 2");
                else joueur2 = new IA_intermediare("IA 2");
                joueur3 = new IA_intermediare("IA 3");
                joueur4 = new IA_intermediare("IA 4");
                Configuration.setupConfigurationJeu(Configuration.taillePlateau, 4);
                game = new Jeu(joueur1,joueur2,joueur3,joueur4,Configuration.taillePlateau);// 2 joueur (preciser) alterner les couleurs
                break;
            case 3:
                if (boutonsType[0].getChoix()) joueur1 = new Humain("Joueur 1");
                else joueur1 = new IA_intermediare("IA 1");
                if (boutonsType[1].getChoix()) joueur2 = new Humain("Joueur 2");
                else joueur2 = new IA_intermediare("IA 2");
                if (boutonsType[2].getChoix()) joueur3 = new Humain("Joueur 3");
                else joueur3 = new IA_intermediare("IA 3");
                joueur4 = new IA_intermediare("IA 4");
                Configuration.setupConfigurationJeu(Configuration.taillePlateau, 4);
                game = new Jeu(joueur1,joueur2,joueur3,joueur4,Configuration.taillePlateau);// 3 joueur (precise) + rajout d'une IA (non precise)
                break;
            case 4:
                if (boutonsType[0].getChoix()) joueur1 = new Humain("Joueur 1");
                else joueur1 = new IA_intermediare("IA 1");
                if (boutonsType[1].getChoix()) joueur2 = new Humain("Joueur 2");
                else joueur2 = new IA_intermediare("IA 2");
                if (boutonsType[2].getChoix()) joueur3 = new Humain("Joueur 3");
                else joueur3 = new IA_intermediare("IA 3");
                if (boutonsType[3].getChoix()) joueur4 = new Humain("Joueur 4");
                else joueur4 = new IA_intermediare("IA 4");
                Configuration.setupConfigurationJeu(Configuration.taillePlateau, 4);
                game = new Jeu(joueur1,joueur2,joueur3,joueur4,Configuration.taillePlateau);// 4 joueur (precise)
                break;
            default:
                System.err.println("Erreur : Unknown Action");
                System.exit(-1);
                break;
        }
        GameWindow gameWindow = new GameWindow(true, game);

        for (Joueur joueur : new Joueur[]{joueur1, joueur2, joueur3, joueur4}) {
            joueur.setGame(game);
            joueur.setJeuGraphique(gameWindow);
        }

        int delay = 2000;
        //Timer pour jouer un coup toute les delay mili-secondes
        timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!game.estPartieFinie()) {
                    game.getJoueurCourrant().jouer();
                } else {
                    timer.stop(); // Arrêter la partie
                    System.out.println("Partie terminée !");
                    new FinDeJeu(game);
                }
            }
        });

        timer.setInitialDelay(delay); // Attendre 1s avant le 1er coup
        gameWindow.getPanelGestionCoup().setTimer(timer);
        timer.start(); // Démarrer la boucle de jeu
    }

    public void setNbJoueurs(int nbJoueurs) {
        this.nbJoueurs = nbJoueurs;
    }
}
