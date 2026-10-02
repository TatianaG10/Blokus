package Controller;

import Controller.Joueurs.Humain;
import Controller.Joueurs.Joueur;
import Model.Jeu;
import View.Panels.DragAndDrop.DragGlassPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionListenerValidation implements ActionListener {
    public static final int VALIDER=0;
    public static final int ANNULER=1;

    private final int mode;
    private final DragGlassPanel glassPanel;
    private Humain player;

    public ActionListenerValidation(int mode, DragGlassPanel glassPanel, Humain player) {
        this.mode = mode;
        this.glassPanel = glassPanel;
        this.player = player;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (mode){
            case VALIDER:
                if (glassPanel.getPiece().isPlayed()){
                    glassPanel.onClick();
                    glassPanel.clearDragPiece();
                    player.setSelectedPiece(null);
                }
                break;
            case ANNULER:
                glassPanel.retirerPlateau();
                glassPanel.clearDragPiece();
                player.getSelectedPiece().setPlayed(false);
                player.setSelectedPiece(null);
                break;
            default:
                System.err.println("Error : Unknown Action");
                System.exit(-1);
                break;
        }
    }

    public void setPlayer(Humain player) {
        this.player=player;
    }
}
