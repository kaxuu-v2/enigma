import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;


public class Labyrinthe {

    private int hauteur, largeur;
    private Square[][] laby; //le labyrinthe est défini par un tableau 2D de squares (cases)
    private int initX, initY;

    public int getInitX(){ return this.initX;}
    public int getInitY(){ return this.initY;}

    public int getHauteur(){
        return this.hauteur;
    }

    public int getLargeur(){
        return this.largeur;
    }

    public Square getSquare(int x, int y){
        return this.laby[y][x];
    }

    //Inspiré du TP7
    public Labyrinthe(String file) {
        try {
            //ouverture du fichier
            Scanner sc = new Scanner(new FileInputStream(file));

            //lecture des dimensions
            this.hauteur = sc.nextInt();
            this.largeur = sc.nextInt();
            sc.nextLine();

            //initialisation du labyrinthe/tableau
            Teleport[] memo = new Teleport[10]; //on va stocker les teleporteurs dans un tableau
            this.laby = new Square[hauteur][largeur];
            for (int l=0; l<hauteur; l++) {
                String line = sc.nextLine();
                for (int c=0; c<largeur; c++) {
                    Square s;
                    Character ch = line.charAt(c);
                    if (Character.isDigit(ch)) {
                        int id = Character.getNumericValue(ch); //convertit '1' en int 1
                        Teleport t = new Teleport(l, c);

                        if (memo[id] == null) {
                            //pour le premier qu'on trouve on l'associe a la couleur bleu
                            memo[id] = t;
                            t.setColor(Color.BLUE);
                        } else {
                            //et donc le deuxieme de couleur orange
                            //on relit ensuite les deux teleporteurs
                            t.setDestination(memo[id]);
                            memo[id].setDestination(t);

                            t.setColor(Color.ORANGE);
                        }
                        s = t;
                    } else {
                        switch (ch) {
                            case '#':
                                s = new Mur(l, c);
                                break;
                            case ' ':
                                s = new Ordinaire(l, c);
                                break;
                            case 'O':
                                s = new Sortie(l, c);
                                break;
                            case 'D':
                                this.initX = c;
                                this.initY = l;
                                s = new Ordinaire(l, c);
                                break;
                            case 'T':
                                s = new Teleport(l, c);
                                break;
                            case 'F' :
                                s = new Freeze(l,c);
                                break;
                            case '!' :
                                s = new Hole(l,c);
                                break;
                            case 'E' :
                                s = new Electro(l,c);
                                break;
                            //on pourra ajouter d'autres cases pour les obstacles ou autres types de cases

                            default:
                                s = new Ordinaire(l, c);
                                break;
                        }
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