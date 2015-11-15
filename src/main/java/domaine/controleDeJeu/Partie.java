package domaine.controleDeJeu;

import java.util.List;

import domaine.elements.De;
import domaine.elements.Joueur;
import domaine.elements.Plateau;
import domaine.elements.statique.Couleur;

public class Partie {
	private List<Joueur>joueurs;
	De de;
	Plateau plateau;
	StrategieVictoire strategieAlgorithme;
	
	/*
	 * Constructeur
	 */
	public Partie(List<Joueur>listeJoueur,int typeDe, int longueur, int largeur, int serpents, int echelles, int typeAlgorithme){
		this.joueurs.addAll(listeJoueur);
		switch(typeDe){							//voir pour un pattern GoF
			//case 6 : this.de = new De6Faces();
			//case 8 : this.de = new De8Faces();
			//case 20 : this.de = new De20Faces();
			//default : System.out.println("Probleme instantiation Partie - (a faire : interrompre la partie"); //a faire : securiser le default
		}
		this.plateau = new Plateau(longueur,largeur,serpents,echelles);
		switch(typeAlgorithme){					//voir pour un pattern GoF
			case 1 : this.strategieAlgorithme = new StrategieAlgorithme1();
			case 2 : this.strategieAlgorithme = new StrategieAlgorithme2();
			case 3 : this.strategieAlgorithme = new StrategieAlgorithme3();
		}
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
	
	/*
	 * Dans le cas d'un undo/redo : avise le controleur qu'il faut deplacer le pion
	 * du joueur graphiquement
	 */
	public void deplacementPion(int position, Couleur couleurPion){
		//A faire
	}
	
	/*
	 * Demarre une nouvelle partie
	 */
	public void jouerUnePartie(){
		
	
	}

}
