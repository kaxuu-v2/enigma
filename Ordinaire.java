public class Ordinaire extends Square {

    protected Obstacle contenu;
    //on a decidé de mettre l'attribut uniquement dans cette classe car sortie et mur n'auront jamais de contenu

    public Ordinaire(int x, int y, Obstacle contenu){
        super(x,y);
        this.contenu = contenu;
    }

    public Ordinaire(int x, int y){
        super(x,y);
        this.contenu = null;
    }

    public Obstacle getContenu(){
        return this.contenu;
    }

    @Override
    public boolean isEmpty(){
        return this.contenu == null; //renvoi un boolean selon si la case a un contenu ou non
    }

    @Override
    public void enter(Ball b){
        //compléter
        System.out.println("La boule est entrée dans la case x = " + this.i + ", y = " + this.j);
    }

    @Override
    public void touch(Ball b) {

    }

    @Override
    public void touchHorizontal(Ball b){
        if (!this.isEmpty()){
            System.out.println("La boule est entrée en collision avec l'obstacle en x = " + this.i + ", y = " + this.j);
            //ajouter du code pour gérer le rebondissement de la bille
        }
        //si la case est vide, on ne fait rien
    }

    @Override
    public void touchVertical(Ball b){
        if (!this.isEmpty()){
            System.out.println("La boule est entrée en collision avec l'obstacle en x = " + this.i + ", y = " + this.j);
            //ajouter du code pour gérer le rebondissement de la bille
        }
        //si la case est vide, on ne fait rien
    }

    @Override
    public void touchCorner(Ball b){
        if (!this.isEmpty()){
            System.out.println("La boule est entrée en collision avec l'obstacle en x = " + this.i + ", y = " + this.j);
            //ajouter du code pour gérer le rebondissement de la bille
        }
        //si la case est vide, on ne fait rien
    }

    @Override
    public void leave(Ball b) {
        //compléter
        System.out.println("La boule est sortie de la case : x = " + this.i + ", y = " + this.j);
    }

    public String toString(){
        return " ";
    }
}