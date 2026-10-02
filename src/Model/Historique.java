package Model;

import Controller.Joueurs.Joueur;
import Structures.Iterateur;
import Structures.SequenceListe;

import java.io.Serializable;

public class Historique implements Serializable {
    private static final long serialVersionUID = 1L;

    private Coup dernierCoupJoue;
    private SequenceListe<Coup> pileCoupAnnule;
    private SequenceListe<Coup> pileCoupJoue;

    public Historique() {
        dernierCoupJoue=null;

        // initialisation de l'historique
        pileCoupJoue = new SequenceListe<Coup>();
        pileCoupAnnule = new SequenceListe<Coup>();
    }

    public Historique(Historique historique) {
        dernierCoupJoue=historique.dernierCoupJoue;

        // initalisation de l'historique
        pileCoupJoue = new SequenceListe<Coup>();
        Iterateur<Coup> it = historique.pileCoupJoue.iterateur();
        while (it.aProchain()){
            pileCoupJoue.insereQueue(it.prochain());
        }
        pileCoupAnnule = new SequenceListe<Coup>();

        it = historique.pileCoupAnnule.iterateur();
        while (it.aProchain()){
            pileCoupAnnule.insereQueue(it.prochain());
        }
    }

    /// GETTERS
    public Coup getDernierCoupJoue() {
        return dernierCoupJoue;
    }

    /// METHODES
    public boolean peutAnnuler() {
        return !pileCoupJoue.estVide();
    }

    public boolean peutRefaire() {
        return !pileCoupAnnule.estVide();
    }


    // Fonctions qui geres l'historique du Jeu

    public Coup annulerCoup() {
        // verif si la pile est vide ou pas
        if (peutAnnuler()) { // verification que au moins un coup a été jouer sur le plateau
            Coup coup = pileCoupJoue.extraitTete();
            // insert dans historique annuler un coup non null
            pileCoupAnnule.insereTete(coup);
            dernierCoupJoue = coup;
            return coup;
        }
        return null;
    }

    public Coup refaireCoup(){
        // Verifie qu'il y a eu au moins un coup annuler pour pouvoir rejouer
        // Historique coup annuler ne peux pas contenir des coup null car on en empile jamais
        if(peutRefaire()){
            Coup coup = pileCoupAnnule.extraitTete();
            pileCoupJoue.insereTete(coup);
            return coup;
        }
        return null;
    }

    public void ajouterCoup(Coup coup, Joueur joueur) {
        pileCoupAnnule = new SequenceListe<>();
        pileCoupJoue.insereTete(coup);
        if (coup!=null){
            dernierCoupJoue = coup;
        }
    }

    public void reset() {
        pileCoupJoue = new SequenceListe<>();
        pileCoupAnnule = new SequenceListe<>();
        dernierCoupJoue = null;
    }

    public String toString(){
        String res="";
        res+=dernierCoupJoue+"\n";
        res+=pileCoupJoue+"\n";
        res+=pileCoupAnnule+"\n";
        return res;
    }

}
