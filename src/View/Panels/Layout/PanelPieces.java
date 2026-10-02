package View.Panels.Layout;

import Controller.Configuration;
import Controller.Joueurs.Humain;
import Controller.Joueurs.Joueur;
import Model.Piece;
import Model.Jeu;
import View.GridLayoutManager;
import View.ComposantsGraphiques.PieceGraphique;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class PanelPieces extends JPanel {
    public static final int LANDSCAPE = 0;
    public static final int PORTRAIT = 1;
    public static final int REVERSE_LANDSCAPE = 2;
    public static final int REVERSE_PORTRAIT = 3;

    private static List<PanelPieces> panelPieces = new ArrayList<PanelPieces>(Configuration.nbJoueurs);
    private static List<List<PieceGraphique>> pieces = new ArrayList<>(Configuration.nbJoueurs);

    private static final int nbPieces = 21;
    private Color couleurBackground;
    private Color couleurBordure;
    private Color couleurPieces;
    private final int nbLignes;
    private final int nbColonnes;
    private int joueurDisplayed;
    private int orientation;
    private int currentPlayer;

    private GridLayoutManager layout = new GridLayoutManager();
    private Joueur player;

    public PanelPieces(Joueur player, int o){
        orientation=o;
        joueurDisplayed=player.getIDJoueur();
        currentPlayer=0;
        this.player = player;

        setLayout(new GridBagLayout());
        couleurPieces = Configuration.ordreJoueurs[joueurDisplayed];
        couleurBackground = Configuration.backgroundPiecesPanel[joueurDisplayed];
        couleurBordure = Configuration.bordureJoueurs[joueurDisplayed];

        if (orientation == LANDSCAPE){ //joueur Courrant
            nbLignes=3;nbColonnes=nbPieces/nbLignes;
            //JoueurCourrant et celui qui joue les pieces
        } else if (orientation == PORTRAIT || orientation == REVERSE_PORTRAIT){
            nbColonnes=3;nbLignes=nbPieces/nbColonnes;
        } else {
            nbLignes=3;nbColonnes=nbPieces/nbLignes;
        }

        initComposants();
        organiserComposants();
        panelPieces.add(orientation, this);
    }

    public static void reinitialise(){
        panelPieces = new ArrayList<>(4);
        pieces = new ArrayList<>(4);
    }

    private void initComposants(){
        List<PieceGraphique> grille = new ArrayList<>(nbPieces);
        for (int i=0;i<nbLignes;i++){
            for (int j=0;j<nbColonnes;j++){
                int id = i*nbColonnes+j;
                PieceGraphique piece = new PieceGraphique(id,couleurPieces, couleurBordure, couleurBackground);
                if (player instanceof Humain){
                    piece.setPlayer((Humain) player);
                }
                grille.add(id,piece);
            }
        }
        pieces.add(orientation, grille);
    }

    private void organiserComposants() {
        removeAll();
        setBackground(couleurBackground);
        setBorder(BorderFactory.createLineBorder(couleurBordure,4));

        HashSet<Piece> piecesJouables = player.getPiecesJouables();
        List<Piece> piecesDispo = player.getPieces();

        pieces.get(joueurDisplayed).forEach(piece->{
            piece.setCurrentPlayer(currentPlayer==joueurDisplayed);
            if (currentPlayer==joueurDisplayed){
                boolean isPlayable = piecesJouables.stream()
                        .anyMatch(p -> p.getId() == piece.getId());
                piece.setUnPlayable(!isPlayable);
            }
            boolean estPasJouee = piecesDispo.stream().anyMatch(p -> p.getId() == piece.getId());
            piece.setPlayed(!estPasJouee);
        });

        //Initialize le layout
        for (int i=0;i<nbLignes;i++){
            for (int j=0;j<nbColonnes;j++){
                int id = i*nbColonnes+j;
                layout.updateGBC(j,i,1.0,1.0,new Insets(0,0,0,0),GridLayoutManager.CENTER,GridLayoutManager.BOTH);
                add(pieces.get(joueurDisplayed).get(id), layout);
            }
        }
        revalidate();
        repaint();
    }

    public static void update(Jeu game){
        int joueur = game.getIndexJoueurCourrant();
        for (int i=0;i<Configuration.nbJoueurs;i++){
            int index = (joueur+i)% Configuration.nbJoueurs;
            panelPieces.get(i).setJoueur(index);
            panelPieces.get(i).currentPlayer=joueur;
            panelPieces.get(i).organiserComposants();
        }
    }

    //Action from player IHM
    public void setJoueur(int j){
        joueurDisplayed=j;
        player = player.getGame().getJoueurs()[joueurDisplayed];
        couleurPieces = Configuration.ordreJoueurs[joueurDisplayed];
        couleurBordure = Configuration.bordureJoueurs[joueurDisplayed];
        couleurBackground = Configuration.backgroundPiecesPanel[joueurDisplayed];

        organiserComposants();
    }

    public static void setJoueurs(int joueurDisplayed){
        for (int i=0;i<Configuration.nbJoueurs;i++){
            int index = (joueurDisplayed+i)% Configuration.nbJoueurs;
            panelPieces.get(i).setJoueur(index);
        }
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
    }
}
