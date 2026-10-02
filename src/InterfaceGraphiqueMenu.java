import Controller.Configuration;
import View.Windows.MenuPrincipal;

import javax.swing.*;

public class InterfaceGraphiqueMenu implements Runnable{


    public void run(){
        Configuration.setupConfigurationMenu();
        new MenuPrincipal(true);
    }

    public static void main (String [] args){
        SwingUtilities.invokeLater(new InterfaceGraphiqueMenu());
    }

}