package Structures;

import Structures.Sequence;

public abstract class FAP<Bob> {
	public Sequence<Bob> s;

	public abstract void insere(Bob element);
	public Bob extrait() {
		return s.extraitTete();
	}
	public boolean estVide() {
		return s.estVide();
	}
}
