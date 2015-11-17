package controleurs;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import domaine.configuration.ConfigPartie;
import domaine.configuration.SerializerConfigPartie;
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
import presentation.vue.MenuPrincipal;

public class FacadeJeu {
	private SerializerConfigPartie configLoader;
	private ConfigPartie config;
	
	public void demarrerPartie(){ //on commence par creer les objets du domaine
		try {
			configLoader = new SerializerConfigPartie();
			config = configLoader.chargerConfig();
		
			Scanner sc = new Scanner(System.in);
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
			
			Plateau p = new Plateau(config.getLongueurPlateau(), config.getLargeurPlateau(), config.getNbSerpents(), config.getNbEchelles());
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
			sc.close();
			
		// Si la configuration n'existe pas on skip et on représente le menu principal	
		} catch (IOException e) {
			System.out.println("Si la configuration n'existe pas veuiller en créer une!");
			MenuPrincipal mp = new MenuPrincipal();
			mp.afficherEcran();
		}
	}
	
	/*
	 * Temporaire : utile seulement pour debuguage
	 * Attention pour un affichage correct : donner des noms de joueurs avec 2 caracteres (ex : j1 ou j2)
	 * Si deux joueurs ou plus sont sur la meme case : un seul apparaitra en console
	 */
	public void majPlateau(int longueur, int largeur, List<Joueur>joueurs){	//affiche le plateau en console
		int i,j,k;
		k = (longueur*largeur)-1; //nombre de case totale sur le plateau
		boolean decroissant = true;
		for(i=longueur; i>0; i--){
			System.out.print(" ");
			for(j=largeur; j>0; j--){
				System.out.print("---");
			}
			System.out.println("");
			System.out.print("|");
			if(decroissant==true){
				for(j=largeur; j>0; j--){
					boolean trouve = false;
					for(Joueur p : joueurs){
						if(p.getCaseCourante()==k){
							System.out.print(p.getNom().toUpperCase()+"|");
							trouve = true;
							break;
						}
					}
					if(k<10&&trouve==false){
						System.out.print(k+" |");
					}else if(trouve==false){
						System.out.print(k+"|");
					}
					k--;
				}
			}else{
				int temp = k-largeur+1;
				for(j=largeur; j>0; j--){
					boolean trouve = false;
					for(Joueur p : joueurs){
						if(p.getCaseCourante()==temp){
							System.out.print(p.getNom().toUpperCase()+"|");
							trouve = true;
							break;
						}
					}
					if(temp<10 && trouve==false){
						System.out.print(temp+" |");
					}else if(trouve==false){
						System.out.print(temp+"|");

					}
					temp++;
					k--;
				}				
			}
			if(decroissant==true){
				decroissant = false;
			}else{
				decroissant = true;
			}
			System.out.println("");
		}
		System.out.println("");
	}
	
}
