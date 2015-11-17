package presentation.vue;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controleur.jeu.ControleurMenuPrincipal;

public class PlateauJeu extends JFrame{

	JButton b_debutPartie;
	JButton b_configurerPartie;
	JButton b_quitterPartie;
	
	ControleurMenuPrincipal controleurMenuPrincipal;
	
	//IHM Graphique
	public void afficherFenetre(){
		
		controleurMenuPrincipal = new ControleurMenuPrincipal();

		this.setTitle("Snakes and Ladders - Partie en cours");

		Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
		int largeur = (int)tailleEcran.getWidth();
		int hauteur = (int)tailleEcran.getHeight();
		this.setSize(largeur, hauteur-(hauteur/20));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
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
		
		
		this.add(panelMenu);
		this.setVisible(true);
	}
	
	private class EcouteurBouton implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			if(e.getSource()== b_debutPartie){
				controleurMenuPrincipal.gererCommande(1);
			}
			else if(e.getSource() == b_configurerPartie){
				controleurMenuPrincipal.gererCommande(2);
			}
			else if(e.getSource()== b_quitterPartie){
				controleurMenuPrincipal.gererCommande(3);
			}
		}
	}

}
