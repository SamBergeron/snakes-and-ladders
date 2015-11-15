package domaine.elements;

public class CaseEchelle extends Case {
	
	private Case redirection;
	
	public CaseEchelle(int position, Case redirection){
		super(position);
		this.redirection = redirection;
	}
	
	@Override
	public int getPosition(){
		System.out.println("Case Echelle... ON MONTE!");
		return redirection.getPosition();
	}
	
	public Case getRedirection() {
		return redirection;
	}

	public void setRedirection(Case redirection) {
		if(redirection.getPosition() > this.position)
			this.redirection = redirection;
		else throw new Error("Une echelle doit rediriger vers une case plus grande");
	}

	@Override
	public String toString() {
		return "CaseEchelle [redirection=" + redirection + "], position =" + position;
	}
	

}
