package View.Windows;

import Controller.Configuration;
import View.Panels.Layout.PanelBoutons;

import javax.swing.*;
import java.awt.*;

public class ParametresWindow extends JDialog {
    private static final ImageIcon sfxNone = new ImageIcon(Configuration.SfxNone.getScaledInstance(30,30,Image.SCALE_SMOOTH));
    private static final ImageIcon sfxLow = new ImageIcon(Configuration.SfxLow.getScaledInstance(30,30,Image.SCALE_SMOOTH));
    private static final ImageIcon sfxMedium = new ImageIcon(Configuration.SfxMedium.getScaledInstance(30,30,Image.SCALE_SMOOTH));
    private static final ImageIcon sfxHigh = new ImageIcon(Configuration.SfxHigh.getScaledInstance(30,30,Image.SCALE_SMOOTH));

    public ParametresWindow(Window parent, PanelBoutons panelB) {
        super(parent, "Paramètres", Dialog.ModalityType.APPLICATION_MODAL);

        Dimension dimension = new Dimension(300, 200);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setPreferredSize(dimension);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // marges autour des éléments
        gbc.anchor = GridBagConstraints.WEST; // aligner à droite pour les labels

        // ---------- Ligne Musique ----------
        JLabel labelMusique = new JLabel("Musique : ");
        labelMusique.setIcon(new ImageIcon(Configuration.Musique.getScaledInstance(30,30,Image.SCALE_SMOOTH)));
        gbc.gridx = 0; // colonne 0
        gbc.gridy = 0; // ligne 0
        mainPanel.add(labelMusique, gbc);

        JSlider musique = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
        musique.setPreferredSize(new Dimension(200, 30));
        musique.setMajorTickSpacing(50);
        musique.setMinorTickSpacing(5);
        musique.setSnapToTicks(true);
        musique.setPaintLabels(true);
        musique.setCursor(new Cursor(Cursor.HAND_CURSOR));
        musique.addChangeListener(e -> {
            int valeur = musique.getValue();
            if (valeur%5!=0) return;
            System.out.println("Volume musique : " + valeur);
            // ici mettre code pour changer le volume
        });


        gbc.gridx = 0;
        gbc.gridy = 1; //ligne 1
        gbc.anchor = GridBagConstraints.WEST; // aligner le slider à gauche
        mainPanel.add(musique, gbc);

        // ---------- Ligne SFX ----------
        JLabel labelSfx = new JLabel("SFX : ");
        labelSfx.setIcon(sfxNone);
        gbc.gridx = 0;
        gbc.gridy = 2; //ligne 2
        gbc.anchor = GridBagConstraints.WEST; // remettre à droite pour le label
        mainPanel.add(labelSfx, gbc);

        JSlider sfx = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
        sfx.setPreferredSize(new Dimension(200, 30));
        sfx.setMajorTickSpacing(50);
        sfx.setMinorTickSpacing(5);
        sfx.setSnapToTicks(true);
        sfx.setPaintLabels(true);
        sfx.setCursor(new Cursor(Cursor.HAND_CURSOR));
        sfx.addChangeListener(e -> {
            int valeur = sfx.getValue();
            if (valeur%5!=0) return;
            if (valeur==0){ labelSfx.setIcon(sfxNone);
            }else if (valeur>0 && valeur <= 30){
                labelSfx.setIcon(sfxLow);
            }else if (valeur>30 && valeur <= 70){
                labelSfx.setIcon(sfxMedium);
            }else{
                labelSfx.setIcon(sfxHigh);
            }
            System.out.println("Volume sfx : " + valeur);
            // ici mettre code pour changer le volume
        });


        gbc.gridx = 0;
        gbc.gridy = 3; //ligne 3
        gbc.anchor = GridBagConstraints.WEST; // à gauche pour le slider
        mainPanel.add(sfx, gbc);

        add(mainPanel);

        /* Config Window */
        getContentPane().setPreferredSize(dimension);
        pack();

        // Position par rapport au panelB
        Point panelPos = panelB.getLocationOnScreen();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = panelPos.x + panelB.getWidth()+10;
        if (x>screenSize.width - getWidth()){
            x = screenSize.width-getWidth()-10;
        }
        int y = panelPos.y;
        setLocation(x, y);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}


