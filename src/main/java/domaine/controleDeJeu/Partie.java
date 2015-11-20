package domaine.controleDeJeu;

import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.SliderUI;

import controleurs.FacadeJeu;
import domaine.elements.De;
import domaine.elements.Joueur;
import domaine.elements.Plateau;
import domaine.elements.statique.Couleur;

public class Partie {
	private List<Joueur> joueurs;
	private De de;
	private Plateau plateau;
	private StrategieVictoire algo;
	
	public Partie() {
		this.joueurs = new ArrayList<Joueur>();
	}
	
	public De getDe() {
		return de;
	}

	public void setDe(De de) {
		this.de = de;
	}

	public Plateau getPlateau() {
		return plateau;
	}

	public void setPlateau(Plateau plateau) {
		this.plateau = plateau;
	}

	public StrategieVictoire getAlgo() {
		return algo;
	}

	public void setAlgo(StrategieVictoire algo) {
		this.algo = algo;
	}

	public void addJoueur(Joueur j){
		joueurs.add(j);
	}
	
	/*
	 * Permet de verifier si la nouvelle position sur laquelle se trouve le joueur
	 * est une case de type serpent ou echelle en renvoyant la valeur de la redirection
	 * le cas echeant. Si c'est une case normale, resultat=position;
	 */
	public int getNouvellePosition(int position){
		int resultat;
		resultat = this.plateau.getNouvellePosition(position);
		return resultat;
	}
	
	/*
	 * Dans le cas d'un undo/redo : avise le controleur qu'il faut deplacer le pion
	 * du joueur graphiquement
	 */
	public void deplacementPion(int position, Couleur couleurPion){
		//A faire
	}
	
	/*
	 * Demarre une nouvelle partie
	 */
	public Joueur jouerUnePartie(){
		FacadeJeu facadeJeu = new FacadeJeu(); //controleur qui communique avec Partie
		
		Joueur gagnant = null;
		while(gagnant == null){
			for(Joueur j : joueurs){
				while(j.gererCommande()){
					//on continue de gere la commande tant que le joueur
					//humain decide de faire des undo redo et on met à jour le plateau de jeu
					//facadeJeu.majPlateau(plateau.getLongueur(), plateau.getLargeur(), joueurs);
				}
				
				int valeurDe = de.rouler();
				int deplacement = valeurDe + j.getCaseCourante();
				
				// Check victoire avec l'algo
				int posFinale = plateau.getCaseFinale().getPosition();
				deplacement = algo.calculerVictoire(j.getCaseCourante(), deplacement, posFinale);
				deplacement = plateau.getCases().get(deplacement).getPosition();
				
				System.out.println("Tour du joueur " + j.getNom());
				j.deplacer(deplacement);
				
				//facadeJeu.majPlateau(plateau.getLongueur(), plateau.getLargeur(), joueurs);
				
				if(deplacement == posFinale){
					gagnant = j;
					break;
				}
			}
		}
		return gagnant;
		
	}

}
