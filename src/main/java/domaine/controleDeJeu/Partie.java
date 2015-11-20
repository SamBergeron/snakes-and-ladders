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
		//Probablement pas utile finalement (?)
	}
	
	/*
	 * determine si on a un joueur humain ou artificiel, pour savoir
	 * si le de doit se lancer automatiquement ou non
	 */
	public boolean estAI(int indexJoueur){
		return joueurs.get(indexJoueur).estAI();
	}
	
	public void undo(int indexJoueur){
		joueurs.get(indexJoueur).undo();
	}
	public void redo(int indexJoueur){
		joueurs.get(indexJoueur).redo();
	}
	public String afficherNomJoueur(int indexJoueur){
		return joueurs.get(indexJoueur).getNom();
	}
	public boolean tirerDeEtDeplacer(int indexJoueur){
		boolean unGagnant = false;
		int valeurDe = de.rouler();
		int deplacement = valeurDe + joueurs.get(indexJoueur).getCaseCourante();	
		int posFinale = plateau.getCaseFinale().getPosition();
		deplacement = algo.calculerVictoire(joueurs.get(indexJoueur).getCaseCourante(), deplacement, posFinale);
		deplacement = plateau.getCases().get(deplacement).getPosition();
		System.out.println("Tour du joueur " + joueurs.get(indexJoueur).getNom()); //pour debuguage seulement
		joueurs.get(indexJoueur).deplacer(deplacement);
		if(deplacement==posFinale){
			unGagnant = true;
		}
		return unGagnant;
	}
}
