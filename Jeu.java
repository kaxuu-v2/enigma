import javax.swing.*;
import java.awt.event.*;
public class Jeu extends JPanel {

    public static void main(String[] args){
        Labyrinthe laby = new Labyrinthe("laby1.txt");
        Ball b = new Ball(1.5 - Ball.rayon,1.5 - Ball.rayon); //position choisie arbitrairement

        JFrame frame = new JFrame("Enigma");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JeuGraphique jeu = new JeuGraphique(laby, b);

        frame.add(jeu);
        frame.pack();
        frame.setVisible(true);

        Timer chrono = new Timer(10, e -> {jeu.update(); jeu.repaint();}); //actualisation toutes les 10ms
        chrono.setInitialDelay(0);
        chrono.start();
    }
}
