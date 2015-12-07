### Jeu de Serpents et Échelles
#### Cours INF5153
____________________________________

Instructions d'installation
- Faire un maven Clean Install (mvn clean install)
- Naviguer dans le répertoire /target
- Exécuter la commande "java -jar NOM_DU_JAR.jar"

# Rapport
___

# INF5153-20
# Génie logiciel : conception
___
## Compte rendu d'implémentation pour l'application stand-alone Serpents et Échelles
___
##### Présenté à M. BERGER, Jacques
- Nom : __Laurier Paquette-Bérubé__, Code Permanent : __PAQL20099109__
- Nom : __Samuel Bergeron__, Code Permanent : __BERS24129004__
- Nom : __Benjamin Rosa__, Code Permanent : __ROSB31128601__
___

## Table des matières
### 1. [Différences entre la conception 	et l'implémentation](#Différences)
- Modification 	de calculerVictoire()
- Correction 	erreur DSS jouerUnePartie
- Abandon du 	système d'interface pour un dé
- Ajout des 	fonctionnalités Undo et Redo dans la classe Joueur
- Changement des 	relations entre FacadeJeu et Partie
- Affichage 	des serpents et des échelles
	
### 2. [Choix des patterns GoF](#Gof)
- Strategy pour 	l'algorithme de victoire
- Facade pour la 	communication entre PlateauJeu et Partie
- Factory pour les 	cases
___
## Différences entre la conception et l'implémentation
### Modification de calculerVictoire()

Nous avons dû revoir la signature de la fonction calculerVictoire proposée par l'interface StratégieVictoire. Initialement il était prévu de ne faire passer en paramètre de la fonction que la valeur de la nouvelle position du joueur après le jet de dé. Nous nous sommes vite rendu compte de l'erreur de conception faite, et nous l'avons corrigé en rajoutant deux nouveaux paramètres, afin de permettre à la fonction de remplir correctement son rôle.

Ces paramètres sont :
- la valeur de la 				dernière case du plateau
- la valeur de la 				position initiale du joueur (utile pour l'algorithme 2 et 3)
			
Dans le diagramme de séquence système de jouerUnTour, en plus de corriger la signature de la fonction calculerVictoire(), il faudra également rajouter une étape juste avant la 2ème: celle ou Partie demande à Plateau la valeur de la dernière case du jeu.


### Correction erreur DSS jouerUnePartie

Dans le diagramme de séquence système jouerUnePartie, une erreur d'inattention nous a fais omettre d'intégrer le jet de dé à la boucle for qui itère à travers tous les joueurs jusqu'à ce qu'un gagnant soit trouvé.

Cette erreur a été corrigé lors de l'implémentation du projet. Partie lance toujours un dé avant de faire jouer un tour à un joueur.

### Abandon du système d'interface pour un dé

Au cours du développement, il nous est finalement apparu comme plus intuitif de créer une classe dé qui prendrait en paramètres un nombre de face, plutôt qu'une interface de dé comme c'était initialement prévu dans la conception.

Puisque dans tous les cas, Partie doit être amené à connaître le nombre de face qu'un dé doit avoir pour pouvoir créer le bon objet. Autant faire passer ce paramètre directement dans le constructeur d'une classe dé, plutôt que de choisir quelle classe dé instancier.

### Ajout des fonctionnalités Undo et Redo dans la classe Joueur
	
FacadeJeu demande à Partie d'exécuter une action (undo,redo,tirer le dé) sur un Joueur contenu dans le tableau de type Joueur, selon un index particulier (indexJoueurCourant). Dans le cas de undo et redo, il y a la possibilité que le joueur en question soit artificiel, même si à priori ce cas de figure ne devrait jamais se produire puisque les boutons undo et redo sont désactivés si le joueur qui prend le tour n'est pas un humain. Dans le doute et pour s'assurer de sécuriser au mieux le programme, cette fonction a été implémenté dans la classe Joueur. Dans le cas d'un joueur artificiel, l'appel à cette fonction ne produira aucune action.

### Changement des relations entre FacadeJeu et Partie
	
Il a fallu profondément changer les relations entre ces deux entités. Ce qui était initialement prévu fonctionnait bien pour un système implémenté en mode console mais a ensuite perdu tout son sens lorsque l'on a commencé à développer l'application en mode graphique.

La boucle for initialement prévu dans l'objet Partie n'est pas du tout adaptée à ce genre de situation. À la place, c'est FacadeJeu qui garde en mémoire l'index du joueur courant sur le tableau de Joueur contenu dans Partie. Cet index est incrémenté à chaque fois que le bouton Tirer au dé est cliqué.

En procédant ainsi, il a fallu de facto créer plusieurs accesseurs dans Partie, pour que FacadeJeu puisse récupérer l'information et procéder aux bonnes opérations dessus. Parmi ces accesseurs on peut citer : partie.getDeplacement, partie.getAnciennePosition, partie.AfficherJoueur... Toutes ces fonctions offertes par Partie n'était pas prévu initialement puisque c'est Partie qui gérait la boucle sur les Joueurs et avait donc toute ces informations à disposition.

Cette nouvelle façon de faire a rendu obsolète la fonction jouerUnePartie qui a donc été supprimée. À la place, le déroulement d'une partie est géré dans FacadeJeu et dépend du type de joueur dont c'est le tour et si c'est un humain, de l'action effectué par lui sur les boutons de PlateauJeu.

### Affichage des serpents et des échelles
Un autre problème est apparu lors de la génération du plateau de jeu. Pour dessiner les Serpents et les Échelles PlateauJeu doit connaître les cases choisies par la fonction générant ces cases spéciales, pour pouvoir ensuite dessiner les Serpents et Échelles. \

La fonction générant les cases spéciales se trouve dans Plateau. Nous avons dû rajouter deux attributs privées dans cette classe : deux tableaux de Point. La partie x du point correspond au numéro de la case de départ et la partie y au numéro de la case d'arrivée du Serpent ou de l'Échelle.

FacadeJeu peut récupérer l'information contenu dans ces tableaux grâce aux get correspondant dans Plateau. Il peut ensuite faire passer ces tableaux à PlateauJeu pour que ce dernier puisse dessiner les Serpents et Échelles à l'écran. 

---
## Choix des patterns GoF
### Strategy pour l'algorithme de victoire

Le patron de conception GoF Strategy est idéal pour implémenter la contrainte qui demande à avoir 3 façons différentes de terminer le jeu. Les objets du domaine manipuleront seulement une interface StrategieVictoire via sa fonction calculerVictoire(), sans se soucier de la manière dont un joueur est déclaré gagnant.

### Facade pour FacadeJeu
Le patron de conception Facade est idéal pour faire l’implémentatino des fonctionnalité de jeu. À la place de devoir utiliser plusieurs classes de gestion de partie, la facade jeu englobe tous ces fonctionnalité est les gère elle même. Par ce fait, les controleurs ont uniquement besoin d’instancier la classe FacadeJeu pour avoir accès à tous les fonctionnalité des interfaces d’une partie. Voila le diagramme de package qui illustre ce patron de conception. 

	mettre le diagramme ici

