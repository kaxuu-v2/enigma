Voici ma version du projet de IPO. 

Quelques remarques : 
- Collision avec le mur de gauche et droite ok
- Collision avec le mur de bas et haut A REVOIR
- Peut être plus optimiser le code de la méthode update() (voire en créer une nouvelle) 

Potentielle raison du problème de collision : 
- En fait, lorsqu'on dessine une boule sur le terrain, on le dessine en partant du coin supérieur gauche, c'est pourquoi les cas avec des cases en haut et a gauche fonctionnent, alors que lorsque c'est pour le cases de bas et droite, le jeu a l'air d'attendre que la boule entre complètement dans la case pour appliquer une collision (donc a priori, on compare ce coin supérieur avec le mur)

Quelques idées qu'on pourra coder une fois le problème des collisions réglé : 
- Case point de départ pour indiquer la position initiale de la boule en début de jeu
- Faire des plaques de pression (a priori une extension de la classe square) qui ouvrira une allée dès qu'on passe dessus (on pourra aussi ajouter du délai si on veut)
- Pièges : si on se prend 3 fois le piège (on pourra changer le seuil plus tard si souhaité)
- Trou : dès qu'on passe par dessus, on retourne au point de départ
- Case qui éléctrocute : si on va dessus on se fait électrocuter pendant quelques secondes (freeze + animation changements de couleurs pour représenter l'électrocutions)
- Case qui gèle : si on va dessus on peut plus bouger pendant quelques secondes
- Implanter un timer dans le jeu pour les mordus qui voudront faire des speedruns 

Pour tester le projet : 
Compiler les fichiers au terminal puis executer fichier Jeu ou bien sinon utiliser IntelliJ qui compile tout à la fois 

Historique : 
18/12 - J'ai terminé de coder la sortie et j'y ai ajouté une boite de dialogue qui donne le choix entre fermer le niveau et passer au suivant (voir code Sortie.java)

