package domaine.controleDeJeu;

public class StrategieAlgorithme1 implements StrategieVictoire{

	/*
	 * Si un joueur arrive sur la derniere case, ou si il la depasse : c'est le gagnant(non-Javadoc)
	 * @see domaine.controleDeJeu.StrategieVictoire#calculerVictoire(int, int)
	 */
	public int calculerVictoire(int anciennePosition, int nouvellePosition, int derniereCase) {
		if(nouvellePosition>=derniereCase)
			return derniereCase;
		
		return nouvellePosition;
	}
	
}
