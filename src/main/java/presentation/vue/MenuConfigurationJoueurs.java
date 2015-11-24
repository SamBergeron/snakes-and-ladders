package presentation.vue;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PersistenceDelegate;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.plaf.metal.MetalBorders.TextFieldBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.Position;

import controleurs.ControleurMenuConfigurationJoueurs;
import domaine.controleDeJeu.StrategieVictoire;
import domaine.elements.De;
import domaine.elements.Humain;
import domaine.elements.Joueur;
import domaine.elements.statique.Couleur;
import domaine.elements.statique.NombreFaces;

public class MenuConfigurationJoueurs implements IMenu{
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
	
	private JTextArea textNomJoueur;
	
	private JComboBox<domaine.elements.statique.Couleur> comboCouleur;
	private JComboBox<domaine.elements.statique.NombreFaces> comboValeurDE;
	private JComboBox<String> comboAlgo;
	
	private JCheckBox checkAI;
	
	private JTable tableJoueurs;
	private String[] tableCollumnName = {"Nom","Couleur","Type"};
	private DefaultTableModel tableModel;
	private ArrayList<domaine.elements.Joueur> listeJoueurs;

	
	public void afficherEcran() {
		EcouterBoutton e = new EcouterBoutton();
		
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
				comboCouleur = new JComboBox<Couleur>(domaine.elements.statique.Couleur.values());
			
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
			buttonAjouterJoueur.addActionListener(e);
			
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
				comboValeurDE = new JComboBox<NombreFaces>(domaine.elements.statique.NombreFaces.values());
			panelValeurDE.add(labelDE);
			panelValeurDE.add(comboValeurDE);
			
			JPanel panelAlgo = new JPanel(new GridLayout(1, 2));
			panelAlgo.setBounds(5, panelValeurDE.getY() + panelValeurDE.getHeight() + 5, panelDe.getWidth() - 10, 20);
				labelAlgo = new JLabel("Algorithme de victoire");
				comboAlgo = new JComboBox<String>();
				comboAlgo.addItem("algorithme 1");
				comboAlgo.addItem("algorithme 2");
				comboAlgo.addItem("algorithme 3");
			panelAlgo.add(labelAlgo);
			panelAlgo.add(comboAlgo);
			
		panelDe.add(panelAlgo);
		panelDe.add(labelJoueur);
		panelDe.add(panelValeurDE);
		
		//section panel fait  ////////////////////
		panelFait = new JPanel(null);
		panelFait.setBounds(panelDe.getX() + panelDe.getX(), 5, LargeurFrame / 3 - 5, hauteurFrame - 40);

			tableModel = new DefaultTableModel(tableCollumnName, 0);
			tableJoueurs = new JTable(tableModel);
			JScrollPane scroll = new JScrollPane(tableJoueurs);
			scroll.setBounds(5, 45, panelFait.getWidth() - 10, panelFait.getHeight() / 2);
			
			buttonRetirerJoueur = new JButton("Retirer joueur");
			buttonRetirerJoueur.setBounds(5, scroll.getY() + scroll.getHeight() + 5, panelFait.getWidth() - 10, 20);
			buttonRetirerJoueur.addActionListener(e);

			nouvellePartieBouton = new JButton("Demmarer partie");
			nouvellePartieBouton.setBounds(5, panelDe.getHeight() - 40, panelDe.getWidth() - 10, 40);
			nouvellePartieBouton.addActionListener(e);
			
		panelFait.add(scroll);
		panelFait.add(buttonRetirerJoueur);
		panelFait.add(nouvellePartieBouton);
		//panelContenu.add(panelJoueurs);
		
		frameConteneurconfiguration.add(panelJoueurs);
		frameConteneurconfiguration.add(panelDe);
		frameConteneurconfiguration.add(panelFait);
		frameConteneurconfiguration.setVisible(true);
	}
	/**
	 * Classe privée qui fait office de action listener
	 * */
	private class EcouterBoutton implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == buttonAjouterJoueur){
				ControleurMenuConfigurationJoueurs c = new ControleurMenuConfigurationJoueurs();
				if(c.ajouterJoueurs(textNomJoueur.getText(), (Couleur)comboCouleur.getSelectedItem(), checkAI.isSelected(), tableModel)){
					comboCouleur.removeItemAt(comboCouleur.getSelectedIndex());
				}
			
			}else if(e.getSource() == buttonRetirerJoueur){
				if(tableJoueurs.getSelectedRow() != -1){
					ControleurMenuConfigurationJoueurs c = new ControleurMenuConfigurationJoueurs();
					c.retirerJoueur(tableJoueurs.getSelectedRow(), tableModel);
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
}


