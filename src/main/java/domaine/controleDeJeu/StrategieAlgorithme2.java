package domaine.controleDeJeu;

public class StrategieAlgorithme2 implements StrategieVictoire{
	/*
	 * Si un joueur arrive sur la derniere case : c'est le gagnant(non-Javadoc)
	 * Sinon il doit passer son tour
	 * @see domaine.controleDeJeu.StrategieVictoire#calculerVictoire(int, int)
	 */
	public int calculerVictoire(int anciennePosition, int nouvellePosition, int derniereCase) {
		if(nouvellePosition==derniereCase){
			return derniereCase;
		} else if (nouvellePosition>derniereCase){
			return anciennePosition;
		}
		return nouvellePosition;
	}
	
}
