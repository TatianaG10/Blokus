package Controller.Joueurs;

import Model.*;
import View.ComposantsGraphiques.PieceGraphique;
import View.Panels.DragAndDrop.DragGlassPanel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.Serializable;
import java.util.ArrayList;

public class Humain extends Joueur implements Serializable, MouseListener, MouseMotionListener {
    private static final long serialVersionUID = 1L;

    private transient DragGlassPanel glassPane;
    private transient PieceGraphique selectedPiece=null;
    private transient PieceGraphique lastSelected=null;

    public Humain(Joueur joueur) {
        super(joueur.getPseudo());
        score=joueur.getScore();
        pieces = new  ArrayList<>(joueur.getPieces());
        idJoueur=joueur.getIDJoueur();
    }

    public Humain(String pseudo) {
        super(pseudo);
    }

    public void setGlassPane(DragGlassPanel glassPane) {
        this.glassPane = glassPane;
    }
    public void setSelectedPiece(PieceGraphique selectedPiece) {
        if (this.selectedPiece!=null) {
            lastSelected=selectedPiece;
        }
        this.selectedPiece = selectedPiece;
    }
    public PieceGraphique getSelectedPiece() {
        return selectedPiece;
    }

    public void jouer() {
        // On attend que le joueur clique : on installe un listener
        glassPane.setOnCoupSelectedListener(coup -> {
            if (!game.coupValid(coup, this)) {
                System.out.println("Coup invalide");
                return;
            }

            game.jouerCoup(coup);
            updateGraphics();
        });
    }

    public Jeu getJeu(){
        return super.game;
    }

    /// Mouse Listeners

    @Override
    public void mouseClicked(MouseEvent e) {
        Component component = e.getComponent();
        if (component instanceof PieceGraphique) {
            if (selectedPiece!=null){ //updates the status of old selected piece
                glassPane.retirerPlateau();
                glassPane.clearDragPiece();
                lastSelected=selectedPiece;
                lastSelected.setSelected(false);
            }
            selectedPiece = (PieceGraphique) e.getComponent(); //sets new selected piece
            if (selectedPiece.isPlayed() || selectedPiece.isUnPlayable() || !selectedPiece.isCurrentPlayer()){ //if played or unplayable or not current Players turn then unselect it (unplayable)
                glassPane.clearDragPiece();
                selectedPiece.setSelected(false);
                return;
            }
            //define Piece as Selected
            selectedPiece.setSelected(true);
            glassPane.setDragPiece(selectedPiece); //define piece as dragged piece
            System.out.println("Piece selectionne "+selectedPiece);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        glassPane.updateDragLocation(e.getLocationOnScreen());
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
