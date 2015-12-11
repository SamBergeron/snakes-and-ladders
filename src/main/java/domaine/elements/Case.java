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
	
	public String message(){
		return "Vous \u00eates tomb\u00E9 sur la case " + position; 
	}

	@Override
	public String toString() {
		return "Case [position=" + position + ", nbJoueurs=" + nbJoueurs + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Case other = (Case) obj;
		if (position != other.position)
			return false;
		return true;
	}
	
	
	
	
	
	
	
}
