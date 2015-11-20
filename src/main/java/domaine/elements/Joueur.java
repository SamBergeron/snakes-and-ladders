package domaine.elements;
import domaine.elements.statique.*;

public abstract class Joueur {
	private String nom;
	private Couleur couleurPion;
	private int caseCourante;

	public Joueur (String nom, Couleur couleurPion){
		this.nom = nom;
		this.couleurPion = couleurPion;
		this.caseCourante = 0;
	}
	
	/*
	 * Prend en parametre la valeur du jet de de 
	 * Retourne la nouvelle position du joueur 
	 */
	public void deplacer(int deplacement){
		System.out.println("Je suis rendu a la case : " + deplacement + " ");
		this.caseCourante = deplacement;
	}
	
	/*
	 * Retourne vrai si le joueur est de type AI
	 */
	public boolean estAI(){
		return true;
	}
	
	public void undo(){
		
	}
	
	public void redo(){
		
	}
	
	public void setCaseCourante(int nouvellePosition){
		this.caseCourante = nouvellePosition;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Couleur getCouleurPion() {
		return couleurPion;
	}

	public void setCouleurPion(Couleur couleurPion) {
		this.couleurPion = couleurPion;
	}

	public int getCaseCourante() {
		return caseCourante;
	}

	@Override
	public String toString() {
		return "Joueur [nom=" + nom + ", couleurPion=" + couleurPion + ", caseCourante=" + caseCourante + "]";
	}
	
	
	
	
}
