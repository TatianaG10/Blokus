package View;

import Controller.Configuration;
import Controller.Joueurs.Humain;
import Controller.Joueurs.IA.IA_alea;
import Model.Jeu;
import Controller.Joueurs.Joueur;
import View.Windows.GameWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfaceGraphiqueGame implements Runnable {

    private Jeu jeu;
    private Timer timer;

    @Override
    public void run() {
        Configuration.setupConfigurationMenu();
        Configuration.setupConfigurationJeu(20, 4);

        Joueur joueur1 = new Humain("Kalv");
        Joueur joueur2 = new IA_alea("Robin");  // met une IA ici pour tester
        Joueur joueur3 = new Humain("Tats");
        Joueur joueur4 = new IA_alea("Big Kev");

        jeu = new Jeu(joueur1, joueur2, joueur3, joueur4, 20);
        GameWindow gameWindow = new GameWindow(true, jeu);

        for (Joueur joueur : new Joueur[]{joueur1, joueur2, joueur3, joueur4}) {
            joueur.setGame(jeu);
            joueur.setJeuGraphique(gameWindow);
        }

        // Timer pour jouer un coup toutes les 1 seconde
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!jeu.estPartieFinie()) {
                    jeu.getJoueurCourrant().jouer();
                } else {
                    timer.stop(); // Arrêter la partie
                    System.out.println("Partie terminée !");
                }
            }
        });

        timer.setInitialDelay(1000); // Attendre 1s avant le 1er coup
        timer.start(); // Démarrer la boucle de jeu
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new InterfaceGraphiqueGame());
    }
}
