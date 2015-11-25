package domaine.elements;

import domaine.elements.statique.Couleur;

import java.util.ArrayList;
import java.util.List;

public class Humain extends Joueur{
	
	private List<Integer> histoCase;
	private int positionListe;
	
	
	public Humain(String nom, Couleur couleurPion) {
		super(nom, couleurPion);
		this.histoCase = new ArrayList<Integer>();
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
	 * Retourne vrai si le joueur decide de faire un undo ou un redo(non-Javadoc)
	 * @see domaine.elements.Joueur#gererCommande()
	 */
	@Override
	public boolean estAI(){
		return false; //retourne vrai si c'est un humain : permet de savoir que l'on a besoin des boutons undo/redo
	}
	
	/*
	 * Fait une update du tableau contenant l'historique des positions du joueur
	 * en rajoutant une nouvelle position au tableau
	 */
	private void updateCaseHisto(int valeurDe){
		histoCase.add(valeurDe);
		this.positionListe++;
	}
	
	public boolean undo(){	
		if(positionListe == 0 || histoCase.size()-positionListe>10){
			return false;
		} else {
			positionListe--;
			this.setCaseCourante(histoCase.get(positionListe));
			return true;
		}
	}
	
	public boolean redo(){
		if(positionListe==histoCase.size()-1){
			return false;
		}
		else{
			positionListe++;
			this.setCaseCourante(histoCase.get(positionListe));
			return true;
		}	
	}
	

}
