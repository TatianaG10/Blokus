package Controller.Joueurs;

import Model.*;
import Structures.Coin;
import Structures.Position;
import View.Windows.GameWindow;

import java.io.Serializable;
import java.util.*;

public abstract class Joueur implements Serializable {
    private static final long serialVersionUID = 1L;

    public static int nbJoueurs = 0;

    protected Jeu game;
    protected transient GameWindow jeuGraphique;
    protected List<Piece> pieces;

    protected int idJoueur;
    protected int score;
    protected String pseudo;
    protected Coin coinInitial = null;

    public Joueur(String pseudo) {
        this.score=0;
        this.pseudo=pseudo;
        idJoueur=nbJoueurs;
        nbJoueurs++;
    }

    public void updateGraphics(){
        if (jeuGraphique!=null){
            jeuGraphique.updateAllGraphics(game,game.getIndexJoueurCourrant());
        }
    }

    ///  SETTERS
    public void setJeuGraphique(GameWindow jeuGraphique) {
        this.jeuGraphique = jeuGraphique;
        updateGraphics();
    }

    public void setGame(Jeu game) {
        this.game = game;
    }
    public void setScore(int score) {this.score = score;}
    public void setPieces(List<Piece> pieces) {
        this.pieces = new  ArrayList<>(pieces);
    }

    public void setCoin(Position coin){
        this.coinInitial = Coin.getCoin(coin);
    }

    /// GETTERS

    public List<Piece> getPieces(){return pieces;}
    public int getScore() {
        return score;
    }
    public int getIDJoueur() {
        return idJoueur;
    }
    public String getPseudo() {
        return pseudo;
    }
    public Jeu getGame() {
        return game;
    }
    public Coin getCoin(){return coinInitial;}

    /// STATUS BOOLEANS
    public boolean estBloque(){
        return getPiecesJouables().isEmpty();
    }

    /// METHODS
    public void initCasesJouables(){
        Plateau plateau = game.getPlateau();
        int indexMax = plateau.getNbCases()-1;

        HashSet<Position> casesJouables = new HashSet<>();
        casesJouables.add(new Position(0,0));
        casesJouables.add(new Position(0,indexMax));
        casesJouables.add(new Position(indexMax,0));
        casesJouables.add(new Position(indexMax,indexMax));

        for (Position p : casesJouables){
            plateau.getCase(p.getLigne(), p.getColonne()).setType(Case.COIN, idJoueur);
            plateau.getCase(p.getLigne(), p.getColonne()).ajouterJoueur(idJoueur);
        }
    }

    public void jouer(){}

    public HashSet<Position> getPositionsJouables() {
        HashSet<Position> positions = new HashSet<>();
        for (int i = 0; i < game.getPlateau().getNbCases(); i++) {
            for (int j = 0; j < game.getPlateau().getNbCases(); j++) {
                if (game.getPlateau().getCase(i, j).getType(idJoueur) == Case.COIN) {
                    positions.add(new Position(i, j));
                }
            }
        }
        return positions;
    }

    public HashSet<Piece> getPiecesJouables() {
        HashSet<Piece> piecesJouables = new HashSet<>();
        //Piece est jouable si -> au moins une rotation a une position jouable et un coup jouable
        for (Piece piece : pieces) {
            boolean occurence=false;
            for (Piece pieceRotation : piece.getListeRotationsPossibles()) {
                for (Position positionPlateau : getPositionsJouables()) {
                    for (Position positionPiece : pieceRotation.getAncragesPiece()) {
                        Coup coup = new Coup(pieceRotation, positionPiece, positionPlateau);
                        if (game.coupValid(coup, this)) {
                            occurence=true;
                            break;
                        }
                    }
                    if (occurence) {
                        break;
                    }
                }
                if (occurence) {
                    piecesJouables.add(piece);
                    break;
                }
            }
        }
        return piecesJouables;
    }


    public HashSet<Coup> getCoupsJouables() {
        HashSet<Coup> coupsJouables = new HashSet<>();
        HashSet<Piece> piecesJouables = getPiecesJouables();
        HashSet<Position> positionsJouables = getPositionsJouables(); // calculé une seule fois

        for (Piece piece : piecesJouables) {
            for (Piece pieceRotation : piece.getListeRotationsPossibles()) {
                for (Position positionPlateau : positionsJouables) {
                    for (Position positionPiece : pieceRotation.getAncragesPiece()) {
                        Coup coup = new Coup(pieceRotation, positionPiece, positionPlateau);
                        if (game.coupValid(coup, this)) {
                            coupsJouables.add(coup);
                        }
                    }
                }
            }
        }
        return coupsJouables;
    }


    public String toString(){
        return pseudo+": ("+score+" pts)\n";
    }
}
