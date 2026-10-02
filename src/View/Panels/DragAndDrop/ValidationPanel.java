package View.Panels.DragAndDrop;

import Controller.ActionListenerValidation;
import Controller.Configuration;
import Controller.Joueurs.Humain;
import Model.Jeu;
import View.Panels.Layout.PanelJoueurs;

import javax.swing.*;
import java.awt.*;

public class ValidationPanel extends JPanel {
    private final DragGlassPanel glassPanel;
    private PanelJoueurs panelJoueurs;
    private final ActionListenerValidation[] actionListener = new ActionListenerValidation[2];
    private Humain player;

    public ValidationPanel(DragGlassPanel glassP){
        super();
        this.glassPanel = glassP;

        Dimension dimension = new Dimension(60,30);

        setLayout(new GridLayout(1,2));
        int width = dimension.width/2;
        int height = dimension.height;

        setPreferredSize(dimension);
        //Set images Icon
        ImageIcon valid = new ImageIcon(Configuration.Valid.getScaledInstance(width,height,Image.SCALE_SMOOTH));
        ImageIcon cancel = new ImageIcon(Configuration.Cancel.getScaledInstance(width,height,Image.SCALE_SMOOTH));

        JButton valider = createJButton(valid, ActionListenerValidation.VALIDER );
        JButton annuler = createJButton(cancel, ActionListenerValidation.ANNULER );

        add(valider);
        add(annuler);
    }

    private JButton createJButton(ImageIcon valid, int mode) {
        JButton valider = new JButton();
        valider.setIcon(valid);
        valider.setFocusPainted(false);
        valider.setPreferredSize(new Dimension(valid.getIconWidth(), valid.getIconHeight()));
        valider.setBorderPainted(false);  // Supprime le contour du bouton
        valider.setContentAreaFilled(false);  // Supprime l'arrière-plan du bouton
        valider.setFocusPainted(false);  // Supprime l'effet de focus
        valider.setCursor(new Cursor(Cursor.HAND_CURSOR)); //main comme curseur (indique clickable)
        actionListener[mode] = new ActionListenerValidation(mode, glassPanel, player);
        valider.addActionListener(actionListener[mode]);
        return valider;
    }

    public void setPlayer(Humain ecouteurPiece){
        player = ecouteurPiece;
        actionListener[ActionListenerValidation.VALIDER].setPlayer(ecouteurPiece);
        actionListener[ActionListenerValidation.ANNULER].setPlayer(ecouteurPiece);
    }
}
