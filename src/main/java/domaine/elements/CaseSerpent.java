package domaine.elements;

import javax.swing.JOptionPane;

public class CaseSerpent extends Case {
	
	private Case redirection;
	
	public CaseSerpent(int position, Case redirection){
		super(position);
		this.redirection = redirection;
	}
	
	@Override
	public int getPosition(){
		JOptionPane.showMessageDialog(null, "Oups! On descend un serpent!");
		return redirection.getPosition();
	}
	
	public Case getRedirection() {
		return redirection;
	}

	public void setRedirection(Case redirection) {
		if(redirection.getPosition() < this.position)
			this.redirection = redirection;
		else throw new Error("Un serpent doit rediriger vers une case plus petite");
	}

	@Override
	public String toString() {
		return "CaseSerpent [redirection=" + redirection + "], position =" + position;
	}
	
	
	
}
