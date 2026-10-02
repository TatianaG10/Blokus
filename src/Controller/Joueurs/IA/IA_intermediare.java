package Controller.Joueurs.IA;

import Model.*;
import Controller.Joueurs.IA.Methodes.AlphaBeta_Pruning;
import Controller.Joueurs.Joueur;
import Structures.Coin;

import java.io.Serializable;
import java.util.ArrayList;

public class IA_intermediare extends Joueur implements Serializable {
    private static final long serialVersionUID = 1L;

    //private MiniMax minimax;
    private AlphaBeta_Pruning alphB;
    private Joueur[] joueurs;
    private int indexIA;
    private Jeu jeuCourant;
    private int profondeur;
    private Coin coin;
    private int nbCoupsJoue = 0;

    //TODO: Il faudrait vérifier que indexIA est bien celui du joueur en question sinon ça pourrait partir en cacahuete

    public IA_intermediare(Joueur joueur, int profondeur){
        super(joueur.getPseudo());
        score=joueur.getScore();
        pieces = new ArrayList<>(joueur.getPieces());
        idJoueur=joueur.getIDJoueur();
        this.profondeur = profondeur;
    }

    public IA_intermediare(String pseudo){
        super(pseudo);
        this.profondeur = 1;
    }


    public void setPlayers(Joueur[] j, int id){
        joueurs = j;
        this.indexIA = id;
    }

    public int getNbCoupsJoue(){
        return nbCoupsJoue;
    }

    @Override
    public void jouer(){
        /*if(nbCoupsJoue < 5){
            this.profondeur = 1;
        }

        else if(this.getCoupsJouables().size() > 9){
            this.profondeur = 2;
        }*/

        if (getCoupsJouables().size() < 10){   //augmentation de la profondeur en fin de partie
            this.profondeur = 4;
        }

        Coup c = getCoup();
        nbCoupsJoue++;
        System.out.println("Coup numero "+ nbCoupsJoue +" joué : " + c);

        if (getPieces().size() == PiecesBlokus.NOMBREDEPIECES){
            setCoin(c.getReferencePlateau());
            coin = getCoin();
            System.out.println("COIN IA :"+ coin);
        }

        super.game.jouerCoup(c);
        updateGraphics();
    }

    private Coup getCoup() {
        System.out.println("profondeur dans iaInter "+profondeur);
        this.alphB = new AlphaBeta_Pruning(profondeur, indexIA, game);
        return alphB.execution(joueurs);
    }


}