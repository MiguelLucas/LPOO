package logic;

import java.util.Random;

import logic.Item.Position;

public class GameState {
	
	private Labyrinth labyrinth = new Labyrinth();
	private boolean settingSleep = true;
	private int sleepRate = 20;
	private boolean settingMove = true;
	private boolean settingSledgehammer = true;
	//LA
	private boolean settingSpear = true;
	
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

	public boolean isSettingSledgehammer() {
		return settingSledgehammer;
	}

	public void setSettingSledgehammer(boolean settingSledgehammer) {
		this.settingSledgehammer = settingSledgehammer;
	}
	
	//LA 
	
	public boolean isSettingSpear() {
		return settingSpear;
	}

	public void setSettingSpear(boolean settingSpear) {
		this.settingSpear = settingSpear;
	}

	public void generateLabyrinth(int size){
		MazeBuilder m1 = new MazeBuilder();
		labyrinth.setLabyrinth(m1.buildMaze(size));
	}
	
	

	public void generateDragons(){
		Random r = new Random();
		int it = 0;
		while (it < labyrinth.getDragons().size()){
			int x = r.nextInt(labyrinth.getLabyrinth()[0].length-2)+1;
			int y = r.nextInt(labyrinth.getLabyrinth()[0].length-2)+1;
			
			if (labyrinth.getLabyrinth()[y][x] == "  "){
				Position p1 = new Position(x, y);
				boolean jaExiste = false;
				for (int j=0;j<labyrinth.getDragons().size();j++){
					if(labyrinth.getDragons().get(j).getPosition().equals(p1)){
						jaExiste = true;
					}
				}
				if (!jaExiste){
					/*System.out.println("N " + it);
					System.out.println(p1.toString());*/
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
			if (!adjacentDragons(temp) && labyrinth.getLabyrinth()[y][x] == "  "){
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
				Position p1 = new Position(x, y);
				boolean jaExiste = false;
				for (int j=0;j<labyrinth.getDragons().size();j++){
					if(labyrinth.getDragons().get(j).getPosition().equals(p1)){
						jaExiste = true;
						break;
					}
				}
				if (labyrinth.getHero().getPosition().equals(p1))
					jaExiste = true;
				if (!jaExiste){
					labyrinth.getSword().setPosition(p1);
					break;
				}
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
	
	public void generateSledgehammer(){
		Random r = new Random();
		while (true){
			int x = r.nextInt(labyrinth.getLabyrinth()[0].length-2)+1;
			int y = r.nextInt(labyrinth.getLabyrinth()[0].length-2)+1;
			if (labyrinth.getLabyrinth()[x][y] == "  "){
				Position p1 = new Position(x, y);
				boolean jaExiste = false;
				for (int j=0;j<labyrinth.getDragons().size();j++){
					if(labyrinth.getDragons().get(j).getPosition().equals(p1)){
						jaExiste = true;
						break;
					}
				}
				if (labyrinth.getHero().getPosition().equals(p1))
					jaExiste = true;
				if (labyrinth.getSword().getPosition().equals(p1))
					jaExiste = true;
				if (!jaExiste){
					labyrinth.getSledgehammer().setPosition(p1);
					break;
				}
			}
		}
		labyrinth.getSledgehammer().setUses(labyrinth.getLabyrinth().length/4);
	}
	
	//LA 
	public void generateSpear(){
		Random r = new Random();
		while (true){
			int x = r.nextInt(labyrinth.getLabyrinth()[0].length-2)+1;
			int y = r.nextInt(labyrinth.getLabyrinth()[0].length-2)+1;
			if (labyrinth.getLabyrinth()[x][y] == "  "){
				Position p1 = new Position(x, y);
				boolean jaExiste = false;
				for (int j=0;j<labyrinth.getDragons().size();j++){
					if(labyrinth.getDragons().get(j).getPosition().equals(p1)){
						jaExiste = true;
						break;
					}
				}
				if (labyrinth.getHero().getPosition().equals(p1))
					jaExiste = true;
				if (labyrinth.getSword().getPosition().equals(p1))
					jaExiste = true;
				if (!jaExiste){
					labyrinth.getSpear().setPosition(p1);
					break;
				}
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
