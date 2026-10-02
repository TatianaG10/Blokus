package Model;

import Structures.Position;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Piece implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final int hauteurMax = 7;
    public static final int largeurMax = 7;

    private final int id;
    private final int valeur;
    private int[][] matricePiece;

    public Piece(int id) {
        this.id = id;
        this.valeur = copyValeur();
        this.matricePiece = copyPiece();
    }

    ///METHODES

    /**
     * Méthode qui copie "profondément" une pièce hardcodée de PiecesBlokus.PIECES vers une instance de Piece
     * @return une pièce qui est un tableau d'entiers
     */
    private int[][] copyPiece() {
        int[][] original = PiecesBlokus.PIECES.get(id).getFirst();
        int[][] copy = new int[hauteurMax][largeurMax];
        for (int i = 0; i < hauteurMax; i++) {
            System.arraycopy(original[i], 0, copy[i], 0, largeurMax);
        }
        return copy;
    }

    private int copyValeur(){
        return PiecesBlokus.PIECES.get(id).getSecond();
    }

    public ArrayList<Position> getAncragesPiece() {
        ArrayList<Position> ancrages = new ArrayList<>();
        for (int i = 0; i < hauteurMax; i++) {
            for (int j = 0; j < largeurMax; j++) {
                if (matricePiece[i][j] == Case.CASE) {
                    ancrages.add(new Position(i, j));
                }
            }
        }
        return ancrages;
    }


    /**
     * reCentreMatrice : re-centre le "corps" de la pièce (avec ces bords et coins) en haut à gauche de sa représentation matricielle.
     * @param matrix : une matrice d'entiers
     * @return une matrice d'entier correspondant à la matrice donnée en paramètre mais re-centrée
     */
    private int[][] reCentreMatrice(int[][] matrix) {
        int minRow = hauteurMax;
        int minCol = largeurMax;

        // Trouver les bornes hautes de la pièce
        for (int i = 0; i < hauteurMax; i++) {
            for (int j = 0; j < largeurMax; j++) {
                if (matrix[i][j] != 0) {
                    if (i < minRow) minRow = i;
                    if (j < minCol) minCol = j;
                }
            }
        }
        // Créer une nouvelle matrice vide
        int[][] nouvellePiece = new int[hauteurMax][largeurMax];

        // Copier les valeurs décalées
        for (int i = minRow; i < hauteurMax; i++) {
			System.arraycopy(matrix[i], minCol, nouvellePiece[i - minRow], 0, largeurMax - minCol);
        }

        // Remplacer l’ancienne matrice
        return nouvellePiece;
    }


    public void tourneAntiClockWise(){
        int [][] nouvelleMatrice = transposeSlashMatrix(matricePiece);
        nouvelleMatrice = inverseColonnesMatrix(nouvelleMatrice);
        matricePiece = reCentreMatrice(nouvelleMatrice);
    }

    public void tourneClockWise(){
        int[][] nouvelleMatrice = transposeMatrix(matricePiece);
        nouvelleMatrice = inverseColonnesMatrix(nouvelleMatrice);
        matricePiece = reCentreMatrice(nouvelleMatrice);
    }

    public void miroirVertical(){
        matricePiece = reCentreMatrice(inverseColonnesMatrix(matricePiece));
    }

    public void miroirHorizontal(){
        matricePiece = reCentreMatrice(inverseLignesMatrix(matricePiece));
    }

    ///GETTERS
    public ArrayList<Piece> getListeRotationsPossibles() {
        return new ArrayList<>(Objects.requireNonNull(PiecesBlokus.getRotationsPossibles(getId())));
    }
    public int getId() {return id;}
    public int getValeur() {return valeur;}
    public int[][] getMatrice() {
        return matricePiece;
    }


    /// FONCTIONS De Rotations

    /**
     * On suppose m carrée. Cette fonction transpose la matrice m en place.
     * @param matrix : une matrice d'entiers.
     */
    private static int[][] transposeMatrix(int [][] matrix){
        int [][] m = matrix.clone();
        int n = m.length;
        for (int i=0; i<n; i++){
            for (int j=i+1; j<n; j++){
                int tmp = m[i][j];
                m[i][j] = m[j][i];
                m[j][i] = tmp;
            }
        }
        return m;
    }

    /**
     * On suppose m carrée. Cette fonction transpose la matrice m en place selon la diagonale non triviale (/).
     * @param matrix : une matrice d'entiers.
     */
    private static int[][] transposeSlashMatrix(int [][] matrix){
        int [][] m = matrix.clone();
        int n = m.length;
        for (int i=0; i<n; i++){
            for (int j=0; j<n-i-1; j++){
                int tmp = m[i][j];
                m[i][j] = m[n-j-1][n-i-1];
                m[n-j-1][n-i-1] = tmp;
            }
        }
        return m;
    }

    private static int[][] inverseLignesMatrix(int [][] matrix) {
        int [][] m = matrix.clone();
        int n = m.length;
        for (int i = 0; i < n / 2; i++) {
            int[] tmp = m[i];
            m[i] = m[n - i - 1];
            m[n - i - 1] = tmp;
        }
        return m;
    }

    private static int[][] inverseColonnesMatrix(int [][] matrix) {
        int [][] m = matrix.clone();
        int n = m.length;
        for (int i = 0; i < n; i++) {
            for (int j=0; j<n/2; j++) {
                int tmp = m[i][j];
                m[i][j] = m[i][n-j-1];
                m[i][n-j-1] = tmp;
            }
        }
        return m;
    }


    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("\n");
        res.append("pièce : ").append(id).append(" \n");
        res.append("  0 1 2 3 4 5 6 \n");
        for (int i=0; i<hauteurMax; i++){
            res.append(i).append(" ");
            for (int j=0; j<largeurMax; j++) {
                if (matricePiece[i][j] == Case.CASE) {
                    res.append("X ");
                }else if (matricePiece[i][j] == Case.BORD) {
                    res.append("2 ");
                }else if (matricePiece[i][j] == Case.COIN) {
                    res.append("3 ");
                } else {
                    res.append("  ");
                }
            }
            res.append("\n");
        }
        return res.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;

        return Arrays.deepEquals(this.matricePiece, piece.matricePiece);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(matricePiece);
    }
}
