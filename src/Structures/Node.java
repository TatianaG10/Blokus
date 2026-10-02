package Structures;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Node<E> {
    public static int nbNoeud=0;

    private E valeur;
    private Set<Vecteur<E>> fils;
    private int profondeur;
    private int score;

    public Node(E v){
        valeur=v;
        fils = new HashSet<>();
        profondeur=0;
        score=0;
        nbNoeud++;
    }

    public void addFils(Vecteur<E> son){
        fils.add(son);
    }
    public void setProfondeur(int prof){profondeur=prof;}
    public int getProfondeur(){return profondeur;}
    public int getScore(){ return score; }
    public void setScore(int s){ score=s; }
    public E getValeur(){ return valeur; }
    public void setValeur(E v){ valeur=v; }

    public Set<Vecteur<E>> getFils(){ return fils; }
    public void setFils(Set<Vecteur<E>> f){ fils=f; }

    public boolean estFeuille(){ return fils.isEmpty(); }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        Sequence<Node<E>> fap = new SequenceListe<>();
        fap.insereTete(this);
        int prof=profondeur;
        while (!fap.estVide()){
            Node<E> n = fap.extraitTete();
            if (n.getProfondeur()>prof){
                prof=n.getProfondeur();
                res.append("--------------------------------------------\n");
            }
            res.append("(").append(n.getScore()).append(")").append(" ").append("Prof : ").append(n.getProfondeur()).append(" ").append("\n").append(n.getValeur()).append("\n");
            for (Vecteur<E> a : n.getFils()){
                fap.insereQueue(a.getNoeud());
            }
        }
        return res.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Node<E> other = (Node<E>) obj;
        return score==other.getScore() && profondeur==other.getProfondeur() && valeur.equals(other.valeur);
    }

    @Override
    public int hashCode() {
        return Objects.hash(profondeur,score,valeur);
    }
}
