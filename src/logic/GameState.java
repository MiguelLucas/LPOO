package logic;

import java.util.Random;

import logic.Item.Position;

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

	public void generateDragons(){
		Random r = new Random();
		int it = 0;
		while (it < labyrinth.getDragons().size()){
			int x = r.nextInt(labyrinth.getLabyrinth()[0].length-2)+1;
			int y = r.nextInt(labyrinth.getLabyrinth()[0].length-2)+1;
			
			if (labyrinth.getLabyrinth()[x][y] == "  "){
				Position p1 = new Position(x, y);
				boolean jaExiste = false;
				for (int j=0;j<labyrinth.getDragons().size();j++){
					if(labyrinth.getDragons().get(j).getPosition().equals(p1)){
						jaExiste = true;
					}
				}
				if (!jaExiste){
					System.out.println("N " + it);
					System.out.println(p1.toString());
					labyrinth.getDragons().get(it).setPosition(p1);
					it++;
				}
			}
		}
	}
	
	public void generateHero(){
		Random r = new Random();
		while (true){
			int x = r.nextInt(labyrinth.getLabyrinth()[0].length-2)+1;
			int y = r.nextInt(labyrinth.getLabyrinth()[0].length-2)+1;
			Position temp = new Position(x, y);
			if (!adjacentDragons(temp)){
				labyrinth.getHero().setPosition(temp);
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
				labyrinth.getSword().getPosition().setX(x);
				labyrinth.getSword().getPosition().setY(y);
				break;
			}
		}
	}
	
	public boolean adjacentDragons(Position p1){
		for (int i=0;i<labyrinth.getDragons().size();i++){
			for (int posX=-1;posX<2;posX++){
				for (int posY=-1;posY<2;posY++){
					Position temp = new Position(p1.getX()+posX, p1.getY()+posY);
					if (labyrinth.getDragons().get(i).getPosition().equals(temp))
						return true;
				}
			}
		}
		return false;
	}
	
	public void saveGame(){
		//a fazer
	}
	
	public void loadGame(){
		//a fazer
	}

}
