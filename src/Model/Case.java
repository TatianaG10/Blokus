package Model;

import Structures.Sequence;
import Structures.SequenceListe;

import java.io.Serializable;

public class Case implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final int BLOQUE = -1;
    public static final int VIDE = 0;
    public static final int CASE = 1;
    public static final int BORD = 2;
    public static final int COIN = 3;

    private int[] types;         // Types de case pour chaque joueur
    private final Sequence<int[]> oldTypes;
    private final Sequence<Integer> oldBits;
    private int joueursBits; // Bits représentant les joueurs

    public Case() {
        this.types = new int[]{VIDE, VIDE, VIDE, VIDE};
        this.oldTypes = new SequenceListe<>();
        this.joueursBits = 0;
        this.oldBits = new SequenceListe<>();
    }

    /// SETTERS

    /**
     * Fonction permettant de definir le type de Case {Vide, Case, Bord, Coin}
     */
    public void setType(int type, int indexJoueur) {
        types[indexJoueur] = type;
    }

    public void setJoueursBits(int bits) {
        joueursBits = bits;
    }

    /// GETTERS

    /**
     * Fonction permettant de recuperer le type de Case {Vide, Case, Bord, Coin}
     */
    public int getType(int indexJoueur) {
        return types[indexJoueur];
    }
    public int getOldType(int indexJoueur) {
        if (oldTypes.estVide()) return VIDE;
        return oldTypes.iterateur().prochain()[indexJoueur];
    }

    public int[] getTypes() {
        return types;
    }
    public int[] getOldTypes() {
        if (oldTypes.estVide()) return null;
        return oldTypes.iterateur().prochain();
    }

    /**
     * Fonction permettant de recuperer l'ensemble des joueurs associé à la Case
     */
    public int getJoueursBits() {
        return joueursBits;
    }
    public int getJoueursOldBits() {
        if (oldBits.estVide()) return 0;
        return oldBits.iterateur().prochain();
    }

    /// METHODES

    public void rebaseOldVersion(boolean estCoin) {
        types = oldTypes.extraitTete();
        joueursBits = oldBits.extraitTete();
    }


    public void setVersion(){
        oldTypes.insereTete(types.clone());
        oldBits.insereTete(joueursBits);
    }

    /**
     * Fonction qui permet de bloquer la case pour joueurId
     */
    public void setJoueur(int joueurId) {
        joueursBits = (1 << joueurId);
        types = new int[]{BLOQUE, BLOQUE, BLOQUE, BLOQUE};
        types[joueurId] = CASE;
    }

    /**
     * Fonction qui permet de dire que la case appartient au joueurId
     */
    public void ajouterJoueur(int joueurId) {
        joueursBits |= (1 << joueurId);
    }
    /**
     * Fonction qui permet de retirer l'appartenance de la case pour joueurId
     */
    public void retirerJoueur(int joueurId) {
        joueursBits &= ~(1 << joueurId);
    }

    /**
     * Fonction qui permet de verifier si la case appartient a joueurId
     */
    public boolean appartientAuJoueur(int joueurId) {
        return (joueursBits & (1 << joueurId)) != 0;
    }

    @Override
    public String toString() {
        String res="[";
        for (int type : types) {
            switch (type) {
                case CASE:
                    res += "x";
                    break;
                case BORD:
                    res += "B";
                    break;
                case COIN:
                    res += "C";
                    break;
                default:
                    res += ".";
                    break;
            }
            res += ", ";
        }

        res+="] "+joueursBits;
        return res;
    }
}
