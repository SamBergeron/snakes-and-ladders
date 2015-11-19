package presentation.vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controleurs.ControleurMenuConfiguration;

public class MenuConfiguration implements IMenu {
	
	JFrame f_menuConfiguration;
	
	ControleurMenuConfiguration controleurMenuConfiguration;
	
	JPanel panelConfiguration; //un panel gerant la configuration generale de la page de configuration
	JPanel panel1,panel2,panel3,panel4,panel5,panel6,panel7,panel8; //un panel pour chaque case du GridLayout
	
	GridLayout layoutConfiguration;
	
	JLabel label_case;
	JLabel label_serpents;
	JLabel label_echelles;
	
	JButton b_svgConfig;
	JButton b_retour;
	
	JSpinner spinnerCases;
	JSpinner spinnerSerpents;
	JSpinner spinnerEchelles;
	
	int nbCases = 40; //nb de cases choisit par le joueur
	
	//signifie que 1/5eme au maximum des cases peuvent etre des serpents 
	//signifie que un autre 1/5eme au maximum des cases peuvent etre des echelles 
	final int pourcentageSerpentEchelle = 5;

	public void afficherEcran() {
		
		/* Mode Graphique */
		
		controleurMenuConfiguration = new ControleurMenuConfiguration();
		
		f_menuConfiguration = new JFrame();
	
		f_menuConfiguration.setTitle("Snakes and Ladders - Menu Configuration");
		Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
		int largeur = (int)tailleEcran.getWidth();
		int hauteur = (int)tailleEcran.getHeight();

		f_menuConfiguration.setSize(largeur/2, hauteur/2);
		f_menuConfiguration.setLocationRelativeTo(null);
		f_menuConfiguration.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f_menuConfiguration.setResizable(false);	
		
		
		//Ajout du panel et de son layout
		panelConfiguration = new JPanel();
		layoutConfiguration = new GridLayout(4,2);
		panelConfiguration.setLayout(layoutConfiguration);
		
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		panel4 = new JPanel();
		panel5 = new JPanel();
		panel6 = new JPanel();
		panel7 = new JPanel();
		panel8 = new JPanel();
		
		//Ajout des labels
		label_case = new JLabel("Cases");
		label_serpents = new JLabel("Serpents");
		panelConfiguration.add(label_serpents);
		label_echelles = new JLabel("Echelles");	
		panelConfiguration.add(label_echelles);

		//Ajout des spinner
		//Spinner choix du nombre de case (commence a 40 cases, minimum 40 cases, maximum 200cases, incremente de 10cases)
		EcouteurSpinner ecouteurSpinner = new EcouteurSpinner();
		spinnerCases = new JSpinner(new SpinnerNumberModel(40,40,200,10));
		spinnerCases.addChangeListener(ecouteurSpinner);
		Dimension d = spinnerCases.getPreferredSize();
		d.width = 150;
		spinnerCases.setPreferredSize(d);
		panel1.add(label_case);
		panel2.add(spinnerCases);
		panelConfiguration.add(panel1);
		panelConfiguration.add(panel2);

		
		//1 case sur 5 peut etre un serpent
		spinnerSerpents = new JSpinner(new SpinnerNumberModel(0,0,nbCases/pourcentageSerpentEchelle,1));
		d = spinnerSerpents.getPreferredSize();
		d.width = 150;
		spinnerSerpents.setPreferredSize(d);
		panel3.add(label_serpents);
		panel4.add(spinnerSerpents);
		panelConfiguration.add(panel3);
		panelConfiguration.add(panel4);
		
		//1 case sur 5 peut etre une echelle
		spinnerEchelles = new JSpinner(new SpinnerNumberModel(0,0,nbCases/pourcentageSerpentEchelle,1));	
		d = spinnerEchelles.getPreferredSize();
		d.width = 150;
		spinnerEchelles.setPreferredSize(d);
		panel5.add(label_echelles);
		panel6.add(spinnerEchelles);
		panelConfiguration.add(panel5);
		panelConfiguration.add(panel6);
		
		//Ajout des boutons sauvegarde et retour
		EcouteurBouton ecouteurBouton = new EcouteurBouton();
		b_svgConfig = new JButton("Sauvegarder Configuration");
		b_svgConfig.addActionListener(ecouteurBouton);
		b_retour = new JButton("Retour Menu Principal");	
		b_retour.addActionListener(ecouteurBouton);
		panel7.add(b_svgConfig);
		panel8.add(b_retour);
		panelConfiguration.add(panel7);
		panelConfiguration.add(panel8);		
		
			
		f_menuConfiguration.add(panelConfiguration);
		f_menuConfiguration.pack();
		f_menuConfiguration.setVisible(true);		

		// Mode Console
		/*
		boolean retry = true;
		SerializerConfigPartie serializerConfig = new SerializerConfigPartie();
		ConfigPartie config = new ConfigPartie();
		while(retry){
			try {
				Scanner sc = new Scanner(System.in);
				System.out.println("Veuillez configurer votre partie");
				System.out.println("Commencez par donner les dimensions du plateau");
				System.out.println("Longueur: ");
				config.setLongueurPlateau(sc.nextInt());
				
				System.out.println("Largeur: ");
				config.setLargeurPlateau(sc.nextInt());
				
				System.out.println("Nombre d'�chelles: ");
				config.setNbEchelles(sc.nextInt());
				
				System.out.println("Nombre de Serpents: ");
				config.setNbSerpents(sc.nextInt());
				
				serializerConfig.sauverConfig(config);
				
				// On retourne au menu principal
				MenuPrincipal m = new MenuPrincipal();
				m.afficherEcran();
				
				sc.close();
				retry = false;
				
			} catch (InputMismatchException e) {
				System.out.println("Cette entr�e est invalide veuillez r�essayer");
			}
		}*/
		
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
