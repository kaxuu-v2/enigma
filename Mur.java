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

    @Override
    public void touch(Ball b) {
    }

    public String toString(){
        return "#";
    }

}