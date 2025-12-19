public class Hole extends Square {


    public Hole(int i, int j) {
        super(i, j);
    }

    @Override
    public boolean isEmpty() {
        return true;
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

    public String toString(){
        return "!";
    }
}
