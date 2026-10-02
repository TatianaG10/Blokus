package Model;

import Controller.Joueurs.Humain;
import Controller.Joueurs.Joueur;
import Structures.Position;

import java.util.HashSet;

public class TestJeu {
    public static void main(String [] args){
        Joueur joueur1 = new Humain("Robin");
        Joueur joueur2 = new Humain("Kalv");
        Joueur joueur3 = new Humain("Tats");
        Joueur joueur4 = new Humain("Big Kev");
        Jeu jeu = new Jeu(joueur1, joueur2, joueur3, joueur4, 20);

        ///  Scenario
        int indexMax = jeu.getPlateau().getNbCases()-1;

        HashSet<Coup> coupsJouables;
        HashSet<Piece> piecesJouables;
        Piece p; Position refPiece; Position refPlateau;

        System.out.println(jeu);

        coupsJouables = jeu.getJoueurCourrant().getCoupsJouables();
        piecesJouables = jeu.getJoueurCourrant().getPiecesJouables();
        System.out.println("Nombre de Coups jouables: "+coupsJouables.size());
        System.out.println("Nombre de Pieces jouables: "+piecesJouables.size());

        p = new Piece(0);
        refPiece = new Position(1,1);
        refPlateau = new Position(0,0);
        jeu.jouerCoup(new Coup(p, refPiece, refPlateau));

        System.out.println(jeu);

        coupsJouables = jeu.getJoueurCourrant().getCoupsJouables();
        piecesJouables = jeu.getJoueurCourrant().getPiecesJouables();
        System.out.println("Nombre de Coups jouables: "+coupsJouables.size());
        System.out.println("Nombre de Pieces jouables: "+piecesJouables.size());

        p = new Piece(0);
        refPiece = new Position(1,1);
        refPlateau = new Position(0,indexMax);
        jeu.jouerCoup(new Coup(p, refPiece, refPlateau));

        System.out.println(jeu);

        jeu.annulerCoup();
        System.out.println(jeu);
        jeu.annulerCoup();
        System.out.println(jeu);
        jeu.rejouerCoup();
        System.out.println(jeu);
    }
}
