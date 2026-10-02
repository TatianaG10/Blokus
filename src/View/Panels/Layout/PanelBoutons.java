package View.Panels.Layout;

import Controller.Configuration;
import Model.Jeu;
import View.ComposantsGraphiques.BoutonResizable;
import View.Windows.*;

import javax.swing.*;
import java.awt.*;

public class PanelBoutons extends JPanel {
    private static final int nombreBoutons = 3;

    private final JButton[] buttons = new JButton[nombreBoutons];
    private final Image[] logos = new Image[nombreBoutons];
    private final Image[] logosHover = new Image[nombreBoutons];

    public PanelBoutons(GameWindow windowP, Jeu game){
        setOpaque(false);
        setLayout(new GridLayout(nombreBoutons,1 , 0, 5));

        // Charge les images originales (non redimensionnées)
        logos[0] = Configuration.MenuLogo;
        logosHover[0] = Configuration.MenuLogoHover;

        logos[1] = Configuration.SettingsLogo;
        logosHover[1] =  Configuration.SettingsLogoHover;

        logos[2] = Configuration.HelpLogo;
        logosHover[2] =Configuration.HelpLogoHover;

        for (int i=0;i<nombreBoutons;i++){
            BoutonResizable bouton = new BoutonResizable(BoutonResizable.SQUARE,logos[i], logosHover[i]);
            bouton.setBorder(null);

            // Action Listeners (New Windows)
            switch (i){
                case 0:
                    bouton.addActionListener(e -> new MenuWindow(windowP, game));
                    break;
                case 1:
                    bouton.addActionListener(e -> new ParametresWindow(windowP, this));
                    break;
                case 2:
                    bouton.addActionListener(e -> new ReglesWindow(windowP, this));
                    break;
            }

            buttons[i] = bouton;
            add(bouton);
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }
}
