import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;


public class Labyrinthe {

    private int hauteur, largeur;
    private Square[][] laby; //le labyrinthe est défini par un tableau 2D de squares (cases)

    public int getHauteur(){
        return this.hauteur;
    }

    public int getLargeur(){
        return this.largeur;
    }

    public Square getSquare(int x, int y){
        return this.laby[y][x];
    }

    public Labyrinthe(String file) {
        try {
            //ouverture du fichier
            Scanner sc = new Scanner(new FileInputStream(file));

            //lecture des dimensions
            this.hauteur = sc.nextInt();
            this.largeur = sc.nextInt();
            sc.nextLine();

            //initialisation du labyrinthe/tableau
            this.laby = new Square[hauteur][largeur];
            for (int l=0; l<hauteur; l++) {
                String line = sc.nextLine();
                for (int c=0; c<largeur; c++) {
                    Square s;
                    Character ch = line.charAt(c);
                    switch (ch) {
                        case '#': s = new Mur(c, l); break; //quand il y a un mur, on affiche #
                        case ' ': s = new Ordinaire(c, l); break; //quand c'est une case ordinaire " "
                        case 'O': s = new Sortie(c, l); break; //quand c'est la sortie O
                        //on pourra ajouter d'autres cases pour les obstacles
                        default:  s =  new Ordinaire(c, l); break;
                    }
                    this.laby[l][c] = s;
                }
            }
            sc.close();
        }
        catch (IOException e) {
            System.out.println("Erreur");
            e.printStackTrace();
        }
    }

    public void print(){
        for (int l = 0; l < hauteur; l++) {
            for (int c = 0; c < largeur; c++) {
                System.out.print(this.laby[l][c].toString());
            }
            System.out.println();  // Nouvelle ligne après chaque rangée
        }
    }

    public static void main(String[] args){
        Labyrinthe laby = new Labyrinthe("laby1.txt");
        laby.print();
    }

}