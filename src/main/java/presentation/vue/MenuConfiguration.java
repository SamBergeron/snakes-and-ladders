package presentation.vue;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controleurs.ControleurMenuConfiguration;

public class MenuConfiguration implements IMenu {
	
	JFrame f_menuConfiguration;
	
	ControleurMenuConfiguration controleurMenuConfiguration;
	
	JPanel panelConfiguration; //un panel gerant la configuration generale de la page de configuration
	JPanel panel1,panel2,panel3,panel4,panel5,panel6,panel7,panel8; //un panel pour chaque case du GridLayout
	
	GridBagLayout layoutConfiguration;
	
	JLabel label_case;
	JLabel label_serpents;
	JLabel label_echelles;
	
	JButton b_svgConfig;
	JButton b_retour;
	
	JSpinner spinnerCases;
	JSpinner spinnerSerpents;
	JSpinner spinnerEchelles;
	
	int nbCases = 40; //nb de cases choisit par le joueur, initialise au minimum : 40
	
	//signifie que 1/5eme au maximum des cases peuvent etre des serpents 
	//signifie qu'un autre 1/5eme au maximum des cases peuvent etre des echelles 
	final int pourcentageSerpentEchelle = 5;

	public void afficherEcran() {
		
		/* Mode Graphique */
		
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
		
		controleurMenuConfiguration = new ControleurMenuConfiguration();
		
		/* Creation de la fenetre */
		f_menuConfiguration = new JFrame();
	
		f_menuConfiguration.setTitle("Snakes and Ladders - Menu Configuration");
		Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
		int largeur = (int)tailleEcran.getWidth();
		int hauteur = (int)tailleEcran.getHeight();

		f_menuConfiguration.setSize(largeur/2, hauteur/2);
		f_menuConfiguration.setLocationRelativeTo(null);
		f_menuConfiguration.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		f_menuConfiguration.setResizable(false);
		
		/* Mise en place de l'image de background du menu de configuration */
		BufferedImage img = null;
		try{
			img = ImageIO.read(getClass().getResource("/images/configurationBackground.jpg"));
		}catch(IOException e){
			e.printStackTrace();
		}
		Image dimg = img.getScaledInstance(largeur/2, hauteur/2, Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		f_menuConfiguration.setContentPane(new JLabel(imageIcon));		
		
		
		layoutConfiguration = new GridBagLayout();
		f_menuConfiguration.setLayout(layoutConfiguration);		

		
		//Ajout des labels
		final Font police_label = new Font(Font.DIALOG, Font.BOLD, 35);
		label_case = new JLabel("Cases");
		label_case.setFont(police_label);
		label_case.setForeground(Color.BLUE);
		Image iconeBouton = null;
		try {
			iconeBouton = ImageIO.read(getClass().getResource("/images/square.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dimg = iconeBouton.getScaledInstance(largeur/40, hauteur/30, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(dimg);
		label_case.setIcon(imageIcon);		
		
		label_serpents = new JLabel("Serpents");
		label_serpents.setFont(police_label);
		label_serpents.setForeground(Color.RED);
		iconeBouton = null;
		try {
			iconeBouton = ImageIO.read(getClass().getResource("/images/snakeConfig.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dimg = iconeBouton.getScaledInstance(largeur/40, hauteur/30, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(dimg);
		label_serpents.setIcon(imageIcon);		
		
		
		label_echelles = new JLabel("Echelles");	
		label_echelles.setFont(police_label);
		label_echelles.setForeground(Color.GREEN);
		iconeBouton = null;
		try {
			iconeBouton = ImageIO.read(getClass().getResource("/images/ladderConfig.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dimg = iconeBouton.getScaledInstance(largeur/40, hauteur/30, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(dimg);
		label_echelles.setIcon(imageIcon);	

		//Ajout des spinner
		//Spinner choix du nombre de case (commence a 40 cases, minimum 40 cases, maximum 200cases, incremente de 10cases)
		EcouteurSpinner ecouteurSpinner = new EcouteurSpinner();
		spinnerCases = new JSpinner(new SpinnerNumberModel(40,40,200,10));
		spinnerCases.addChangeListener(ecouteurSpinner);
		Dimension d = spinnerCases.getPreferredSize();
		d.width = largeur/10;
		d.height = hauteur/30;
		spinnerCases.setPreferredSize(d);
		spinnerCases.setFont(police_label);
	
		//1 case sur 5 peut etre un serpent
		spinnerSerpents = new JSpinner(new SpinnerNumberModel(0,0,nbCases/pourcentageSerpentEchelle,1));
		d = spinnerSerpents.getPreferredSize();
		d.width = largeur/10;
		d.height = hauteur/30;
		spinnerSerpents.setPreferredSize(d);
		spinnerSerpents.setFont(police_label);
		
		//1 case sur 5 peut etre une echelle
		spinnerEchelles = new JSpinner(new SpinnerNumberModel(0,0,nbCases/pourcentageSerpentEchelle,1));	
		d = spinnerEchelles.getPreferredSize();
		d.width = largeur/10;
		d.height = hauteur/30;
		spinnerEchelles.setPreferredSize(d);
		spinnerEchelles.setFont(police_label);
		
		//Creation des boutons sauvegarde et retour
		final Font police_bouton = new Font(Font.DIALOG, Font.BOLD, 45);
		
		EcouteurBouton ecouteurBouton = new EcouteurBouton();
		b_svgConfig = new JButton("Sauvegarder");
		b_svgConfig.addActionListener(ecouteurBouton);
		b_svgConfig.setFont(police_bouton);
		d = b_svgConfig.getPreferredSize();
		d.width = largeur/5;
		d.height = hauteur/10;
		b_svgConfig.setPreferredSize(d);
		b_svgConfig.setForeground(Color.BLACK);
		iconeBouton = null;
		try {
			iconeBouton = ImageIO.read(getClass().getResource("/images/save.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dimg = iconeBouton.getScaledInstance(largeur/25, hauteur/30, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(dimg);
		b_svgConfig.setIcon(imageIcon);		
		b_svgConfig.setOpaque(false);
		b_svgConfig.setContentAreaFilled(false);
		b_svgConfig.setBorderPainted(false);	
		b_svgConfig.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent evt){
				b_svgConfig.setForeground(Color.GREEN);
			}
			public void mouseExited(MouseEvent evt){
				b_svgConfig.setForeground(Color.BLACK);
			}
		});			
		
		b_retour = new JButton("Retour");	
		b_retour.addActionListener(ecouteurBouton);	
		b_retour.setFont(police_bouton);
		b_retour.setPreferredSize(d);
		b_retour.setForeground(Color.BLACK);
		iconeBouton = null;
		try {
			iconeBouton = ImageIO.read(getClass().getResource("/images/back.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dimg = iconeBouton.getScaledInstance(largeur/25, hauteur/30, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(dimg);
		b_retour.setIcon(imageIcon);		
		b_retour.setOpaque(false);
		b_retour.setContentAreaFilled(false);
		b_retour.setBorderPainted(false);	
		b_retour.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent evt){
				b_retour.setForeground(Color.RED);
			}
			public void mouseExited(MouseEvent evt){
				b_retour.setForeground(Color.BLACK);
			}
		});				
		
		
		
		/* Ajout des boutons sur la fenetre */
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = gbc.gridy = 0;	//la grille commence en [0,0]
		gbc.gridheight = 1; 		// valeur par défaut - peut s'étendre sur une seule ligne.
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets(hauteur/56, largeur/50, hauteur/56, largeur/80);
		f_menuConfiguration.add(label_case,gbc);
		
		gbc.gridx = 1;
		gbc.insets = new Insets(hauteur/56, largeur/50, hauteur/56, largeur/50);
		f_menuConfiguration.add(spinnerCases,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets = new Insets(hauteur/40, largeur/50, hauteur/75,largeur/80);
		f_menuConfiguration.add(label_serpents,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.insets = new Insets(hauteur/40, largeur/50, hauteur/75, largeur/50);
		f_menuConfiguration.add(spinnerSerpents,gbc);
		
		gbc.gridx = 0;
		gbc.gridy =2;
		gbc.insets = new Insets(hauteur/40, largeur/50, hauteur/20, largeur/80);
		f_menuConfiguration.add(label_echelles,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.insets = new Insets(hauteur/40, largeur/50, hauteur/20, largeur/50);
		f_menuConfiguration.add(spinnerEchelles,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.insets = new Insets(hauteur/56, largeur/50, hauteur/75, largeur/50);
		f_menuConfiguration.add(b_svgConfig,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.insets = new Insets(hauteur/56, largeur/50, hauteur/75, largeur/50);
		f_menuConfiguration.add(b_retour,gbc);
		
			
		f_menuConfiguration.setVisible(true);		
		
	}
	
	private class EcouteurBouton implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			if(e.getSource()== b_svgConfig){
				controleurMenuConfiguration.sauvegarderConfiguration((Integer)spinnerCases.getValue()/10,10,(Integer)spinnerEchelles.getValue(),(Integer)spinnerSerpents.getValue());
			}
			else if(e.getSource() == b_retour){
				//action a faire lorsque le bouton retour est clique
				f_menuConfiguration.dispose();
				MenuPrincipal mp = new MenuPrincipal();
				mp.afficherEcran();
			}
		}
	}
	
	private class EcouteurSpinner implements ChangeListener{

		public void stateChanged(ChangeEvent e) {
			if(e.getSource()==spinnerCases){
				/*
				 * On s'assure que l'utilisateur ne peut pas avoir plus de 1/5eme des cases du plateau
				 * comme etant des echelles et pas plus d'un autre 1/5eme des cases du plateau comme etant des serpents
				 */
				nbCases = (Integer)spinnerCases.getValue();
				((SpinnerNumberModel) spinnerEchelles.getModel()).setMaximum(nbCases/pourcentageSerpentEchelle);
				spinnerEchelles.setValue(0);
				((SpinnerNumberModel) spinnerSerpents.getModel()).setMaximum(nbCases/pourcentageSerpentEchelle);
				spinnerSerpents.setValue(0);
			}
		}
	}
	
}
