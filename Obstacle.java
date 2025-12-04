public abstract class Obstacle {

    //on va mettre deux attributs qui nous indiqueront si un obstable est destructible/déplacable ou non
    private boolean indestructible;
    private boolean deplacable;

    public Obstacle(boolean indestructible, boolean deplacable){
        this.indestructible = indestructible;
        this.deplacable = deplacable;
    }

    public boolean isIndestructible(){
        return this.indestructible;
    }

    public boolean isDeplacable(){
        return this.deplacable;
    }

    //On pourra faire un code pour gérer les collisions avec des obstacles

}