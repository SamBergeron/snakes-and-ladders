package controleur.jeu;

import java.util.Scanner;

import domaine.controleDeJeu.Partie;
import domaine.controleDeJeu.StrategieAlgorithme1;
import domaine.controleDeJeu.StrategieAlgorithme2;
import domaine.controleDeJeu.StrategieAlgorithme3;
import domaine.elements.AI;
import domaine.elements.De;
import domaine.elements.Humain;
import domaine.elements.Joueur;
import domaine.elements.Plateau;
import domaine.elements.statique.Couleur;
import domaine.elements.statique.NombreFaces;

public class ControleurMenuPrincipal {

	
	/*
	 * Gere la commande specifie dans commande
	 * 1 : ecran pour une partie
	 * 2 : ecran de configuration
	 */
	public void gererCommande (int commande){
		if(commande==1){ //on demarre une nouvelle partie
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
