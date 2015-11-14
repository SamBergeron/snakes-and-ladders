package main;

import domaine.elements.De;
import domaine.elements.Plateau;
import domaine.elements.statique.*;

public class Main {

	public static void main(String[] args) {
		// Juste un test pour qu'on roule dequoi
		Plateau p = new Plateau(5, 5, 3, 3);
		System.out.println(p);
		
		// Petit test de hasard
		De d = new De(NombreFaces.VINGT);
		for(int i=0; i<10; i++){
			System.out.println(d.rouler());
		}
	}

}
