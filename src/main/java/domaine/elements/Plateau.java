package domaine.elements;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Plateau {
	private List<Case> cases;
	private int longueur;
	private int largeur;
	private int nbEchelle;
	private int nbSerpent;
	
	private Point adresseSerpents[];
	private Point adresseEchelles[];
	
	public Plateau(int longueur, int largeur, int serpents, int echelles) {
		this.longueur = longueur;
		this.largeur = largeur;
		this.nbEchelle = echelles;
		this.nbSerpent = serpents;
		
		cases = new ArrayList<Case>();
		int size = longueur*largeur;
		for(int i=0; i < size; i++){
			cases.add(new Case(i+1));
		}
		
		genererCasesSerpentsEchelles();
	}
	
	private void genererCasesSerpentsEchelles(){
		adresseSerpents = new Point[nbSerpent];
		adresseEchelles = new Point[nbEchelle];
		
		/* On genere pseudo-aleatoirement les serpents*/
		for(int i=0; i < nbSerpent; i++){
			int debut = 0;
			int fin = 0;
			while((debut-fin)<10){ //car on veut que le serpent fasse descendre d'au moins un niveau complet
				debut = (int)(Math.random() * (cases.size()-2)); // Pcq on veut ignorer le 1er et dernier element
				fin = 1 + (int)(Math.random() * (debut-2)); 
				if(verifierDisponibiliteCase(debut,fin,adresseSerpents)){ //si une des cases choisies est deja utilisee : on recommence
					debut = 0;
					fin = 0;
				}
			}
			cases.set(debut, new CaseSerpent(debut, cases.get(fin)));
			Point p = new Point(debut+1,fin+1);
			adresseSerpents[i]=p;
		}
		
		/* On genere pseudo-aleatoirement les echelles */
		for(int i=0; i < nbEchelle; i++){
			int debut = 0;
			int fin = 0;
			while((fin-debut)<10 || debut==0){ //car on veut que l'echelle fasse monter d'au moins un niveau complet
				debut = (int)(Math.random() * (cases.size() - 2)); // Pcq on veut ignorer le 1er et dernier element
				fin = debut + ((int)(Math.random() * (cases.size() - debut - 2))); 
				if(verifierDisponibiliteCase(debut,fin,adresseSerpents)||verifierDisponibiliteCase(debut,fin,adresseEchelles)){ //si une des cases choisies est deja utilisee : on recommence
					debut = 0;
					fin = 0;
				}
			}
			cases.set(debut, new CaseEchelle(debut, cases.get(fin)));
			Point p = new Point(debut+1,fin+1);
			adresseEchelles[i]=p;
		}
	}

	public Point[] getAdresseEchelle(){
		return adresseEchelles;
	}
	
	public Point[] getAdresseSerpent(){
		return adresseSerpents;
	}
	
	public Case getCaseFinale(){
		return cases.get(cases.size()-1);
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
	
	/*
	 * Verifie qu'une des choisi pour devenir un serpent ou une echelle
	 * n'est pas deja utilisee dans le tableau specifie en parametre
	 * Retourne vrai si la case est deja utilise
	 * Retourne faux si elle est libre
	 */
	public boolean verifierDisponibiliteCase(int indexCase, int indexCase2, Point[] tableau){
		for(Point p : tableau){
			if(p!=null && ((p.x-1)==indexCase || (p.x-1)==indexCase2 || (p.y-1)==indexCase || (p.y-1)==indexCase2)){
				return true;
			}
		}	
		return false;
	}
	
}
