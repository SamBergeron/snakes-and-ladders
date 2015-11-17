package domaine.configuration;

import java.io.Serializable;

public class ConfigPartie implements Serializable{
	private static final long serialVersionUID = -6108400994560236023L;
	private int longueurPlateau;
	private int largeurPlateau;
	private int nbEchelles;
	private int nbSerpents;
	
	public int getLongueurPlateau() {
		return longueurPlateau;
	}
	public void setLongueurPlateau(int longueurPlateau) {
		this.longueurPlateau = longueurPlateau;
	}
	public int getLargeurPlateau() {
		return largeurPlateau;
	}
	public void setLargeurPlateau(int largeurPlateau) {
		this.largeurPlateau = largeurPlateau;
	}
	public int getNbEchelles() {
		return nbEchelles;
	}
	public void setNbEchelles(int nbEchelles) {
		this.nbEchelles = nbEchelles;
	}
	public int getNbSerpents() {
		return nbSerpents;
	}
	public void setNbSerpents(int nbSerpents) {
		this.nbSerpents = nbSerpents;
	}
	@Override
	public String toString() {
		return "ConfigPartie [longueurPlateau=" + longueurPlateau + ", largeurPlateau=" + largeurPlateau
				+ ", nbEchelles=" + nbEchelles + ", nbSerpents=" + nbSerpents + "]";
	}
	
	
	
}
