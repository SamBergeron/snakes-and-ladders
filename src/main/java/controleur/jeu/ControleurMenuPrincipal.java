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
			Scanner sc = new Scanner(System.in);
			/*on doit recuperer les donnees XML pour :
			 * - nombres de cases du plateau de jeu
			 * - nombre de serpents
			 * - nombre d'echelles
			 * pour le moment, ces donnees sont attribuees automatiquement 
			 */
			int longueur = 8;
			int largeur = 8;
			int nbEchelles = 5;
			int nbSerpents = 5;
			
			System.out.println("Rentrez le type de de (6,8 ou 20 faces) : ");
			int nbFaceDe = sc.nextInt();
			while(nbFaceDe!=6 && nbFaceDe!=8 && nbFaceDe!=20){
				System.out.println("Type de de incorrect");
				System.out.println("Rentrez le type de de (6,8 ou 20 faces) : ");
				nbFaceDe = sc.nextInt();
			}
			
			System.out.println("Rentrez le type d'algorithme de victoire (1,2 ou 3) : ");
			int typeAlgo = sc.nextInt();
			while(typeAlgo!=1 && typeAlgo!=2 && typeAlgo!=3){
				System.out.println("Type d'algorithme incorrect");
				System.out.println("Rentrez le type d'algorithme de victoire (1,2 ou 3) : ");
				typeAlgo = sc.nextInt();
			}
			
			Plateau p = new Plateau(longueur, largeur, nbEchelles, nbSerpents);
			De d;
			if(nbFaceDe==6){
				d = new De(NombreFaces.SIX);
			}else if(nbFaceDe==8){
				d = new De(NombreFaces.HUIT);
			}else{
				d = new De(NombreFaces.VINGT);
			}
			
			Partie partie = new Partie();
			partie.setDe(d);
			partie.setPlateau(p);
			
			if(typeAlgo==1){
				partie.setAlgo(new StrategieAlgorithme1());
			}else if(typeAlgo==2){
				partie.setAlgo(new StrategieAlgorithme2());
			}else{
				partie.setAlgo(new StrategieAlgorithme3());
			}			
			
			System.out.println("Nombre de joueurs : ");
			int nbJoueur = sc.nextInt();
			while(nbJoueur<1 || nbJoueur>6){
				System.out.println("Nombre de joueur incorrect");
				System.out.println("Nombre de joueurs : ");
				nbJoueur = sc.nextInt();
			}
			int i;
			for(i=0; i<nbJoueur; i++){
				String nomJoueur;
				int typeJoueur=0;
				Joueur nouveauJoueur; 
				Couleur couleurPion = Couleur.BLEU; //pour le moment tous les pions sont bleu, on changera cela plus tard
				System.out.println("Nom du joueur : ");
				nomJoueur = sc.next();
				while(typeJoueur!=1 && typeJoueur!=2){
					System.out.println("Humain (1) ou AI(2) ? : ");
					typeJoueur = sc.nextInt();
				}
				if(typeJoueur==1){
					nouveauJoueur = new Humain(nomJoueur,couleurPion);
				}else{
					nouveauJoueur = new AI(nomJoueur,couleurPion);
				}
				partie.addJoueur(nouveauJoueur);
			}
			
			System.out.println(partie.jouerUnePartie());
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
