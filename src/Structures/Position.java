package Structures;

import java.io.Serializable;

public class Position implements Serializable {
	private static final long serialVersionUID = 1L;

	public int ligne;
	public int colonne;

	public Position(int ligne, int colonne) {
		this.ligne = ligne;
		this.colonne = colonne;
	}

	/// Getters
	public int getLigne() { return ligne; }
	public int getColonne() { return colonne; }

	/// Setters
	public void setLigne(int ligne) { this.ligne = ligne; }
	public void setColonne(int colonne) { this.colonne = colonne; }

	// Distance to another point
	public double distanceTo(Position other) {
		int dx = this.ligne - other.ligne;
		int dy = this.colonne - other.colonne;
		return Math.sqrt(dx * dx + dy * dy);
	}

	// Equals
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!(obj instanceof Position)) return false;
		Position other = (Position) obj;
		return this.ligne == other.ligne && this.colonne == other.colonne;
	}

	// HashCode
	@Override
	public int hashCode() {
		return super.hashCode(); //automatique
	}

	// ToString
	@Override
	public String toString() {
		return "(" + ligne + ", " + colonne + ")";
	}
}
