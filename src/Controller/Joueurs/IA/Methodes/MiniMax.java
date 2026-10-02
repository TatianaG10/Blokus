package Controller.Joueurs.IA.Methodes;

import Model.Jeu;
import Controller.Joueurs.Joueur;
import Model.Piece;
import Model.Plateau;
import Model.Coup;
import Structures.Position;

import java.awt.*;
import java.util.Arrays;
import  java.util.List;

public class MiniMax {

    private final int profondeur;
    private final int indexIA;
    private Jeu jeu;

    public MiniMax(int profondeur, int indexIA, Jeu game) {
        this.profondeur = profondeur;
        this.indexIA = indexIA;
        jeu = new Jeu(game);
    }

    public int minimax(Coup c, Joueur[] joueurs, int indexCourrant, int profondeur) {
        Joueur courant = joueurs[indexCourrant];
        System.out.println("profondeur dans minimax :" + profondeur);
        if (profondeur == 0 || courant.getCoupsJouables().isEmpty()) {
            return new Heuristique(jeu, indexCourrant, indexIA).evalSimple();
        }
        int bestScore = (indexCourrant == indexIA) ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (Coup coups : courant.getCoupsJouables()) {
            jeu.jouerCoup(coups);
            int indexSuivant = (indexCourrant + 1) % joueurs.length;
            int score = minimax(coups, joueurs, indexSuivant, profondeur - 1);

            jeu.annulerCoup();

            if (indexCourrant == indexIA) {
                bestScore = Math.max(bestScore, score);
            } else {
                bestScore = Math.min(bestScore, score);
            }
        }
        return bestScore;
    }

    public Coup execution(Joueur[] joueurs) {
        //this.jeu = new Jeu(jeu);
        long startTime = System.nanoTime(); //début chronometre
        Joueur ia = joueurs[indexIA];
        int bestValeur = Integer.MIN_VALUE;
        int score;
        Coup bestCoup = null;
        int i = 1;
        for (Coup coup : ia.getCoupsJouables()) {
            System.out.println(" ------------------------------------------------------------------- DEBUT EXECUTION "+i+" ---------------------------------");
            System.out.println(" le meilleur coup vu pour le moment est" +bestCoup);
            System.out.println(" et la meilleure valeur vue est" +bestValeur);
            System.out.println("coup courant : " + coup);
            jeu.jouerCoup(coup);
            System.out.println(" -------------- MINIMAX ---------------");
            score = minimax(coup, joueurs, 1, profondeur - 1);
            System.out.println(" -------------- SCORE "+i+": "+score+ " ---------------");
            jeu.annulerCoup();
            if (score > bestValeur) {
                bestValeur = score;
                bestCoup = coup;
            }
            System.out.println(" -------------------------------------------------------------------- FIN EXECUTION "+i+" ------------------------------------");
            System.out.println("################################################################################################################################");
            i++;
        }
        long endTime = System.nanoTime();
        long tempsExec = endTime - startTime;
        System.out.println("l'execution du minimax simple s'est fait en " + tempsExec / 1_000_000.0 + " ms");
        System.out.println("le meilleur coup est "+ bestCoup);

        return bestCoup;
    }

}







/*  Pour 2 joueurs

    public int min(Plateau plateau, Joueur joueur, int profondeurs){
        if (profondeurs == 0){
            return joueur.getScore() + 2 * joueur.getCasesJouables().size();
        }
        int minValeurVue = Integer.MAX_VALUE; //juste histoire de lui affecter une valeur stupidement grande
        for(Point point : joueur.getCasesJouables()){
            for (Piece piece : joueur.getPiecesJouables().values()){
                Coup coup = new Coup(piece,point);
                if (joueur.estCoupValide(coup) != -1){
                    plateau.jouerCoup(coup,joueur.getCouleurJoueur());
                    final int valeurC = max(plateau, joueur, profondeurs - 1);
                    if (valeurC <= minValeurVue){
                        minValeurVue = valeurC;
                    }
                }
            }
        }
        return minValeurVue;
    }

    public int max(Plateau plateau, Joueur joueur, int profondeurs){
        if (profondeurs == 0){
            return joueur.getScore() + 2 * joueur.getCasesJouables().size();
        }
        int maxValeurVue = Integer.MIN_VALUE; //juste histoire de lui affecter une valeur extremement petite
        for(Point point : joueur.getCasesJouables()){
            for (Piece piece : joueur.getPiecesJouables().values()){
                Coup coup = new Coup(piece,point);
                if (joueur.estCoupValide(coup) != -1){
                    plateau.jouerCoup(coup,joueur.getCouleurJoueur());
                    final int valeurC = min(plateau, joueur, profondeurs - 1);
                    if (valeurC >= maxValeurVue){
                        maxValeurVue = valeurC;
                    }
                }
            }
        }
        return maxValeurVue;
    }
*/

/*  Old version

        for(Point point : courant.getCasesJouables()) {
            for (Piece piece : courant.getPiecesJouables().values()) {
                Coup coup = new Coup(piece, point);
                if (courant.estCoupValide(coup) != -1) {
                    Plateau copiePlateau = new Plateau(plateau);
                    copiePlateau.jouerCoup(coup, courant.getCouleurJoueur());
                    int indexSuivant = (indexCourrant + 1) % joueurs.size();
                    int score = minimax(copiePlateau, joueurs, indexSuivant, profondeur -1);

                    if (indexCourrant == indexIA){
                        bestScore = Math.max(bestScore, score);
                    }
                    else {
                        bestScore = Math.min(bestScore, score);
                    }
                }
            }
        }
        return bestScore;
    }

    public Coup execution(Plateau plateau, List<Joueur> joueurs){
        long startTime = System.nanoTime(); //début chronometre

        Joueur ia = joueurs.get(indexIA);
        int maxValVue = Integer.MIN_VALUE;
        int score;
        Coup bestCoup = null;
        for (Point point : ia.getCasesJouables()){
            for (Piece pieces : ia.getPiecesJouables().values()){
                Coup coup = new Coup(pieces,point);
                if (ia.estCoupValide(coup) > -1){
                    Plateau copieP = new Plateau(plateau);
                    copieP.jouerCoup(coup,ia.getCouleurJoueur());
                    score = minimax(copieP, joueurs,1, profondeur -1);
                    if (score >= maxValVue){
                        maxValVue = score;
                        bestCoup = new Coup(pieces, point);
                    }
                }

            }
        }
        long endTime = System.nanoTime();
        long tempsExec = endTime - startTime;
        System.out.println("l'execution du minimax simple s'est fait en " + tempsExec / 1_000_000.0 + " ms");

        return bestCoup;
    }

    private int eval( List<Joueur> joueurs) {
        Joueur ia = joueurs.get(indexIA);
        int score = ia.getScore();
        int autreScore = 0;
        //ici on calcul la somme des scores de tous les joueurs sauf l'ia
        for (int i = 0; i < joueurs.size(); i++){
            if (i != indexIA){
                autreScore += joueurs.get(i).getScore();
            }
        }
        return score - autreScore;
    }
*/
