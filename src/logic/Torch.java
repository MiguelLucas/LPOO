package logic;

import logic.Item.Position;

public class Torch extends Item{

	public Torch() {
		Position p1 = new Position(3, 3);
		this.setPosition(p1);
		this.setIcon("T ");
	}

}
