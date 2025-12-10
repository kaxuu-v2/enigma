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
    Note : A priori, touch() beaucoup trop complexe a coder et surtout très difficile a débugger,
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

    public String toString(){
        return "#";
    }

}
