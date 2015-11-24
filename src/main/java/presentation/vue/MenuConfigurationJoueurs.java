package presentation.vue;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.beans.PersistenceDelegate;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.plaf.metal.MetalBorders.TextFieldBorder;
import javax.swing.text.Position;

public class MenuConfigurationJoueurs implements IMenu {
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
	
	private JTextArea textNomJoueur;
	
	private JComboBox<domaine.elements.statique.Couleur> comboCouleur;
	private JComboBox<domaine.elements.statique.NombreFaces> comboValeurDE;
	
	private JCheckBox checkAI;
	
	private JTable tableJoueurs;
	private JList<domaine.elements.Joueur> test;
	
	@Override
	public void afficherEcran() {
		int hauteurFrame = Toolkit.getDefaultToolkit().getScreenSize().height/2;
		int LargeurFrame = Toolkit.getDefaultToolkit().getScreenSize().width/2;
		
		//configuration du frame de configuration
		frameConteneurconfiguration = new JFrame("Snakes and Ladders - Menu Configuration");
		frameConteneurconfiguration.setLayout(null);
		frameConteneurconfiguration.setSize(LargeurFrame, hauteurFrame);
		frameConteneurconfiguration.setResizable(false);
		frameConteneurconfiguration.setLocationRelativeTo(null);
		frameConteneurconfiguration.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		hauteurFrame = frameConteneurconfiguration.getHeight();
		LargeurFrame = frameConteneurconfiguration.getWidth();
		
		//section panel joueurs  /////////////////
		panelJoueurs = new JPanel(null);
		panelJoueurs.setBounds(0, 5, LargeurFrame / 3 - 5, hauteurFrame - 40);
		
		// panel qui contient le nom du joueur
			JPanel panelNomJoueur = new JPanel(new GridLayout(1, 2));
			panelNomJoueur.setBounds(5, 45, panelJoueurs.getWidth() - 10, 20);
				labelNomJoueur = new JLabel("Nom");
				textNomJoueur = new JTextArea();
			
			panelNomJoueur.add(labelNomJoueur);
			panelNomJoueur.add(textNomJoueur);
			
		// panel qui contient la couleur du joueur
			JPanel panelCouleurJoueur = new JPanel(new GridLayout(1, 2));
			panelCouleurJoueur.setBounds(5, panelNomJoueur.getY()+panelNomJoueur.getHeight()+5, panelJoueurs.getWidth() - 10, 20);
				labelCouleurJoueur = new JLabel("Couleur du joueur");
				comboCouleur = new JComboBox<>(domaine.elements.statique.Couleur.values());
			
			panelCouleurJoueur.add(labelCouleurJoueur);
			panelCouleurJoueur.add(comboCouleur);
			
		// panel qui contient si le joueur est un AI
			JPanel panelAI = new JPanel(new GridLayout(1, 2));
			panelAI.setBounds(5, panelCouleurJoueur.getY()+panelCouleurJoueur.getHeight()+5, panelJoueurs.getWidth() - 10, 20);
				labelAI = new JLabel("Joueur AI");
				checkAI = new JCheckBox();
			
			panelAI.add(labelAI);
			panelAI.add(checkAI);
			
			
			buttonAjouterJoueur = new JButton("Ajouter joueur à la liste");
			buttonAjouterJoueur.setBounds(5, panelAI.getHeight()+panelAI.getY()+5, panelJoueurs.getWidth() - 10, 20);
			
			buttonRetour = new JButton("Retour menu principal");
			buttonRetour.setBounds(5, panelJoueurs.getHeight() - 40, panelJoueurs.getWidth()-10, 40);
			
		panelJoueurs.add(panelNomJoueur);
		panelJoueurs.add(panelCouleurJoueur);
		panelJoueurs.add(panelAI);
		panelJoueurs.add(buttonAjouterJoueur);
		panelJoueurs.add(buttonRetour);
		
		//section panel DE  //////////////////////
		panelDe = new JPanel(null);
		panelDe.setBounds(panelJoueurs.getWidth(), 5, LargeurFrame / 3 - 5, hauteurFrame - 40);

		labelJoueur = new JLabel("Partie");
		labelJoueur.setFont(new Font(Font.DIALOG, Font.BOLD, 35));
		labelJoueur.setBounds(20, 5, panelJoueurs.getWidth(), 35);;
		
			JPanel panelValeurDE = new JPanel(new GridLayout(1, 2));
			panelValeurDE.setBounds(5, 45, panelDe.getWidth() - 10, 20);
			labelDE = new JLabel("Valeur du dé");
			comboValeurDE = new JComboBox<>(domaine.elements.statique.NombreFaces.values());
			panelValeurDE.add(labelDE);
			panelValeurDE.add(comboValeurDE);
			
		
		panelDe.add(labelJoueur);
		panelDe.add(panelValeurDE);
		
		//section panel fait  ////////////////////
		panelFait = new JPanel(null);
		panelFait.setBounds(panelDe.getX() + panelDe.getX(), 5, LargeurFrame / 3 - 5, hauteurFrame - 40);
		
			tableJoueurs = new JTable();
			tableJoueurs.setBounds(5, 45, panelFait.getWidth() - 10, panelFait.getHeight() / 2);
			
			buttonRetirerJoueur = new JButton("Retirer joueur");
			buttonRetirerJoueur.setBounds(5, tableJoueurs.getY() + tableJoueurs.getHeight() + 5, panelFait.getWidth() - 10, 20);

			nouvellePartieBouton = new JButton("Demmarer partie");
			nouvellePartieBouton.setBounds(5, panelDe.getHeight() - 40, panelDe.getWidth() - 10, 40);
			
		panelFait.add(tableJoueurs);
		panelFait.add(buttonRetirerJoueur);
		panelFait.add(nouvellePartieBouton);
		//panelContenu.add(panelJoueurs);
		
		frameConteneurconfiguration.add(panelJoueurs);
		frameConteneurconfiguration.add(panelDe);
		frameConteneurconfiguration.add(panelFait);
		frameConteneurconfiguration.setVisible(true);
	}
}
