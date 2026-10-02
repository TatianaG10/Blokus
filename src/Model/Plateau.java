package Model;

import java.io.Serializable;
import java.util.Arrays;

public class Plateau implements Serializable {
    private static final long serialVersionUID = 1L;
    private final int nbCases;
    private final Case[][] plateau;

    public Plateau(int nbCases) {
        this.nbCases=nbCases;
        plateau = new Case[nbCases][nbCases];
        for (int i = 0; i < nbCases; i++) {
            for (int j = 0; j < nbCases; j++) {
                plateau[i][j] = new Case();
            }
        }
    }

    public Plateau(Plateau plat) {
        // Deep copy
        nbCases= plat.nbCases; //suppose plateau carre
        plateau = new Case[nbCases][nbCases];
        for (int i = 0; i < nbCases; i++) {
            plateau[i] = Arrays.copyOf(plat.getGrid()[i], nbCases);
        }
    }

    /// SETTERS
    public void setType(int type, int index, int ligne, int colonne) {
        plateau[ligne][colonne].setType(type, index);
    }
    public void ajouterJoueur(int joueur, int ligne, int colonne) {
        plateau[ligne][colonne].ajouterJoueur(joueur);
    }
    public void retirerJoueur(int joueur, int ligne, int colonne) {
        plateau[ligne][colonne].retirerJoueur(joueur);
    }
    public void setCase(Case c, int ligne, int colonne) {
        plateau[ligne][colonne] = c;
    }

    /// GETTERS
    public Case getCase(int ligne, int colonne) {return plateau[ligne][colonne];}
    public int getNbCases() {
        return nbCases;
    }
    public Case[][] getGrid() {
        return plateau;
    }

    /// BOOLEANS
    public boolean estDansPlateau(int ligne, int colonne){
        return (ligne>=0 && ligne<nbCases && colonne>=0 && colonne<nbCases);
    }

    public boolean estCorner(int l, int c){
        return (l==0 && c==0 || l==0 && c==nbCases-1 || l==nbCases-1 && c==0 || l==nbCases-1 && c==nbCases-1);
    }

    public String toString(int joueur) {
        StringBuilder res = new StringBuilder();
        res.append("   ");
        for (int i=0; i<nbCases; i++){
            res.append(String.format("%-3d", i));
        }
        res.append("\n");

        for (int i = 0; i < nbCases; i++) {
            if (i<10) { //deux espaces
                res.append(i).append("  ");
            } else { //un espace
                res.append(i).append(" ");
            }
            for (int j = 0; j < nbCases; j++) {
                Case casePlateau = this.plateau[i][j];
                int[] types = casePlateau.getTypes();
                if (types[0]==Case.CASE || types[0]==Case.BLOQUE){
                    if (types[0]==Case.CASE){
                        res.append("0");
                    }
                    if (types[1]==Case.CASE){
                        res.append("1");
                    }
                    if (types[2]==Case.CASE){
                        res.append("2");
                    }
                    if (types[3]==Case.CASE){
                        res.append("3");
                    }
                }else {
                    if (types[joueur]==Case.COIN){
                        res.append("*");
                    }else{
                        res.append(" ");
                    }
                }
                res.append("  ");
            }
            res.append("\n");
        }
        return res.toString();
    }
}
