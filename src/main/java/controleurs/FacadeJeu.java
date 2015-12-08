package controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JOptionPane;

import domaine.configuration.ConfigPartie;
import domaine.configuration.SerializerConfigPartie;
import domaine.controleDeJeu.Partie;
import domaine.elements.Plateau;
import presentation.vue.MenuPrincipal;
import presentation.vue.PlateauJeu;

public class FacadeJeu {
	private SerializerConfigPartie configLoader;
	private ConfigPartie config;
	private PlateauJeu plateauJeu;
	private Partie partie;
	private int indexJoueurCourant = 0;
	private int nombreJoueur;
	private ActionListener actionListenerUndo;
	private ActionListener actionListenerRedo;
	private ActionListener actionListenerTirer;
	private ActionListener actionListenerQuitter;
	
	private static final int UNDO = 1;
	private static final int REDO = 2;
	private static final int TIRER = 3;
	private static final int QUITTER = 4;
	
	public void demarrerPartie(Partie partie){ //on commence par creer les objets du domaine
		try {
			this.partie = partie;
			// Charger la configuration
			configLoader = new SerializerConfigPartie();
			config = configLoader.chargerConfig();
			
			// Construire le plateau en fonction de la config
			Plateau p = new Plateau(config.getLongueurPlateau(), config.getLargeurPlateau(), config.getNbSerpents(), config.getNbEchelles());
			
			// Ajouter le plateau a la partie fournie par ControleurMenuConfigurationJoueurs
			this.partie.setPlateau(p);
			
			plateauJeu = new PlateauJeu();
			plateauJeu.afficherEcran();
			
			plateauJeu.afficherPlateauJeu(config.getLongueurPlateau()); //affiche le plateau de jeu du nombre de cases voulues
			plateauJeu.setAdresseSerpent(partie.getPlateau().getAdresseSerpent());
			plateauJeu.setAdresseEchelle(partie.getPlateau().getAdresseEchelle());
			plateauJeu.refreshSpecialPanel();
			
			//afficher les pions de tous les joueurs (faire une boucle for)
			nombreJoueur = partie.getJoueurs().size();
			for(int i=0; i<nombreJoueur; i++){
				plateauJeu.afficherPion(partie.getCouleurPion(i), 1,1);
			}
			
			control(); 									//attache les actionslistener aux boutons de plateau jeu
			afficheUndoRedo(indexJoueurCourant);		//active ou desactive les undo/redo selon le type du joueur courant
			JOptionPane.showMessageDialog(null, "Tour du joueur "+partie.afficherNomJoueur(indexJoueurCourant));
			if(partie.getJoueurs().get(indexJoueurCourant).estAI()){		//si le joueur courant est artificiel, il tire tout de suite au de (pas d'autres actions possble)
				while(gererCommande(TIRER)){
					//voir control() - actionlistener3 pour plus d'information
				}
			}
			
		// Si la configuration n'existe pas on passe et on represente le menu principal	
		} catch (IOException e) {
			System.out.println("Si la configuration n'existe pas veuiller en cr\u00e9er une!");
			MenuPrincipal mp = new MenuPrincipal();
			mp.afficherEcran();
		}
	}
	
	/*
	 * Permet de lancer la bonne commande selon le bouton appuye par l'utilisateur
	 * dans PlateauJeu
	 */
	public boolean gererCommande (int commande){
		switch(commande){
		case UNDO:
			boolean resultUndo = partie.undo(indexJoueurCourant);
			if(!resultUndo){
				JOptionPane.showMessageDialog(null, "Impossible de faire un undo");
			}
			plateauJeu.afficherPion(partie.getCouleurPion(indexJoueurCourant), partie.getDeplacement(), partie.getAnciennePosition());
			return false;
			
		case REDO:
			boolean resultRedo = partie.redo(indexJoueurCourant);
			if(!resultRedo){
				JOptionPane.showMessageDialog(null, "Impossible de faire redo, aucun mouvement suivant dans l'historique");
			}
			plateauJeu.afficherPion(partie.getCouleurPion(indexJoueurCourant), partie.getDeplacement(), partie.getAnciennePosition());
			return false;
			
		case TIRER:
			// On commence par lancer le de
			int resultatDe = partie.tirerAuDe();
			JOptionPane.showMessageDialog(null, "Vous avez jou\u00E9 un "+ resultatDe);
			
			// on prends la position du joueur avant son deplacement
			int dep = partie.deplacerJoueur(indexJoueurCourant, resultatDe);
			
			// On affiche le resultat du deplacement
			if(dep == -1)
				JOptionPane.showMessageDialog(null, "Vous avez d\u00E9pass\u00E9 la case finale");
			else {
				String message = partie.getPlateau().getCases().get(dep-1).message();
				JOptionPane.showMessageDialog(null, message);
			}
			
			// On verifie que le deplacement est sur la case finale
			boolean finPartie = partie.verifierVictoire(indexJoueurCourant);
			
			plateauJeu.afficherPion(partie.getCouleurPion(indexJoueurCourant), partie.getDeplacement(), partie.getAnciennePosition());	//on affiche la nouvelle position du joueur
			if(finPartie==true){
				JOptionPane.showMessageDialog(null, "F\u00E9licitation, le gagnant est : "+partie.afficherNomJoueur(indexJoueurCourant));
				indexJoueurCourant=0;
				gererCommande(4);
				return false;
			}
			indexJoueurCourant++;
			if(indexJoueurCourant>=nombreJoueur){
				indexJoueurCourant=0;
			}	
			afficheUndoRedo(indexJoueurCourant);
			if(partie.getJoueurs().get(indexJoueurCourant).estAI() && finPartie != true){
				JOptionPane.showMessageDialog(null, "Tour du joueur "+partie.afficherNomJoueur(indexJoueurCourant));
				return true;
			}
			JOptionPane.showMessageDialog(null, "Tour du joueur "+partie.afficherNomJoueur(indexJoueurCourant));
			return false;
		
		case QUITTER:
		default:
			plateauJeu.getf_plateauJeu().dispose();
			MenuPrincipal mp = new MenuPrincipal();
			mp.afficherEcran();
			return false;
		
		}
	}
	
	/*
	 * Implemente les actionlistener sur les boutons de PlateauJeu
	 * et leur associe l'action a effectuer en cas de besoin
	 */
	public void control(){
       actionListenerUndo = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gererCommande(UNDO);
				}
	    };                
	    plateauJeu.getb_undo().addActionListener(actionListenerUndo);   
	    
	    actionListenerRedo = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gererCommande(REDO);
				}
	    };                
	    plateauJeu.getb_redo().addActionListener(actionListenerRedo);   
	    
	    actionListenerTirer = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					while(gererCommande(TIRER)){
						//tant que c'est le tour d'un joueur de type AI
						//on continue a lancer automatiquement la commande 3 :
						//la seule action qu'un joueur artificiel peut faire
					}
				}		
	    };                
		plateauJeu.getb_tirerDe().addActionListener(actionListenerTirer);  
		
		actionListenerQuitter = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gererCommande(QUITTER);
				}
		};                
		plateauJeu.getb_quitter().addActionListener(actionListenerQuitter);  
	}			

	/*
	 * Active ou Desactive les boutons undo et redo sur le Plateau de jeu
	 * dependamment que le joueur courant soit AI ou Humain
	 */
	public void afficheUndoRedo(int indexJoueurCourant){
		if(partie.getJoueurs().get(indexJoueurCourant).estAI()){ //si c'est un joueur artificiel je cache les boutons undo/redo
			plateauJeu.cacheUndoRedo(true);
		}
		else{ //sinon je les affiche
			plateauJeu.cacheUndoRedo(false);
		}
	}	
}
