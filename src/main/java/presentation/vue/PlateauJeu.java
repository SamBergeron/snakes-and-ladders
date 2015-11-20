package presentation.vue;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import controleurs.ControleurMenuPrincipal;
import controleurs.FacadeJeu;

public class PlateauJeu implements IMenu{

	JButton b_undo;
	JButton b_redo;
	JButton b_tirerDe;
	JButton b_quitter;
	
	JFrame f_plateauJeu;
	JPanel panelBouton;
	
	FacadeJeu facadeJeu;
	
	GridBagLayout layoutPlateauJeu;
	GridBagConstraints gbc;
	
	int largeur;
	int hauteur;
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
		
		facadeJeu = new FacadeJeu();
		
		f_plateauJeu = new JFrame();

		f_plateauJeu.setTitle("Snakes and Ladders - Partie en cours");

		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		largeur = (int)d.getWidth();
		hauteur = (int)d.getHeight();
		f_plateauJeu.setSize(largeur, hauteur-(hauteur/20));
		f_plateauJeu.setLocationRelativeTo(null);
		f_plateauJeu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f_plateauJeu.setResizable(false);
			
		f_plateauJeu.setLayout(new BorderLayout());
		
		/* Creations des boutons */
		final Font police_bouton = new Font(Font.DIALOG, Font.BOLD, 20);
		b_undo = new JButton("Undo");
		b_undo.setFont(police_bouton);
		d = b_undo.getPreferredSize();
		d.width = largeur/5;
		d.height = hauteur/10;
		b_undo.setPreferredSize(d);
		
		b_redo = new JButton("Redo");	
		b_redo.setFont(police_bouton);
		b_redo.setPreferredSize(d);		
		
		b_tirerDe = new JButton("Tirer Dé");	
		b_tirerDe.setFont(police_bouton);
		b_tirerDe.setPreferredSize(d);	
		
		b_quitter = new JButton("Quitter");	
		b_quitter.setFont(police_bouton);
		b_quitter.setPreferredSize(d);	
		
		/*Ajout des boutons dans la fenetre */
		panelBouton = new JPanel();
		panelBouton.setLayout(new FlowLayout());
		
		panelBouton.add(b_undo);
		panelBouton.add(b_redo);
		panelBouton.add(b_tirerDe);
		panelBouton.add(b_quitter);
		JButton b_test = new JButton();
		b_test = new JButton("test");	
		b_test.setFont(police_bouton);
		b_test.setPreferredSize(d);		
		panelBouton.add(b_test); //TEST
		
		//ajout des boutons dans la fenetre
		Container pane = f_plateauJeu.getContentPane();
		pane.add(panelBouton, BorderLayout.NORTH);
		
		f_plateauJeu.setVisible(true);
	}

	//Permet de cacher les boutons undo et redo si c'est le tour d'un joueur artificiel
	public void cacheUndoRedo(boolean cacherBouton){
		if(cacherBouton){
			b_undo.setEnabled(false);
			b_redo.setEnabled(false);
		}else{
			b_undo.setEnabled(true);
			b_redo.setEnabled(true);
		}
	}
	
	/*
	 * Affiche le plateau de jeu
	 * la largeur est de 10 par defaut
	 * nbLigne demande la nombre de ligne que contient le plateau
	 */
	public void afficherPlateauJeu(int nbLigne){
		//en cours d'implémentation //////////////////////////////////////////
		Border border = LineBorder.createGrayLineBorder();
		final Font police_label = new Font(Font.DIALOG, Font.BOLD, hauteur/50);
		System.out.println("largeur vaut : "+largeur+" hauteur vaut "+hauteur);
		JPanel panelPlateau = new JPanel();
		panelPlateau.setLayout(new GridLayout(nbLigne,10));
		
		int i,j,k;
		k = (nbLigne*10)-1; //nombre de case totale sur le plateau
		boolean decroissant = true;
		for(i=nbLigne; i>0; i--){
			if(decroissant==true){
				for(j=10; j>0; j--){
					JLabel jlab = new JLabel();
					jlab.setText(""+(k+1));
					jlab.setBorder(border);
					jlab.setHorizontalAlignment(SwingConstants.CENTER); //permet de placer le texte au centre du JLabel
					jlab.setVerticalAlignment(SwingConstants.CENTER); //permet de placer le texte au centre du JLabel
					jlab.setFont(police_label);
					panelPlateau.add(jlab);
					k--;
				}
			}else{
				int temp = k-10+1;
				for(j=10; j>0; j--){
					JLabel jlab = new JLabel();
					jlab.setText(""+(temp+1));
					jlab.setBorder(border);
					jlab.setHorizontalAlignment(SwingConstants.CENTER); //permet de placer le texte au centre du JLabel
					jlab.setVerticalAlignment(SwingConstants.CENTER); //permet de placer le texte au centre du JLabel
					jlab.setFont(police_label);
					panelPlateau.add(jlab);
					temp++;
					k--;
				}				
			}
			if(decroissant==true){
				decroissant = false;
			}else{
				decroissant = true;
			}
		}
		Container pane = f_plateauJeu.getContentPane();
		pane.add(panelPlateau, BorderLayout.CENTER);	
	}
	
	public JButton getb_undo(){
		return b_undo;
	}
	
	public JButton getb_redo(){
		return b_redo;
	}
	
	public JButton getb_tirerDe(){
		return b_tirerDe;
	}
	
	public JButton getb_quitter(){
		return b_quitter;
	}
	public JFrame getf_plateauJeu(){
		return f_plateauJeu;
	}
}


