package domaine.controleDeJeu;

public class De20Faces implements De{

	public int rouler() {
		//retourne un nombre compris entre 1(inclusivement) et 21(exclusivement)
		int resultat = (int)(Math.random() * 20)+1;
		
		//utile seulement pour debuguage
		System.out.print(""+resultat+",");
		
		return resultat;
	}

}
