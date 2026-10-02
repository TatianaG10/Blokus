package View.Windows;

import Controller.Configuration;
import View.GridLayoutManager;
import View.Panels.*;
import View.Panels.Menu.Panel2Joueurs;
import View.Panels.Menu.PanelMenu;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipal extends Window{
    private final boolean portrait;
    private final JPanel home;
    private final PanelMenu menu;
    private final Panel2Joueurs joueursSelection;
    private final CardLayout cardLayout;

    public MenuPrincipal(boolean port) {
        super();
        this.portrait = port;

        // Initialisation
        cardLayout = new CardLayout();
        home = new JPanel(cardLayout);

        menu = new PanelMenu(this);
        joueursSelection = new Panel2Joueurs(this);

        // Organisation et affichage
        organiserComposants();
        afficherFenetre();
    }

    private void organiserComposants() {
        // Nettoyer l'ancien contenu
        getContentPane().removeAll();

        // Créer le panneau d’arrière-plan avec fond personnalisé
        BackgroundPanel background = new BackgroundPanel(Configuration.BackGroundMenuFullScreen);
        background.setLayout(new GridBagLayout());
        setContentPane(background);

        home.setPreferredSize(new Dimension(335,295)); //adapt to size of window
        home.setMinimumSize(new Dimension(335,295));
        home.setOpaque(false);

        // Ajouter les vues au panneau à cartes
        home.add(menu, "Menu");
        home.add(joueursSelection, "JoueursSelection");

        // Afficher le menu par défaut
        cardLayout.show(home, "Menu");

        // Placement du panneau principal
        GridLayoutManager gbcMain = new GridLayoutManager();
        gbcMain.updateGBC(0, 0, 1.0, 1.0, new Insets(116, 8, 0, 0), GridBagConstraints.CENTER, GridBagConstraints.NONE);
        add(home, gbcMain);
    }

    private void afficherFenetre() {
        setPreferredSize(Configuration.dimensionMenuWindow);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null); // centre la fenêtre
        setVisible(true);
    }

    public void afficherCarte(String nomCarte) {
        cardLayout.show(home, nomCarte);
    }

}
