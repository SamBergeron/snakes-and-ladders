package domaine.elements;

import domaine.elements.statique.Couleur;

public class AI extends Joueur{
	

	public AI(String nom, Couleur couleurPion) {
		super(nom, couleurPion);
	}

	/*
	 * Prend en parametre la valeur du jet de de 
	 * Retourne la nouvelle position du joueur 
	 */
	@Override
	public void deplacer(int valeurDe){
		super.deplacer(valeurDe);
	}
	
	@Override
	public boolean gererCommande(){
		return super.gererCommande();
	}
}
