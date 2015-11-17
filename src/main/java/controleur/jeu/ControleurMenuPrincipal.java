package controleur.jeu;

import presentation.vue.PlateauJeu;

public class ControleurMenuPrincipal{

	
	/*
	 * Gere la commande specifie dans commande
	 * 1 : ecran pour une partie
	 * 2 : ecran de configuration
	 */
	public void gererCommande (int commande){
		if(commande==1){ //on demarre une nouvelle partie
			//PlateauJeu plateauJeu = new PlateauJeu();
			//plateauJeu.afficherFenetre();
			FacadeJeu facadeJeu = new FacadeJeu();
			facadeJeu.demarrerPartie();

		}
		else if(commande==2){ //on veut configurer une partie
			//affiche l'ecran de configuration -- A faire
			System.out.println("Affichage de l'ecran de configuration - En cours d'implementation");
		}
		else{ //quitte le jeu
			System.out.println("Merci d'avoir joue a Serpents et Echelles!");
			System.exit(0);
		}
		
	}
}
