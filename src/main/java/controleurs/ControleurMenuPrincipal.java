package controleurs;

import presentation.vue.MenuConfiguration;
import presentation.vue.MenuConfigurationJoueurs;

public class ControleurMenuPrincipal {
	private MenuConfiguration mConfig;
	private MenuConfigurationJoueurs menuJoueur;
	/*
	 * Gere la commande specifie dans commande
	 * 1 : ecran pour une partie
	 * 2 : ecran de configuration
	 */
	public void gererCommande (int commande){
		if(commande==1){ //on demarre une nouvelle partie
			menuJoueur = new MenuConfigurationJoueurs();
			menuJoueur.afficherEcran();
		}
		else if(commande==2){ //on veut configurer une partie
			mConfig = new MenuConfiguration();
			mConfig.afficherEcran();
		}
		else{ //quitte le jeu
			System.out.println("Merci d'avoir joue a Serpents et Echelles!");
			System.exit(0);
		}
		
	}
}
