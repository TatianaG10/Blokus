package View.Windows;

import Controller.Configuration;
import Controller.Joueurs.Joueur;
import Model.Jeu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MenuWindow extends JDialog {
    Timer timer;
    public MenuWindow(Window parent, Jeu game){
        super(parent, "Menu", Dialog.ModalityType.APPLICATION_MODAL);
        setLayout(new GridLayout(1,1));

        Dimension dimension = new Dimension(400,300);

        JPanel boutons = new JPanel();
        boutons.setSize(dimension);
        int paddingHori = (int) (boutons.getWidth()*0.2);
        int paddingVert = (int) (boutons.getHeight()*0.1);
        boutons.setBorder(BorderFactory.createEmptyBorder(paddingVert,paddingHori, paddingVert, paddingHori));  // Marges autour de boutons
        boutons.setLayout(new GridLayout(4,1,0,2*paddingVert/3));

        JButton save = new JButton("Sauvegarder Partie");
        save.setIcon(new ImageIcon(Configuration.Save.getScaledInstance(20,20,Image.SCALE_SMOOTH)));
        save.addActionListener(e -> {
            //System.out.println("Save request");
            try {
                game.sauvegarderJeu();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        save.setFocusPainted(false);
        save.setCursor(new Cursor(Cursor.HAND_CURSOR)); //main comme curseur (indique clickable)
        boutons.add(save);

        JButton load = new JButton("Charger Partie");
        load.setIcon(new ImageIcon(Configuration.Load.getScaledInstance(20,20,Image.SCALE_SMOOTH)));
        load.addActionListener(e -> {
            //System.out.println("Load request");
            try {
                Jeu jeuCharge = Jeu.chargerJeu();
                parent.reinitialise();
                parent.dispose();

                GameWindow gameWindow = new GameWindow(true, jeuCharge);
                for (Joueur joueur : jeuCharge.getJoueurs()) {
                    joueur.setGame(jeuCharge);
                    joueur.setJeuGraphique(gameWindow);
                }
                for (Joueur joueur : jeuCharge.getJoueurs()) {
                    joueur.updateGraphics();
                }

                int delay = 2000;
                //Timer pour jouer un coup toute les delay mili-secondes
                timer = new Timer(delay, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!jeuCharge.estPartieFinie()) {
                            jeuCharge.getJoueurCourrant().jouer();
                        } else {
                            timer.stop(); // Arrêter la partie
                            new FinDeJeu(jeuCharge);
                        }
                    }
                });

                timer.setInitialDelay(delay);
                gameWindow.getPanelGestionCoup().setTimer(timer);
                timer.start(); // Démarrer la boucle de jeu
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }

        });
        load.setFocusPainted(false);
        load.setCursor(new Cursor(Cursor.HAND_CURSOR)); //main comme curseur (indique clickable)
        boutons.add(load);

        JButton newGame = new JButton("Nouvelle Partie");
        newGame.addActionListener(e -> {
            parent.reinitialise();
            parent.dispose();
            Configuration.setupConfigurationMenu();
            MenuPrincipal menu = new MenuPrincipal(true);
            menu.afficherCarte("JoueursSelection");
            //System.out.println("New Game request");
        });
        newGame.setFocusPainted(false);
        newGame.setCursor(new Cursor(Cursor.HAND_CURSOR)); //main comme curseur (indique clickable)
        boutons.add(newGame);

        JButton exit = new JButton("Menu");
        exit.setIcon(new ImageIcon(Configuration.Exit.getScaledInstance(20,20,Image.SCALE_SMOOTH)));
        exit.setFocusPainted(false);
        exit.setCursor(new Cursor(Cursor.HAND_CURSOR)); //main comme curseur (indique clickable)
        exit.addActionListener(e -> {
            int choix = JOptionPane.showConfirmDialog(
                MenuWindow.this,
                "Voulez vous vraiment retourner au menu ?",
                "Menu",
                JOptionPane.YES_NO_OPTION);
            if (choix == JOptionPane.YES_OPTION){
                parent.reinitialise();
                new MenuPrincipal(true);
                parent.dispose();
            }
        });
        boutons.add(exit);

        add(boutons,BoxLayout.X_AXIS);

        /* Config Window */
        getContentPane().setPreferredSize(dimension);
        pack();

        // Calculer la position : à droite de panelB
        Point gameWindow = parent.getLocationOnScreen(); // position absolue de panelB sur l'écran
        int x = gameWindow.x + parent.getWidth()/2 - getWidth()/2;
        int y = gameWindow.y + parent.getHeight()/2 - getHeight()/2;
        setLocation(x, y); //centrée sur la fenetre de Jeu

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
