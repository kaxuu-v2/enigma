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

    public String toString(){
        return "O";
    }
}
