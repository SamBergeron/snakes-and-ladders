package domaine.elements;

import domaine.elements.statique.Couleur;

public class AI extends Joueur{
	

	public AI(String nom, Couleur couleurPion, int caseCourante) {
		super(nom, couleurPion, caseCourante);
	}

	/*
	 * Prend en parametre la valeur du jet de de 
	 * Retourne la nouvelle position du joueur 
	 */
	public int jouerUnTour(int valeurDe){
		return super.jouerUnTour(valeurDe);
	}
}
