package presentation.vue;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import controleurs.ControleurMenuPrincipal;
import controleurs.FacadeJeu;
import domaine.elements.statique.Couleur;

public class PlateauJeu implements IMenu{

	JButton b_undo;
	JButton b_redo;
	JButton b_tirerDe;
	JButton b_quitter;
	
	JFrame f_plateauJeu;
	JPanel panelBouton;
	JPanel panelPlateau;
	JLayeredPane layPlateau;
	
	SpecialPanel specialPanel;
	
	FacadeJeu facadeJeu;
	
	GridBagLayout layoutPlateauJeu;
	GridBagConstraints gbc;
	
	int largeur;
	int hauteur;
	
	Point[] adresseSerpent;
	Point[] adresseEchelle;
	
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
	 * Supprime l'image du pion situe sur la case anciennePosition
	 * Affiche l'image du pion situe sur la case nouvellePosition
	 */
	public void afficherPion(Color couleurPion, int nouvellePosition, int anciennePosition){
		ImageIcon imagePion;
		int indexpion = 0; //TEST //numero du panel contenant l'image du pion dans le panel
		if(couleurPion==Color.BLUE){
			imagePion = new ImageIcon(getClass().getResource("/images/pionbleu.png"));
			indexpion=4;
		}
		else if(couleurPion==Color.GREEN){
			imagePion = new ImageIcon(getClass().getResource("/images/pionvert.png"));
			indexpion=0;
		}
		else if(couleurPion==Color.YELLOW){
			imagePion = new ImageIcon(getClass().getResource("/images/pionjaune.png"));
			indexpion=1;
		}
		else if(couleurPion==Color.WHITE){
			imagePion = new ImageIcon(getClass().getResource("/images/pionblanc.png"));
			indexpion=5;
		}
		else if(couleurPion==Color.BLACK){
			imagePion = new ImageIcon(getClass().getResource("/images/pionnoir.png"));
			indexpion=2;
		}
		else{
			imagePion = new ImageIcon(getClass().getResource("/images/pionrouge.png"));
			indexpion=6;
		}
		
		/* Permet de modifier la taille de l'image */
		Image image = imagePion.getImage();
		Image nouvelleImage;
		if(couleurPion==Color.YELLOW && ((largeur*hauteur)<150)){
			nouvelleImage = image.getScaledInstance(largeur/50, hauteur/30, Image.SCALE_SMOOTH);
		}else if((largeur*hauteur)<150){
			nouvelleImage = image.getScaledInstance(largeur/30, hauteur/18, Image.SCALE_SMOOTH);
		}else if(couleurPion==Color.YELLOW){
			nouvelleImage = image.getScaledInstance(largeur/70, hauteur/42, Image.SCALE_SMOOTH);
		}else{
			nouvelleImage = image.getScaledInstance(largeur/50, hauteur/30, Image.SCALE_SMOOTH);
		}
		
		imagePion = new ImageIcon(nouvelleImage);
			
		/* Pour eviter les problemes d'affichage si le joueur part de la 1ere case*/
		if(anciennePosition==0){
			anciennePosition=1;
		}
		if(nouvellePosition==0){
			nouvellePosition=1;
		}
		
		/* On recherche l'ancienne case du joueur pour y supprimer son pion */
		String ancPosition = ""+anciennePosition;
		Component[] components = specialPanel.getComponents();
		for (Component com : components){
			JPanel encours = (JPanel)com;
			JLabel jlabnumcase = (JLabel)encours.getComponent(3);
			JLabel jlabpion = (JLabel)encours.getComponent(indexpion);
			if(jlabnumcase.getText().equals(ancPosition)){
				jlabpion.setIcon(null);
			}			
		}		
	
		/* On recherche la nouvelle case du joueur pour y afficher son pion */
		String nouvPosition = ""+nouvellePosition;
		for (Component com : components){ 
			JPanel encours = (JPanel)com;
			JLabel jlabnumcase = (JLabel)encours.getComponent(3);
			JLabel jlabpion = (JLabel)encours.getComponent(indexpion);
			if(jlabnumcase.getText().equals(nouvPosition)){
				jlabpion.setIcon(imagePion);
			}				
		}
	}
	
	/*
	 * Affiche le plateau de jeu
	 * la largeur est de 10 par defaut
	 * nbLigne demande la nombre de ligne que contient le plateau
	 */
	public void afficherPlateauJeu(int nbLigne){
		Border border = LineBorder.createGrayLineBorder();
		final Font police_label = new Font(Font.DIALOG, Font.BOLD, hauteur/50);
		
		specialPanel = new SpecialPanel();
		specialPanel.setLayout(new GridLayout(nbLigne,10));
		layPlateau = f_plateauJeu.getLayeredPane();
		
		int i,j,k;
		k = (nbLigne*10)-1; //nombre de case totale sur le plateau
		boolean decroissant = true;
		for(i=nbLigne; i>0; i--){
			if(decroissant==true){
				for(j=10; j>0; j--){
					JPanel cases = new JPanel(); 			//le panel represente une case du plateau
					cases.setLayout(new GridLayout(2,4));	//la case est une matrice 2x4 qui contient respectivement :
					JLabel labpionvert = new JLabel();		//pionvert/pionjaune/pionnoir/numcase
					cases.add(labpionvert);					//pionbleu/pionblanc/pionrouge/serpentechelle
					JLabel labpionjaune = new JLabel();
					cases.add(labpionjaune);
					JLabel labpionnoir = new JLabel();
					cases.add(labpionnoir);
					JLabel labnumcase = new JLabel(""+(k+1));
					cases.add(labnumcase);
					JLabel labpionbleu = new JLabel();
					cases.add(labpionbleu);
					JLabel labpionblanc = new JLabel();
					cases.add(labpionblanc);
					JLabel labpionrouge = new JLabel();
					cases.add(labpionrouge);
					JLabel labserpentechelle = new JLabel();
					cases.add(labserpentechelle);
					cases.setBorder(border);
					cases.setOpaque(false);
					specialPanel.add(cases);
					k--;
				}
			}else{
				int temp = k-10+1;
				for(j=10; j>0; j--){
					JPanel cases = new JPanel(); 			//le panel represente une case du plateau
					cases.setLayout(new GridLayout(2,4));	//la case est une matrice 2x4 qui contient respectivement :
					JLabel labpionvert = new JLabel();		//pionvert/pionjaune/pionnoir/numcase
					cases.add(labpionvert);					//pionbleu/pionblanc/pionrouge/serpentechelle
					JLabel labpionjaune = new JLabel();
					cases.add(labpionjaune);
					JLabel labpionnoir = new JLabel();
					cases.add(labpionnoir);
					JLabel labnumcase = new JLabel(""+(temp+1));
					cases.add(labnumcase);
					JLabel labpionbleu = new JLabel();
					cases.add(labpionbleu);
					JLabel labpionblanc = new JLabel();
					cases.add(labpionblanc);
					JLabel labpionrouge = new JLabel();
					cases.add(labpionrouge);
					JLabel labserpentechelle = new JLabel();
					cases.add(labserpentechelle);
					cases.setBorder(border);
					cases.setOpaque(false);
					specialPanel.add(cases);
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
		//Cette configuration fonctionne pour l'affichage des lignes
		specialPanel.revalidate();
		specialPanel.setOpaque(true);
		specialPanel.setBackground(Color.WHITE);
		Container pane = f_plateauJeu.getContentPane();
		pane.add(specialPanel, BorderLayout.CENTER);	
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
	public void refreshSpecialPanel(){
		specialPanel.repaint();
	}
	
	public void setAdresseSerpent(Point[] tabAdresse){
		adresseSerpent = tabAdresse;
		System.out.println("Debuguage setAdresseSerpent");
		for(Point p : adresseSerpent){
			System.out.println(""+p);
		}
	}
	
	public void setAdresseEchelle(Point[] tabAdresse){
		adresseEchelle = tabAdresse;
		System.out.println("Debuguage setAdresseEchelle");
		for(Point p : adresseEchelle){
			System.out.println(""+p);
		}
	}
	
	/*
	 * dessine les serpents a l'ecran
	 * coorSerpent est un tableau de point, chaque point contient la case de depart(x)
	 * et la case d'arrivee(y) d'un serpent
	 */
	public void dessinerSerpent(Graphics g){
		Component[] components = specialPanel.getComponents();
		for(Point p : adresseSerpent){
			String caseDepart = ""+p.x;		//contient le numero de la case de depart
			String caseArrivee = ""+p.y;	//contient le numero de la case d'arrivee
			int x1centre = 0;
			int y1centre = 0;
			int x2centre = 0;
			int y2centre = 0;
			int i = 0;
			int j = 0;
			
			//on recupere la case depart en passant au travers toutes les cases du composant representant le plateau (specialPanel)
			for(Component com : components){
				JPanel encours = (JPanel)com;
				JLabel jlabnumcase = (JLabel)encours.getComponent(3); //encours.getComponent(3) renvoie le numero de la case
				if(jlabnumcase.getText().equals(caseDepart)){
					//on recupere les coordonnees du centre de la case
					x1centre = com.getLocation().x+(com.getSize().width/2);
					y1centre = com.getLocation().y+(com.getSize().height/2);				
					break;
				}
				i++;	
			}
			//on recupere la case arrivee en passant au travers toutes les cases du composant representant le plateau (specialPanel)
			for(Component com2 : components){
				JPanel encours = (JPanel)com2;
				JLabel jlabnumcase = (JLabel)encours.getComponent(3); //encours.getComponent(3) renvoie le numero de la case
				if(jlabnumcase.getText().equals(caseArrivee)){
					//on recupere les coordonnees du centre de la case
					x2centre = com2.getLocation().x+(com2.getSize().width/2);
					y2centre = com2.getLocation().y+(com2.getSize().height/2);				
					break;
				}
				j++;	
			}
			//on affiche le serpent selon les coordonnees obtenues
			Graphics2D g2d = (Graphics2D) g;
			g2d.setPaint(Color.RED);
			g2d.setStroke(new BasicStroke(5));
			g2d.draw(new Line2D.Float(x1centre, y1centre, x2centre, y2centre));		
		}
	}
	
	/*
	 * dessine les echelles a l'ecran
	 * coorEchelle est un tableau de point, chaque point contient la case de depart(x)
	 * et la case d'arrivee(y) d'une echelle
	 */
	public void dessinerEchelle(Graphics g){
		Component[] components = specialPanel.getComponents();
		for(Point p : adresseEchelle){
			String caseDepart = ""+p.x;		//contient le numero de la case de depart
			String caseArrivee = ""+p.y;	//contient le numero de la case d'arrivee
			int x1centre = 0;
			int y1centre = 0;
			int x2centre = 0;
			int y2centre = 0;
			int i = 0;
			int j = 0;
			
			//on recupere la case depart en passant au travers toutes les cases du composant representant le plateau (specialPanel)
			for(Component com : components){
				JPanel encours = (JPanel)com;
				JLabel jlabnumcase = (JLabel)encours.getComponent(3); //encours.getComponent(3) renvoie le numero de la case
				if(jlabnumcase.getText().equals(caseDepart)){
					//on recupere les coordonnees du centre de la case
					x1centre = com.getLocation().x+(com.getSize().width/2);
					y1centre = com.getLocation().y+(com.getSize().height/2);				
					break;
				}
				i++;	
			}
			//on recupere la case arrivee en passant au travers toutes les cases du composant representant le plateau (specialPanel)
			for(Component com2 : components){
				JPanel encours = (JPanel)com2;
				JLabel jlabnumcase = (JLabel)encours.getComponent(3); //encours.getComponent(3) renvoie le numero de la case
				if(jlabnumcase.getText().equals(caseArrivee)){
					//on recupere les coordonnees du centre de la case
					x2centre = com2.getLocation().x+(com2.getSize().width/2);
					y2centre = com2.getLocation().y+(com2.getSize().height/2);				
					break;
				}
				j++;	
			}
			//on affiche l'echelle selon les coordonnees obtenues
			Graphics2D g2d = (Graphics2D) g;
			g2d.setPaint(Color.GREEN);
			g2d.setStroke(new BasicStroke(5));
			g2d.draw(new Line2D.Float(x1centre, y1centre, x2centre, y2centre));		
		}
	}
	
	/*
	 * SpecialPanel est un JPanel regulier mais qui permet de dessiner
	 * des serpents et des echelles a chaque fois que paintComponent est
	 * appelé. Cet appel est souvent implicite
	 * (lors de la creation du JPanel, lors d'un mouvement sur le JPanel etc..)
	 */
	private class SpecialPanel extends JPanel{
		@Override
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			dessinerSerpent(g);
			dessinerEchelle(g);
		}
	}
	
}


