package View.Windows;

import View.Panels.Layout.PanelBoutons;

import javax.swing.*;
import java.awt.*;

public class ReglesWindow extends JDialog {
    public ReglesWindow(Window parent, PanelBoutons panelB) {
        super(parent, "Règles", Dialog.ModalityType.APPLICATION_MODAL);

        setLayout(new BorderLayout());

        JTextPane regles = new JTextPane(); //utilsation d'un JtextPane pour retour a la ligne automatique dans scrollPane
        regles.setContentType("text/html"); //definition du type html comme format texte
        regles.setText(
            "<html>\n" +
            "<h1>Règles de Blokus</h1>\n" +

            "<h2>Composition du jeu</h2>\n" +
            "<p>Le jeu se compose des éléments suivants :</p>\n" +
            "<ul>\n" +
            "<li>Un plateau de jeu de 400 cases.</li>\n" +
            "<li>84 pièces (21 pièces dans chacune des 4 couleurs).</li>\n" +
            "<li>Chaque pièce a une forme différente :</li>\n" +
            "<ul>\n" +
            "<li>1 monomino (1 carré)</li>\n" +
            "<li>1 domino (2 carrés)</li>\n" +
            "<li>2 triominos (3 carrés)</li>\n" +
            "<li>5 tétraminos (4 carrés)</li>\n" +
            "<li>12 pentaminos (5 carrés)</li>\n" +
            "</ul>\n" +
            "</ul>\n" +

            "<h2>But du jeu</h2>\n" +
            "<p>Chaque joueur doit placer ses 21 pièces sur le plateau, ou un maximum d’entre elles.</p>\n" +

            "<h2>Contraintes</h2>\n" +
            "<ul>\n" +
            "<li>Chaque joueur commence sur un coin du plateau.</li>\n" +
            "<li>Chaque nouvelle pièce doit toucher une pièce de la même couleur par un coin uniquement (jamais par les côtés).</li>\n" +
            "</ul>\n" +

            "<h2>Fin de la partie</h2>\n" +
            "<p>Lorsqu’un joueur ne peut plus poser de pièce, il passe son tour. Le jeu continue jusqu'à ce qu'aucun joueur ne puisse poser de pièce.</p>\n" +

            "<h2>Décompte des points</h2>\n" +
            "<ul>\n" +
            "<li>Chaque carré non placé donne un point négatif.</li>\n" +
            "<li>Un bonus de 15 points est accordé si un joueur place toutes ses pièces.</li>\n" +
            // "<li>Un bonus de 20 points est accordé si le carré solitaire est placé en dernier.</li>\n" + il a dit juste ca ok
            "</ul>\n" +

            "<h2>Victoire</h2>\n" +
            "<p>Le joueur ayant le plus de points gagne.</p>\n" +
            "</html>"
        );

        regles.setEditable(false); //non modifiable par utilisateur
        regles.setOpaque(false); //transparence pour utiliser le fond de la fenetre (evite cadre blanc)

        JScrollPane scrollPane = new JScrollPane(regles);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //marge
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); //desactivation de la scrollbar horizontal
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setWheelScrollingEnabled(true);
        scrollPane.getVerticalScrollBar().setCursor(new Cursor(Cursor.HAND_CURSOR)); //main comme curseur (indique clickable)
        scrollPane.setPreferredSize(new Dimension(400, 500)); //definition des dimensions du contenu
        add(scrollPane, BorderLayout.CENTER);


        pack(); //definition de la taille de fenetre automatique en fonction de la taille du contenu
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // Positionner à droite de panelB
        Point panelPos = panelB.getLocationOnScreen();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = panelPos.x + panelB.getWidth()+10;
        if (x>screenSize.width - getWidth()){
            x = screenSize.width-getWidth()-10;
        }
        int y = panelPos.y;
        setLocation(x, y);

        setVisible(true);
    }
}

