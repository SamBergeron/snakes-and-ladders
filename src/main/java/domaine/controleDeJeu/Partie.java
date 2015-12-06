package domaine.controleDeJeu;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

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
	
	public boolean undo(int indexJoueur){
		anciennePosition = joueurs.get(indexJoueur).getCaseCourante(); //utile pour l'affichage du pion a l'ecran
		boolean resultatUndo = joueurs.get(indexJoueur).undo();
		deplacement = joueurs.get(indexJoueur).getCaseCourante(); 		//utile pour l'affichage du pion a l'ecran
		return resultatUndo;
	}
	
	public boolean redo(int indexJoueur){
		anciennePosition = joueurs.get(indexJoueur).getCaseCourante(); //utile pour l'affichage du pion a l'ecran
		boolean resultatRedo = joueurs.get(indexJoueur).redo();
		deplacement = joueurs.get(indexJoueur).getCaseCourante(); 		//utile pour l'affichage du pion a l'ecran
		return resultatRedo;
	}
	
	public String afficherNomJoueur(int indexJoueur){
		return joueurs.get(indexJoueur).getNom();
	}
	
	public int tirerAuDe(){
		return de.rouler();
	}
	
	// Cette methode retourne maintenant la distance de deplacement du joueur et le deplace
	// en comparant avec le lancer du de on peut determiner s'il est tombe sur une case serpent/echelle
	// cette verification pour l'affichage se fera dans FacadeJeu 
	public int deplacerJoueur(int indexJoueur, int resultatDe){
		
		anciennePosition = joueurs.get(indexJoueur).getCaseCourante();
		deplacement = resultatDe + anciennePosition;	
		int posFinale = plateau.getCaseFinale().getPosition();
		deplacement = algo.calculerVictoire(anciennePosition, deplacement, posFinale);
		deplacement = plateau.getCases().get(deplacement-1).getPosition();

		joueurs.get(indexJoueur).deplacer(deplacement);
		
		return deplacement-anciennePosition;
	}
	
	// Cette methode ne fait que confirmer la victoire, 
	// le deplacement sur la case finale se fait avec deplacerJoueur ci-haut ^
	public boolean verifierVictoire(int indexJoueur){
		int posFinale = plateau.getCaseFinale().getPosition();
		int posJ = joueurs.get(indexJoueur).getCaseCourante();
		return posFinale == posJ;
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
	
	public Point[] getAdresseEchelles(){
		return plateau.getAdresseEchelle();
	}
	
	public Couleur getCouleurPion(int indexJoueur){
		return joueurs.get(indexJoueur).getCouleurPion();
		
	}
	
	public List<Joueur> getJoueurs(){
		return joueurs;
	}
}
