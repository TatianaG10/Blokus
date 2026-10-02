package Model;

import Controller.Joueurs.IA.IA_alea;
import Controller.Joueurs.Joueur;

import java.util.Random;

public class TestAleatoireHistorique {
    public static void main(String[] args){
        Joueur joueur1 = new IA_alea("Robin");
        Joueur joueur2 = new IA_alea("Kalv");
        Joueur joueur3 = new IA_alea("Tats");
        Joueur joueur4 = new IA_alea("Big Kev");
        Jeu jeu = new Jeu(joueur1, joueur2, joueur3, joueur4, 20);
        joueur1.setGame(jeu);
        joueur2.setGame(jeu);
        joueur3.setGame(jeu);
        joueur4.setGame(jeu);

        int nombreDeCoupsJouees=0;
        int nbOperationMax=500;
        Random random = new Random();
        while (!jeu.estPartieFinie() && nombreDeCoupsJouees<nbOperationMax) {
            System.out.println(nombreDeCoupsJouees);
            System.out.println(jeu);

            if (random.nextInt(3)==1){
                if (jeu.annulerCoup()){
                    System.out.println("Coup Annule");
                }else{
                    System.out.println("Coup Annul<UNK>");
                }
            }else{
                if (random.nextInt(3)==1){
                    if (jeu.rejouerCoup()){
                        System.out.println("Coup Rejouer");
                    }else{
                        System.out.println("Coup Rejouer<UNK>");
                    }

                }
            }
            jeu.getJoueurCourrant().jouer();
            nombreDeCoupsJouees++;
        }
        System.out.println("Nombre de Coups Jouees: "+nombreDeCoupsJouees);
        System.out.println(jeu);
        System.out.println("FIN DE LA PARTIE");
        System.out.println(jeu.podium());
    }
}
