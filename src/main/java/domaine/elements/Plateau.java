package domaine.elements;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Plateau {
	private List<Case> cases;
	private CasesFactory casesFactory;
	
	public Plateau(int longueur, int largeur, int serpents, int echelles) {
		int size = longueur*largeur;
		casesFactory = new CasesFactory(size, serpents, echelles);
		genererCases(size, serpents, echelles);
	}
	
	private void genererCases(int nbCases, int serpents, int echelles){
		
		// On genere toutes les cases du tableau "normales"
		cases = new ArrayList<Case>();
		for(int i=0; i < nbCases; i++){
			cases.add(casesFactory.genererCaseStandard(i+1));
		}
		
		/* On genere pseudo-aleatoirement les serpents*/
		for(int i=0; i < serpents; i++){
			Case serpent = casesFactory.genererCaseSerpent();
			// On veut la position de départ sans la redirection
			cases.set(serpent.position, serpent);
		}
		
		for(int i=0; i < echelles; i++){
			Case echelle = casesFactory.genererCaseEhelle();
			cases.set(echelle.position, echelle);
		}
	};
	
	public Point[] getAdresseEchelle(){
		return casesFactory.getAdressesEchelles();
	}
	
	public Point[] getAdresseSerpent(){
		return casesFactory.getAdressesSerpents();
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
		return "Plateau [cases=" + cases + "]";
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
