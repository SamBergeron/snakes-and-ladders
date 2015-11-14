package domaine.controleDeJeu;

public class De8Faces implements De{

	public int rouler() {
		//retourne un nombre compris entre 1(inclusivement) et 9(exclusivement)
		int resultat = (int)(Math.random() * 8)+1;
		
		//utile seulement pour debuguage
		System.out.print(""+resultat+",");
		
		return resultat;
	}

}
