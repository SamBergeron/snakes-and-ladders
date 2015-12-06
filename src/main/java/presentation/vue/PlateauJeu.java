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
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import domaine.elements.statique.Couleur;

public class PlateauJeu implements IMenu{


	private JButton b_undo;
	private JButton b_redo;
	private JButton b_tirerDe;
	private JButton b_quitter;
	private JFrame f_plateauJeu;
	private JPanel panelBouton;
	private SpecialPanel specialPanel;
	
	private Point[] adresseSerpent;
	private Point[] adresseEchelle;
	
	/* Path des images utilisees */
	private static final String IMAGE_UNDO = "images/Undo.png";
	private static final String IMAGE_DICE = "images/rollDice.png";
	private static final String IMAGE_REDX = "images/redX.png";
	private static final String IMAGE_BACKGROUND = "images/bgPlateau.png";
	private static final String IMAGE_SUNFLARE = "images/SunFlare.png";
	private static final String IMAGE_REDO = "images/Redo.png";
	
	private static final String PION_BLEU = "images/bluepawn.png";
	private static final String PION_ROUGE = "images/redpawn.png";
	private static final String PION_JAUNE = "images/yellowpawn.png";
	private static final String PION_VERT = "images/greenpawn.png";
	private static final String PION_BLANC = "images/whitepawn.png";
	private static final String PION_NOIR = "images/darkpawn.png";
	
	/* On recupere les dimensions de l'ecran */
	private static final Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	private static final int largeur = (int)d.getWidth();
	private static final int hauteur = (int)d.getHeight();	
	
	/* Definition des polices de caracteres */
	private static final Font POLICE_BOUTON = new Font(Font.DIALOG, Font.BOLD, largeur/40);
	
	/* Permet d'afficher le plateau de jeu */
	public void afficherEcran(){
		f_plateauJeu = new JFrame();
		
		try {
			/* permet d'avoir des composants swing avec un style recent */
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	
			/* Creation de la fenetre du plateau de jeu */
			f_plateauJeu.setTitle("Snakes and Ladders - Partie en cours");
			f_plateauJeu.setSize(largeur, hauteur-(hauteur/20));
			f_plateauJeu.setLocationRelativeTo(null);
			f_plateauJeu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f_plateauJeu.setResizable(false);
			f_plateauJeu.setLayout(new BorderLayout());
			
			/* Creation et configuration du bouton Undo */
			b_undo = new JButton("Undo");
			b_undo.setFont(POLICE_BOUTON);
			b_undo.setPreferredSize(new Dimension(largeur/5, hauteur/10));
			b_undo.setForeground(Color.BLACK);

			Image iconeBouton = ImageIO.read(getClass().getClassLoader().getResource(IMAGE_UNDO));
			ImageIcon imageIcon = new ImageIcon(iconeBouton.getScaledInstance(largeur/15, hauteur/20, Image.SCALE_SMOOTH));
			
			b_undo.setIcon(imageIcon);		
			b_undo.setOpaque(false);
			b_undo.setContentAreaFilled(false);
			b_undo.setBorderPainted(false);	
			b_undo.addMouseListener(new MouseAdapter(){
				public void mouseEntered(MouseEvent evt){
					b_undo.setForeground(Color.WHITE);
				}
				public void mouseExited(MouseEvent evt){
					b_undo.setForeground(Color.BLACK);
				}
			});			
			
			/* Creation et configuration du bouton Redo */
			b_redo = new JButton("Redo");	
			b_redo.setFont(POLICE_BOUTON);
			b_redo.setPreferredSize(new Dimension(largeur/5, hauteur/10));	
			b_redo.setForeground(Color.BLACK);
			
			iconeBouton = ImageIO.read(getClass().getClassLoader().getResource(IMAGE_REDO));
			imageIcon = new ImageIcon(iconeBouton.getScaledInstance(largeur/15, hauteur/20, Image.SCALE_SMOOTH));
			
			b_redo.setIcon(imageIcon);		
			b_redo.setOpaque(false);
			b_redo.setContentAreaFilled(false);
			b_redo.setBorderPainted(false);	
			b_redo.addMouseListener(new MouseAdapter(){
				public void mouseEntered(MouseEvent evt){
					b_redo.setForeground(Color.WHITE);
				}
				public void mouseExited(MouseEvent evt){
					b_redo.setForeground(Color.BLACK);
				}
			});		
			
			/* Creation et configuration du bouton tirer au de */
			b_tirerDe = new JButton("Tirer D\u00e9");	
			b_tirerDe.setFont(POLICE_BOUTON);
			b_tirerDe.setPreferredSize(new Dimension(largeur/5, hauteur/10));	
			b_tirerDe.setForeground(Color.BLACK);
			
			iconeBouton = ImageIO.read(getClass().getClassLoader().getResource(IMAGE_DICE));
			imageIcon = new ImageIcon(iconeBouton.getScaledInstance(largeur/15, hauteur/15, Image.SCALE_SMOOTH));

			b_tirerDe.setIcon(imageIcon);		
			b_tirerDe.setOpaque(false);
			b_tirerDe.setContentAreaFilled(false);
			b_tirerDe.setBorderPainted(false);	
			b_tirerDe.addMouseListener(new MouseAdapter(){
				public void mouseEntered(MouseEvent evt){
					b_tirerDe.setForeground(Color.WHITE);
				}
				public void mouseExited(MouseEvent evt){
					b_tirerDe.setForeground(Color.BLACK);
				}
			});	
			
			/* Creation et configuration du bouton Quitter */
			b_quitter = new JButton("Quitter");	
			b_quitter.setFont(POLICE_BOUTON);
			b_quitter.setPreferredSize(new Dimension(largeur/5, hauteur/10));	
			b_quitter.setForeground(Color.BLACK);
			
			iconeBouton = ImageIO.read(getClass().getClassLoader().getResource(IMAGE_REDX));
			imageIcon = new ImageIcon(iconeBouton.getScaledInstance(largeur/15, hauteur/20, Image.SCALE_SMOOTH));
			
			b_quitter.setIcon(imageIcon);		
			b_quitter.setOpaque(false);
			b_quitter.setContentAreaFilled(false);
			b_quitter.setBorderPainted(false);	
			b_quitter.addMouseListener(new MouseAdapter(){
				public void mouseEntered(MouseEvent evt){
					b_quitter.setForeground(Color.WHITE);
				}
				public void mouseExited(MouseEvent evt){
					b_quitter.setForeground(Color.BLACK);
				}
			});			
			
			/*Ajout des boutons dans le panel */
			panelBouton = new BoutonPanel();
			panelBouton.setLayout(new FlowLayout());
			
			panelBouton.add(b_undo);
			panelBouton.add(b_tirerDe);
			panelBouton.add(b_redo);
			panelBouton.add(b_quitter);	
			
			/* ajout des boutons dans la fenetre */
			Container pane = f_plateauJeu.getContentPane();
			pane.add(panelBouton, BorderLayout.SOUTH);
			
			f_plateauJeu.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	public void afficherPion(Couleur couleurPion, int nouvellePosition, int anciennePosition){
		ImageIcon imagePion;
		int indexpion = 0;	
		//numero du label contenant l'image du pion dans le panel]
		switch(couleurPion){
		case BLEU:
			imagePion = new ImageIcon(getClass().getClassLoader().getResource(PION_BLEU));
			indexpion=4;
			break;
			
		case VERT:
			imagePion = new ImageIcon(getClass().getClassLoader().getResource(PION_VERT));
			indexpion=0;
			break;
		
		case JAUNE:
			imagePion = new ImageIcon(getClass().getClassLoader().getResource(PION_JAUNE));
			indexpion=1;
			break;
		
		case BLANC:
			imagePion = new ImageIcon(getClass().getClassLoader().getResource(PION_BLANC));
			indexpion=5;
			break;
		
		case NOIR:
			imagePion = new ImageIcon(getClass().getClassLoader().getResource(PION_NOIR));
			indexpion=2;
			break;
		
		case ROUGE:
		default:
			imagePion = new ImageIcon(getClass().getClassLoader().getResource(PION_ROUGE));
			indexpion=6;
			break;
		}
		
		/* Permet de modifier la taille de l'image */
		Image image = imagePion.getImage();
		Image nouvelleImage;
		nouvelleImage = image.getScaledInstance(largeur/50, hauteur/30, Image.SCALE_SMOOTH);
		imagePion = new ImageIcon(nouvelleImage);
			
		/* Pour eviter les problemes d'affichage si le joueur part de la 1ere case */
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
		Border border = LineBorder.createBlackLineBorder();
		final Font police_label = new Font(Font.DIALOG, Font.BOLD, hauteur/50);
		
		specialPanel = new SpecialPanel();
		specialPanel.setLayout(new GridLayout(nbLigne,10));
		
		int i,j,k;
		k = (nbLigne*10)-1; 		//nombre de case totale sur le plateau
		boolean decroissant = false;	
		if((k/10)%2 != 0){			//permet de toujours demarrer la partie en bas a gauche de l'ecran
			decroissant = true;		//que le nombre total de cases du plateau soit pair ou impair
		}
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
					labnumcase.setFont(police_label);
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
					labnumcase.setFont(police_label);
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
	}
	
	public void setAdresseEchelle(Point[] tabAdresse){
		adresseEchelle = tabAdresse;
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
			}
					
			//on affiche le serpent selon les coordonnees obtenues
			Graphics2D g2d = (Graphics2D) g;
			g2d.setPaint(Color.RED);
			g2d.setStroke(new BasicStroke(3));
			g2d.draw(new Line2D.Float(x1centre, y1centre, x2centre, y2centre));		//on dessine la ligne
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
			String caseDepart = "" + p.x;		//contient le numero de la case de depart
			String caseArrivee = "" + p.y;		//contient le numero de la case d'arrivee
			int x1centre = 0;
			int y1centre = 0;
			int x2centre = 0;
			int y2centre = 0;
			
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
	@SuppressWarnings("serial")		//car cette classe ne sera jamais serialisee (supprime l'avertissement)
	private class SpecialPanel extends JPanel{
		@Override
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			Image iconeBouton = null;
			try {
				iconeBouton = ImageIO.read(getClass().getClassLoader().getResource(IMAGE_BACKGROUND));
			} catch (IOException e) {
				e.printStackTrace();
			}
			Image dimg = iconeBouton.getScaledInstance(largeur, hauteur, Image.SCALE_SMOOTH);
			g.drawImage(dimg, 0, 0, null);
			
			dessinerSerpent(g);
			dessinerEchelle(g);
		}
	}
	
	@SuppressWarnings("serial")		//car cette classe ne sera jamais serialisee (supprime l'avertissement)
	private class BoutonPanel extends JPanel{
		@Override
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			Image iconeBouton = null;
			try {
				iconeBouton = ImageIO.read(getClass().getClassLoader().getResource(IMAGE_SUNFLARE));
			} catch (IOException e) {
				e.printStackTrace();
			}
			Image dimg = iconeBouton.getScaledInstance(largeur, hauteur-(hauteur/10), Image.SCALE_SMOOTH);
			g.drawImage(dimg, 0, 0, null);
		}
	}
	
}


