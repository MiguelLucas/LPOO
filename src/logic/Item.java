package logic;

public class Item {

	private String icon;
	private Position position;
	
	public Item() {}  

	public static class Position {		
		private int x, y;
		
		public int getX() {
			return x;
		}
		
		public int getY() {
			return y;
		}
		
		public void setX(int x) {
			this.x = x;
		}

		public void setY(int y) {
			this.y = y;
		}

		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Position)			
			return this.getPosition().getX() == ((Position)obj).x && this.getPosition().getY() == ((Position)obj).y;
		else
			return false;
	}
}
