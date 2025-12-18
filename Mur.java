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

    /*Note : Beaucoup trop complexe a coder et surtout très difficile a débugger,
    on va subdiviser les cas en des méthodes séparées à la place de faire un seul touch().
    */

    @Override
    public void touch(Ball b) {
        double r = Ball.rayon;
        //coordonnées du coin supérieur gauche
        double xG = b.getX();
        double yG = b.getY();
        //coordonnées du coin inférieur droit
        double xD = b.getX() + 2*r;
        double yD = b.getY() + 2*r;

        // cas 1 : le mur se trouve a droite de la boule /!\ A REVOIR
        if (xD > this.i + 1) {
            b.inverserVx();
            //cas 2 : le mur se trouve à gauche de la boule (ok)
        } else if (xG < this.i) {
            b.inverserVx();
        }
        //cas 3 : le mur se trouve en haut de la boule (ok)
        if (yG < j) {
            b.inverserVy();
        } //cas 4 : le mur se trouve en bas de la boule /!\ A REVOIR
        else if (yD > j + 1) {
            b.inverserVy();
        }
    }

    public String toString(){
        return "#";
    }

}