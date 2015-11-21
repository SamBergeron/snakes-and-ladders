package controleurs;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;

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
import presentation.vue.MenuConfiguration;
import presentation.vue.MenuPrincipal;
import presentation.vue.PlateauJeu;

public class FacadeJeu {
	private SerializerConfigPartie configLoader;
	private ConfigPartie config;
	private PlateauJeu plateauJeu;
	private Partie partie;
	private int indexJoueurCourant;
	private int nombreJoueur;
	private ActionListener actionListener;
	private ActionListener actionListener2;
	private ActionListener actionListener3;
	private ActionListener actionListener4;
	
	public void demarrerPartie(){ //on commence par creer les objets du domaine
		try {
			
			////////////////////////////// cette portion de code doit remplacer le mode console par le mode graphique
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
			
			partie = new Partie();
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
			nombreJoueur=nbJoueur;
			int i;
			for(i=0; i<nbJoueur; i++){
				String nomJoueur;
				int typeJoueur=0;
				Joueur nouveauJoueur; 
				Couleur couleurPion;
				if(i==0){
					couleurPion = Couleur.VERT; 
				}else if(i==1){
					couleurPion = Couleur.BLEU; 
				}else if(i==2){
					couleurPion = Couleur.JAUNE; 
				}else if(i==3){
					couleurPion = Couleur.NOIR; 
				}else if(i==4){
					couleurPion = Couleur.ROUGE; 
				}else{
					couleurPion = Couleur.BLANC; 
				}
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
				nouveauJoueur.setCaseCourante(1);
				partie.addJoueur(nouveauJoueur);
			}
			sc.close();
			plateauJeu = new PlateauJeu();
			plateauJeu.afficherEcran();
			indexJoueurCourant=0;	
			////////////////////////////// cette portion de code doit basculer en mode graphique plutot que console
			
			
			plateauJeu.afficherPlateauJeu(config.getLongueurPlateau()); //affiche le plateau de jeu du nombre de cases voulues
			plateauJeu.setAdresseSerpent(partie.getAdresseSerpents());
			plateauJeu.setAdresseEchelle(partie.getAdresseEchelles());
			plateauJeu.refreshSpecialPanel();
			
			//afficher les pions de tous les joueurs (faire une boucle for)
			for(i=0; i<nombreJoueur; i++){
				plateauJeu.afficherPion(partie.getCouleurPion(i), 1,1);
			}
			
			control(); 									//attache les actionslistener aux boutons de plateau jeu
			afficheUndoRedo(indexJoueurCourant);		//active ou desactive les undo/redo selon le type du joueur courant
			JOptionPane.showMessageDialog(null, "Tour du joueur "+partie.afficherNomJoueur(indexJoueurCourant));
			if(partie.estAI(indexJoueurCourant)){		//si le joueur courant est artificiel, il tire tout de suite au de (pas d'autres actions possble)
				while(gererCommande(3)){
					//voir control() - actionlistener3 pour plus d'information
				}
			}
			
		// Si la configuration n'existe pas on skip et on repr�sente le menu principal	
		} catch (IOException e) {
			System.out.println("Si la configuration n'existe pas veuiller en cr�er une!");
			MenuPrincipal mp = new MenuPrincipal();
			mp.afficherEcran();
		}
	}
	
	/*
	 * Permet de lancer la bonne commande selon le bouton appuye par l'utilisateur
	 * dans PlateauJeu
	 */
	public boolean gererCommande (int commande){
		if(commande==1){ //on fait un undo
			partie.undo(indexJoueurCourant);
			plateauJeu.afficherPion(partie.getCouleurPion(indexJoueurCourant), partie.getDeplacement(), partie.getAnciennePosition());
			return false;
		}
		else if(commande==2){ //on fait un redo
			partie.redo(indexJoueurCourant);
			plateauJeu.afficherPion(partie.getCouleurPion(indexJoueurCourant), partie.getDeplacement(), partie.getAnciennePosition());
			return false;
		}
		else if(commande==3){ //on lance le de et on deplace le joueur	
			boolean finPartie = partie.tirerDeEtDeplacer(indexJoueurCourant);
			System.out.println("nouvelle position : "+partie.getDeplacement()+" Ancienne position : "+partie.getAnciennePosition());
			plateauJeu.afficherPion(partie.getCouleurPion(indexJoueurCourant), partie.getDeplacement(), partie.getAnciennePosition());	//on affiche la nouvelle position du joueur
			if(finPartie==true){
				JOptionPane.showMessageDialog(null, "Felicitation, le gagnant est : "+partie.afficherNomJoueur(indexJoueurCourant));
				indexJoueurCourant=0;
				gererCommande(4);
				return false;
			}
			indexJoueurCourant++;
			if(indexJoueurCourant>=nombreJoueur){
				indexJoueurCourant=0;
			}	
			afficheUndoRedo(indexJoueurCourant);
			if(partie.estAI(indexJoueurCourant)&&finPartie!=true){
				JOptionPane.showMessageDialog(null, "Tour du joueur "+partie.afficherNomJoueur(indexJoueurCourant));
				return true;
			}
			JOptionPane.showMessageDialog(null, "Tour du joueur "+partie.afficherNomJoueur(indexJoueurCourant));
		}
		else if(commande==4){ //on quitte la partie et on revient au menu principal
			plateauJeu.getf_plateauJeu().dispose();
			MenuPrincipal mp = new MenuPrincipal();
			mp.afficherEcran();
			return false;
		}
		return false;
	}
	
	/*
	 * Implemente les actionlistener sur les boutons de PlateauJeu
	 * et leur associe l'action a effectuer en cas de besoin
	 */
	public void control(){
       actionListener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gererCommande(1);
				}
	    };                
	    plateauJeu.getb_undo().addActionListener(actionListener);   
	    
	    actionListener2 = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gererCommande(2);
				}
	    };                
	    plateauJeu.getb_redo().addActionListener(actionListener2);   
	    
	    actionListener3 = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					while(gererCommande(3)){
						//tant que c'est le tour d'un joueur de type AI
						//on continue a lancer automatiquement la commande 3 :
						//la seule action qu'un joueur artificiel peut faire
					}
				}		
	    };                
		plateauJeu.getb_tirerDe().addActionListener(actionListener3);  
		
		actionListener4 = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gererCommande(4);
				}
		};                
		plateauJeu.getb_quitter().addActionListener(actionListener4);  
	}			

	/*
	 * Active ou Desactive les boutons undo et redo sur le Plateau de jeu
	 * dependamment que le joueur courant soit AI ou Humain
	 */
	public void afficheUndoRedo(int indexJoueurCourant){
		if(partie.estAI(indexJoueurCourant)){ //si c'est un joueur artificiel je cache les boutons undo/redo
			plateauJeu.cacheUndoRedo(true);
		}
		else{ //sinon je les affiche
			plateauJeu.cacheUndoRedo(false);
		}
	}	
	
	public Point[] getAdresseSerpents(){
		return partie.getAdresseSerpents();
	}	
}
