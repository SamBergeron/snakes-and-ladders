package domaine.elements;

import java.util.ArrayList;
import java.util.List;

public class Plateau {
	private List<Case> cases;
	private int longueur;
	private int largeur;
	private int nbEchelle;
	private int nbSerpent;
	
	public Plateau(int longueur, int largeur, int serpents, int echelles) {
		this.longueur = longueur;
		this.largeur = largeur;
		this.nbEchelle = echelles;
		this.nbSerpent = serpents;
		
		cases = new ArrayList<Case>();
		int size = longueur*largeur;
		for(int i=0; i < size; i++){
			cases.add(new Case(i));
		}
		
		genererCasesSerpentsEchelles();
	}
	
	private void genererCasesSerpentsEchelles(){
		for(int i=0; i < nbSerpent; i++){
			int debut = (int)(Math.random() * (cases.size()-2)); // Pcq on veut ignorer le 1er et dernier element
			int fin = (int)(Math.random() * (debut-2)); 
			cases.set(debut, new CaseSerpent(debut, fin));
		}
		
		for(int i=0; i < nbEchelle; i++){
			int debut = (int)(Math.random() * (cases.size() - 2)); // Pcq on veut ignorer le 1er et dernier element
			int fin = debut + ((int)(Math.random() * (cases.size() - debut - 2))); 
			cases.set(debut, new CaseEchelle(debut, fin));
		}
	}

	public Case getCaseFinale(){
		return cases.get(cases.size() - 1);
	}

	public List<Case> getCases() {
		return cases;
	}

	public void setCases(List<Case> cases) {
		this.cases = cases;
	}

	public int getLongueur() {
		return longueur;
	}

	public void setLongueur(int longueur) {
		this.longueur = longueur;
	}

	public int getLargeur() {
		return largeur;
	}

	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}

	public int getNbEchelle() {
		return nbEchelle;
	}

	public void setNbEchelle(int nbEchelle) {
		this.nbEchelle = nbEchelle;
	}

	public int getNbSerpent() {
		return nbSerpent;
	}

	public void setNbSerpent(int nbSerpent) {
		this.nbSerpent = nbSerpent;
	}

	/*
	 * Interroge la case situee a l'index position sur le plateau de jeu
	 * On veut savoir si il y a une redirection (serpents ou echelle)
	 */
	public int getNouvellePosition(int position){
		//A implementer
		return 0;
	}
	
	@Override
	public String toString() {
		return "Plateau [cases=" + cases + ", longueur=" + longueur + ", largeur=" + largeur + "]";
	}
	
	
}
