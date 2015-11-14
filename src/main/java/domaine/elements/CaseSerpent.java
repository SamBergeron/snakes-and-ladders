package domaine.elements;

public class CaseSerpent extends Case {
	
	private int redirection;
	
	public CaseSerpent(int position) {
		super(position);
	}
	
	public CaseSerpent(int position, int redirection){
		super(position);
		this.redirection = redirection;
	}

	public int getRedirection() {
		return redirection;
	}

	public void setRedirection(int redirection) {
		if(redirection < this.position)
			this.redirection = redirection;
		else throw new Error("Un serpent doit rediriger vers une case plus petite");
	}

	@Override
	public String toString() {
		return "CaseSerpent [redirection=" + redirection + "], position =" + position;
	}
	
	
	
}
