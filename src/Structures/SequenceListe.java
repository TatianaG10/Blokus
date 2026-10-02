package Structures;

import java.io.Serializable;

class Maillon<Tutu> implements Serializable {
	private static final long serialVersionUID = 1L;

	Tutu element;
	Maillon<Tutu> suivant;
}

public class SequenceListe<Titi> implements Sequence<Titi>, Serializable {
	private static final long serialVersionUID = 1L;

	Maillon<Titi> tete, queue;
	int taille;

	public void insereTete(Titi element) {
		Maillon<Titi> nouveau = new Maillon<>();
		nouveau.element = element;
		nouveau.suivant = tete;
		if (tete == null) {
			tete = nouveau;
			queue = nouveau;
			taille = 0;
		} else {
			tete = nouveau;
		}
		taille++;
	}

	public void insereQueue(Titi element) {
		Maillon<Titi> nouveau = new Maillon<>();
		nouveau.element = element;
		nouveau.suivant = null;
		if (tete == null) {
			tete = nouveau;
			queue = nouveau;
			taille=0;
		} else {
			queue.suivant = nouveau;
			queue = nouveau;
		}
		taille++;
	}

	public Titi extraitTete() {
		if (tete == null)
			throw new RuntimeException("Sequence vide !");
		Titi resultat = tete.element;
		tete = tete.suivant;
		taille--;
		// Ici, oubli de la mise à jour de la queue probablement sans conséquences :
		// la queue n'est incohérente qu'en cas de liste vide, dans ce cas pas d'itération
		// possible sur ses éléments et tout sera remis en cohérence à la prochaine insertion
		return resultat;
	}

	public Iterateur<Titi> iterateur() {
		return new IterateurListe<>(this);
	}

	public boolean estVide() {
		return tete == null;
	}

	public int size() { return taille; }

	public String toString() {
		StringBuilder resultat = new StringBuilder("Sequence liste : [ ");
		Maillon<Titi> courant = tete;
		while (courant != null) {
			resultat.append(courant.element).append(" ");
			courant = courant.suivant;
		}
		resultat.append("]");
		return resultat.toString();
	}
}
