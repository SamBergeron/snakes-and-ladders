package domaine.elements;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class SerpentEchelleFactory implements AbstractCaseFactory {
	
	private List<Point> adressesSerpents;
	private List<Point> adressesEchelles;
	private List<Case> registeredCases;
	private int nbCases;
	
	public SerpentEchelleFactory(int nbCases, int serpents, int echelles){
		this.nbCases = nbCases;
		adressesEchelles = new ArrayList<Point>();
		adressesSerpents = new ArrayList<Point>();
		registeredCases = new ArrayList<Case>();
	}
	
	public Case genererCaseStandard(int pos){
		Case c = new Case(pos);
		registeredCases.add(c);
		return c;
	}
	
	public CaseSerpent genererCaseDescente(){
		int debut = 0;
		int fin = 0;
		while((debut-fin)<10){ //car on veut que le serpent fasse descendre d'au moins un niveau complet
			debut = (int)(Math.random() * (nbCases-2)); // Pcq on veut ignorer le 1er et dernier element
			fin = 1 + (int)(Math.random() * (debut-2)); 
			if(verifierDisponibiliteCase(debut,fin,adressesSerpents)){ //si une des cases choisies est deja utilisee : on recommence
				debut = 0;
				fin = 0;
			}
		}
		//Creer le serpent, le sauver dans notre liste et puis le retourner au plateau
		CaseSerpent snake = new CaseSerpent(debut, registeredCases.get(fin));
		registeredCases.set(debut, snake);
		adressesSerpents.add(new Point(debut+1,fin+1));
		return snake;
	}
	
	public CaseEchelle genererCaseMontee(){
		int debut = 0;
		int fin = 0;
		while((fin-debut)<10 || debut==0){ //car on veut que l'echelle fasse monter d'au moins un niveau complet
			debut = (int)(Math.random() * (nbCases - 2)); // Pcq on veut ignorer le 1er et dernier element
			fin = debut + ((int)(Math.random() * (nbCases - debut - 2))); 
			if(verifierDisponibiliteCase(debut,fin,adressesSerpents)||verifierDisponibiliteCase(debut,fin,adressesEchelles)){ //si une des cases choisies est deja utilisee : on recommence
				debut = 0;
				fin = 0;
			}
		}
		//Creer le serpent, le sauver dans notre liste et puis le retourner au plateau
		CaseEchelle echelle = new CaseEchelle(debut, registeredCases.get(fin));
		adressesEchelles.add(new Point(debut+1,fin+1));
		registeredCases.set(debut, echelle);
		
		return echelle;
	}
	
	/*
	 * Verifie qu'une des choisi pour devenir un serpent ou une echelle
	 * n'est pas deja utilisee dans le tableau specifie en parametre
	 * Retourne vrai si la case est deja utilise
	 * Retourne faux si elle est libre
	 */
	private boolean verifierDisponibiliteCase(int indexCase, int indexCase2, List<Point> tableau){
		for(Point p : tableau){
			if(((p.x-1)==indexCase || (p.x-1)==indexCase2 
					|| (p.y-1)==indexCase || (p.y-1)==indexCase2)){
				return true;
			}
		}	
		return false;
	}

	public Point[] getAdressesDescentes() {
		Point[] liste = new Point[adressesSerpents.size()];
		return adressesSerpents.toArray(liste);
	}

	public Point[] getAdressesMontees() {
		return adressesEchelles.toArray(new Point[adressesEchelles.size()]);
	}
	
	
	
}
