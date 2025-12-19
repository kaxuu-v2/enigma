import java.awt.*;

public class Teleport extends Square {

    private Teleport destination;
    private Color couleur;

    private long lastTime = 0; //on va ajouter des attributs pour stocker la derniere fois qu'on est entré dans un portal
    private final long delay = 1000; ///on met en place un delai avant de reutiliser un portail (1000ms = 1s)

    public Teleport(int i, int j) {
        super(i, j);
    }

    public void setColor(Color c){
        this.couleur = c;
    }

    public void activation(){
        this.lastTime = System.currentTimeMillis();
    }

    public void setDestination(Teleport t){
        this.destination = t;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public void enter(Ball b) {
        long t = System.currentTimeMillis();
        if (destination != null && (t - this.lastTime > delay)) { //ici on verifie si 1 seconde s'est écoulé depuis la derniere entrée dans le portail
            b.setX(this.destination.j + 0.5);
            b.setY(this.destination.i + 0.5);
            b.stop();
            this.activation();
            this.destination.activation();
            System.out.println("Teleportation");
        }
    }

    @Override
    public void leave(Ball b) {
    }

    @Override
    public void touch(Ball b) {

    }

    @Override
    public String toString(){ return "@"; }

    public Color getColor() {
        return this.couleur;
    }
}
