public class Sortie extends Square {

    public Sortie(int x, int y){
        super(x, y);
    }

    @Override
    public boolean isEmpty() {
        return true; //une sortie sera toujours vide
    }

    @Override
    public void enter(Ball b) {
        //a compléter
        System.out.println("Vous avez réussi à sortir du labyrinthe !");
    }

    @Override
    public void leave(Ball b) {
        //On ne peut pas sortir d'une sortie
    }

    @Override
    public void touch(Ball b) {

    }

    @Override
    public void touchHorizontal(Ball b) {
        //return false;
        //On ne peut pas entrer en collision avec une case Sortie car elle ne contiendra pas d'obstacles
    }

    @Override
    public void touchCorner(Ball b) {

    }

    @Override
    public void touchVertical(Ball b) {

    }

    public String toString(){
        return "O";
    }
}