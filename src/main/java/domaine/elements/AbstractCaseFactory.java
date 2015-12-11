package domaine.elements;

import java.awt.Point;

public interface AbstractCaseFactory {
	
	public abstract Case genererCaseStandard(int pos);
	public abstract Case genererCaseMontee();
	public abstract Case genererCaseDescente();
	
	public abstract Point[] getAdressesMontees();
	public abstract Point[] getAdressesDescentes();
}
