package presentation.vue;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import controleurs.ControleurMenuPrincipal;

public class MenuPrincipal implements IMenu{
	JButton b_debutPartie;
	JButton b_configurerPartie;
	JButton b_quitterPartie;
	
	JFrame f_menuPrincipal;
	
	GridBagLayout layoutPrincipal;
	
	JPanel panelMenu;
	
	ControleurMenuPrincipal controleurMenuPrincipal;

	 Image backgroundImage;
	
	//IHM Graphique
	public void afficherEcran(){
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
		
		controleurMenuPrincipal = new ControleurMenuPrincipal();

		/* Creation de la fenetre principale */
		f_menuPrincipal = new JFrame();

		f_menuPrincipal.setTitle("Snakes and Ladders - Menu Principal");
		Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
		int largeur = (int)tailleEcran.getWidth();
		int hauteur = (int)tailleEcran.getHeight();

		f_menuPrincipal.setSize(largeur/2, hauteur/2);
		f_menuPrincipal.setLocationRelativeTo(null);
		f_menuPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f_menuPrincipal.setResizable(false);
		
		layoutPrincipal = new GridBagLayout();
		f_menuPrincipal.setLayout(layoutPrincipal);

		EcouteurBouton ecouteurBouton = new EcouteurBouton();
		
		/* Creation des boutons */
		final Font police_button = new Font(Font.DIALOG, Font.BOLD, 35);
		
		b_debutPartie = new JButton("Jouer");
		b_debutPartie.addActionListener(ecouteurBouton);
		Dimension d_button = new Dimension(largeur/4,hauteur/14);
		b_debutPartie.setPreferredSize(d_button);
		b_debutPartie.setFont(police_button);
		
		b_configurerPartie = new JButton("Configurer");
		b_configurerPartie.addActionListener(ecouteurBouton);
		b_configurerPartie.setPreferredSize(b_debutPartie.getPreferredSize()); //pour que tous les boutons aient la meme taille
		b_configurerPartie.setFont(police_button);
		
		b_quitterPartie = new JButton("Quitter");
		b_quitterPartie.addActionListener(ecouteurBouton);
		b_quitterPartie.setPreferredSize(b_debutPartie.getPreferredSize());
		b_quitterPartie.setFont(police_button);
		
		/* Ajout des boutons sur la fenetre */
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = gbc.gridy = 0;						//la grille commence en [0,0]
		gbc.gridwidth = GridBagConstraints.REMAINDER;   // seul composant de sa colonne, il est donc le dernier.
		gbc.gridheight = 1; 							// valeur par défaut - peut s'étendre sur une seule ligne.
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets(hauteur/75, largeur/50, hauteur/56, 0);
		f_menuPrincipal.add(b_debutPartie,gbc);
		
		gbc.gridy = 1;
		gbc.insets = new Insets(hauteur/56, largeur/50, hauteur/56, 0);
		f_menuPrincipal.add(b_configurerPartie,gbc);
		
		gbc.gridy = 2;
		gbc.insets = new Insets(hauteur/56, largeur/50, hauteur/75, 0);
		f_menuPrincipal.add(b_quitterPartie,gbc);

		f_menuPrincipal.setVisible(true);
	}
	
	private class EcouteurBouton implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			if(e.getSource()== b_debutPartie){
				f_menuPrincipal.dispose();
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
