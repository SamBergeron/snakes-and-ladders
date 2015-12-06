package presentation.vue;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controleurs.ControleurMenuConfigurationJoueurs;
import domaine.elements.statique.Couleur;
import domaine.elements.statique.NombreFaces;

public class MenuConfigurationJoueurs implements IMenu{
	
	/* Element graphique du menu configuration des joueurs */
	private JFrame frameConteneurconfiguration;
	private JPanel panelJoueurs;
	private JPanel panelDe;
	private JPanel panelFait;
	
	private JButton nouvellePartieBouton;
	private JButton buttonAjouterJoueur;
	private JButton buttonRetirerJoueur;
	private JButton buttonRetour;	
	
	private JLabel labelJoueur;
	private JLabel labelNomJoueur;
	private JLabel labelCouleurJoueur;
	private JLabel labelAI;
	private JLabel labelDE;
	private JLabel labelAlgo;
	
	private JTextField textNomJoueur;
	
	private JComboBox<domaine.elements.statique.Couleur> comboCouleur;
	private JComboBox<domaine.elements.statique.NombreFaces> comboValeurDE;
	private JComboBox<String> comboAlgo;
	
	private JCheckBox checkAI;
	
	private JTable tableJoueurs;
	private String[] tableCollumnName = {"Nom","Couleur","AI"};
	private DefaultTableModel tableModel;
	
	/* Path des images utilisees */
	private static final String IMAGE_CONFIG = "images/configurationBackground.jpg";
	
	/* Recuperation des dimensions de l'ecran */
	private static int hauteurFrame = Toolkit.getDefaultToolkit().getScreenSize().height/2;
	private static int LargeurFrame = Toolkit.getDefaultToolkit().getScreenSize().width/2;
	
	/* Definition des polices utilisees */
	private static final Font POLICE_BOUTON = new Font(Font.DIALOG, Font.BOLD, (LargeurFrame/40));
	private static final Font POLICE_BOUTON2 = new Font(Font.DIALOG, Font.BOLD, LargeurFrame/80);

	/* Permet d'afficher la fenetre de menu de configuration de joueurs */
	public void afficherEcran() {
		EcouterBoutton e = new EcouterBoutton();
		EcouterText k = new EcouterText();
		

		
		//configuration du frame de configuration
		frameConteneurconfiguration = new JFrame("Snakes and Ladders - Menu Configuration");
		frameConteneurconfiguration.setLayout(null);
		frameConteneurconfiguration.setSize(LargeurFrame, hauteurFrame);
		frameConteneurconfiguration.setResizable(false);
		frameConteneurconfiguration.setLocationRelativeTo(null);
		frameConteneurconfiguration.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		hauteurFrame = frameConteneurconfiguration.getHeight();
		LargeurFrame = frameConteneurconfiguration.getWidth();
		
		/* Mise en place de l'image de background du menu de configuration */
		BufferedImage img;
		try {
			img = ImageIO.read(getClass().getClassLoader().getResource(IMAGE_CONFIG));
			ImageIcon imageIcon = new ImageIcon(img.getScaledInstance(LargeurFrame, hauteurFrame, Image.SCALE_SMOOTH));
			frameConteneurconfiguration.setContentPane(new JLabel(imageIcon));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		//section panel joueurs  /////////////////
		panelJoueurs = new JPanel(null);
		panelJoueurs.setBounds(0, 5, LargeurFrame / 3 - 5, hauteurFrame - 40);
		panelJoueurs.setOpaque(false);
		// panel qui contient le nom du joueur
			JPanel panelNomJoueur = new JPanel(new GridLayout(1, 2));
			panelNomJoueur.setBounds(5, 45, panelJoueurs.getWidth() - 10, 20);
			panelNomJoueur.setOpaque(false);
				labelNomJoueur = new JLabel("Nom");
				labelNomJoueur.setFont(POLICE_BOUTON);
				textNomJoueur = new JTextField();
				textNomJoueur.setFont(POLICE_BOUTON2);
				textNomJoueur.addKeyListener(k);
				
			panelNomJoueur.add(labelNomJoueur);
			panelNomJoueur.add(textNomJoueur);
			
		// panel qui contient la couleur du joueur
			JPanel panelCouleurJoueur = new JPanel(new GridLayout(1, 2));
			panelCouleurJoueur.setOpaque(false);
			panelCouleurJoueur.setBounds(5, panelNomJoueur.getY()+panelNomJoueur.getHeight()+5, panelJoueurs.getWidth() - 10, 20);
				labelCouleurJoueur = new JLabel("Couleur");
				labelCouleurJoueur.setFont(POLICE_BOUTON);
				comboCouleur = new JComboBox<Couleur>(domaine.elements.statique.Couleur.values());
			
			panelCouleurJoueur.add(labelCouleurJoueur);
			panelCouleurJoueur.add(comboCouleur);
			
		// panel qui contient si le joueur est un AI
			JPanel panelAI = new JPanel(new GridLayout(1, 2));
			panelAI.setOpaque(false);
			panelAI.setBounds(5, panelCouleurJoueur.getY()+panelCouleurJoueur.getHeight()+5, panelJoueurs.getWidth() - 10, 20);
				labelAI = new JLabel("Joueur AI");
				labelAI.setFont(POLICE_BOUTON);
				checkAI = new JCheckBox();
				checkAI.setOpaque(false);
			panelAI.add(labelAI);
			panelAI.add(checkAI);
			
			
			buttonAjouterJoueur = new JButton("Ajouter joueur a la liste");
			buttonAjouterJoueur.setFont(POLICE_BOUTON2);
			buttonAjouterJoueur.setBounds(5, panelAI.getHeight()+panelAI.getY()+5, panelJoueurs.getWidth() - 10, 20);
			buttonAjouterJoueur.addActionListener(e);
			
			buttonRetour = new JButton("Retour menu principal");
			buttonRetour.setFont(POLICE_BOUTON);
			buttonRetour.setBounds(5, panelJoueurs.getHeight() - 40, panelJoueurs.getWidth()-10, 40);
			buttonRetour.addActionListener(e);
			
		panelJoueurs.add(panelNomJoueur);
		panelJoueurs.add(panelCouleurJoueur);
		panelJoueurs.add(panelAI);
		panelJoueurs.add(buttonAjouterJoueur);
		panelJoueurs.add(buttonRetour);
		
		//section panel DE  //////////////////////
		panelDe = new JPanel(null);
		panelDe.setBounds(panelJoueurs.getWidth(), 5, LargeurFrame / 3 - 5, hauteurFrame - 40);
		panelDe.setOpaque(false);
		
		labelJoueur = new JLabel("Options Partie");
		labelJoueur.setFont(POLICE_BOUTON);
		labelJoueur.setBounds(20, 5, panelJoueurs.getWidth(), 35);;
		
			JPanel panelValeurDE = new JPanel(new GridLayout(1, 2));
			panelValeurDE.setBounds(5, 45, panelDe.getWidth() - 10, 20);
			panelValeurDE.setOpaque(false);
				labelDE = new JLabel("De : Valeur");
				labelDE.setFont(POLICE_BOUTON);
				comboValeurDE = new JComboBox<NombreFaces>(domaine.elements.statique.NombreFaces.values());
			panelValeurDE.add(labelDE);
			panelValeurDE.add(comboValeurDE);
			
			JPanel panelAlgo = new JPanel(new GridLayout(1, 2));
			panelAlgo.setBounds(5, panelValeurDE.getY() + panelValeurDE.getHeight() + 5, panelDe.getWidth() - 10, 20);
			panelAlgo.setOpaque(false);
				labelAlgo = new JLabel("Difficulte");
				labelAlgo.setFont(POLICE_BOUTON);
				comboAlgo = new JComboBox<String>();
				comboAlgo.addItem("Facile (algorithme 1)");
				comboAlgo.addItem("Moyen (algorithme 2)");
				comboAlgo.addItem("Difficile (algorithme 3)");			
			panelAlgo.add(labelAlgo);
			panelAlgo.add(comboAlgo);
			
		panelDe.add(panelAlgo);
		panelDe.add(labelJoueur);
		panelDe.add(panelValeurDE);
		
		//section panel fait  ////////////////////
		panelFait = new JPanel(null);
		panelFait.setBounds(panelDe.getX() + panelDe.getX(), 5, LargeurFrame / 3 - 5, hauteurFrame - 40);
		panelFait.setOpaque(false);

			tableModel = new DefaultTableModel(tableCollumnName, 0);
			tableJoueurs = new JTable(tableModel);
			JScrollPane scroll = new JScrollPane(tableJoueurs);
			scroll.setBounds(5, 45, panelFait.getWidth() - 10, panelFait.getHeight() / 2);
			
			buttonRetirerJoueur = new JButton("Retirer joueur");
			buttonRetirerJoueur.setFont(POLICE_BOUTON2);
			buttonRetirerJoueur.setBounds(5, scroll.getY() + scroll.getHeight() + 5, panelFait.getWidth() - 10, 20);
			buttonRetirerJoueur.addActionListener(e);

			nouvellePartieBouton = new JButton("Demarrer partie");
			nouvellePartieBouton.setFont(POLICE_BOUTON);
			nouvellePartieBouton.setBounds(5, panelDe.getHeight() - 40, panelDe.getWidth() - 10, 40);
			nouvellePartieBouton.addActionListener(e);
			
		panelFait.add(scroll);
		panelFait.add(buttonRetirerJoueur);
		panelFait.add(nouvellePartieBouton);
		
		frameConteneurconfiguration.add(panelJoueurs);
		frameConteneurconfiguration.add(panelDe);
		frameConteneurconfiguration.add(panelFait);
		frameConteneurconfiguration.setVisible(true);
	}

	private void ajouterJoueur(){
		ControleurMenuConfigurationJoueurs c = new ControleurMenuConfigurationJoueurs();
		if(c.ajouterJoueurs(textNomJoueur.getText(), (Couleur)comboCouleur.getSelectedItem(), checkAI.isSelected(), tableModel)){
			comboCouleur.removeItemAt(comboCouleur.getSelectedIndex());
			textNomJoueur.setText("");
			textNomJoueur.requestFocus();
		}
	}
	
	/**
	 * Classe priv�e qui fait office de action listener pour les button
	 * */
	private class EcouterBoutton implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == buttonAjouterJoueur){
				ajouterJoueur();
				
			}else if(e.getSource() == buttonRetirerJoueur){
				if(tableJoueurs.getSelectedRow() != -1){
					comboCouleur.addItem((Couleur)tableModel.getValueAt(tableJoueurs.getSelectedRow(), 1));
					ControleurMenuConfigurationJoueurs c = new ControleurMenuConfigurationJoueurs();
					c.retirerJoueur(tableJoueurs.getSelectedRow(), tableModel);
					if(!buttonAjouterJoueur.isEnabled()){
						buttonAjouterJoueur.setEnabled(true);
					}
				}
				
			}else if(e.getSource() == buttonRetour){
				frameConteneurconfiguration.dispose();
				MenuPrincipal MP = new MenuPrincipal();
				MP.afficherEcran();
				
			}else if(e.getSource() == nouvellePartieBouton){
				if(tableJoueurs.getRowCount() < 2){
					JOptionPane.showMessageDialog(null, "Le nombre de joueurs ne peut pas etre inferieur a 2");
					return;
				}
				frameConteneurconfiguration.dispose();
				ControleurMenuConfigurationJoueurs c = new ControleurMenuConfigurationJoueurs();
				c.demarerPartie(tableJoueurs, (String)comboAlgo.getSelectedItem(), (NombreFaces)comboValeurDE.getSelectedItem());
			}
		}
	}
	
	//Permet d'ajouter un joueur en appuyant sur la touche entree
	private class EcouterText implements KeyListener{
		public void keyPressed(KeyEvent arg0) {
			if(arg0.getKeyChar() == 10){
				ajouterJoueur();
			}
		}

		public void keyReleased(KeyEvent arg0) {
			return;	
		}

		public void keyTyped(KeyEvent arg0) {
			return;
		}
	}
}