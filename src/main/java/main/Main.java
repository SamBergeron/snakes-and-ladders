package main;

import domaine.elements.AI;
import domaine.elements.De;
import domaine.elements.Humain;
import domaine.elements.Joueur;
import domaine.elements.Plateau;
import domaine.elements.statique.*;
import presentation.vue.MenuPrincipal;
import domaine.controleDeJeu.*;

public class Main {

	public static void main(String[] args) {
		// Juste un test pour qu'on roule dequoi
		/*
		Plateau p = new Plateau(8, 8, 5, 5);
		De d = new De(NombreFaces.SIX);
		System.out.println(p);
		
		//Test creation de partie
		Partie partie = new Partie();
		partie.setDe(d);
		partie.setPlateau(p);
		partie.setAlgo(new StrategieAlgorithme3());
		
		Joueur j1 = new Humain("joueur1", Couleur.BLEU);
		Joueur j2 = new Humain("joueur2", Couleur.VERT);
		Joueur j3 = new AI("joueur3", Couleur.JAUNE);
		
		partie.addJoueur(j1);
		partie.addJoueur(j2);
		partie.addJoueur(j3);
		
		System.out.println(partie.jouerUnePartie());
		*/
		MenuPrincipal menuPrincipal = new MenuPrincipal();
		menuPrincipal.afficherEcran();
		
	}

}
