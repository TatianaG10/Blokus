package Model;

import Structures.Pair;
import java.util.ArrayList;
import java.util.List;

/**
 * Les pièces ont une hauteur ou largeur par convention.
 * Ce fichier à juste vocation d'hardcoder les pièces
 */

public final class PiecesBlokus {
        public static final int NOMBREDEPIECES = 21;

        public static final List<Pair<int[][], Integer>> PIECES = new ArrayList<>() {{
                /*
                 * 0 : Monomino
                 */
                add(new Pair<>(new int[][]{
                        {3, 2, 3, 0, 0, 0, 0},
                        {2, 1, 2, 0, 0, 0, 0},
                        {3, 2, 3, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0}
                }, 1));

                /*
                 * 1 : Domino
                 */
                add(new Pair<>(new int[][]{
                        {3, 2, 2, 3, 0, 0, 0},
                        {2, 1, 1, 2, 0, 0, 0},
                        {3, 2, 2, 3, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0}
                }, 2));

                /*
                 * 2 : Triomino
                 */
                add(new Pair<>(new int[][]{
                        {3, 2, 2, 2, 3, 0, 0},
                        {2, 1, 1, 1, 2, 0, 0},
                        {3, 2, 2, 2, 3, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0}
                }, 3));

                /*
                 * 3 : Petit L de 3
                 */
                add(new Pair<>(new int[][]{
                        {3, 2, 3, 0, 0, 0, 0},
                        {2, 1, 2, 3, 0, 0, 0},
                        {2, 1, 1, 2, 0, 0, 0},
                        {3, 2, 2, 3, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0}
                }, 3));

                /*
                 * 4 : Carré de 4
                 */
                add(new Pair<>(new int[][]{
                        {3, 2, 2, 3, 0, 0, 0},
                        {2, 1, 1, 2, 0, 0, 0},
                        {2, 1, 1, 2, 0, 0, 0},
                        {3, 2, 2, 3, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0}
                }, 4));

                /*
                 * 5 : Ligne de 4
                 */
                add(new Pair<>(new int[][]{
                        {3, 2, 2, 2, 2, 3, 0},
                        {2, 1, 1, 1, 1, 2, 0},
                        {3, 2, 2, 2, 2, 3, 0},
                        {0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0}
                }, 4));

                /*
                 * 6 : T de 4
                 */
                add(new Pair<>(new int[][]{
                        {3, 2, 2, 2, 3, 0, 0},
                        {2, 1, 1, 1, 2, 0, 0},
                        {3, 2, 1, 2, 3, 0, 0},
                        {0, 3, 2, 3, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0}
                }, 4));

                /*
                 * 7 : S de 4
                 */
                add(new Pair<>(new int[][]{
                        {3, 2, 2, 3, 0, 0, 0},
                        {2, 1, 1, 2, 3, 0, 0},
                        {3, 2, 1, 1, 2, 0, 0},
                        {0, 3, 2, 2, 3, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0}
                }, 4));

                /*
                 * 8 : L de 4
                 */
                add(new Pair<>(new int[][]{
                        {3, 2, 3, 0, 0, 0, 0},
                        {2, 1, 2, 2, 3, 0, 0},
                        {2, 1, 1, 1, 2, 0, 0},
                        {3, 2, 2, 2, 3, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0}
                }, 4));

                /*
                 * 9 : Ligne de 5
                 */
                add(new Pair<>(new int[][]{
                        {3, 2, 2, 2, 2, 2, 3},
                        {2, 1, 1, 1, 1, 1, 2},
                        {3, 2, 2, 2, 2, 2, 3},
                        {0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0}
                }, 5));

                /*
                 * 10 : U de 5
                 */
                add(new Pair<>(new int[][]{
                        {3, 2, 3, 2, 3, 0, 0},
                        {2, 1, 2, 1, 2, 0, 0},
                        {2, 1, 1, 1, 2, 0, 0},
                        {3, 2, 2, 2, 3, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0}
                }, 5));

                /*
                 * 11 : Carré de 4 + 1
                 */
                add(new Pair<>(new int[][]{
                        {3, 2, 2, 2, 3, 0, 0},
                        {2, 1, 1, 1, 2, 0, 0},
                        {2, 1, 1, 2, 3, 0, 0},
                        {3, 2, 2, 3, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0}
                }, 5));

                /*
                 * 12 : Ligne de 4 + 1
                 */
                add(new Pair<>(new int[][]{
                        {0, 3, 2, 3, 0, 0, 0},
                        {3, 2, 1, 2, 2, 3, 0},
                        {2, 1, 1, 1, 1, 2, 0},
                        {3, 2, 2, 2, 2, 3, 0},
                        {0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0}
                }, 5));

                /*
                 * 13 : Ligne de 3 + 2
                 */
                add(new Pair<>(new int[][]{
                        {3, 2, 2, 3, 0, 0, 0},
                        {2, 1, 1, 2, 2, 3, 0},
                        {3, 2, 1, 1, 1, 2, 0},
                        {0, 3, 2, 2, 2, 3, 0},
                        {0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0}
                }, 5));

                /*
                 * 14 : L de 5
                 */
                add(new Pair<>(new int[][]{
                        {3, 2, 3, 0, 0, 0, 0},
                        {2, 1, 2, 2, 2, 3, 0},
                        {2, 1, 1, 1, 1, 2, 0},
                        {3, 2, 2, 2, 2, 3, 0},
                        {0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0}
                }, 5));

                /*
                 * 15 : Croix de 5
                 */
                add(new Pair<>(new int[][]{
                        {0, 3, 2, 3, 0, 0, 0},
                        {3, 2, 1, 2, 3, 0, 0},
                        {2, 1, 1, 1, 2, 0, 0},
                        {3, 2, 1, 2, 3, 0, 0},
                        {0, 3, 2, 3, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0}
                }, 5));

                /*
                 * 16 : Z de 5
                 */
                add(new Pair<>(new int[][]{
                        {3, 2, 2, 3, 0, 0, 0},
                        {2, 1, 1, 2, 0, 0, 0},
                        {3, 2, 1, 2, 3, 0, 0},
                        {0, 2, 1, 1, 2, 0, 0},
                        {0, 3, 2, 2, 3, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0}
                }, 5));

                /*
                 * 17 : Escalier de 5
                 */
                add(new Pair<>(new int[][]{
                        {3, 2, 2, 3, 0, 0, 0},
                        {2, 1, 1, 2, 3, 0, 0},
                        {3, 2, 1, 1, 2, 0, 0},
                        {0, 3, 2, 1, 2, 0, 0},
                        {0, 0, 3, 2, 3, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0}
                }, 5));

                /*
                 * 18 : Grand L de 5
                 */
                add(new Pair<>(new int[][]{
                        {3, 2, 2, 2, 3, 0, 0},
                        {2, 1, 1, 1, 2, 0, 0},
                        {3, 2, 2, 1, 2, 0, 0},
                        {0, 0, 2, 1, 2, 0, 0},
                        {0, 0, 3, 2, 3, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0}
                }, 5));

                /*
                 * 19 : T de 5
                 */
                add(new Pair<>(new int[][]{
                        {3, 2, 2, 2, 3, 0, 0},
                        {2, 1, 1, 1, 2, 0, 0},
                        {3, 2, 1, 2, 3, 0, 0},
                        {0, 2, 1, 2, 0, 0, 0},
                        {0, 3, 2, 3, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0}
                }, 5));

                /*
                 * 20 : "F" de 5
                 */
                add(new Pair<>(new int[][]{
                        {3, 2, 2, 3, 0, 0, 0},
                        {2, 1, 1, 2, 3, 0, 0},
                        {3, 2, 1, 1, 2, 0, 0},
                        {0, 2, 1, 2, 3, 0, 0},
                        {0, 3, 2, 3, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0}
                }, 5));
        }};

        private static final List<List<Piece>> rotationsPossibles = new ArrayList<>();
        static {
                List<Piece> listeDesRotations;
                Piece p;
                for (int i = 0; i < NOMBREDEPIECES; i++) {
                        listeDesRotations = new ArrayList<>();
                        p = new Piece(i);
                        listeDesRotations.add(p);

                        // 90° rotation droite
                        p = new Piece(i);
                        p.tourneClockWise();
                        if (!listeDesRotations.contains(p))
                                listeDesRotations.add(p);

                        // 180° rotation à droite
                        p = new Piece(i);
                        p.tourneClockWise();
                        p.tourneClockWise();
                        if (!listeDesRotations.contains(p))
                                listeDesRotations.add(p);

                        // 90° Rotation à gauche
                        p = new Piece(i);
                        p.tourneAntiClockWise();
                        if (!listeDesRotations.contains(p))
                                listeDesRotations.add(p);

                        // Flip horizontal
                        p = new Piece(i);
                        p.miroirHorizontal();
                        if (!listeDesRotations.contains(p))
                                listeDesRotations.add(p);

                        // Flip vertical
                        p = new Piece(i);
                        p.miroirVertical();
                        if (!listeDesRotations.contains(p))
                                listeDesRotations.add(p);

                        // Flip vertical 90° rotation droite
                        p = new Piece(i);
                        p.miroirVertical();
                        p.tourneClockWise();
                        if (!listeDesRotations.contains(p))
                                listeDesRotations.add(p);

                        // Flip vertical + 90° rotation gauche
                        p = new Piece(i);
                        p.miroirVertical();
                        p.tourneAntiClockWise();
                        if (!listeDesRotations.contains(p))
                                listeDesRotations.add(p);

                        // Ajoute la liste des rotations
                        rotationsPossibles.add(listeDesRotations);
                }
        }

        /**
         * getRotationsPossibles : renvoie une liste de pièces représentant toutes les rotations possibles pour une pièce donnée.
         * @param id : l'id d'une pièce.
         * @return un ArrayList de Pieces representant les rotations possibles.
         **/
        public static ArrayList<Piece> getRotationsPossibles(int id) {
                return new ArrayList<>(rotationsPossibles.get(id));
        }
}