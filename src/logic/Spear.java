package logic;

import logic.Item.Position;

public class Spear extends Item{
	
	public Spear() {
		Position p1 = new Position(3, 2);
		this.setPosition(p1);
		this.setIcon("^ ");
	}

}
