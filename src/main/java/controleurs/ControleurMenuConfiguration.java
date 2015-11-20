package controleurs;

import javax.swing.JOptionPane;

import domaine.configuration.ConfigPartie;
import domaine.configuration.SerializerConfigPartie;
import presentation.vue.MenuConfiguration;

public class ControleurMenuConfiguration {
	private MenuConfiguration mConfig;
	private FacadeJeu facadeJeu;
	/*
	 * Gere la commande specifie dans commande
	 * 1 : ecran pour une partie
	 * 2 : ecran de configuration
	 */
	public void sauvegarderConfiguration (int longueur, int largeur, int nbEchelles, int nbSerpents){
		//action a faire lorsque le bouton svg est clique
		SerializerConfigPartie serializerConfig = new SerializerConfigPartie();
		ConfigPartie config = new ConfigPartie();
		//la longueur du plateau est egale au nb de cases/10 (la largeur est de 10 par defaut)
		config.setLongueurPlateau(longueur);
		config.setLargeurPlateau(largeur);
		config.setNbEchelles(nbEchelles);
		config.setNbSerpents(nbSerpents);
		boolean verifSvg = serializerConfig.sauverConfig(config);
		if(verifSvg==true){
			JOptionPane.showMessageDialog(null, "Sauvegarde des configurations effectuees avec succes !");
		}else{
			JOptionPane.showMessageDialog(null, "Probleme de sauvegarde - Annulation de la commande");
		}
	}
}