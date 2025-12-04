public abstract class Square { 
    
    public final int x, y; //position d'une case
    //a priori, une case reste fixe (sa position ne change pas)

    public Square(int x, int y){
        this.x = x;
        this.y = y;
    }

    abstract public boolean isEmpty(); //verifie si une case est libre 
    abstract public void enter(Ball b); //verifie si la boule entre dans une case
    abstract public void leave(Ball b); //verifie si la boule sort d'une case
    abstract public void touch(Ball b); //verifie si la boule est en train de percuter une case (qui contiendra un obstacle)

}