package domaine.elements;

import domaine.elements.statique.NombreFaces;

public class De {
	
	private NombreFaces nbFaces;
 
	public De(NombreFaces nbFaces) {
		this.nbFaces = nbFaces;
	}
	
	public int rouler(){
		return (int) (Math.random() * nbFaces.getValeur()) + 1;
	}

	public NombreFaces getNbFaces() {
		return nbFaces;
	}

	public void setNbFaces(NombreFaces nbFaces) {
		this.nbFaces = nbFaces;
	}
	
	

}
