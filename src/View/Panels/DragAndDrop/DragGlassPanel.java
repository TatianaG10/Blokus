package View.Panels.DragAndDrop;

import Controller.Joueurs.Humain;
import Model.Coup;
import Structures.Position;
import View.Panels.Layout.PanelPlateau;
import View.ComposantsGraphiques.PieceGraphique;
import View.Windows.GameWindow;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class DragGlassPanel extends JComponent {
    private Consumer<Coup> listener;
    private Position posPiece;
    private Coup coupPossible;

    private Point location = null;
    private Position lastLocation=null;
    private PieceGraphique piece = null;
    private PieceGraphique pieceOriginale = null;

    private Point screenLocation;

    private ValidationPanel validationPanel;
    private PanelPlateau plateau;
    private Humain player;

    public DragGlassPanel(GameWindow windowP) {
        setOpaque(false);
        windowP.setGlassPane(this);
        setSize(windowP.getSize());
        setLayout(null); // Permet de positionner manuellement les composants

        plateau = windowP.getPanelPlateau();

        validationPanel = new ValidationPanel(this);
        validationPanel.setSize(validationPanel.getPreferredSize()); // Nécessaire pour le positionnement manuel
        validationPanel.setVisible(false);
        add(validationPanel);

        setVisible(false);
    }

    public void setOnCoupSelectedListener(Consumer<Coup> listener) {
        this.listener = listener;
    }

    // Appelé lors du clic utilisateur :
    public void onClick() {
        if (listener != null) {
            listener.accept(coupPossible); // notifie le joueur
            listener = null;       // désactive le listener après usage (optionnel)
        }
    }


    public void setDragPiece(PieceGraphique piece) {
        pieceOriginale = piece;
        this.piece = new PieceGraphique(piece);
        this.piece.setMoving(true);
    }

    public void ajoutPlateau() {
        if (isDisplayed()) {
            plateau.mettrePiece(pieceOriginale, coupPossible.getReferencePlateau().ligne,  coupPossible.getReferencePlateau().colonne);
            piece.setMoving(false);
            piece.setPlayed(true);
        }
    }

    public void retirerPlateau(){
        if (isDisplayed() && piece.isPlayed()) {
            plateau.retirerPiece(pieceOriginale,lastLocation.getLigne(),  lastLocation.getColonne());
            piece.setMoving(true);
            piece.setPlayed(false);
            pieceOriginale.setPlayed(false);
        }
    }

    public PieceGraphique getPiece() {
        return piece;
    }
    public boolean isDisplayed(){
        return piece!=null && location!=null;
    }

    public void updateDragLocation(Point location) {
        if (piece==null) {
            return;
        }
        setVisible(true);
        screenLocation = getLocationOnScreen();
        if (this.location!=null){
            lastLocation = new Position(coupPossible.getReferencePlateau().ligne, coupPossible.getReferencePlateau().colonne);
        }
        this.location = location;

        Point positionPlateau = plateau.getLocationOnScreen();
        posPiece = new Position((this.location.y - positionPlateau.y)/PanelPlateau.caseSize, (this.location.x - positionPlateau.x)/PanelPlateau.caseSize);
        coupPossible = new Coup(piece.getPiece(),new Position(1,1), posPiece);
        if (player.getJeu().coupValid(coupPossible, player)){
            ajoutPlateau();
        }else{
            if (!piece.isMoving()) {
                retirerPlateau();
            }
        }
        pieceOriginale.setPlayed(true); //updates PiecePanel

        if (validationPanel != null && isDisplayed()) {
            int offsetX = PanelPlateau.caseSize * piece.getLargeur();
            int offsetY = - validationPanel.getHeight() - PanelPlateau.caseSize / 2 ;
            validationPanel.setLocation(this.location.x - screenLocation.x + offsetX, this.location.y - screenLocation.y + offsetY);
            validationPanel.setVisible(true);
        }
        repaint();
    }

    public void clearDragPiece() {
        if (pieceOriginale!=null) {
            pieceOriginale.setMoving(false);
            pieceOriginale.setSelected(false);
        }
        piece = null;
        location = null;
        if (validationPanel != null) validationPanel.setVisible(false);
        setVisible(false);
        repaint();
    }

    public void setPlayer(Humain player) {
        this.player = player;
        validationPanel.setPlayer(player);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isDisplayed() && piece.isMoving()) { //can't be posed
            Graphics2D g2d = (Graphics2D) g;
            Point panelLocation = getLocationOnScreen();
            Point screenLocation = new Point(location.x - panelLocation.x, location.y - panelLocation.y);
            piece.paint(g2d, screenLocation.x, screenLocation.y, PanelPlateau.caseSize, Color.RED, piece.getColorBordure());
        }
    }
}
