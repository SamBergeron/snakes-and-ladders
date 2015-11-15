package domaine.controleDeJeu;

public interface StrategieVictoire {
	/*
	 * Renvoie la valeur de la derniere case du plateau de jeu si le joueur a gagne
	 * Sinon renvoie la nouvelle position du joueur
	 */
	public int calculerVictoire  (int anciennePosition, int nouvellePosition, int derniereCase);
}
