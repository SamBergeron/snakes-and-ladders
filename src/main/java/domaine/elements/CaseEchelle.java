package domaine.elements;

public class CaseEchelle extends Case {
	
	private int redirection;
	
	public CaseEchelle(int position) {
		super(position);
		// TODO Auto-generated constructor stub
	}
	
	public CaseEchelle(int position, int redirection){
		super(position);
		this.redirection = redirection;
	}

	public int getRedirection() {
		return redirection;
	}

	public void setRedirection(int redirection) {
		if(redirection > this.position)
			this.redirection = redirection;
		else throw new Error("Une echelle doit rediriger vers une case plus grande");
	}
	

	@Override
	public String toString() {
		return "CaseEchelle [redirection=" + redirection + "], position =" + position;
	}
	

}
