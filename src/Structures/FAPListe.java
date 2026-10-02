package Structures;

import Structures.FAP;
import Structures.Maillon;
import Structures.SequenceListe;

public class FAPListe<Bill extends Comparable<Bill>> extends FAP<Bill> {
	public SequenceListe<Bill> s;

	public FAPListe() {
		s = new SequenceListe<>();
		super.s = s;
	}

	@Override
    public void insere(Bill element) {
		Maillon<Bill> courant, precedent;

		precedent = null;
		courant = s.tete;
		while ((courant != null) && (element.compareTo(courant.element) > 0)) {
			precedent = courant;
			courant = courant.suivant;
		}

		Maillon<Bill> m = new Maillon<>();
		m.element = element;
		m.suivant = courant;
		if (precedent == null)
			s.tete = m;
		else
			precedent.suivant = m;
		if (courant == null)
			s.queue = m;
	}
}
