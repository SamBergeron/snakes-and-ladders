package domaine.controleDeJeu;

public class StrategieAlgorithme3 implements StrategieVictoire{
	/*
	 * Si un joueur arrive sur la derniere case : c'est le gagnant(non-Javadoc)
	 * Si un joueur depasse la derniere case : il recule depuis la derniere case, du nombre de case supplementaire
	 * Sinon il avance normalement
	 * @see domaine.controleDeJeu.StrategieVictoire#calculerVictoire(int, int)
	 */
	public int calculerVictoire(int anciennePosition, int nouvellePosition, int derniereCase) {
		if(nouvellePosition==derniereCase){
			return derniereCase;
		} else if (nouvellePosition>derniereCase){
			return (derniereCase-(nouvellePosition-derniereCase));
		}
		return nouvellePosition;
	}
}
