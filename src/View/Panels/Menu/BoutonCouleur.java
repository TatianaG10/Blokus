//public class BoutonCouleur extends JButton {
//
//    public BoutonCouleur(int j, List<Color> couleursDispo){
//        super("Couleur");
//        setFocusable(false);
//        //setBorderPainted(false);
//
//
//        addActionListener(e ->{
//            new ChoixCouleur(j, couleursDispo, this);
//        });
//    }
//
//    public void update(Color c){
//        setBackground(c);
//        repaint();
//    }
//
//}

package View.Panels.Menu;

import Controller.Configuration;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BoutonCouleur extends JButton {
    private int playerId;
    private ColorManager manager;

    public BoutonCouleur(int playerId, ColorManager manager) {
        this.playerId = playerId;
        this.manager = manager;

        setPreferredSize(new Dimension(100, 30)); // Wider to fit text + color
        setText("Couleur");
        setFocusPainted(false);
        setFocusable(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setBorderPainted(true);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        addActionListener(e -> {
            updateColor();
            // Optional: notify UI to repaint in case you need others to update
            SwingUtilities.getWindowAncestor(this).repaint();
        });
    }

    public void updateColor() {
        List<Color> available = manager.getAvailableColors(playerId);
        if (available.isEmpty()) {
            return;
        }
        Color current = manager.getColor(playerId);
        int index = available.indexOf(current);
        int nextIndex = (index + 1) % available.size();

        Color nextColor = available.get(nextIndex);
        manager.selectColor(playerId, nextColor);
        setBackground(nextColor);
    }
}
