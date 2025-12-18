import javax.swing.*;

public class Sortie extends Square {

    public Sortie(int x, int y){
        super(x, y);
    }

    @Override
    public boolean isEmpty() {
        return true; //une sortie sera toujours vide
    }

    @Override
    public void enter(Ball b) {
        b.setX(-32.);
        b.setY(-32.);
        int choix = JOptionPane.showOptionDialog(
                null,
                "Bravo ! Vous avez terminé le niveau !",
                "Niveau terminé !",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                new ImageIcon(), //on pourra ajouter une icone plus tard
                new String[]{"Passer au niveau suivant", "Fermer le jeu"},
                "Passer au niveau suivant"
        );

        // gestion du choix du joueur
        if (choix == JOptionPane.YES_OPTION) {
            // pour l'instant ça marche pas car on n'a pas crée de nouveaux niveaux
            System.out.println("Chargement du niveau suivant...");
            //on pourra utilser la méthode niveauSuivant(String f) de la class JeuGraphique
        } else {
            // Fermer le jeu
            System.exit(0);
        }
        System.out.println("Vous avez réussi à sortir du labyrinthe !");
    }

    @Override
    public void leave(Ball b) {
        //On ne peut pas sortir d'une sortie
    }

    @Override
    public void touch(Ball b) {

    }

    public String toString(){
        return "O";
    }
}