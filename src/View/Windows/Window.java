package View.Windows;

import Controller.Configuration;

import javax.swing.*;

public abstract class Window extends JFrame {
    public Window() {
        super("Blokus");
        setIconImage(Configuration.BlokusLogo);
    }

    public void afficherCarte(String s) {}

    public void reinitialise() {

    }
}
