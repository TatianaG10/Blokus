package View.Panels.Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/* Simple class pour le choix IA ou Joueur */

public class BoutonTypeJoueur extends JButton {
    private Boolean choix;    //ça pourrait servir plus tard

    public BoutonTypeJoueur(boolean bool){
        super("Joueur / IA");
        choix = bool;
        setFocusPainted(false);
        setFocusable(false);
        setBorderPainted(true);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        super.setPreferredSize(new Dimension(100, 30));

        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (getText().equals("Joueur / IA")  || getText().equals("Joueur")){
                    setText("IA");
                    choix = false;
                } else{
                    setText("Joueur");
                    choix = true;
                }
            }
        });
    }
    public boolean getChoix(){
        return choix;
    }
}
