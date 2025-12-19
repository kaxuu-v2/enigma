public class Freeze extends Square {

    //on fait la meme chose que pour les téléporteurs
    private long lastFreeze = 0;
    private final long delay = 5000; //on met 5s pour pouvoir laisser 2s a la boule pour sortir de la case et éviter des problemes de gel infini

    public Freeze(int i, int j) {
        super(i, j);
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    public boolean ready(){
        long t = System.currentTimeMillis(); //on procede de la meme maniere que avec les téléporteurs
        return (t - this.lastFreeze > delay);
    }

    public void declenchement(){
        this.lastFreeze = System.currentTimeMillis();
    }

    @Override
    public void enter(Ball b) {

    }

    @Override
    public void leave(Ball b) {

    }

    @Override
    public void touch(Ball b) {

    }

    //je rajoute le toString pour la classe Labyrinthe
    @Override
    public String toString(){ return "F"; }
}
