package logic;

import java.util.Random;

public class Dragon extends Item {
	private boolean isSleeping;	
	private boolean isDead;	
	public Dragon(){
		this.setX(1);
		this.setY(3);
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
	public void goToSleep(){
		if(!isDead){
		Random sleepiness = new Random();
		if(sleepiness.nextInt(99)<20){
			isSleeping = true; 
			setIcon("d ");
		}
		else{
			isSleeping = false; 
			setIcon("D ");
		}
		}
	}
	
}
