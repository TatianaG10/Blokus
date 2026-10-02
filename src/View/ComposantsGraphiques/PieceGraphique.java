package View.ComposantsGraphiques;

import Controller.Joueurs.Humain;
import Model.Piece;
import View.GridLayoutManager;

import javax.swing.*;
import java.awt.*;

public class PieceGraphique extends JComponent {
    //CONSTANTS
    public static final int CASE = 1;
    public static final int BORD = 2;
    public static final int COIN = 3;
    public static final int hauteurMaxPiece = 7;
    public static final int largeurMaxPiece = 7;

    //ATTRIBUTS
    private Humain player;

    private final int id;
    private final Piece piece;
    private final int score;
    private Color color;
    private Color colorBordure;
    private Color colorBackground;

    private CaseGraphique[][] grille;
    private int height;
    private int width;
    private int ancrageHaut;
    private int ancrageGauche;

    //STATUS
    private boolean isPlayed;
    private boolean isUnPlayable;
    private boolean isSelected;
    private boolean isMoving;
    private boolean isCurrentPlayer;

    private GridLayoutManager layout;


    //CONSTRUCTEUR
    public PieceGraphique(int id, Color colorP, Color colorB, Color colorBack) {
        this.id = id;
        color = colorP;
        colorBordure = colorB;
        colorBackground = colorBack;

        piece = new Piece(id);
        grille = copyPiece(piece.getMatrice());
        calculAncrageDimension();
        score = piece.getValeur();

        isPlayed = false;
        isSelected = false;
        isMoving = false;
        isUnPlayable = false;
        isCurrentPlayer = false;

        setLayout(new GridBagLayout());
        layout = new GridLayoutManager();
        organiserComposants();
    }

    public PieceGraphique(PieceGraphique pieceG) {
        this.id = pieceG.id;
        color = pieceG.color;
        colorBordure = pieceG.colorBordure;
        colorBackground = pieceG.colorBackground;

        piece = new Piece(id);
        grille = copyPiece(piece.getMatrice());
        calculAncrageDimension();
        score = piece.getValeur();

        isPlayed = false;
        isSelected = false;
        isMoving = false;
        isUnPlayable = false;
        isCurrentPlayer = false;

        setLayout(new GridBagLayout());
        layout = new GridLayoutManager();
        organiserComposants();
    }

    private void organiserComposants() {
        removeAll();
        for (int i=0;i<hauteurMaxPiece;i++){
            for (int j=0;j<largeurMaxPiece;j++){
                if (piece.getMatrice()[i][j]==CASE){
                    grille[i][j].setUnPlayable(isUnPlayable);
                    grille[i][j].setSelected(isSelected);
                    grille[i][j].setPlayed(isPlayed, colorBackground);
                }
                layout.updateGBC(j,i,1.0,1.0,new Insets(0,0,0,0),GridLayoutManager.CENTER,GridLayoutManager.BOTH);
                add(grille[i][j], layout);
            }
        }
    }

    public void setPlayed(boolean played) {
        isPlayed = played;
        organiserComposants();
        repaint();
    }

    public void setCurrentPlayer(boolean players){
        isCurrentPlayer = players;
        if (players){
            setCursor(new Cursor(Cursor.HAND_CURSOR)); //main comme curseur (indique clickable)
        }else{
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); //pas d'indication (pas une piece que le joueur peut jouer)
        }
        organiserComposants();
        repaint();
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
        organiserComposants();
        repaint();
    }
    public void setMoving(boolean moving) {
        isMoving = moving;
        repaint();
    }
    public void setUnPlayable(boolean unPlayable) {
        isUnPlayable = unPlayable;
        if (unPlayable){
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); //pas d'indication (peut pas etre joue)
        }else{
            setCursor(new Cursor(Cursor.HAND_CURSOR)); //main comme curseur (indique clickable)
        }
        organiserComposants();
        repaint();
    }
    public void setPlayer(Humain player) {
        this.player = player;
        addMouseListener(player);
    }

    /// GETTERS
    public int getId() {
        return id;
    }
    public Color getColor() {
        return color;
    }
    public Color getColorBordure() {
        return colorBordure;
    }
    public CaseGraphique[][] getGrille() {
        return grille;
    }
    public int getScore() {
        return score;
    }
    public Piece getPiece() {
        return piece;
    }
    public int getHauteur() {
        return height;
    }
    public int getLargeur() {
        return width;
    }
    public int getAncrageHaut() {
        return ancrageHaut;
    }
    public int getAncrageGauche() {
        return ancrageGauche;
    }

    /// Status
    public boolean isPlayed() {
        return isPlayed;
    }
    public boolean isUnPlayable() {return isUnPlayable;}
    public boolean isSelected() {
        return isSelected;
    }
    public boolean isMoving() {
        return isMoving;
    }
    public boolean isCurrentPlayer() {return isCurrentPlayer;}

    /// METHODES
    private void calculAncrageDimension(){
        int minI = grille.length;
        int maxI = 0;
        int minJ = grille[0].length;
        int maxJ = 0;

        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille[i].length; j++) {
                if (piece.getMatrice()[i][j] == CASE) {
                    if (i < minI) minI = i;
                    if (i > maxI) maxI = i;
                    if (j < minJ) minJ = j;
                    if (j > maxJ) maxJ = j;
                }
            }
        }

        // Met à jour les attributs
        ancrageHaut = minI;
        ancrageGauche = minJ;
        height = maxI - minI + 1;
        width = maxJ - minJ + 1;
    }

    /**
     * Méthode qui copie "profondément" une pièce hardcodée de PiecesBlokus.PIECES vers une instance de Piece
     * @return une pièce qui est un tableau d'entiers
     */
    private CaseGraphique[][] copyPiece(int [][] grille) {
        CaseGraphique[][] copy = new CaseGraphique[hauteurMaxPiece][largeurMaxPiece];
        for (int i = 0; i < hauteurMaxPiece; i++) {
            for (int j=0;j<largeurMaxPiece;j++) {
                if (grille[i][j] == CASE) {
                    copy[i][j] = new CaseGraphique(color, colorBordure);
                }else{
                    copy[i][j] = new CaseGraphique(colorBackground, null);
                }
            }
        }
        return copy;
    }

    public void tourneAntiClockWise(){
        piece.tourneAntiClockWise();
        grille = copyPiece(piece.getMatrice());
        calculAncrageDimension();
        organiserComposants();
        revalidate();
        repaint();
    }

    public void tourneClockWise(){
        piece.tourneClockWise();
        grille = copyPiece(piece.getMatrice());
        calculAncrageDimension();
        organiserComposants();
        revalidate();
        repaint();
    }

    public void miroirVertical(){

        piece.miroirVertical();
        grille = copyPiece(piece.getMatrice());
        calculAncrageDimension();
        organiserComposants();
        revalidate();
        repaint();
    }

    public void miroirHorizontal(){
        piece.miroirHorizontal();
        grille = copyPiece(piece.getMatrice());
        calculAncrageDimension();
        organiserComposants();
        revalidate();
        repaint();
    }

    private void dessineContour(Graphics2D g, int x, int y, Color couleurExterieur, Color couleurInterieur, int sizeStroke, int caseSize) {
        g.setStroke(new BasicStroke(sizeStroke));
        int[][] matrice = piece.getMatrice();

        for (int i = 0; i < hauteurMaxPiece; i++) {
            for (int j = 0; j < largeurMaxPiece; j++) {
                if (matrice[i][j] == CASE) {
                    int drawX = x + (j - ancrageGauche) * caseSize;
                    int drawY = y + (i - ancrageHaut) * caseSize;

                    int top = drawY;
                    int bottom = drawY + caseSize;
                    int left = drawX;
                    int right = drawX + caseSize;

                    // === Haut ===
                    if (i - 1 < 0 || matrice[i - 1][j] != CASE) {
                        g.setColor(couleurExterieur);
                        g.drawLine(left, top, right, top);
                    } else {
                        g.setColor(couleurInterieur);
                        g.drawLine(left + 1, top, right - 1, top);
                    }

                    // === Bas ===
                    if (i + 1 >= hauteurMaxPiece || matrice[i + 1][j] != CASE) {
                        g.setColor(couleurExterieur);
                        g.drawLine(left, bottom, right, bottom);
                    } else {
                        g.setColor(couleurInterieur);
                        g.drawLine(left + 1, bottom, right - 1, bottom);
                    }

                    // === Gauche ===
                    if (j - 1 < 0 || matrice[i][j - 1] != CASE) {
                        g.setColor(couleurExterieur);
                        g.drawLine(left, top, left, bottom);
                    } else {
                        g.setColor(couleurInterieur);
                        g.drawLine(left, top + 1, left, bottom - 1);
                    }

                    // === Droite ===
                    if (j + 1 >= largeurMaxPiece || matrice[i][j + 1] != CASE) {
                        g.setColor(couleurExterieur);
                        g.drawLine(right, top, right, bottom);
                    } else {
                        g.setColor(couleurInterieur);
                        g.drawLine(right, top + 1, right, bottom - 1);
                    }
                }
            }
        }
    }



    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //int caseSize = getWidth()/largeurMaxPiece; //carre
        //dessineContour((Graphics2D) g, 0,0, colorBordure, 2, caseSize );
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

    public void paint(Graphics2D g2d, int x, int y, int caseSize, Color bordureExterieur, Color bordureInterieur) {
        for (int i = 0; i < hauteurMaxPiece; i++) {
            for (int j = 0; j < largeurMaxPiece; j++) {
                if (piece.getMatrice()[i][j]==CASE) {
                    int drawX = x + (j - ancrageGauche) * caseSize - caseSize / 2;
                    int drawY = y + (i - ancrageHaut) * caseSize - caseSize / 2;

                    g2d.setColor(color);
                    g2d.fillRect(drawX, drawY, caseSize, caseSize);
                }
            }
        }
        //TODO : affiche bordure interieur
        dessineContour(g2d,x- caseSize / 2,y- caseSize / 2, bordureExterieur, bordureInterieur,2, caseSize);
    }
}
