package Controller.Joueurs.IA.Methodes;

import Model.Jeu;
import Controller.Joueurs.Joueur;
import Model.Piece;
import Model.Plateau;
import Model.Coup;

import java.awt.*;
import java.util.ArrayList;
import  java.util.List;

//TODO ICI CONTRAIREMENT A DANS HEURISTIQUE IL Y A CE LEGER SOUCI DE LIST JOUEUR
public class AlphaBeta_Pruning {
    private final int profondeur;
    private final int indexIA;
    private Jeu jeu;
    private Joueur[] players;

    public AlphaBeta_Pruning(int profondeur, int indexIA, Jeu game) {
        this.profondeur = profondeur;
        this.indexIA = indexIA;
        jeu = game;
        players = jeu.getJoueurs();

    }

    public int minimaxAB(Coup c, Joueur[] joueurs, int indexCourrant, int profondeur, int alpha, int beta) {
        Joueur courant = joueurs[indexCourrant];
        List<Coup> coupsPossible = new ArrayList<>(courant.getCoupsJouables());

        if (profondeur == 0 || courant.getCoupsJouables().isEmpty() ) {
            int indexPrecedent = ((indexCourrant - 1)%joueurs.length +joueurs.length) % joueurs.length;
            Heuristique h = new Heuristique(jeu, indexPrecedent, indexIA);
            return h.evalPoussee(c);
        }

        int bestScore = (indexCourrant == indexIA) ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (Coup coups : coupsPossible) {
            jeu.jouerCoup(coups);
            int indexSuivant = (indexCourrant + 1) % joueurs.length;
            int score = minimaxAB(coups,joueurs, indexSuivant, profondeur - 1, alpha, beta);

            jeu.annulerCoup();

            if (indexCourrant == indexIA) {  //MAX tour de notre IA
                bestScore = Math.max(bestScore, score);
                alpha = Math.max(alpha, score);
            } else {  //MIN tour d'un joueur adverse
                bestScore = Math.min(bestScore, score);
                beta = Math.min(beta, score);
            }

            if (alpha >= beta) {  //ELAGAGE de branche
                break;
            }
        }
        return bestScore;
    }

    public Coup execution(Joueur[] joueurs) {
        long startTime = System.nanoTime(); //debut chrono
        Joueur ia = joueurs[indexIA];
        int maxValVue = Integer.MIN_VALUE;
        int score;
        Coup bestCoup = null;
        int indexSuivant = (indexIA + 1) % joueurs.length;
        List<Coup> coupsPossible = new ArrayList<>(ia.getCoupsJouables());
        int i = 1;
        for (Coup coup : coupsPossible) {
            jeu.jouerCoup(coup);
            //System.out.println(jeu);
            score = minimaxAB(coup,joueurs, indexSuivant, profondeur -1, Integer.MIN_VALUE, Integer.MAX_VALUE);
            jeu.annulerCoup();

            if (score >= maxValVue) {
                maxValVue = score;
                bestCoup = coup;
            }

            i++;
        }
        long endTime = System.nanoTime();   //fin du chrono
        long tempsExec = endTime - startTime;
        System.out.println("l'execution du minimax alpha-beta s'est fait en " + tempsExec / 1_000_000.0 + " ms");

        return bestCoup;
    }
}






    /*
        for(Point point : courant.getCasesJouables()) {
            for (Piece piece : courant.getPiecesJouables().values()) {
                Coup coup = new Coup(piece, point);
                if (courant.estCoupValide(coup) != -1) {
                    Plateau copiePlateau = new Plateau(plateau);
                    copiePlateau.jouerCoup(coup, courant.getCouleurJoueur());
                    int indexSuivant = (indexCourrant + 1) % joueurs.size();
                    int score = minimaxAB(copiePlateau, joueurs, indexSuivant, profondeur -1, alpha, beta);

                    if (indexCourrant == indexIA){  //MAX tour de notre IA
                        bestScore = Math.max(bestScore, score);
                        alpha = Math.max(alpha, score);
                    }
                    else {  //MIN tour d'un joueur adverse
                        bestScore = Math.min(bestScore, score);
                        beta = Math.min(beta, score);
                    }

                    if(alpha >= beta){  //ELAGAGE de branche
                        break;
                    }
                }
            }
        }
        return bestScore;
    }
    */

     /*
    public Coup execution(Plateau plateau, List<Joueur> joueurs){
        long startTime = System.nanoTime(); //debut chrono

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
                    score = minimaxAB(copieP, joueurs,1, profondeur -1, Integer.MIN_VALUE, Integer.MAX_VALUE);
                    if (score >= maxValVue){
                        maxValVue = score;
                        bestCoup = new Coup(pieces, point);
                    }
                }

            }
        }
        long endTime = System.nanoTime();   //fin du chrono
        long tempsExec = endTime - startTime;
        System.out.println("l'execution du minimax alpha-beta s'est fait en " + tempsExec / 1_000_000.0 + " ms");

        return bestCoup;
    }*/

