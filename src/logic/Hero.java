package logic;

public class Hero extends Item{

	private boolean armedSword;
	private boolean isDead;	
	
	public Hero(){
		this.setX(1);
		this.setY(1);
		this.setIcon("H ");
		this.armedSword = false;
		this.isDead = false;
	}

	public boolean isArmedSword() {
		return armedSword;
	}

	public void setArmedSword(boolean armedSword) {
		this.armedSword = armedSword;
	}
	
	public boolean isDead() {
		return isDead;
	}
	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}	
}
