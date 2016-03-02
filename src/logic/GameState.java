package logic;

import java.util.Random;

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

	public void generateLabyrinth(int size){
		MazeBuilder m1 = new MazeBuilder();
		labyrinth.setLabyrinth(m1.buildMaze(size));
		generateExit();
		generateHero();
		//generateDragon();
		generateSword();
	}
	
	public void generateExit(){
		Random r = new Random();
		int direccao = r.nextInt(4);
		int posicao = r.nextInt(labyrinth.getLabyrinth()[0].length-2)+1;
		switch (direccao){
		case 0:{
			while (true){
				if (labyrinth.getLabyrinth()[1][posicao] != "X "){
					labyrinth.getLabyrinth()[0][posicao] = "S ";
					break;
				}
				else
					posicao = r.nextInt(labyrinth.getLabyrinth()[0].length-2)+1;
			}
			break;
		}
		case 1:{
			while (true){
				if (labyrinth.getLabyrinth()[labyrinth.getLabyrinth()[0].length-2][posicao] != "X "){
					labyrinth.getLabyrinth()[labyrinth.getLabyrinth()[0].length-1][posicao] = "S ";
					break;
				}
				else
					posicao = r.nextInt(labyrinth.getLabyrinth()[0].length-2)+1;
			}
			break;
		}
		case 2:{
			while (true){
				if (labyrinth.getLabyrinth()[posicao][1] != "X "){
					labyrinth.getLabyrinth()[posicao][0] = "S ";
					break;
				}
				else
					posicao = r.nextInt(labyrinth.getLabyrinth()[0].length-2)+1;
			}
			break;
		}
		case 3:{
			while (true){
				if (labyrinth.getLabyrinth()[posicao][labyrinth.getLabyrinth()[0].length-2] != "X "){
					labyrinth.getLabyrinth()[posicao][labyrinth.getLabyrinth()[0].length-1] = "S ";
					break;
				}
				else
					posicao = r.nextInt(labyrinth.getLabyrinth()[0].length-2)+1;
			}
			break;
		}
		}
	}
	
	public void generateHero(){
		Random r = new Random();
		while (true){
			int x = r.nextInt(labyrinth.getLabyrinth()[0].length-2)+1;
			int y = r.nextInt(labyrinth.getLabyrinth()[0].length-2)+1;
			if (labyrinth.getLabyrinth()[x][y] == "  "){
				labyrinth.getHero().setX(x);
				labyrinth.getHero().setY(y);
				break;
			}
		}
	}
	
	public void generateSword(){
		Random r = new Random();
		while (true){
			int x = r.nextInt(labyrinth.getLabyrinth()[0].length-2)+1;
			int y = r.nextInt(labyrinth.getLabyrinth()[0].length-2)+1;
			if (labyrinth.getLabyrinth()[x][y] == "  "){
				labyrinth.getSword().setX(x);
				labyrinth.getSword().setY(y);
				break;
			}
		}
		
	}
	public void saveGame(){
		//a fazer
	}
	
	public void loadGame(){
		//a fazer
	}

}
