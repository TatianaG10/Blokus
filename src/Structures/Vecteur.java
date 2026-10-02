package Structures;

import java.awt.*;
import java.util.Objects;

public class Vecteur<E> {
    public static int nbVecteur=0;
    private Node<E> noeud;
    private Point coup;

    public Vecteur(Node<E> n, Point c){
        noeud=n;
        coup=c;
        nbVecteur++;
    }


    public Node<E> getNoeud(){ return noeud; }
    public Point getCoup(){ return coup; }
    public void setCoup(Point c){ coup=c; }
    public void setNoeud(Node<E> n){ noeud=n; }


    public String toString(){
        return coup.toString() ;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vecteur<E> other = (Vecteur<E>) obj;
        return noeud.equals(other.noeud) && coup==other.coup;
    }

    @Override
    public int hashCode() {
        return Objects.hash(noeud,coup);
    }
}
