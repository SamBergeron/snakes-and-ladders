package domaine.elements;

public class CaseSerpent extends Case {
	
	private Case redirection;
	
	public CaseSerpent(int position, Case redirection){
		super(position);
		this.redirection = redirection;
	}
	
	@Override
	public int getPosition(){
		System.out.println("Case Serpent... ON DESCEND!");
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
