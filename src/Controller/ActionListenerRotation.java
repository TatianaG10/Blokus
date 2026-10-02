package Controller;

import Controller.Joueurs.Humain;
import Model.Jeu;
import View.Panels.DragAndDrop.DragGlassPanel;
import View.ComposantsGraphiques.PieceGraphique;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionListenerRotation implements ActionListener {
    public static final int GAUCHE=0;
    public static final int DROITE=1;
    public static final int HORIZONTAL=2;
    public static final int VERTICAL=3;

    private final int mode;
    private final DragGlassPanel dragGlassPanel;
    private Humain player ;

    public ActionListenerRotation(int mode, DragGlassPanel dragGlassPanel) {
        this.mode = mode;
        this.dragGlassPanel = dragGlassPanel;
    }

    public void setPlayer(Humain player){this.player=player;}

    @Override
    public void actionPerformed(ActionEvent e) {
        PieceGraphique piece = null;
        switch (mode) {
            case GAUCHE:
                if (player.getSelectedPiece()!=null){
                    dragGlassPanel.retirerPlateau();
                    dragGlassPanel.getPiece().tourneAntiClockWise();
                    //dragGlassPanel.ajoutPlateau();
                    piece = player.getSelectedPiece();
                    piece.tourneAntiClockWise();
                    piece.repaint();
                }
                System.out.println("Rotation Gauche de la pièce "+piece);
                break;
            case DROITE:
                if (player.getSelectedPiece()!=null){
                    dragGlassPanel.retirerPlateau();
                    dragGlassPanel.getPiece().tourneClockWise();
                    //dragGlassPanel.ajoutPlateau();
                    piece = player.getSelectedPiece();
                    piece.tourneClockWise();
                    piece.repaint();
                }
                System.out.println("Rotation Droite de la pièce "+piece);
                break;
            case HORIZONTAL:
                if (player.getSelectedPiece()!=null){
                    dragGlassPanel.retirerPlateau();
                    dragGlassPanel.getPiece().miroirHorizontal();
                    //dragGlassPanel.ajoutPlateau();
                    piece = player.getSelectedPiece();
                    piece.miroirHorizontal();
                    piece.repaint();
                }
                System.out.println("symetrie horizontale de la pièce "+piece);
                break;
            case VERTICAL:
                if (player.getSelectedPiece()!=null){
                    dragGlassPanel.retirerPlateau();
                    dragGlassPanel.getPiece().miroirVertical();
                    //dragGlassPanel.ajoutPlateau();
                    piece = player.getSelectedPiece();
                    piece.miroirVertical();
                    piece.repaint();
                }
                System.out.println("symetrie verticale de la pièce "+piece);
                break;
            default:
                System.err.println("Unknown Action");
                System.exit(-1);
                break;
        }
        if (dragGlassPanel.isDisplayed()) {
            dragGlassPanel.repaint();
        }
    }
}
