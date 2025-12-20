public class Piege extends Square {

    private int pv;
    public static final int MAX_PV = 3;

    public long lastDmg = 0; //même principe que les cases freeze et electro

    public Piege(int i, int j) {
        super(i, j);
    }

    public int getPv(){ return this.pv;}

    public void reset(){
        this.pv = 0;
    }

    public void resetCooldown() {
        this.lastDmg = System.currentTimeMillis();
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public void enter(Ball b) {
        long now = System.currentTimeMillis();
        if (now - lastDmg > 1000){
            this.pv++;
            this.lastDmg = now;
            System.out.println("Piège");
        }
    }

    @Override
    public void leave(Ball b) {

    }

    @Override
    public void touch(Ball b) {

    }

    public String toString(){ return "P"; }
}
