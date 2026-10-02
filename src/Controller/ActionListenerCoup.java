package Controller;

import Controller.Joueurs.Joueur;
import Model.Jeu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionListenerCoup implements ActionListener {
    public static final int REWIND=0;
    public static final int UNWIND=1;

    private int mode;
    private Jeu game;
    private Timer timer;

    public ActionListenerCoup(int mode, Jeu game, Timer timer) {
        this.mode = mode;
        this.game = game;
        this.timer = timer;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (timer != null && timer.isRunning()) {
            timer.stop(); // Interrompre le jeu automatique
        }

        if (mode == REWIND) {
            game.annulerCoup();
            for (Joueur joueur : game.getJoueurs()) {
                joueur.updateGraphics();
            }
            System.out.println("Annuler");
        } else if (mode == UNWIND) {
            game.rejouerCoup();
            for (Joueur joueur : game.getJoueurs()) {
                joueur.updateGraphics();
            }
            System.out.println("Rejouer");
        } else {
            System.err.println("Erreur : Unknown Action");
            System.exit(-1);
        }

        // Redémarrer le timer (optionnel)
        timer.start();
    }

}
