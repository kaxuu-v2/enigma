public class Mur extends Square {

    public Mur(int x, int y){
        super(x, y);
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
    public void touch(Ball b){
        //ajouter du code pour gérer le rebond de la boule
        System.out.println("La balle est entrée en collision avec le mur en x = " + this.x + ", y = " + this.y);
    }

    public String toString(){
        return "#";
    }

}