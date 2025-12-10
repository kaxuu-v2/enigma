import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JeuGraphique extends JPanel implements MouseListener, MouseMotionListener {

    private Labyrinthe laby;
    private Ball boule;
    private boolean etat; //pour stocker l'état en jeu qui sera toujours par défaut en false

    public final int TAILLE_CASE = 50; //taille en pixels pour une case

    //Position de la souris
    private int mouseX, mouseY;

    public boolean enCours(){ return this.etat; }

    public JeuGraphique(Labyrinthe laby, Ball b){
        this.laby = laby;
        this.boule = b;

        int widthPx = laby.getLargeur() * TAILLE_CASE;
        int heightPx = laby.getHauteur() * TAILLE_CASE;
        this.mouseX = widthPx / 2;
        this.mouseY = heightPx / 2;

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

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
        g.setColor(Color.GRAY);
        int rayonPx = (int)(Ball.rayon * TAILLE_CASE);

        //Définition de la position de la boule en convertissant les coordonnées en int
        int xPx = (int)(boule.getX() * TAILLE_CASE);
        int yPx = (int)(boule.getY() * TAILLE_CASE);

        g.fillOval(xPx, yPx, rayonPx * 2, rayonPx * 2);
    }

    //Méthode qui actualise le jeu
    public void update(){
        if (etat){
            //on calcule la direction (différence entre le centre et la souris)
            int centreX = this.getWidth() / 2;
            int centreY = this.getHeight() / 2;
            double dX = mouseX - centreX;
            double dY = mouseY - centreY;

            this.boule.acceleration(dX * 0.05, dY * 0.05);
            this.boule.frottement();
            this.boule.avance();
            //System.out.println("Vitesse : " + boule.vAbs());

            //code pour gérer les collisions avec les murs
            //on va prendre la future case dans laquelle va se trouver la boule
            int i = (int)(this.boule.getX() + this.boule.getVx());
            int j = (int)(this.boule.getY() + this.boule.getVy());

            // empêcher les dépassements d'indices
            if (i < 0 || i >= laby.getLargeur() || j < 0 || j >= laby.getHauteur()) {
                return;
            }

            /// on vérifie si c'est un case intraversable (mur)
            Square s = this.laby.getSquare(i,j);
            if (s instanceof Mur){
                s.touch(this.boule);
            }

            this.boule.avance();
        }
    }

    //Implémentations des écouteurs
    @Override
    public void mouseClicked(MouseEvent e){
        this.etat = !this.etat;
        //System.out.println("Clique");
        if (etat){
            this.mouseX = getWidth() / 2;
            this.mouseY = getHeight() / 2; //centrage de la souris
        }
    }


    @Override
    public void mousePressed(MouseEvent e){}

    @Override
    public void mouseReleased(MouseEvent e){}

    @Override
    public void mouseEntered(MouseEvent e){}

    @Override
    public void mouseExited(MouseEvent e){}

    @Override
    public void mouseDragged(MouseEvent e){}

    @Override
    public void mouseMoved(MouseEvent e){
        if (this.etat){
            //System.out.println("Bouge");
            this.mouseX = e.getX();
            this.mouseY = e.getY();
            }
        }
    }





