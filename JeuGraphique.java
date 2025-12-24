import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JeuGraphique extends JPanel implements MouseListener, MouseMotionListener, KeyListener {

    private Labyrinthe laby;
    private Ball boule;
    private boolean etat; //pour stocker l'état en jeu qui sera toujours par défaut en false
    private boolean freezed;

    public final String[] levels = {"levels/laby0.txt", "levels/laby1.txt", "levels/laby2.txt", "levels/laby3.txt", "levels/laby4.txt", "levels/laby5.txt","levels/laby6.txt","levels/laby7.txt","levels/laby8.txt", "levels/laby9.txt",
                                    "levels/laby10.txt","levels/laby11.txt",};
    private int currentLevel = 0;


    //pour le niveau 0
    private boolean tutoMur = false;
    private boolean tutoTrou = false;
    private boolean tutoPiege = false;
    private boolean tutoFreeze = false;
    private boolean tutoElectro = false;
    private boolean tutoTeleport = false;
    private boolean tutoFake = false;
    private boolean tutoSortie = false;
    //Explication : on ajoute des booléens pour s'assurer que dans le tutoriel, on affichera le message qu'une seule fois

    public final int TAILLE_CASE = 40; //taille en pixels pour une case

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
        this.addKeyListener(this);
        this.setFocusable(true);

        this.setPreferredSize(new Dimension(widthPx, heightPx)); //Définition des dimensions de la fenêtre

    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for (int y = 0; y < laby.getHauteur(); y++){
            for (int x = 0; x < laby.getLargeur(); x++) {
                Square c = laby.getSquare(x,y);

                if (c instanceof Mur){
                    g.setColor(Color.DARK_GRAY);
                }
                if (c instanceof Sortie){
                    g.setColor(Color.GREEN);
                }
                if (c instanceof Ordinaire && c.isEmpty()){
                    g.setColor(new Color(6,64,43));
                }
                if (c instanceof Teleport){
                    Teleport t = (Teleport)c;
                    g.setColor(t.getColor());
                }
                if (c instanceof Freeze){
                    g.setColor(Color.CYAN);
                }
                if (c instanceof Hole){
                    g.setColor(Color.BLACK);
                }
                if (c instanceof Electro){
                    g.setColor(Color.MAGENTA);
                }
                if (c instanceof Piege){
                    g.setColor(new Color(153,101,21));
                }
                if (c instanceof Fake){
                    g.setColor(Color.GREEN);
                }
                //ajouter les cas avec les autres types de cases

                g.fillRect(x * TAILLE_CASE, y * TAILLE_CASE, TAILLE_CASE, TAILLE_CASE); //tracage du contour du laby
                g.setColor(Color.LIGHT_GRAY); //on ajoute un contour au cases pour bien voir la grille
                g.drawRect(x * TAILLE_CASE, y * TAILLE_CASE, TAILLE_CASE, TAILLE_CASE);
            }
        }
        //Dessin de la boule
        g.setColor(this.boule.getColor());
        int rayonPx = (int)(Ball.rayon * TAILLE_CASE);

        //Définition de la position de la boule en convertissant les coordonnées en int
        int xPx = (int)(boule.getX() * TAILLE_CASE);
        int yPx = (int)(boule.getY() * TAILLE_CASE);

        g.fillOval(xPx, yPx, rayonPx * 2, rayonPx * 2);

        //on ajoute un bloc pour indiquer au joueur quand il est en pause ou pas
        if (!etat && !freezed){
            g.setColor(new Color(0,0,0, 150));
            g.fillRect(0,0, this.getWidth(),this.getHeight());

            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            String message = "Cliquez pour jouer";

            FontMetrics fm = g.getFontMetrics(); //on utilise FontMetrics pour récupérer les caracteristiques de la police de caracteres (taille, largeur, etc)
            int texteLargeur = fm.stringWidth(message);
            int texteHauteur = fm.getAscent();

            //centrage du texte
            int x = (this.getWidth() - texteLargeur) / 2;
            int y = (this.getHeight() + texteHauteur) / 2;

            //dessinage du texte
            g.drawString(message, x, y);

        }
        
    }

    public void update() {
        if (etat) {

            int centreX = this.getWidth() / 2;
            int centreY = this.getHeight() / 2;
            double dX = mouseX - centreX;
            double dY = mouseY - centreY;

            this.boule.acceleration(dX * 0.025, dY * 0.025); //la boule allait trop vite malgré le seuil, je réduis donc un peu la vitesse
            this.boule.frottement();

            //on récupère le rayon et le diamètre
            double r = Ball.rayon;
            double diametre = 2 * r;

            //gestion de l'axe X
            double futureX = this.boule.getX() + this.boule.getVx();
            double checkX;

            if (this.boule.getVx() > 0){ //si vx > 0 (on va a droite) alors on teste avec (x + diametre)
                checkX = futureX + diametre;
            } else {
                checkX = futureX; //sinon on teste seulement avec x
            }

            int iX = (int) checkX; //c'est le bord qu'on va vérifier
            int jY_haut = (int) (this.boule.getY()); //coordonnée du mur au dessus de la boule
            int jY_bas  = (int) (this.boule.getY() + diametre); //coordonnées du mur en dessous

            // on s'assure que les coordonnées ne dépassent pas les bornes du labyrinthe sinon on a une erreur OutOfBoundsException
            if (iX >= 0 && iX < laby.getLargeur()) {
                Square murHaut = laby.getSquare(iX, jY_haut);
                Square murBas = laby.getSquare(iX, jY_bas);

                if ((murHaut instanceof Mur) || (murBas instanceof Mur)) {
                    this.boule.inverserVx(); //on vérifie si la case est un mur et si c'est le cas on applique les rebonds (inversement)
                } else {
                    //sinon on laisse la boule se déplacer
                    this.boule.setX(futureX);
                }
            }

            //on fait la même chose mais avec l'axe Y
            double futureY = this.boule.getY() + this.boule.getVy();

            double checkY;

            if (this.boule.getVy() > 0){
                checkY = futureY + diametre;
            } else {
                checkY = futureY;
            }

            int jY = (int) checkY;
            int iX_gauche = (int) (this.boule.getX()); //coordonnée du mur a gauche
            int iX_droite = (int) (this.boule.getX() + diametre); //coordonnée du mur a droite

            //on verifie si les coordonnées dépassent pas les bornes
            if (jY >= 0 && jY < laby.getHauteur()) {
                Square murGauche = laby.getSquare(iX_gauche, jY);
                Square murDroite = laby.getSquare(iX_droite, jY);

                //application ou non des rebonds
                if ((murGauche instanceof Mur) || (murDroite instanceof Mur)) {
                    this.boule.inverserVy();
                } else {
                    this.boule.setY(futureY);
                }
            }

            //on gère maintenant le cas des cases spéciales (on doit attendre que la case entre pour appliquer les algorithmes donc que le centre soit en contact avec la case)
            int ballCentreX = (int) (this.boule.getX() + r);
            int ballCentreY = (int) (this.boule.getY() + r);

            if (ballCentreX >= 0 && ballCentreX < laby.getLargeur() && ballCentreY >= 0 && ballCentreY < laby.getHauteur()) {
                Square s = this.laby.getSquare(ballCentreX, ballCentreY);
                traiterInteraction(s);
            }
        }
    }

    // finalement, on va mettre les cas des cases spéciales dans un méthode a part
    private void traiterInteraction(Square s) {
        if (this.currentLevel == 0) {
            tutoriel(s);
        }
        //ici pas de else car sinon le code va pas appliquer les effets dans le tutoriel
        if (s instanceof Sortie){
            niveauSuivant();
            return;
        } else if (s instanceof Teleport) {
            s.enter(this.boule);
        } else if (s instanceof Electro){ //c'est le même procédé que pour freeze, seulement l'animation qui change
            Electro e = (Electro)s;
            if (e.ready()) {
                System.out.println("Bzzzzz");
                e.declenchement();
                this.etat = false;
                this.freezed = true;
                this.boule.stop();

                //on ajoute l'animation
                Timer anim = new Timer(100, e1 -> {
                    if (this.boule.getColor() == Color.BLACK){
                        this.boule.setColor(Color.CYAN);
                        this.repaint();
                        this.boule.setColor(Color.BLUE);
                    } else {
                        this.boule.setColor(Color.BLACK);
                    }
                    this.repaint();
                });

                //ajout de l'autre timer pour le délai
                Timer t = new Timer(3000, e2 -> {
                    anim.stop(); //on arrête le timer pour l'animation, sinon la boule continuera de clignoter
                    this.etat = true;
                    this.freezed = false;
                    this.boule.setColor(Color.BLACK);
                    this.repaint();
                    System.out.println("Fin d'électrocution");
                });
                anim.start();
                t.setRepeats(false);
                t.start();
            }
        } else if (s instanceof Freeze){
            Freeze f = (Freeze)s;
            if (f.ready()){
                System.out.println("Freeze");
                f.declenchement();
                this.freze();
                this.boule.setColor(Color.WHITE);
                this.repaint();
                Timer t = new Timer(3000, e -> {
                    this.defreeze();
                    this.boule.setColor(Color.BLACK);
                    System.out.println("Defreeze");
                }); //on remet a true apres les 3s ecoulées
                t.setRepeats(false); //on execute le timer une seule fois pour freeze le joueur puis on le réutilise plus
                        /* Explication : si on va par exemple sur un case freeze et qu'on retourne ensuite rapidement sur une autre,
                        l'autre va prendre en compte l'ancien timer et va donc pas forcement s'arrêter au bout des 3secondes */
                        t.start();
                    }

        } else if (s instanceof Fake){
            this.respawn();
            System.out.println(":P");
        } else if (s instanceof Hole){
                    this.respawn();
                    System.out.println("Il est tombé");
                } else if (s instanceof Piege) {
                    System.out.println("Piège");
                    boolean b = this.boule.damage();
                    if (b){
                        this.freze();
                        this.boule.setColor(Color.RED);
                        this.repaint();

                        Timer t = new Timer(1000, e -> {
                            if (this.boule.getPv() <= 0){ //on verifie si le joueur est mort (il se prend plus de 3 fois le piege)
                                System.out.println("Mort !");
                                this.respawn();
                            } else {
                                this.boule.invincible(); //on laisse 1s au joueur pour sortir de la case piege
                            }
                            this.defreeze();
                            this.boule.setColor(Color.BLACK);
                        });
                        t.setRepeats(false);
                        t.start();
                    }

            }
    }

    //j'ajoute un méthode respawn pour éviter la redondance du code
    public void respawn(){
        this.boule.setX(this.laby.getInitX() + Ball.rayon);
        this.boule.setY(this.laby.getInitY() + Ball.rayon);
        this.boule.stop();
        this.boule.heal();
    }

    public void freze(){
        this.etat = false;
        this.freezed = true;
        this.boule.stop();
    }

    public void defreeze(){
        this.etat = true;
        this.freezed = false;
    }

    //on ajoute une méthode dédiée au niveau 0 (le tutoriel)
    private void tutoriel(Square s) {
        String titre = "";
        String message = "";

        if (s instanceof Fake && !tutoFake) {
            titre = "Fausse sortie";
            message = "Fais gaffe ! Ce trou vert est une illusion.\nCe n'est pas la vraie sortie !";
            tutoFake = true;
        }
        else if (s instanceof Hole) {
            titre = "Trou";
            message = "Attention ! Tu viens de tomber dans un trou. Tu réapparais donc au point initial";
            tutoTrou = true;
        }
        else if (s instanceof Piege && !tutoPiege) {
            titre = "Piège";
            message = "Aïe ! Ce piège t'enlève 1 Point de Vie.\nÀ 0 PV, tu meurs. (tu commences avec 3PV)";
            tutoPiege = true;
        }
        else if (s instanceof Freeze && !tutoFreeze) {
            titre = "Freeze";
            message = "Cette case te gèle sur place.\nTu ne pourras plus bouger pendant quelques secondes.";
            tutoFreeze = true;
        }
        else if (s instanceof Electro && !tutoElectro) {
            titre = "Électricité";
            message = "Cette case t'électrocute.\nIdem que le freeze, tu ne pourras plus bouger pendant quelques secondes!";
            tutoElectro = true;
        }
        else if (s instanceof Teleport && !tutoTeleport) {
            titre = "Téléporteur";
            message = "Tu viens de te téléporter. A chaque fois que tu verras une case bleue, elle te mènera vers une case orange (et inversement)";
            tutoTeleport = true;
        }
        else if (s instanceof Sortie && !tutoSortie) {
            titre = "Sortie";
            message = "C'est l'objectif ! Atteins cette case pour gagner le niveau.";
            tutoSortie = true;
        }
        if (!message.isEmpty()){
            this.boule.stop();
            this.repaint(); //on reforce l'affichage
            JOptionPane.showMessageDialog(this, message, "Tutoriel : " + titre, JOptionPane.INFORMATION_MESSAGE);
        }
    }

    //Implémentations des écouteurs
    @Override
    public void mouseClicked(MouseEvent e){
        if (this.freezed){
            System.out.println("Impossible ! Vous êtes en freeze !");
            return;
        }
        this.etat = !this.etat;
        //System.out.println("Clique");
        if (etat){
            this.mouseX = e.getX();
            this.mouseY = e.getY();
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

    //Méthode pour charger d'autres niveaux
    public void niveauSuivant(){
        this.etat = false;
        boule.setX(-32.);
        boule.setY(-32.);
        this.repaint();
        int choix = JOptionPane.showOptionDialog(
                this,
                "Bravo ! Vous avez terminé le niveau !",
                "Niveau terminé !",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                new ImageIcon("stringformat.ico"), //on pourra ajouter une icone plus tard
                new String[]{"Niveau suivant", "Quitter"},
                "Niveau suivant"
        );

        // gestion du choix du joueur
        if (choix == JOptionPane.YES_OPTION) {
            this.currentLevel++;
            if (this.currentLevel < this.levels.length){
                this.laby = new Labyrinthe(levels[currentLevel]);

                //mise en place du niveau suivant
                double initialX = this.laby.getInitX() + Ball.rayon;
                double initialY = this.laby.getInitY() + Ball.rayon;
                this.boule.setX(initialX);
                this.boule.setY(initialY);
                this.boule.stop();
                this.repaint();

            } else {
                JOptionPane.showMessageDialog(this, "Felicitations ! Vous avez fini le jeu !");
                System.exit(0);
            }
        } else {
            // Fermer le jeu
            System.exit(0);
        }

    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {}

    @Override
    public void keyPressed(KeyEvent keyEvent) { //on a ajouté ca afin de pouvoir passer directement les niveaux (pour tester les labyrinthes sans changer le laby initial dans le code)
        if (keyEvent.getKeyCode() == KeyEvent.VK_CONTROL){
            System.out.println("Skip");
            niveauSuivant();
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {}
}





