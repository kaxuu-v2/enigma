    import java.lang.Math;

    public class Ball {
        public static final double f = 0.005; //le facteur de friction
        public static final double f_a = 0.001; //le facteur d'accélération


        private double x, y; //position de la bille
        private double vx, vy; //vitesse de la bille
        public final double rayon = 0.3; //rayon de la bille

        public Ball(double x, double y){
            this.x = x;
            this.y = y; //on initialise la position de la boule en début de jeu
            this.vx = 0; //initialement, la bille n'aura pas de vitesse
            this.vy = 0;
        }

        public double getX(){
            return this.x;
        }

        public double getY(){
            return this.y;
        }

        public double getVx(){
            return this.vx;
        }

        public double getVy(){
            return this.vy;
        }

        public double vAbs(){ //méthode pour renvoyer la vitesse absolue de la boule
            return Math.sqrt(this.vx * this.vx + this.vy * this.vy);
        }

        public void avance(){ //on l'utilisera pour avancer la bille
            this.x += this.vx;
            this.y += this.vy;
        }

        //Vecteur de direction de déplacement
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
        }

        public void inverserVy() {
            this.vy = -this.vy;
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