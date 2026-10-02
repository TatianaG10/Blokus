package Model;

import Controller.Joueurs.IA.IA_intermediare;
import Controller.Joueurs.IA.IA_alea;
import Controller.Joueurs.Joueur;

public class TestAleatoire {
    public static void main(String[] args){
        Joueur joueur1 = new IA_alea("Robin");
        Joueur joueur2 = new IA_alea("Kalv");
        Joueur joueur3 = new IA_alea("Tats");
        Joueur joueur4 = new IA_intermediare("Big Kev");
        Jeu jeu = new Jeu(joueur1, joueur2, joueur3, joueur4, 20);
        joueur1.setGame(jeu);
        joueur2.setGame(jeu);
        joueur3.setGame(jeu);
        joueur4.setGame(jeu);

        int nombreDeCoupsJouees=0;
        int nbOperationMax=500;
        while (!jeu.estPartieFinie() && nombreDeCoupsJouees<nbOperationMax) {
            System.out.println(nombreDeCoupsJouees);
            System.out.println(jeu);
            jeu.getJoueurCourrant().jouer();
            nombreDeCoupsJouees++;
        }
        System.out.println("Nombre de Coups Jouees: "+nombreDeCoupsJouees);
        System.out.println(jeu);
        System.out.println("FIN DE LA PARTIE");
        System.out.println(jeu.podium());
    }
}
