import javax.swing.*;

public class Jeu {

    public static void main(String[] args){
        Labyrinthe laby = new Labyrinthe("laby1.txt");
        Ball b = new Ball(1,1); //position choisie arbitrairement

        JFrame frame = new JFrame("Enigma");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JeuGraphique jeu = new JeuGraphique(laby, b);
        frame.add(jeu);
        frame.pack();
        frame.setVisible(true);
    }
}
