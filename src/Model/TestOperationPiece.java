package Model;

import java.util.Scanner;

/**
 * Ce fichier de test permet de tester les transformations qui peuvent être appliquées à une pièce.
 */
public class TestOperationPiece {
    public static void main(String[] args) {
        int option;
        boolean wannaContinue = true;
        Scanner scanner = new Scanner(System.in);

        /* Test comparaison des pièces */
        Piece p1 = new Piece(0);
        p1.tourneClockWise();
        String sontEgaux = p1.equals(new Piece(0)) ? "LA MÊME" : "DIFFERENTES";
        System.out.println("Les pièces sont " + sontEgaux);


        while (wannaContinue) {
            System.out.println(
                    "0. Arrêter le test\n" +
                    "1. Tester manuellement\n" +
                    "2. Afficher toutes les différentes rotations de chaque pièce du Blokus\n"
            );
            option = scanner.nextInt();
            switch (option) {
                case 0:
                    wannaContinue = false;
                    break;
                case 1:
                    boolean testeManuel = true;
                    System.out.println("1/2. ID pièce ?");
                    int id = scanner.nextInt();
                    Piece p = new Piece(id);
                    while (testeManuel) {
                        System.out.println(p);
                        System.out.println("2/2. Type de rotation: [1]:90°Droite,  [2]:90°Gauche,  [3]:Symétrie Horizontale,  [4]:Symétrie Verticale ?");
                        int typeRotation = scanner.nextInt();
                        switch (typeRotation) {
                            case 1:
                                p.tourneClockWise();
                                break;
                            case 2:
                                p.tourneAntiClockWise();
                                break;
                            case 3:
                                p.miroirHorizontal();
                                break;
                            case 4:
                                p.miroirVertical();
                                break;
                            default:
                                System.err.println("Option invalide");
                        }
                        System.out.println(p);
                        System.out.println("Continuer à tourner la meme pièce ? [1]:oui,  [2]:non ?");
                        option = scanner.nextInt();
                        testeManuel = (1 == option);
                    }
                    break;
                case 2:
                    for (int i=0; i<21; i++){
                        p = new Piece(i);
                        System.out.println("La pièce d'ID "+i+" à "+p.getListeRotationsPossibles().size()+" rotations différentes "+ ":");
                        for (Piece rotation : p.getListeRotationsPossibles()){
                            p.tourneClockWise();
                            System.out.println(rotation);
                        }
                    }

                    break;

                default:
                    System.err.println("Argument invalide");
                    break;
            }
        }
    }
}