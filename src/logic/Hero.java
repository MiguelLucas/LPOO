package logic;

public class Hero extends Item{

	private boolean armedSword;
	
	public Hero(){
		this.setX(1);
		this.setY(1);
		this.setIcon("H ");
		this.armedSword = false;
	}

	public boolean isArmedSword() {
		return armedSword;
	}

	public void setArmedSword(boolean armedSword) {
		this.armedSword = armedSword;
	}
	
}
