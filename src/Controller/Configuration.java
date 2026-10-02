package Controller;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class Configuration {
    public static Color[] ordreJoueurs = defineColors();
    public static Color[] bordureJoueurs = defineBorderColors();
    public static Color[] backgroundPiecesPanel = definePanelColors();
    public static int taillePlateau;
    public static int nbJoueurs;
    public static String saveFileName;

    /// Colors
    public static final Color BACKGROUND_JOUEUR_SELECTION = new Color(245, 197, 101);
    public static final Color GRAY = new Color(208,208,208);
    public static final Color WHITE = new Color(243, 244, 246);
    public static final Color GRAY_DARK = new Color(129, 129, 129);


    public static final Color RED = new Color(240,85,85);
    public static final Color RED_BOLD = new Color(153,27,27);
    public static final Color RED_LIGHT = new Color(254, 242, 242);

    public static final Color BLUE = new Color(37,173,234);
    public static final Color BLUE_BOLD = new Color(7,89,133);
    public static final Color BLUE_LIGHT = new Color(240, 249, 255);

    public static final Color GREEN = new Color(55,202,109);
    public static final Color GREEN_BOLD = new Color(22,101,52);
    public static final Color GREEN_LIGHT = new Color(240, 253, 244);

    public static final Color YELLOW = new Color(235,185,31);
    public static final Color YELLOW_BOLD = new Color(173,103,25);
    public static final Color YELLOW_LIGHT = new Color(254, 252, 232);

    /// Dimensions
    public static Dimension dimensionGameWindow = new Dimension(700, 750);
    public static Dimension dimensionMenuWindow = new Dimension(600,750);

    /// All images and logos references
    public static Image BlokusLogo;

    //MainButtons
    public static Image MenuLogo;
    public static Image MenuLogoHover;
    public static Image SettingsLogo;
    public static Image SettingsLogoHover;
    public static Image HelpLogo;
    public static Image HelpLogoHover;

    //Plateau
    public static Image Board;
    public static Image RedCell;
    public static Image BlueCell;
    public static Image GreenCell;
    public static Image YellowCell;
    public static Image BoardCellGris;
    public static Image BoardCellWhite;
    public static Image GrayCell;
    public static Image Valid;
    public static Image Cancel;

    //GestionJeu
    public static Image AnnuleLogo;
    public static Image AnnuleLogoHover;
    public static Image RejouerLogo;
    public static Image RejouerLogoHover;

    //Rotations
    public static Image RotationDroite;
    public static Image RotationGauche;
    public static Image SymetrieHorizontale;
    public static Image SymetrieVerticale;
    public static Image RotationDroiteHover;
    public static Image RotationGaucheHover;
    public static Image SymetrieHorizontaleHover;
    public static Image SymetrieVerticaleHover;

    //Sound
    public static Image Musique;
    public static Image SfxNone;
    public static Image SfxLow;
    public static Image SfxMedium;
    public static Image SfxHigh;

    //Menu Icons
    public static Image Exit;
    public static Image Load;
    public static Image Save;
    public static Image Back;

    //Backgrounds
    public static Image BackGroundGameFullScreen;
    public static Image BackGroundMenuFullScreen;
    public static Image BackGroundMenuFullScreenBg;
    public static Image MenuBlokus;
    public static Image MenuPlateau;

    public static void setupConfigurationMenu() {
        resetColors();

        BlokusLogo = lisImage("Graphics/blokus.png");

        //Main Buttons c ici
        MenuLogo = lisImage("Graphics/MainButtons/Setting-button.png");
        MenuLogoHover = lisImage("Graphics/MainButtons/Setting-Hover-button.png");
        SettingsLogo = lisImage("Graphics/MainButtons/Sound-button.png");
        SettingsLogoHover = lisImage("Graphics/MainButtons/Sound-Hover-button.png");
        HelpLogo = lisImage("Graphics/MainButtons/Help-button.png");
        HelpLogoHover = lisImage("Graphics/MainButtons/Help-Hover-button.png");

        //Menu Principal
        Back = lisImage("Graphics/Menu/arrow.png");

        //BackGrounds
        BackGroundMenuFullScreenBg = lisImage("Graphics/Backgrounds/Menu-test/menu-blokus-bg.png");
        BackGroundMenuFullScreen = lisImage("Graphics/Backgrounds/Menu-test/menu-blokus-2carre.png");
        MenuBlokus = lisImage("Graphics/Backgrounds/Menu-test/Logo-Blokus.png");
        MenuPlateau = lisImage("Graphics/Backgrounds/Menu-test/Menu-board.png");

    }
        public static void setupConfigurationJeu(int taillePlateau, int nbJ) {
        Configuration.taillePlateau =taillePlateau;
        nbJoueurs = nbJ;
        saveFileName = "res/Save/sauvegarde.txt";

            //Plateau
            Board = lisImage("Graphics/Plateau/Blokus-board.png");
            RedCell = lisImage("Graphics/Plateau/case-rouge.png");
            BlueCell = lisImage("Graphics/Plateau/case-bleu.png");
            GreenCell = lisImage("Graphics/Plateau/case-verte.png");
            YellowCell = lisImage("Graphics/Plateau/case-jaune.png");
            BoardCellGris = lisImage("Graphics/Plateau/board-square1.png");
            BoardCellWhite = lisImage("Graphics/Plateau/board-square2.png");
            GrayCell = lisImage("Graphics/Plateau/case-gris.png");
            Valid = lisImage("Graphics/Plateau/Valider-coup.png");
            Cancel = lisImage("Graphics/Plateau/Annule-coup.png");

            //Gestion de Jeu
            AnnuleLogo = lisImage("Graphics/Annule-Rejoue/annule-coup.png");
            AnnuleLogoHover = lisImage("Graphics/Annule-Rejoue/annule-coup-hover.png");
            RejouerLogo = lisImage("Graphics/Annule-Rejoue/rejoue-coup.png");
            RejouerLogoHover = lisImage("Graphics/Annule-Rejoue/rejoue-coup-hover.png");

            // Rotations
            RotationDroite = lisImage("Graphics/Rotations/Right-rotation-button.png");
            RotationDroiteHover = lisImage("Graphics/Rotations/Right-rotation-button-Hover.png");
            RotationGauche = lisImage("Graphics/Rotations/Left-rotation-button.png");
            RotationGaucheHover = lisImage("Graphics/Rotations/Left-rotation-button-Hover.png");
            SymetrieHorizontale = lisImage("Graphics/Rotations/Horizontal-rotation-button.png");
            SymetrieHorizontaleHover = lisImage("Graphics/Rotations/Horizontal-rotation-button-hover.png");
            SymetrieVerticale = lisImage("Graphics/Rotations/Vertical-rotation-button.png");
            SymetrieVerticaleHover = lisImage("Graphics/Rotations/Vertical-rotation-button-Hover.png");

            //Sound Logo
            Musique = lisImage("Graphics/Sound/logo-musique.png");
            SfxNone = lisImage("Graphics/Sound/sfx-logo-no-sound.png");
            SfxLow = lisImage("Graphics/Sound/sfx-logo-low-sound.png");
            SfxMedium = lisImage("Graphics/Sound/sfx-logo-medium-sound.png");
            SfxHigh = lisImage("Graphics/Sound/sfx-logo-high-sound.png");

            //MenuWindow Icons
            Exit = lisImage("Graphics/Menu/Exit-icon.png");
            Load = lisImage("Graphics/Menu/Upload-icon.png");
            Save = lisImage("Graphics/Menu/Save-icon.png");

            //Background
            BackGroundGameFullScreen = lisImage("Graphics/Backgrounds/background-pays.png");
    }

    private static Color[] defineColors(){
        Color[] colors = new Color[4];
        colors[0] = BLUE;
        colors[1] = YELLOW;
        colors[2] = RED;
        colors[3] = GREEN;
        return colors;
    }

    private static Color[] definePanelColors(){
        Color[] colors = new Color[4];
        colors[0] = BLUE_LIGHT;
        colors[1] = YELLOW_LIGHT;
        colors[2] = RED_LIGHT;
        colors[3] = GREEN_LIGHT;
        return colors;
    }

    private static Color[] defineBorderColors(){
        Color[] colors = new Color[4];
        colors[0] = BLUE_BOLD;
        colors[1] = YELLOW_BOLD;
        colors[2] = RED_BOLD;
        colors[3] = GREEN_BOLD;
        return colors;
    }

    public static void resetColors(){
        ordreJoueurs = defineColors();
        bordureJoueurs = defineBorderColors();
        backgroundPiecesPanel = definePanelColors();
    }

    public static String getSaveFileName(){
        return saveFileName;
    }

    // Chargement img .jar
    public static InputStream ouvre(String s) {
        InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream(s);
        if (in == null) {
            System.err.println("impossible de charger le ressource " + s);
            System.exit(1);
        }
        return in;
    }

    private static Image lisImage(String nom) {
        Image image = null;
        try {
            image = ImageIO.read(ouvre(nom));
        } catch (Exception e) {
            System.err.println("Impossible de charger l'image " + nom);
        }
        return image;
    }

}
