import javax.swing.*;
import java.awt.event.*;
public class Jeu extends JPanel {

    public static void main(String[] args){
        Labyrinthe laby = new Labyrinthe("levels/laby1.txt");
        double x = laby.getInitX() + 0.5 ;
        double y = laby.getInitY() + 0.5;
        Ball b = new Ball(x - Ball.rayon,y - Ball.rayon); //position choisie arbitrairement (on pourra coder une case départ pour que la boule s'y place a chaque niveau)

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
