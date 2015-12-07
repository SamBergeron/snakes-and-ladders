package presentation.vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import controleurs.ControleurMenuPrincipal;

public class MenuPrincipal implements IMenu{
	
	/* Element graphique du menu principal */
	private JButton b_debutPartie;
	private JButton b_configurerPartie;
	private JButton b_quitterPartie;
	private JFrame f_menuPrincipal;
	private GridBagLayout layoutPrincipal;
	
	/* Controleur du menu principal */
	private ControleurMenuPrincipal controleurMenuPrincipal;
	
	/* Path des images utilisees */
	private static final String IMAGE_UNAMED = "images/unnamed.png";
	private static final String IMAGE_MENU = "images/snakeMenuPrincipal.png";
	private static final String IMAGE_ICON_PARAM = "images/paramIcon.png";
	private static final String IMAGE_REDX = "images/redX.png";
	
	/* Recuperation des dimensions de l'ecran */
	private static final Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
	private static final int largeur = (int)tailleEcran.getWidth();
	private static final int hauteur = (int)tailleEcran.getHeight();
	
	/* Definition des polices utilisees */
	private static final Font POLICE_BOUTON = new Font(Font.DIALOG, Font.BOLD, (largeur/50));
	private static final Font POLICE_BOUTON2 = new Font(Font.DIALOG, Font.BOLD, largeur/30);
	
	/* Permet d'afficher la fenetre de menu principal */
	public void afficherEcran(){
		EcouteurBouton ecouteurBouton = new EcouteurBouton();
		controleurMenuPrincipal = new ControleurMenuPrincipal();
		try {
			/* permet d'avoir des composants swing avec un style recent */
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); 			
			
			/* Creation de la fenetre principale */
			f_menuPrincipal = new JFrame("Snakes and Ladders - Menu Principal");
			f_menuPrincipal.setSize(largeur/2, hauteur/2);
			f_menuPrincipal.setLocationRelativeTo(null);
			f_menuPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f_menuPrincipal.setResizable(false);
			
			/* Mise en place de l'image de background du menu principal */
			BufferedImage img = ImageIO.read(getClass().getClassLoader().getResource(IMAGE_UNAMED));
			Image dimg = img.getScaledInstance(largeur/2, hauteur/2, Image.SCALE_SMOOTH);
			
			f_menuPrincipal.setContentPane(new JLabel(new ImageIcon(dimg)));
			layoutPrincipal = new GridBagLayout();
			f_menuPrincipal.setLayout(layoutPrincipal);
			
			/* Creation et configuration du bouton Jouer */
			b_debutPartie = new JButton("Jouer");
			b_debutPartie.addActionListener(ecouteurBouton);
			b_debutPartie.setPreferredSize(new Dimension(largeur/6,hauteur/14));
			b_debutPartie.setFont(POLICE_BOUTON);
			b_debutPartie.setForeground(Color.WHITE);
			Image iconeBouton = ImageIO.read(getClass().getClassLoader().getResource(IMAGE_MENU));

			dimg = iconeBouton.getScaledInstance(largeur/25, hauteur/30, Image.SCALE_SMOOTH);
			b_debutPartie.setIcon(new ImageIcon(dimg));		
			b_debutPartie.setOpaque(false);
			b_debutPartie.setContentAreaFilled(false);
			b_debutPartie.setBorderPainted(false);	
			b_debutPartie.addMouseListener(new MouseAdapter(){
				public void mouseEntered(MouseEvent evt){
					b_debutPartie.setFont(POLICE_BOUTON2);
					int x = b_debutPartie.getWidth();
					int y = b_debutPartie.getHeight();
					Dimension d_button = new Dimension(x*2,y);
					b_debutPartie.setPreferredSize(d_button);
					b_debutPartie.setForeground(Color.GREEN);
				}
				public void mouseExited(MouseEvent evt){
					b_debutPartie.setFont(POLICE_BOUTON);
					int x = b_debutPartie.getWidth();
					int y = b_debutPartie.getHeight();
					Dimension d_button = new Dimension(x/2,y);
					b_debutPartie.setPreferredSize(d_button);
					b_debutPartie.setForeground(Color.WHITE);
				}
			});		
	
			/* Creation et configuration du bouton Configurer */
			b_configurerPartie = new JButton("Configurer");
			b_configurerPartie.addActionListener(ecouteurBouton);
			b_configurerPartie.setPreferredSize(b_debutPartie.getPreferredSize()); //pour que tous les boutons aient la meme taille
			b_configurerPartie.setFont(POLICE_BOUTON);
			b_configurerPartie.setForeground(Color.WHITE);
			iconeBouton = ImageIO.read(getClass().getClassLoader().getResource(IMAGE_ICON_PARAM));

			dimg = iconeBouton.getScaledInstance(largeur/25, hauteur/30, Image.SCALE_SMOOTH);
			b_configurerPartie.setIcon(new ImageIcon(dimg));		
			b_configurerPartie.setOpaque(false);
			b_configurerPartie.setContentAreaFilled(false);
			b_configurerPartie.setBorderPainted(false);	
			b_configurerPartie.addMouseListener(new MouseAdapter(){
				public void mouseEntered(MouseEvent evt){
					b_configurerPartie.setFont(POLICE_BOUTON2);
					int x = b_configurerPartie.getWidth();
					int y = b_configurerPartie.getHeight();
					Dimension d_button = new Dimension(x*2,y);
					b_configurerPartie.setPreferredSize(d_button);
					b_configurerPartie.setForeground(Color.ORANGE);
				}
				public void mouseExited(MouseEvent evt){
					b_configurerPartie.setFont(POLICE_BOUTON);
					int x = b_configurerPartie.getWidth();
					int y = b_configurerPartie.getHeight();
					Dimension d_button = new Dimension(x/2,y);
					b_configurerPartie.setPreferredSize(d_button);
					b_configurerPartie.setForeground(Color.WHITE);
				}
			});
			
			/* Creation et configuration du bouton Quitter */
			b_quitterPartie = new JButton("Quitter");
			b_quitterPartie.addActionListener(ecouteurBouton);
			b_quitterPartie.setPreferredSize(b_debutPartie.getPreferredSize());
			b_quitterPartie.setFont(POLICE_BOUTON);
			b_quitterPartie.setForeground(Color.WHITE);

			iconeBouton = ImageIO.read(getClass().getClassLoader().getResource(IMAGE_REDX));
			b_quitterPartie.setIcon(new ImageIcon(iconeBouton.getScaledInstance(largeur/25, hauteur/30, Image.SCALE_SMOOTH)));		
			b_quitterPartie.setOpaque(false);
			b_quitterPartie.setContentAreaFilled(false);
			b_quitterPartie.setBorderPainted(false);	
			b_quitterPartie.addMouseListener(new MouseAdapter(){
				public void mouseEntered(MouseEvent evt){
					b_quitterPartie.setFont(POLICE_BOUTON2);
					int x = b_quitterPartie.getWidth();
					int y = b_quitterPartie.getHeight();
					Dimension d_button = new Dimension(x*2,y);
					b_quitterPartie.setPreferredSize(d_button);
					b_quitterPartie.setForeground(Color.BLACK);
				}
				public void mouseExited(MouseEvent evt){
					b_quitterPartie.setFont(POLICE_BOUTON);
					int x = b_quitterPartie.getWidth();
					int y = b_quitterPartie.getHeight();
					Dimension d_button = new Dimension(x/2,y);
					b_quitterPartie.setPreferredSize(d_button);
					b_quitterPartie.setForeground(Color.WHITE);
				}
			});		
			
			/* Insertion des boutons dans la fenetre */
			GridBagConstraints gbc = genererGrid(hauteur, largeur);
			f_menuPrincipal.add(b_quitterPartie,gbc);
			f_menuPrincipal.setVisible(true);
			
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private GridBagConstraints genererGrid(int hauteur, int largeur){
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
		
		return gbc;
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
