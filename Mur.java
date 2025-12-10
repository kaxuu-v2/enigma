public class Mur extends Square {

    public Mur(int i, int j){
        super(i, j);
    }

    @Override
    public boolean isEmpty(){
        return false; //un mur sera toujours vide
    }

    @Override
    public void enter(Ball b){
        //Entrée dans un mur est impossible
    }

    @Override
    public void leave(Ball b){
        //Idem que enter(Ball b)
    }

    /*
    Note : Beaucoup trop complexe a coder et surtout très difficile a débugger,
    on va subdiviser les cas en des méthodes séparées à la place de faire un seul touch().
    */

    @Override
    public void touch(Ball b) {
        double r = Ball.rayon;
        //centrage des coordonnées
        double x = b.getX() + r;
        double y = b.getY() + r;

        // cas 1 : le mur se trouve a droite de la boule
        if (x + r > this.i + 1) {
            b.inverserVx();
            //cas 2 : le mur se trouve à gauche de la boule (ok)
        } else if (x - r < this.i) {
            b.inverserVx();
        }
        //cas 3 : le mur se trouve en haut de la boule (ok)
        if (y - r < j + 1) {
            b.inverserVy();
        } //cas 4 : le mur se trouve en bas de la boule
        else if (y + r > j + 1) {
            b.inverserVy();
        }
    }

    @Override
    public void touchHorizontal(Ball b){
        // Appelé pour les murs à Gauche et à Droite
        if (estEnContact(b)) {
            b.inverserVx();
        }
    }

    @Override
    public void touchVertical(Ball b){
        // Appelé pour les murs en Haut et en Bas
        if (estEnContact(b)) {
            b.inverserVy();
        }
    }

    @Override
    public void touchCorner(Ball b){
        // Appelé pour les diagonales
        if (estEnContact(b)) {
            // Dans un coin, on inverse tout pour un rebond propre
            b.inverserVx();
            b.inverserVy();
        }
    }

    // --- MÉTHODE DE DÉTECTION (HITBOX) ---
    // Vérifie si le rectangle de la balle chevauche le rectangle du mur
    private boolean estEnContact(Ball b) {
        // Coordonnées de la Balle (Centre et Rayon)
        double bx = b.getX();
        double by = b.getY();
        double br = Ball.rayon; // Ton rayon est 0.3

        // Coordonnées du Mur (i=Ligne=Y, j=Colonne=X)
        // Le mur va de j à j+1 en X, et de i à i+1 en Y
        double murGauche = this.j;
        double murDroit = this.j + 1;
        double murHaut = this.i;
        double murBas = this.i + 1;

        // Test de chevauchement AABB (Axis-Aligned Bounding Box)
        // Est-ce que la balle touche le mur horizontalement ?
        boolean toucheX = (bx + br > murGauche) && (bx - br < murDroit);
        // Est-ce que la balle touche le mur verticalement ?
        boolean toucheY = (by + br > murHaut) && (by - br < murBas);

        return toucheX && toucheY;
    }


    public String toString(){
        return "#";
    }

}