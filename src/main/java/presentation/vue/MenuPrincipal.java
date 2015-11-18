package presentation.vue;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controleurs.ControleurMenuPrincipal;

//public class MenuPrincipal extends JFrame implements IMenu{
public class MenuPrincipal implements IMenu{
	JButton b_debutPartie;
	JButton b_configurerPartie;
	JButton b_quitterPartie;
	
	JFrame f_menuPrincipal;
	
	ControleurMenuPrincipal controleurMenuPrincipal;
	
	//IHM Graphique
	public void afficherEcran(){
		
		controleurMenuPrincipal = new ControleurMenuPrincipal();

		f_menuPrincipal = new JFrame();

		f_menuPrincipal.setTitle("Snakes and Ladders - Menu Principal");
		Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
		int largeur = (int)tailleEcran.getWidth();
		int hauteur = (int)tailleEcran.getHeight();

		f_menuPrincipal.setSize(largeur/2, hauteur/2);
		f_menuPrincipal.setLocationRelativeTo(null);
		f_menuPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f_menuPrincipal.setResizable(false);
		
		JPanel panelMenu = new JPanel();
		
		EcouteurBouton ecouteurBouton = new EcouteurBouton();
		
		b_debutPartie = new JButton("Jouer");
		b_debutPartie.addActionListener(ecouteurBouton);
		panelMenu.add(b_debutPartie);
		
		b_configurerPartie = new JButton("Configurer");
		b_configurerPartie.addActionListener(ecouteurBouton);
		panelMenu.add(b_configurerPartie);
		
		b_quitterPartie = new JButton("Quitter");
		b_quitterPartie.addActionListener(ecouteurBouton);
		panelMenu.add(b_quitterPartie);
		
		f_menuPrincipal.add(panelMenu);
		f_menuPrincipal.setVisible(true);
	}
	
	//IHM Console
	/*
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
	}*/
	
	private class EcouteurBouton implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			if(e.getSource()== b_debutPartie){
				f_menuPrincipal.setVisible(false);
				controleurMenuPrincipal.gererCommande(1);
			}
			else if(e.getSource() == b_configurerPartie){
				f_menuPrincipal.setVisible(false);
				controleurMenuPrincipal.gererCommande(2);
			}
			else if(e.getSource()== b_quitterPartie){
				f_menuPrincipal.setVisible(false);
				controleurMenuPrincipal.gererCommande(3);
			}
		}
	}
}
