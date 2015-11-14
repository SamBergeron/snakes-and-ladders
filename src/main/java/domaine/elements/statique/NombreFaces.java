package domaine.elements.statique;

public enum NombreFaces {
	SIX(6), HUIT(8), VINGT(20);
	private int valeur;
	
	private NombreFaces(int v){
		this.valeur = v;
	}

	public int getValeur() {
		return valeur;
	}
}
