package main;

import domaine.elements.Case;
import domaine.elements.CaseSerpent;
import domaine.elements.Plateau;
import domaine.elements.statique.*;
import domaine.controleDeJeu.*;

public class Main {

	public static void main(String[] args) {
		// Juste un test pour qu'on roule dequoi
		Plateau p = new Plateau(4, 4, 3, 3);
		System.out.println(p);
		
		//Test des differents des
		De test6Face = new De6Faces();
		for(int i=0; i<18; i++)
			test6Face.rouler();
		System.out.println();
		
		De test8Face = new De8Faces();
		for(int i=0; i<24; i++)
			test8Face.rouler();
		System.out.println();
		
		De test20Face = new De20Faces();
		for(int i=0; i<60; i++)
			test20Face.rouler();
		System.out.println();

	}

}
