package logic;

public class GameState {
	
	private Labyrinth labyrinth = new Labyrinth();
	private boolean settingSleep = true;
	private int sleepRate = 20;
	private boolean settingMove = true;
	
	public GameState() {}
	
	public Labyrinth getLabyrinth() {
		return labyrinth;
	}
	
	public void setLabyrinth(Labyrinth labyrinth) {
		this.labyrinth = labyrinth;
	}
	
	public boolean isSettingSleep() {
		return settingSleep;
	}

	public void setSettingSleep(boolean settingSleep) {
		this.settingSleep = settingSleep;
	}

	public int getSleepRate() {
		return sleepRate;
	}

	public void setSleepRate(int sleepRate) {
		this.sleepRate = sleepRate;
	}

	public boolean isSettingMove() {
		return settingMove;
	}

	public void setSettingMove(boolean settingMove) {
		this.settingMove = settingMove;
	}

	public void saveGame(){
		//a fazer
	}
	
	public void loadGame(){
		//a fazer
	}

}
