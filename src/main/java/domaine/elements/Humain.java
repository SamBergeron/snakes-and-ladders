package domaine.elements;

import domaine.elements.statique.Couleur;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Humain extends Joueur{
	
	private List<Integer> histoCase;
	private int positionListe;
	
	
	public Humain(String nom, Couleur couleurPion) {
		super(nom, couleurPion);
		this.histoCase = new LinkedList<Integer>();
		this.histoCase.add(0);					//on ajoute la premiere position du joueur : la case 0
		this.positionListe = 0;
	}
	
	/*
	 * Prend en parametre la valeur du jet de de 
	 * Retourne la nouvelle position du joueur 
	 */
	@Override
	public void deplacer(int deplacement){
		updateCaseHisto(deplacement);
		super.deplacer(deplacement);
	}	
	
	/*
	 * Fait une update du tableau contenant l'historique des positions du joueur
	 * en rajoutant une nouvelle position au tableau
	 */
	private void updateCaseHisto(int valeurDe){
		histoCase.add(valeurDe);
		this.positionListe++;
	}
	
	public void undo(){
		//a implementer		
	}
	
	public void redo(){
		//a implementer		
	}

}
