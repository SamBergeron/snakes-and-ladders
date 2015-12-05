package controleurs;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import domaine.controleDeJeu.Partie;
import domaine.controleDeJeu.StrategieAlgorithme1;
import domaine.controleDeJeu.StrategieAlgorithme2;
import domaine.controleDeJeu.StrategieAlgorithme3;
import domaine.controleDeJeu.StrategieVictoire;
import domaine.elements.AI;
import domaine.elements.De;
import domaine.elements.Humain;
import domaine.elements.Joueur;
import domaine.elements.statique.Couleur;
import domaine.elements.statique.NombreFaces;

public class ControleurMenuConfigurationJoueurs {
	private final int nbJoueurMaxmum = 6;
	
	public void nouvellePartie(ArrayList<Joueur> listeJoueurs, De de, StrategieVictoire strat) {
		Partie p = new Partie();
		p.setDe(de);
		p.setAlgo(strat);
		for (Joueur j : listeJoueurs) {
			p.addJoueur(j);
		}
		FacadeJeu fj = new FacadeJeu();
		fj.demarrerPartie(p);
	}

	public boolean ajouterJoueurs(String nom, Couleur couleur, boolean ai, DefaultTableModel tableModel) {
		if(!validerJoueur(nom, tableModel))
			return false;
		if (ai) {
			Object[] o = { nom, couleur, true };
			tableModel.addRow(o);
		} else {
			Object[] o = { nom, couleur, false };
			tableModel.addRow(o);
		}
		return true;
	}
	
	private boolean validerJoueur(String nom, DefaultTableModel tableModel) {
		if(tableModel.getRowCount() >= nbJoueurMaxmum){
			JOptionPane.showMessageDialog(null, "Le nombre de joueur maximum est atteint");
			return false;
		}
		if(nom.isEmpty()){
			JOptionPane.showMessageDialog(null, "Le nom du joueur je peut pas etre vide");
			return false;
		}
		for(int i = 0 ; i < tableModel.getRowCount() ; i++){
			if(tableModel.getValueAt(i, 0).equals(nom)){
				JOptionPane.showMessageDialog(null, "Le joueur est deja present dans la liste");
				return false;
			}
		}
		return true;
	}

	public void retirerJoueur(int selectedRow, DefaultTableModel tableModel) {
		tableModel.removeRow(selectedRow);
	}

	public void demarerPartie(JTable tableJoueurs, String strat, NombreFaces nbFaces) {
		Partie p = new Partie();
		
		// ce code va etre getho!!!!!
		for (int i = 0; i < tableJoueurs.getRowCount(); i++) {
			String nom = null;
			Couleur c = null;
			Boolean isAI = null;
			//fait la construction des objects joueurs
			for (int j = 0; j < 3; j++) {
				if (j == 0) {
					nom = (String) tableJoueurs.getModel().getValueAt(i, j);
				} else if (j == 1) {
					c = (Couleur) tableJoueurs.getModel().getValueAt(i, j);
				} else {
					isAI = (Boolean)tableJoueurs.getModel().getValueAt(i, j);
				}
			}
			//separe si c'est un ai ou un humain
			if (isAI) {
				AI a = new AI(nom, c);
				p.addJoueur(a);
			} else {
				Humain H = new Humain(nom, c);
				p.addJoueur(H);
			}
		}
		p.setAlgo(selectStrategieAlgo(strat));
		De de = new De(nbFaces);
		p.setDe(de);
		
		FacadeJeu FJ = new FacadeJeu();
		FJ.demarrerPartie(p);
	}

	private StrategieVictoire selectStrategieAlgo(String s) {
		StrategieVictoire strat = null;
		if (s == "algorithme 1") {
			strat = new StrategieAlgorithme1();
			return strat;
		} else if (s == "algorithme 2") {
			strat = new StrategieAlgorithme2();
			return strat;
		} else if (s == "algorithme 3") {
			strat = new StrategieAlgorithme3();
			return strat;
		}
		return strat;
	}
}
