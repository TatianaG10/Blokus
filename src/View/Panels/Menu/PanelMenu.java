package View.Panels.Menu;

import Controller.Configuration;
import View.GridLayoutManager;
import View.Windows.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelMenu extends JPanel {

    public PanelMenu(Window windowP){
        setOpaque(false);
        int marge = 40;
        setLayout(new GridLayout(3,1,0,marge/2));
        setBorder(BorderFactory.createEmptyBorder(marge,marge,marge,marge));

        Font boutonFont = new Font("Dialog", Font.BOLD, 16);

        JButton local = createButton("Local", null, boutonFont);
        local.addActionListener(e -> {
            windowP.afficherCarte("JoueursSelection");
        });

        JButton online = createButton("Online", null, boutonFont);
        online.addActionListener(e -> System.out.println("Online Game"));

        JButton quitter = createButton("Quitter", Configuration.Exit, boutonFont);
        quitter.addActionListener(e -> {
            // 📦 Affichage de la boîte de dialogue
            int choix = JOptionPane.showConfirmDialog(
                    windowP,
                    "Voulez-vous vraiment quitter ?",
                    "Quitter",
                    JOptionPane.YES_NO_OPTION);

            if (choix == JOptionPane.YES_OPTION){
                System.exit(0);
            }
        });

        /// Mise En Page des Boutons sur le Panel Boutons


        add(local);
        add(online);
        add(quitter);
    }

    private JButton createButton(String text, Image icon, Font boutonFont) {
        JButton bouton = new JButton(text);
        bouton.setFont(boutonFont);
        if (icon != null) bouton.setIcon(new ImageIcon(icon.getScaledInstance(22,22,Image.SCALE_SMOOTH)));
        bouton.setBackground(Configuration.BACKGROUND_JOUEUR_SELECTION);
        bouton.setFocusPainted(false);
        bouton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bouton.setForeground(Color.BLACK);
        bouton.setBorder(BorderFactory.createLineBorder(Configuration.YELLOW_BOLD,2));

        bouton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                bouton.setBorder(BorderFactory.createLineBorder(Configuration.YELLOW_BOLD, 4));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                bouton.setBorder(BorderFactory.createLineBorder(Configuration.YELLOW_BOLD, 2));
            }
        });
        return bouton;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
