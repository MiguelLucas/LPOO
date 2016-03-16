package logic;

import logic.Item.Position;

public class Hero extends Item{

	private boolean armedSword;
	private boolean armedSledgehammer;
	private boolean isDead;	
	
	public Hero(){
		Position p1 = new Position(1, 1);
		this.setPosition(p1);
		this.setIcon("H ");
		this.armedSword = false;
		this.armedSledgehammer = false;
		this.isDead = false;
	}

	public boolean isArmedSword() {
		return armedSword;
	}

	public void setArmedSword(boolean armedSword) {
		this.armedSword = armedSword;
	}
	
	public boolean isArmedSledgehammer() {
		return armedSledgehammer;
	}

	public void setArmedSledgehammer(boolean armedSledgehammer) {
		this.armedSledgehammer = armedSledgehammer;
	}

	public boolean isDead() {
		return isDead;
	}
	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}	
}
