package Model;
import Structures.Position;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class Coup implements Serializable {
    private static final long serialVersionUID = 1L;

    private Piece piece; // La pièce jouée
    private Position referencePiece;
    private Position referencePlateau; // Position de la pièce sur le plateau

    public Coup (Piece piece, Position pointPiece, Position pointPlateau) {
        this.piece = piece;
        referencePiece = pointPiece;
        referencePlateau = pointPlateau;
    }

    /// GETTERS
    public Piece getPiece() {
        return piece;
    }
    public Position getReferencePlateau() {
        return referencePlateau;
    }
    public Position getReferencePiece() {
        return referencePiece;
    }

    /// SETTERS
    public void setPiece(Piece p) {
        piece = p;
    }
    public void setReferencePiece(Position position) {
        referencePiece = position;
    }
    public void setReferencePlateau(Position position){ referencePlateau = position;}


    @Override
    public String toString() {
        return "( " + piece.getId() +
                ", " +
                referencePiece +
                ", " +
                referencePlateau + " )";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Coup coup = (Coup) obj;

        return (piece.equals(coup.getPiece())
                && (Objects.equals(referencePiece, coup.getReferencePiece()))
                && (Objects.equals(referencePlateau, coup.getReferencePlateau())));
    }

    public int hashCode() {
        return Objects.hash(Arrays.deepHashCode(piece.getMatrice()), referencePiece, referencePlateau);
    }

}