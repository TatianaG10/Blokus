package Controller.Joueurs.IA;

import Controller.Joueurs.Joueur;
import Model.Coup;

import java.io.Serializable;
import java.util.*;

public class IA_alea extends Joueur implements Serializable {
    private static final long serialVersionUID = 1L;
    Random random;

    public IA_alea(Joueur joueur) {
        super(joueur.getPseudo());
        score=joueur.getScore();
        pieces = new  ArrayList<>(joueur.getPieces());
        idJoueur=joueur.getIDJoueur();
        random = new Random();
    }

    public IA_alea(String pseudo) {
        super(pseudo);
        random = new Random();
    }

    public void jouer(){

        List<Coup> coups = new ArrayList<>(getCoupsJouables());
        if (coups.isEmpty()) return;

        Coup ajouerCoup = coups.get(random.nextInt(coups.size()));
        super.game.jouerCoup(ajouerCoup);

        updateGraphics();
    }
}
