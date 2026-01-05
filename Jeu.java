import javax.swing.*;
import java.awt.event.*;
public class Jeu extends JPanel {

    public static void main(String[] args){
        Labyrinthe laby = new Labyrinthe("levels/laby0.txt");
        double x = laby.getInitX();
        double y = laby.getInitY();
        Ball b = new Ball(x + Ball.rayon,y + Ball.rayon);

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
