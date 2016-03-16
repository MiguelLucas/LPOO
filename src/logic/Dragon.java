package logic;

import java.util.Random;

import javax.swing.Icon;

import logic.Item.Position;

public class Dragon extends Item {
	private boolean isSleeping;	
	private boolean isDead;	
	public Dragon(){
		Position p1 = new Position(1, 3);
		this.setPosition(p1);
		this.setIcon("D ");
		this.isSleeping = false;
		this.isDead = false;
	}
	public boolean isSleeping() {
		return isSleeping;
	}
	public void setSleeping(boolean isSleeping) {
		this.isSleeping = isSleeping;
	}
	
	public boolean isDead() {
		return isDead;
	}
	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}
	public void goToSleep(int sleepRate){
		if(!isDead){
			Random sleepiness = new Random();
			if(sleepiness.nextInt(99) < sleepRate){
				isSleeping = true; 
				setIcon(getIcon().toLowerCase());
			}
			else{
				isSleeping = false; 
				setIcon(getIcon().toUpperCase());
			}
		}
	}
	
}
