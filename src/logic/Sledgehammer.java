package logic;

public class Sledgehammer extends Item{

	private int uses;
	
	public Sledgehammer() {
		Position p1 = new Position(3, 1);
		this.setPosition(p1);
		this.setIcon("SL");
		this.uses = 1;
	}

	public int getUses() {
		return uses;
	}

	public void setUses(int uses) {
		this.uses = uses;
	}
	
	public void decrementUses(){
		uses--;
	}

}
