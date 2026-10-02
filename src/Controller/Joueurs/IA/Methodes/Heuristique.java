package Controller.Joueurs.IA.Methodes;

import Controller.Joueurs.IA.IA_intermediare;
import Model.Jeu;
import Controller.Joueurs.Joueur;
import Model.Piece;
import Model.Plateau;
import Model.Coup;
import Structures.Coin;
import Structures.Position;

import java.util.ArrayList;
import java.util.List;

//TODO REGLER LE SOUCI AVEC SIMULATION DE COUP AVEC VALEUR INCHANGEE

public class Heuristique {

    private Plateau copieP;
    private Joueur joueur;
    private Coin coinInitial;
    private Joueur[] players;
    private Jeu jeu;
    private int indexIA;

    //Pondération BONUS ET PENALITES
    private static final int BONUS_COINS_ADVERSE_BLOQUES = 4;
    private static final int BONUS_COINS_DEBLOQUES = 6;
    private static final int BONUS_AXIAL = 6;
    private static final int BONUS_PIECE_JOUEE = 3;
    private static final int PENALITE_PETITES_PIECES = 5;
    private static final int PENALITE_COINS_BLOQUES = 4;
    private static final int PENALITE_DISTANCE_OPPS = 4;

    public Heuristique(Jeu j, int emplacement, int ia){
        this.jeu = j;
        players = jeu.getJoueurs();
        this.joueur = jeu.getJoueur(emplacement);
        this.indexIA = ia;
        this.coinInitial = j.getJoueur(emplacement).getCoin();
    }

    /// METHODES DE CALCULS


    public int coinJouablesAdverse(){
        int nbCoin = 0;
        for (Joueur j : players){
            nbCoin += j.getPositionsJouables().size();
        }
        return nbCoin - joueur.getPositionsJouables().size();   //pour avoir uniquement le nombre de cases des ops
    }

    public int coupsJouablesAdverse(){
        int nbCoup = 0;
        for (Joueur j : players){
            nbCoup += j.getCoupsJouables().size();
        }
        return nbCoup - joueur.getCoupsJouables().size();
    }

    public int pieceAdverse(){
        int pieces = 0;
        for(Joueur j : players){
            pieces += j.getPiecesJouables().size();
        }
        return pieces - joueur.getPiecesJouables().size();
    }

    public int distanceDuCentre(Position coup){
        Position mid1 = new Position(9,9);
        Position mid2 = new Position(9,10);
        Position mid3 = new Position(10,9);
        Position mid4 = new Position(10,10);

        List<Double> lesDistances = new ArrayList<>();
        lesDistances.add(0,coup.distanceTo(mid1));
        lesDistances.add(0,coup.distanceTo(mid2));
        lesDistances.add(0,coup.distanceTo(mid3));
        lesDistances.add(0,coup.distanceTo(mid4));

        double distanceMin = lesDistances.get(0);
        for(double d : lesDistances){
            if (distanceMin >= d){
                distanceMin = d;
            }
        }

        return (int)distanceMin;
    }

    /// PENALITES

    public boolean peutMieuxFaire(Coup coup){
        boolean aPlusGros = false;
        for (Piece p : joueur.getPiecesJouables()){
            if (p.getValeur() > 3){
                aPlusGros = true;
                break;
            }
        }
        return aPlusGros;
    }


    /// LES FONCTIONS D'EVALUATIONS

    public int evalScore(){
        //joueur = players.get(courant);
        System.out.println( joueur.getPseudo() +" A UN RESULTAT DE: " + joueur.getScore());
        return joueur.getScore();
    }
    /**
     * @param
     * @return le score du joueur courant - la difference de nombre de coupsJouables avant et après coup joué   */

    public int evalNbCasesJouables() {
        return coinJouablesAdverse();
    }

    public int evalPieceJouables(){
        return pieceAdverse();
    }

    public int evalSimple() {
        int scoreIA = players[indexIA].getScore();
        int coupsRestantsAdverses = coinJouablesAdverse();

        // Pondération simple : plus le score est haut, mieux c’est ; moins les adversaires peuvent jouer, mieux c’est
        int score = scoreIA - coupsRestantsAdverses;
        System.out.println("Heuristique : scoreIA = " + scoreIA + ", coupsRestantsAdverses = " + coupsRestantsAdverses + ", eval = " + score);
        return score;
    }

    public int evalSimple2(Coup coup) {
        int scoreIA = players[indexIA].getScore();
        int coinsRestantsAdverses = coinJouablesAdverse();
        int distance = distanceDuCentre(coup.getReferencePlateau());
        int nbCoinIA = players[indexIA].getPositionsJouables().size();

        if( ((IA_intermediare)players[indexIA]).getNbCoupsJoue() > 5){
            int score = scoreIA - (coinsRestantsAdverses * BONUS_COINS_ADVERSE_BLOQUES) + nbCoinIA * BONUS_COINS_DEBLOQUES;
            System.out.println("Heuristique : scoreIA = " + scoreIA + ", coupsRestantsAdverses = " + coinsRestantsAdverses + ", eval = " + score);
            return score;
        }
        // Pondération simple : plus le score est haut, mieux c’est ; moins les adversaires peuvent jouer, mieux c’est
        int score = 2*scoreIA - (coinsRestantsAdverses * BONUS_COINS_ADVERSE_BLOQUES) - (distance * BONUS_AXIAL);
        System.out.println("Heuristique : scoreIA = " + scoreIA + ", coupsRestantsAdverses = " + coinsRestantsAdverses + ", eval = " + score + ", distanceC = "+distance);
        return score;
    }

    public int evalPoussee(Coup coup) {
        int scoreIA = players[indexIA].getScore();
        int coupsRestantsAdverses = coupsJouablesAdverse();
        int distance = distanceDuCentre(coup.getReferencePlateau());
        int nbCoinIA = players[indexIA].getPositionsJouables().size();

        int score = 0;

        if (((IA_intermediare) players[indexIA]).getNbCoupsJoue() < 6) {
            //EARLY GAME
            score = 5 * scoreIA + (nbCoinIA * BONUS_COINS_DEBLOQUES) - (distance * BONUS_AXIAL);
            return score;
        }

        else if(((IA_intermediare) players[indexIA]).getNbCoupsJoue() < 12){
            //MID GAME
            score = 2*scoreIA + (nbCoinIA * BONUS_COINS_DEBLOQUES) - (coupsRestantsAdverses * BONUS_COINS_ADVERSE_BLOQUES);
            return score;
        }

        else{
            score = 5*scoreIA + (nbCoinIA * BONUS_COINS_DEBLOQUES) - (coupsRestantsAdverses * BONUS_COINS_ADVERSE_BLOQUES);
            return score;
        }


    }

    public int evalPoussee2(Coup coup) {
        int scoreIA = players[indexIA].getScore();
        int coinsRestantsAdverses = coinJouablesAdverse();
        int distance = distanceDuCentre(coup.getReferencePlateau());
        int nbCoinIA = players[indexIA].getPositionsJouables().size();

        int score = 0;

        if (((IA_intermediare) players[indexIA]).getNbCoupsJoue() < 6) {
            //EARLY GAME
            score = 5 * scoreIA + (nbCoinIA * BONUS_COINS_DEBLOQUES) - (distance * BONUS_AXIAL);
            return score;
        }

        else if(((IA_intermediare) players[indexIA]).getNbCoupsJoue() < 12){
            //MID GAME
            score = 2*scoreIA + (nbCoinIA * BONUS_COINS_DEBLOQUES) - (coinsRestantsAdverses * BONUS_COINS_ADVERSE_BLOQUES);
            return score;
        }

        else{
            score = 5*scoreIA + (nbCoinIA * BONUS_COINS_DEBLOQUES) - (coinsRestantsAdverses * BONUS_COINS_ADVERSE_BLOQUES);
            System.out.println("END GAME");
            return score;
        }

    }

    public int eval(Coup coup){
        int scoreIA = players[indexIA].getScore();
        int coupsAdverses = coinJouablesAdverse();
        Position cp = coup.getReferencePiece();
        Position centre = new Position(10,10);
        int distanceCentre = (int)cp.distanceTo(centre);
        int score = 2*scoreIA - coupsAdverses;
        score -= distanceCentre*BONUS_AXIAL;
        return score;
    }

    public int evaluation(Coup coup){
        int score = 0;
        int valeurPiece = coup.getPiece().getValeur();
        //Coin j = joueur.getCoin();
        Coin coinOppsJcourant = coinInitial.getCoinOpps();
        //on récupère la distance entre la position du coup et le coin opposé à son coin de depart
        double distance = coup.getReferencePlateau().distanceTo(coinOppsJcourant.getPosition());
        score -= (int)(distance * PENALITE_DISTANCE_OPPS);

        score += valeurPiece * BONUS_PIECE_JOUEE;
        if (!peutMieuxFaire(coup)){
            score -= PENALITE_PETITES_PIECES;
        }
        int res = joueur.getScore() + (2 * score) - coinJouablesAdverse();
        System.out.println("RESULTAT : "+res);
        return res;
    }
    /**
     * @param coup
     * @param courant
     * @return (score total - celui du jCourant + nbCasesAdverse + nbPiecesAdverse)après coup - (la même chose avant coup)
     */

    /*public int eval(Coup coup, int courant){
        joueur = players.get(courant);      //maj du joueur courant

        //Des valeurs que l'on prend avant avoir joué coup
        int score = joueur.getScore();
        int nbCasesOps = caseJouablesAdverse();
        jeu.jouerCoupJoueurJeu(joueur.getId(), coup);

        //Les valeurs après coup
        int score2 = joueur.getScore();
        int nbCasesOps2 = caseJouablesAdverse();

        jeu.annulerCoup();

        return (score2 + nbCasesOps2) - (score + nbCasesOps);
    }*/


}
