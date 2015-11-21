package domaine.controleDeJeu;

import java.awt.Color;
import java.awt.Point;
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
	private int deplacement;
	private int anciennePosition = 1;
	
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
		anciennePosition = joueurs.get(indexJoueur).getCaseCourante(); //utile pour l'affichage du pion a l'ecran
		joueurs.get(indexJoueur).undo();
		deplacement = joueurs.get(indexJoueur).getCaseCourante(); 		//utile pour l'affichage du pion a l'ecran
	}
	public void redo(int indexJoueur){
		anciennePosition = joueurs.get(indexJoueur).getCaseCourante(); //utile pour l'affichage du pion a l'ecran
		joueurs.get(indexJoueur).redo();
		deplacement = joueurs.get(indexJoueur).getCaseCourante(); 		//utile pour l'affichage du pion a l'ecran
	}
	public String afficherNomJoueur(int indexJoueur){
		return joueurs.get(indexJoueur).getNom();
	}
	public boolean tirerDeEtDeplacer(int indexJoueur){
		boolean unGagnant = false;
		int valeurDe = de.rouler();
		anciennePosition = joueurs.get(indexJoueur).getCaseCourante();
		deplacement = valeurDe + anciennePosition;	
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
	
	public int getDeplacement(){
		return deplacement;
	}
	
	public int getAnciennePosition(){
		return anciennePosition;
	}
	
	public Point[] getAdresseSerpents(){
		return plateau.getAdresseSerpent();
	}
	
	public Color getCouleurPion(int indexJoueur){
		Couleur couleur = joueurs.get(indexJoueur).getCouleurPion();
		switch(couleur){
			case ROUGE : return Color.RED;
			case VERT : return Color.GREEN;
			case BLEU : return Color.BLUE;
			case JAUNE : return Color.YELLOW;
			case BLANC : return Color.WHITE;
			default : return Color.BLACK; 
		}
	}
}
