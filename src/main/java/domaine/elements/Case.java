package domaine.elements;

public class Case {
	protected int position;
	private int nbJoueurs;
	
	public Case(int position) {
		this.position = position;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getNbJoueurs() {
		return nbJoueurs;
	}

	public void setNbJoueurs(int nbJoueurs) {
		this.nbJoueurs = nbJoueurs;
	}

	@Override
	public String toString() {
		return "Case [position=" + position + ", nbJoueurs=" + nbJoueurs + "]";
	}
	
	
	
	
	
}
