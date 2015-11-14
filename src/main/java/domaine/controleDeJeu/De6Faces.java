package domaine.controleDeJeu;

public class De6Faces implements De{

	public int rouler() {
		//retourne un nombre compris entre 1(inclusivement) et 7(exclusivement)
		int resultat = (int)(Math.random() * 6)+1;
		
		//utile seulement pour debuguage
		System.out.print(""+resultat+",");
		
		return resultat;
	}
}
