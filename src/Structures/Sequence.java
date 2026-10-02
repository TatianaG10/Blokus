package Structures;

import Structures.Iterateur;

public interface Sequence<Toto> {
	void insereTete(Toto element);
	void insereQueue(Toto element);
	Toto extraitTete();
	boolean estVide();
	int size();
	Iterateur<Toto> iterateur();
}
