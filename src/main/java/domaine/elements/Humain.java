package domaine.elements;

import domaine.elements.statique.Couleur;
import java.util.LinkedList;
import java.util.ListIterator;

public class Humain extends Joueur{
	
	private LinkedList histoCase;
	private int positionListe;
	
	
	public Humain(String nom, Couleur couleurPion, int caseCourante) {
		super(nom, couleurPion, caseCourante);
		this.histoCase = new LinkedList();
		this.histoCase.add(0);					//on ajoute la premiere position du joueur : la case 0
		this.positionListe = 0;
	}
	
	/*
	 * Prend en parametre la valeur du jet de de 
	 * Retourne la nouvelle position du joueur 
	 */
	public int jouerUnTour(int valeurDe){
		int resultat = super.jouerUnTour(valeurDe);
		//updateCaseHisto(resultat);					//a implementer
		return resultat;
	}	
	
	/*
	 * Fait une update du tableau contenant l'historique des positions du joueur
	 * en rajoutant une nouvelle position au tableau
	 */
	private void updateCaseHisto(int valeurDe){
		this.histoCase.add(valeurDe);
		this.positionListe++;
		//A faire : la taille de histoCase ne doit jamais etre plus grande que 10
	}
	
	public void undo(){
		//a implementer		
	}
	
	public void redo(){
		//a implementer		
	}

}
