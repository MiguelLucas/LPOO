package logic;

import java.util.Random;

import logic.Item.Position;

public class GameState {
	
	private Labyrinth labyrinth = new Labyrinth();
	private boolean settingSleep = true;
	private int sleepRate = 20;
	private boolean settingMove = true;
	private boolean settingSledgehammer = true;
	private boolean settingFogOfWar = true;
	private int fogOfWarRadius = 5;
	private boolean settingTorch = true;
	private int torchRadius = 7;
	
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

	public boolean isSettingFogOfWar() {
		return settingFogOfWar;
	}

	public void setSettingFogOfWar(boolean settingFogOfWar) {
		this.settingFogOfWar = settingFogOfWar;
	}

	public int getFogOfWarRadius() {
		return fogOfWarRadius;
	}

	public void setFogOfWarRadius(int fogOfWarRadius) {
		this.fogOfWarRadius = fogOfWarRadius;
	}

	public boolean isSettingTorch() {
		return settingTorch;
	}

	public void setSettingTorch(boolean settingTorch) {
		this.settingTorch = settingTorch;
	}

	public int getTorchRadius() {
		return torchRadius;
	}

	public void setTorchRadius(int torchRadius) {
		this.torchRadius = torchRadius;
	}

	public void generateLabyrinth(int size){
		MazeBuilder m1 = new MazeBuilder();
		labyrinth.setLabyrinth(m1.buildMaze(size));
	}
	
	

	public void generateDragons(int nDragons){
		Random r = new Random();
		
		for (int i=1;i<nDragons;i++){
			int size = labyrinth.getLabyrinth()[0].length;
			Dragon d1 = new Dragon();
			int x = 0, y = 0;
			while (labyrinth.getLabyrinth()[y][x] != "  "){
				x = r.nextInt(size-1)+1;
				y = r.nextInt(size-1)+1;
			}
			d1.getPosition().setX(x);
			d1.getPosition().setY(y);
			labyrinth.addDragon(d1);
		}
		
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
	
	public void saveGame(){
		//a fazer
	}
	
	public void loadGame(){
		//a fazer
	}

}
