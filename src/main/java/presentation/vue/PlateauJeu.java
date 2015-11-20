package presentation.vue;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import controleurs.ControleurMenuPrincipal;
import controleurs.FacadeJeu;

public class PlateauJeu implements IMenu{

	JButton b_debutPartie;
	JButton b_configurerPartie;
	JButton b_quitterPartie;
	
	JFrame f_plateauJeu;
	
	GridBagLayout layoutPlateauJeu;
	
	//IHM Graphique
	public void afficherFenetre(){
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); //permet des composants swing avec un style recent
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		f_plateauJeu = new JFrame();

		f_plateauJeu.setTitle("Snakes and Ladders - Partie en cours");

		Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
		int largeur = (int)tailleEcran.getWidth();
		int hauteur = (int)tailleEcran.getHeight();
		f_plateauJeu.setSize(largeur, hauteur-(hauteur/20));
		f_plateauJeu.setLocationRelativeTo(null);
		f_plateauJeu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f_plateauJeu.setResizable(false);
		
		layoutPlateauJeu = new GridBagLayout();
		f_plateauJeu.setLayout(layoutPlateauJeu);			
		
		/*
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
		
		
		this.add(panelMenu);
		this.setVisible(true);*/
	}
	
	private class EcouteurBouton implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			if(e.getSource()== b_debutPartie){
				//controleurMenuPrincipal.gererCommande(1);
			}
			else if(e.getSource() == b_configurerPartie){
				//controleurMenuPrincipal.gererCommande(2);
			}
			else if(e.getSource()== b_quitterPartie){
				//controleurMenuPrincipal.gererCommande(3);
			}
		}
	}

	public void afficherEcran() {
		// TODO Auto-generated method stub
		
	}

}
