public abstract class Square { 
    
    public final int i, j; //position d'une case
    //a priori, une case reste fixe (sa position ne change pas)

    public Square(int i, int j){
        this.i = i;
        this.j = j;
    }

    abstract public boolean isEmpty(); //verifie si une case est libre 
    abstract public void enter(Ball b); //verifie si la boule entre dans une case
    abstract public void leave(Ball b); //verifie si la boule sort d'une case

    //Donc on abandonne l'idée de faire une méthode touch()
    abstract public void touch(Ball b);
    abstract public void touchHorizontal(Ball b); //verifie si la boule est en train de percuter une case (qui contiendra un obstacle)
    abstract public void touchVertical(Ball b);
    abstract public void touchCorner(Ball b);

}