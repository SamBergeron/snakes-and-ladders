package domaine.elements;
import domaine.elements.statique.*;

public abstract class Joueur {
	private String nom;
	private Couleur couleurPion;
	private int caseCourante;
	
	/*
	 * Constructeur
	 */
	public Joueur (String nom, Couleur couleurPion, int caseCourante){
		this.nom = nom;
		this.couleurPion = couleurPion;
		this.caseCourante = caseCourante;
	}
	
	/*
	 * Prend en parametre la valeur du jet de de 
	 * Retourne la nouvelle position du joueur 
	 */
	public int jouerUnTour(int valeurDe){
		//int resultat = partie.getNouvellePosition(caseCourante+valeurDe); //lorsque Partie sera fonctionnel
		//setCaseCourante(resultat);										//lorsque Partie sera fonctionnel
		return this.caseCourante+valeurDe; 							 		//en attendant que Partie soit fonctionnel
	}
	
	/*
	 * Change la valeur de la case courante du joueur
	 * pour celle passee en parametre
	 */
	public void setCaseCourante(int nouvellePosition){
		this.caseCourante = nouvellePosition;
	}
}
