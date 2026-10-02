package View.Panels.Layout;

import Controller.Configuration;
import Controller.Joueurs.Joueur;
import Model.Case;
import Model.Jeu;
import Model.Coup;
import Model.Plateau;
import Structures.Position;
import View.ComposantsGraphiques.CaseGraphique;
import View.ComposantsGraphiques.PieceGraphique;
import View.GridLayoutManager;

import javax.swing.*;
import java.awt.*;

public class PanelPlateau extends JPanel {
    public static final int nbCases = 20;
    public static int caseSize;


    private GridLayoutManager layoutManager;

    private final Color[][] gridColor;
    private final Color[][] gridColorBorder;
    private CaseGraphique[][] grilleDeJeu;


    public PanelPlateau(){
        grilleDeJeu = new CaseGraphique[nbCases][nbCases];
        gridColor = new Color[nbCases][nbCases];
        gridColorBorder = new Color[nbCases][nbCases];

        setOpaque(true);
        setBackground(Configuration.GRAY_DARK);
        layoutManager = new GridLayoutManager();
        setLayout(new GridBagLayout());

        initComposants();
        organiserComposants();
    }

    private Color getGridColor(int i, int j){
        if ((i & 1) == 0){
            if ( (j & 1) == 0) return Configuration.GRAY;
            else return Configuration.WHITE;
        }else{
            if ( (j & 1) == 1) return Configuration.GRAY;
            else return Configuration.WHITE;
        }
    }

    public CaseGraphique[][] getGrilleDeJeu() {
        return grilleDeJeu;
    }

    private void initComposants(){
        for (int i=0;i<nbCases;i++){
            for (int j=0;j<nbCases;j++){
                gridColor[i][j] = getGridColor(i,j);
                grilleDeJeu[i][j] = new CaseGraphique(gridColor[i][j], null);
                gridColorBorder[i][j] = null;
            }
        }
    }

    private void organiserComposants() {
        for (int i=0;i<nbCases;i++){
            for (int j=0;j<nbCases;j++){
                layoutManager.updateGBC(j,i,1.0,1.0,new Insets(0,0,0,0),GridLayoutManager.CENTER,GridLayoutManager.BOTH);
                add(grilleDeJeu[i][j], layoutManager);
            }
        }
    }

    public void update(Jeu game){
        Plateau plateau = game.getPlateau();
        for  (int i=0;i<nbCases;i++){
            for (int j=0;j<nbCases;j++){
                if (plateau.estDansPlateau(i,j)){
                    Case casePlateau = plateau.getCase(i,j);
                    for (Joueur k : game.getJoueurs()){
                        int id = k.getIDJoueur();
                        int type = casePlateau.getType(id);
                        switch (type) {
                            case Case.CASE:
                                grilleDeJeu[i][j].unMarquer();
                                grilleDeJeu[i][j].setColor(Configuration.ordreJoueurs[id]);
                                grilleDeJeu[i][j].setBordure(Configuration.bordureJoueurs[id]);
                                break;
                            case Case.BLOQUE:
                                // No action
                                break;
                            default:
                                grilleDeJeu[i][j].setBordure(gridColorBorder[i][j]);
                                grilleDeJeu[i][j].setColor(gridColor[i][j]);
                                break;
                        }

                    }
                }
            }
        }
    }

    public void updateFeedForward(Jeu game, int joueur){
        for (int i=0;i<nbCases;i++){
            for (int j=0;j<nbCases;j++){
                grilleDeJeu[i][j].unMarquer();
            }
        }
        for (Joueur k : game.getJoueurs()){
            for (Position p : game.getPositionsJouables(k.getIDJoueur())){
                int ligne=p.getLigne();
                int colonne=p.getColonne();
                if (k.getIDJoueur() == game.getIndexJoueurCourrant() && k.getIDJoueur() == joueur){
                    if (game.getCoupsJouables(k.getIDJoueur()).stream().anyMatch(coup -> coup.getReferencePlateau().equals(p))){
                        grilleDeJeu[ligne][colonne].marquer(Configuration.ordreJoueurs[k.getIDJoueur()]);
                        grilleDeJeu[ligne][colonne].setBordure(gridColorBorder[ligne][colonne]);
                        grilleDeJeu[ligne][colonne].setColor(gridColor[ligne][colonne]);
                    }
                }
            }
        }
    }

    public void mettrePiece(PieceGraphique movingPiece, int ligne, int colonne) {
        for (int i=movingPiece.getAncrageHaut();i<PieceGraphique.hauteurMaxPiece;i++){
            for (int j=movingPiece.getAncrageGauche();j<PieceGraphique.largeurMaxPiece;j++){
                if (movingPiece.getPiece().getMatrice()[i][j]==PieceGraphique.CASE){
                    int indexL = ligne+i-movingPiece.getAncrageHaut();
                    int indexC = colonne+j-movingPiece.getAncrageGauche();
                    if (indexL>=0 && indexL<20 && indexC>=0 && indexC<20){
                        grilleDeJeu[indexL][indexC].setColor(movingPiece.getColor());
                        grilleDeJeu[indexL][indexC].setBordure(movingPiece.getColorBordure());
                    }
                }
            }
        }
        repaint();
    }


    public void retirerPiece(PieceGraphique movingPiece, int ligne, int colonne) {
        for (int i=movingPiece.getAncrageHaut();i<PieceGraphique.hauteurMaxPiece;i++){
            for (int j=movingPiece.getAncrageGauche();j<PieceGraphique.largeurMaxPiece;j++){
                if (movingPiece.getPiece().getMatrice()[i][j]==PieceGraphique.CASE){
                    int indexL = ligne+i-movingPiece.getAncrageHaut();
                    int indexC = colonne+j-movingPiece.getAncrageGauche();
                    if (indexL>=0 && indexL<20 && indexC>=0 && indexC<20){
                        grilleDeJeu[indexL][indexC].setBordure(gridColorBorder[indexL][indexC]);
                        grilleDeJeu[indexL][indexC].setColor(gridColor[indexL][indexC]);
                    }
                }
            }
        }
        repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension size = super.getPreferredSize();
        int dim = Math.min(size.width, size.height);
        return new Dimension(dim, dim);
    }

    @Override
    public void setBounds(int x, int y, int width, int height) {
        int size = Math.min(width, height);
        // Centrer dans l'espace alloué
        int offsetX = x + (width - size) / 2;
        int offsetY = y + (height - size) / 2;
        super.setBounds(offsetX, offsetY, size, size);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        caseSize=getWidth()/nbCases;
    }
}
