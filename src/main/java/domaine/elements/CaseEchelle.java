package domaine.elements;

public class CaseEchelle extends Case {
	
	private Case redirection;
	
	public CaseEchelle(int position, Case redirection){
		super(position);
		this.redirection = redirection;
	}
	
	@Override
	public int getPosition(){
		return redirection.getPosition();
	}
	
	public Case getRedirection() {
		return redirection;
	}

	public void setRedirection(Case redirection) {
		if(redirection.getPosition() > this.position)
			this.redirection = redirection;
		else throw new Error("Une \u00e9chelle doit rediriger vers une case plus grande");
	}
	
	@Override
	public String message() {
		return "Chanceux! Vous avez tomb\u00e9 sur la case " + (position + 1)
				+ " , et montez l'\u00E9chele jusqu'\u00E0 la case "
				+ getPosition();
	}
	
	@Override
	public String toString() {
		return "CaseEchelle [redirection=" + redirection + "], position =" + position;
	}
	

}
