import java.awt.*;
import javax.swing.*;

public class JeuGraphique extends JPanel {

    private Labyrinthe laby;
    private Ball boule;

    private final int TAILLE_CASE = 30; //30 pixels pour une case

    public JeuGraphique(Labyrinthe laby, Ball b){
        this.laby = laby;
        this.boule = b;

        int widthPx = laby.getLargeur() * TAILLE_CASE;
        int heightPx = laby.getHauteur() * TAILLE_CASE;
        this.setPreferredSize(new Dimension(widthPx, heightPx)); //Définition des dimensions de la fenêtre
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for (int y = 0; y < laby.getHauteur(); y++){
            for (int x = 0; x < laby.getLargeur(); x++) {
                Square c = laby.getSquare(x,y);

                if (c instanceof Mur){
                    g.setColor(Color.BLACK); //on met les murs en noir
                }
                if (c instanceof Sortie){
                    g.setColor(Color.GREEN); //on met la sortie en vert
                }
                if (c instanceof Ordinaire && c.isEmpty()){
                    g.setColor(Color.WHITE); //on mettra du blanc pour les cases vides
                }
                //ajouter les cas avec les autres types de cases

                g.fillRect(x * TAILLE_CASE, y * TAILLE_CASE, TAILLE_CASE, TAILLE_CASE); //tracage du contour du laby
                g.setColor(Color.LIGHT_GRAY); //on ajoute un contour au cases pour bien voir la grille
                g.drawRect(x * TAILLE_CASE, y * TAILLE_CASE, TAILLE_CASE, TAILLE_CASE);
            }
        }
        //Dessin de la boule
        g.setColor(Color.RED);
        int rayonPx = (int)(boule.rayon * TAILLE_CASE);

        //Définition de la position de la boule en convertissant les coordonnées en int
        int yPx = (int)(boule.getX() * TAILLE_CASE);
        int xPx = (int)(boule.getY() * TAILLE_CASE);

        g.fillOval(xPx, yPx, rayonPx * 2, rayonPx * 2);
    }
}



