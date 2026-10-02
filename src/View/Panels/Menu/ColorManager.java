package View.Panels.Menu;

import java.awt.*;
import java.util.*;
import java.util.List;

import Controller.Configuration;

public class ColorManager {
    private final ArrayList<Color> allColors;
    private ArrayList<Color> colorsLeft;
    private final Map<Integer, Color> selectedColors;
    private final Map<Color,Color> mapCouleurBordure;
    private final Map<Color,Color> mapCouleurBackground;

    public ColorManager() {
        allColors = new ArrayList<Color>();
        selectedColors = new HashMap<Integer, Color>();

        allColors.add(Configuration.BLUE);
        allColors.add(Configuration.RED);
        allColors.add(Configuration.GREEN);
        allColors.add(Configuration.YELLOW);

        colorsLeft = new ArrayList<>(allColors);

        mapCouleurBordure = new HashMap<>();    //la hachmap sert uniquement à associer une couleur à sa version BOLD
        mapCouleurBordure.put(Configuration.RED, Configuration.RED_BOLD);
        mapCouleurBordure.put(Configuration.BLUE,Configuration.BLUE_BOLD);
        mapCouleurBordure.put(Configuration.GREEN,Configuration.GREEN_BOLD);
        mapCouleurBordure.put(Configuration.YELLOW,Configuration.YELLOW_BOLD);

        mapCouleurBackground = new HashMap<>();    //la hachmap sert uniquement à associer une couleur à sa version BOLD
        mapCouleurBackground.put(Configuration.RED, Configuration.RED_LIGHT);
        mapCouleurBackground.put(Configuration.BLUE,Configuration.BLUE_LIGHT);
        mapCouleurBackground.put(Configuration.GREEN,Configuration.GREEN_LIGHT);
        mapCouleurBackground.put(Configuration.YELLOW,Configuration.YELLOW_LIGHT);

    }

    public ArrayList<Color> getAvailableColors(int playerId) {
        colorsLeft = new ArrayList<>(allColors);
        for (Map.Entry<Integer, Color> entry : selectedColors.entrySet()) {
            if (entry.getKey() != playerId) {
                colorsLeft.remove(entry.getValue());
            }
        }
        colorsLeft.add(null);
        return colorsLeft;
    }

    public void selectColor(int playerId, Color color) {
        selectedColors.put(playerId, color);
        colorsLeft.remove(color);
    }

    public Color getColor(int playerId) {
        return selectedColors.get(playerId);
    }

    public void leftOver(){
        Iterator<Color> it = colorsLeft.iterator();
        for (int i=0; i < 4 && it.hasNext(); i++){
            if (!selectedColors.containsKey(i) || selectedColors.get(i) == null){
                Color couleur = it.next();
                selectedColors.put(i,couleur);
                it.remove();
            }
        }
    }

    public void defineColors(){
        leftOver();
        for (int i = 0; i < selectedColors.size(); i++){
            Configuration.ordreJoueurs[i] = selectedColors.get(i);
            Configuration.bordureJoueurs[i] = mapCouleurBordure.get(selectedColors.get(i));
            Configuration.backgroundPiecesPanel[i] = mapCouleurBackground.get(selectedColors.get(i));
        }
    }
}
