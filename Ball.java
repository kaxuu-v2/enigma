import java.awt.event.MouseListener;
import java.lang.Math;

    public class Ball {

        //Definition des constantes donné par le sujet
        public static final double f = 0.005; //le facteur de friction
        public static final double f_a = 0.001; //le facteur d'accélération
        public static final double rayon = 0.3; //rayon de la boule

        private double x, y; //position de la boule
        private double vx, vy; //vitesse de la boule
        private double v; //vitesse absolue

        public final double seuil = 0.2; //la vitesse a ne pas dépasser

        public Ball(double x, double y){
            this.x = x;
            this.y = y; //on initialise la position de la boule en début de jeu
            this.vx = 0; //initialement, la bille n'aura pas de vitesse
            this.vy = 0;
        }

        public double getSeuil(){ return this.seuil; }

        public double getX(){
            return this.x;
        }

        public double getY(){
            return this.y;
        }

        public void setX(double x){ this.x = x;}

        public void setY(double y){ this.y = y;}

        public double getVx(){
            return this.vx;
        }

        public double getVy(){
            return this.vy;
        }

        public double vAbs(){ //méthode pour renvoyer la vitesse absolue de la boule
            return Math.sqrt(this.vx * this.vx + this.vy * this.vy);
        }

        private void limite(){
            double v = this.vAbs();
            if (v > seuil){
                double ratio = this.seuil / v;
                this.vx *= ratio;
                this.vy *= ratio;
            }

        }

        public void avance(){ //on l'utilisera pour avancer la bille
            limite();
            this.x += this.vx;
            this.y += this.vy;
        }

        //Vecteurs de direction de déplacement
        public double getDx(){
            double v = this.vAbs(); //on stocke le résultat pour éviter de le calculer 2 fois
            if (v == 0) {
                return 0; //le sujet dit "Si elle ne bouge pas (donc si v = 0), on retournera dx = dy = 0."
            }
            return this.vx / v;
        }

        public double getDy() {
            double v = this.vAbs();
            if (v == 0) {
                return 0;
            }
            return this.vy / v;
        }

        //Pour gérer les rebonds
        public void inverserVx() {
            this.vx = -this.vx;
            System.out.println("Inversement du X");
        }

        public void inverserVy() {
            this.vy = -this.vy;
            System.out.println("Inversement du Y");
        }


        //Méthodes liées aux frottements
        public void frottement(){
            double v = this.vAbs();
            if (v <= f){
                this.vx = 0;
                this.vy = 0;
            } else {
                this.vx = vx - f * getDx();
                this.vy = vy - f * getDy();
            }
        }

        public void acceleration(double sx, double sy){
            this.vx += f_a * sx;
            this.vy += f_a * sy;
        }
    }
