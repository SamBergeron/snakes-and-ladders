package presentation.vue;

import java.util.InputMismatchException;
import java.util.Scanner;

import domaine.configuration.ConfigPartie;
import domaine.configuration.SerializerConfigPartie;

public class MenuConfiguration implements IMenu {

	public void afficherEcran() {

		boolean retry = true;
		SerializerConfigPartie serializerConfig = new SerializerConfigPartie();
		ConfigPartie config = new ConfigPartie();
		while(retry){
			try {
				Scanner sc = new Scanner(System.in);
				System.out.println("Veuillez configurer votre partie");
				System.out.println("Commencez par donner les dimensions du plateau");
				System.out.println("Longueur: ");
				config.setLongueurPlateau(sc.nextInt());
				
				System.out.println("Largeur: ");
				config.setLargeurPlateau(sc.nextInt());
				
				System.out.println("Nombre d'Échelles: ");
				config.setNbEchelles(sc.nextInt());
				
				System.out.println("Nombre de Serpents: ");
				config.setNbSerpents(sc.nextInt());
				
				serializerConfig.sauverConfig(config);
				
				// On retourne au menu principal
				MenuPrincipal m = new MenuPrincipal();
				m.afficherEcran();
				
				sc.close();
				retry = false;
				
			} catch (InputMismatchException e) {
				System.out.println("Cette entrée est invalide veuillez réessayer");
			}
		}
		
	}

	
}
