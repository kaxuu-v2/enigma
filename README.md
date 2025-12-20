Voici ma version du projet de IPO. 

Quelques remarques : 
- Collision avec le mur de gauche et droite -> ok
- Collision avec le mur de bas et haut A REVOIR (Note 18/12 : on met ce problème de côté pour le moment, on y reviendra plus tard)
- Le jeu peut facilement se faire "bug abuse" en cas de freeze, comme elle met l'état a false alors cliquer suffit amplement a la remettre a true et donc reprendre le contrôle du jeu. Il faudrait ajouter un autre booléen destiné au freeze 

Potentielle raison du problème de collision : 
- En fait, lorsqu'on dessine une boule sur le terrain, on le dessine en partant du coin supérieur gauche, c'est pourquoi les cas avec des cases en haut et a gauche fonctionnent, alors que lorsque c'est pour le cases de bas et droite, le jeu a l'air d'attendre que la boule entre complètement dans la case pour appliquer une collision (donc a priori, on compare ce coin supérieur avec le mur)

Quelques idées qu'on pourra coder une fois le problème des collisions réglé : 
- Case point de départ pour indiquer la position initiale de la boule en début de jeu -> ok 
- Faire des plaques de pression (a priori une extension de la classe square) qui ouvrira une allée dès qu'on passe dessus (on pourra aussi ajouter du délai si on veut)
- Pièges : si on se prend 3 fois le piège (on pourra changer le seuil plus tard si souhaité) -> ok
- Trou : dès qu'on passe par dessus, on retourne au point de départ -> ok
- Case qui éléctrocute : si on va dessus on se fait électrocuter pendant quelques secondes (freeze + animation changements de couleurs pour représenter l'électrocutions) -> ok
- Case qui gèle : si on va dessus on peut plus bouger pendant quelques secondes -> on pourra directement implanter ca dans la case JeuGraphique en verifiant instanceof Freeze et ensuite mettre l'attribut etat a false puis attendre 3s = 3000ms puis la remettre a true -> ok
- Implanter un timer dans le jeu pour les speedruns
- Des téléporteurs qui fonctionneront par pairs et qui comme le nom l'indique, téléporteront la bille (comme dans le jeu Portal) -> ~~a priori, une telle classe aura comme attributs deux cases qui correpondent a la source et a la destination~~ -> ok

Pour tester le projet : 
Compiler les fichiers au terminal puis executer fichier Jeu ou bien sinon utiliser IntelliJ qui compile tout à la fois 

Historique : 
- 18/12 - J'ai terminé de coder la sortie et j'y ai ajouté une boite de dialogue qui donne le choix entre fermer le niveau et passer au suivant (voir code Sortie.java) + j'ai codé un deuxieme niveau pour le jeu
- 19/12 - J'ai terminé de coder les téléporteurs et j'ai fait les ajustements dans les classes JeuGraphique et Labyrinthe. Fonctionnement des cases téléporteurs : dans le fichier texte, on indique le numéro du i-ème téléporteur par pairs (ce qui veut dire que dans un fichier texte, si on a un téléporteur alors il devrait y a voir deux fois "1" (voir laby4.txt pour plus de détails)) qui seront affichés en orange et en bleu (en référence au jeu Portal) et la classe possède un attribut pour stocker la derniere fois que le portail a été pris dans le but d'ajouter du délai et éviter des problèmes de téléportation infinie. + J'ai rajouté la classe pour la case qui gèle (pour plus de détails voir la classe JeuGraphique méthode update()) + J'ai codé la classe pour le trou + J'ai ajouté la classe pour les case qui électrocutent
- 20/12 - J'ai terminé de coder les pièges. Fonctionnement : on a mis comme attributs des "pv" avec un seuil de 3, lorsque le jeu detecte qu'on entre dans un piege, il freeze le jeu, met la boule en rouge temporairement et ensuite on a 1seconde pour sortir du piege (grâce au timer) et si on se prend plus de 3 fois le piège, on retourne au point initial

