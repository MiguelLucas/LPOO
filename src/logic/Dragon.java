package logic;

import java.util.Random;

public class Dragon extends Item {
	private boolean isSleeping;	
	public Dragon(){
		this.setX(1);
		this.setY(3);
		this.setIcon("D ");
		this.isSleeping = false;
	}
	public boolean isSleeping() {
		return isSleeping;
	}
	public void setSleeping(boolean isSleeping) {
		this.isSleeping = isSleeping;
	}
	
	public void goToSleep(){
		Random sleepiness = new Random();
		int cenas = sleepiness.nextInt(99);
		System.out.println(cenas);
		if(cenas<20){
			isSleeping = true; 
			setIcon("d ");
		}
		else{
			isSleeping = false; 
			setIcon("D ");
		}
	}
	
}
