package presentation.vue;

import java.util.Scanner;

import controleurs.ControleurMenuPrincipal;

public class MenuPrincipal implements IMenu {

	public void afficherEcran(){
		boolean commandeOk = false;
		Scanner sc = new Scanner(System.in);
		ControleurMenuPrincipal cmp = new ControleurMenuPrincipal();
		while(!commandeOk){
			System.out.println("Choisissez une option : ");
			System.out.println("1 - Nouvelle Partie");
			System.out.println("2 - Configurer Partie");
			System.out.println("3 - Quitter");
			int choix = sc.nextInt();
			if(choix!=1 && choix!=2 && choix!=3){
				System.out.println("Commande inconnue - Veuillez retaper votre commande");
			}else{
				commandeOk= true;
				cmp.gererCommande(choix);
			}
		}
		sc.close();
	}
	
}
