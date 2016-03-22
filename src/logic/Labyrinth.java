package logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import logic.Item.Position;

public class Labyrinth {

	public Labyrinth() {
		Dragon d1 = new Dragon();
		addDragon(d1);
	}

	public Labyrinth(String[][] labyrinth) { 
		Dragon d1 = new Dragon();
		addDragon(d1);
		this.labyrinth = labyrinth;
	}
	
	//0 - nao acabou, 1 - ganhou jogo, -1 - perdeu jogo
	private int endGame = 0;
	
	private Hero hero = new Hero();
	private Sword sword = new Sword();
	private Sledgehammer sledgehammer = new Sledgehammer();
	//LA
	private Spear spear = new Spear(); 
	private ArrayList<Dragon> dragons = new ArrayList<Dragon>(); 
	
	private String message = "Hero,choose your action!";
	
	private String[][] labyrinth = 
		{{"X ","X ","X ","X ","X ","X ","X ","X ","X ","X "},
		{"X ","H ","  ","  ","  ","  ","  ","  ","  ","X "},
		{"X ","  ","X ","X ","  ","X ","  ","X ","  ","X "},
		{"X ","D ","X ","X ","  ","X ","  ","X ","  ","X "},
		{"X ","  ","X ","X ","  ","X ","  ","X ","  ","X "},
		{"X ","  ","  ","  ","  ","  ","  ","X ","  ","S "},
		{"X ","  ","X ","X ","  ","X ","  ","X ","  ","X "},
		{"X ","  ","X ","X ","  ","X ","  ","X ","  ","X "},
		{"X ","E ","X ","X ","  ","  ","  ","  ","  ","X "},
		{"X ","X ","X ","X ","X ","X ","X ","X ","X ","X "}};
	
	
	public String[][] getLabyrinth() {
		return labyrinth;
	}
	public void setLabyrinth(String[][] labyrinth) {
		this.labyrinth = labyrinth;
	}
	public Hero getHero() {
		return hero;
	}
	public void setHero(Hero hero) {
		this.hero = hero;
	}
	public Sword getSword() {
		return sword;
	}
	public void setSword(Sword sword) {
		this.sword = sword;
	}
	public ArrayList<Dragon> getDragons() {
		return dragons;
	}
	public void setDragons(ArrayList<Dragon> dragons) {
		this.dragons = dragons;
	}
	
	public int getEndGame() {
		return endGame;
	}

	public void setEndGame(int endGame) {
		this.endGame = endGame;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Sledgehammer getSledgehammer() {
		return sledgehammer;
	}

	public void setSledgehammer(Sledgehammer sledgehammer) {
		this.sledgehammer = sledgehammer;
	}

	//LA
	public Spear getSpear() {
		return spear;
	}

	public void setSpear(Spear spear) {
		this.spear = spear;
	}
	
	@Override
	public String toString() {
		String str = "";
		for (int i=0;i<labyrinth.length;i++){
			for (int k=0;k<labyrinth.length;k++){
				str += labyrinth[i][k];
			}
			str += "\n";
		}
		return str;
	}

	public void move_hero(char direction){
		
		switch(direction){
		case 'W':
			move_up(hero);
			break;
		case 'S':
			move_down(hero);
			break;
		case 'A':
			move_left(hero);
			break;
		case 'D':
			move_right(hero);
			break;
		}
		if (position_has_sword(hero.getPosition().getX(),hero.getPosition().getY()))
				hero_catches_sword();
		
		if (positionHasSledgehammer(hero.getPosition()))
			heroCatchesSledgehammer();
	
		//LA
		//Falta o que acontece quando ele apanha a lança

		if (positionHasSpear(hero.getPosition()))
			heroCatchesSpear();
		
		if (positionHasDeadDragon(hero.getPosition().getX(),hero.getPosition().getY()) != null){
			//junta o 1º carater do icone do heroi com o 1º carater do dragao
			//é feito desta forma porque o heroi pode ter vários icones diferentes
			hero.setIcon(hero.getIcon().substring(0,1) +
					positionHasDeadDragon(hero.getPosition().getX(),hero.getPosition().getY()).getIcon().substring(0,1));
		}
		else
			hero.setIcon(hero.getIcon().substring(0,1) + " ");
		
	}
	
	//LA 
	public boolean validPosition(int x, int y){
		Position p = new Position(x, y);
		if ((labyrinth[y][x] == "  " || position_has_sword(x,y) 
				|| positionHasDeadDragon(x,y) != null || positionHasSledgehammer(p) || positionHasSpear(p)) && !positionHasHero(x,y)){
			return true;
		}
		else
			return false;
	}
	
	public void move_up(Item i1){
		if (validPosition(i1.getPosition().getX(),i1.getPosition().getY()-1)){
			labyrinth[i1.getPosition().getY()][i1.getPosition().getX()] = "  ";
			i1.getPosition().setY(i1.getPosition().getY()-1);
			return;
		}
		if (i1.getClass().getName() == "logic.Hero"){
			boolean allDead = true;
			for (int i=0;i<dragons.size();i++){
				if (!dragons.get(i).isDead()){
					allDead = false;
					break;
				}	
			}
			if (labyrinth[i1.getPosition().getY()-1][i1.getPosition().getX()] == "S "){
				if (allDead){
					labyrinth[i1.getPosition().getY()][i1.getPosition().getX()] = "  ";
					i1.getPosition().setY(i1.getPosition().getY()-1);
					gameOver(true);
				}
				else {
					message = "Hero, you can't escape without eliminating all the remaining Dragons!";
				}
			}
		}
	}

	public void move_down(Item i1){
		if(validPosition(i1.getPosition().getX(),i1.getPosition().getY()+1)){
			labyrinth[i1.getPosition().getY()][i1.getPosition().getX()] = "  ";
			i1.getPosition().setY(i1.getPosition().getY()+1);
			return;
		}
		if (i1.getClass().getName() == "logic.Hero"){
			boolean allDead = true;
			for (int i=0;i<dragons.size();i++){
				if (!dragons.get(i).isDead()){
					allDead = false;
					break;
				}	
			}
			if (labyrinth[i1.getPosition().getY()+1][i1.getPosition().getX()] == "S "){
				if (allDead){
					labyrinth[i1.getPosition().getY()][i1.getPosition().getX()] = "  ";
					i1.getPosition().setY(i1.getPosition().getY()+1);
					gameOver(true);
				}
				else {
					message = "Hero, you can't escape without eliminating all the remaining Dragons!";
				}
			}
		}
	}
	
	public void move_left(Item i1){
		if(validPosition(i1.getPosition().getX()-1,i1.getPosition().getY())){
			labyrinth[i1.getPosition().getY()][i1.getPosition().getX()] = "  ";
			i1.getPosition().setX(i1.getPosition().getX()-1);
			return;
		}
		if (i1.getClass().getName() == "logic.Hero"){
			boolean allDead = true;
			for (int i=0;i<dragons.size();i++){
				if (!dragons.get(i).isDead()){
					allDead = false;
					break;
				}	
			}
			if (labyrinth[i1.getPosition().getY()][i1.getPosition().getX()-1] == "S "){
				if (allDead){
					labyrinth[i1.getPosition().getY()][i1.getPosition().getX()] = "  ";
					i1.getPosition().setX(i1.getPosition().getX()-1);
					gameOver(true);
				}
				else {
					message = "Hero, you can't escape without eliminating all the remaining Dragons!";
				}
			}
		}	
	}
	
	public void move_right(Item i1){
		if(validPosition(i1.getPosition().getX()+1,i1.getPosition().getY())){
			labyrinth[i1.getPosition().getY()][i1.getPosition().getX()] = "  ";
			i1.getPosition().setX(i1.getPosition().getX()+1);
			return;
		}
		if (i1.getClass().getName() == "logic.Hero"){
			boolean allDead = true;
			for (int i=0;i<dragons.size();i++){
				if (!dragons.get(i).isDead()){
					allDead = false;
					break;
				}	
			}
			if (labyrinth[i1.getPosition().getY()][i1.getPosition().getX()+1] == "S "){
				if (allDead){
					labyrinth[i1.getPosition().getY()][i1.getPosition().getX()] = "  ";
					i1.getPosition().setX(i1.getPosition().getX()+1);
					gameOver(true);
				}
				else {
					message = "Hero, you can't escape without eliminating all the remaining Dragons!";
				}
			}
		}
	}
	
	
	public boolean position_has_sword(int x,int y){
		if (sword.getPosition().getX() == x && sword.getPosition().getY() == y)
			return true;
		else
			return false;
	}
	
	public boolean positionHasSledgehammer(Position p){
		if (sledgehammer.getPosition().equals(p))
			return true;
		else
			return false;
	}
	
	//LA
	public boolean positionHasSpear(Position p){
		if (spear.getPosition().equals(p))
			return true;
		else
			return false;
	}
	
	//testar retornar objeto
	public Dragon positionHasDeadDragon(int x, int y){
		for (int i=0;i<dragons.size();i++){
			Position p1 = new Position(x, y);
			if (dragons.get(i).getPosition().equals(p1) && dragons.get(i).isDead()){
				return dragons.get(i);
			}
		}
		return null;
	}
	//LA 
	//verdadeira 
	public boolean HasDeadDragon(int x, int y){
		for (int i=0;i<dragons.size();i++){
			Position p1 = new Position(x, y);
			if (dragons.get(i).getPosition().equals(p1) && dragons.get(i).isDead()){
				return true;
			}
		}
		return false;
	}
	
	
	
	public boolean positionHasHero(int x, int y){
		if (hero.getPosition().getX() == x && hero.getPosition().getY() == y)
			return true;
		else
			return false;
	}
	
	public void hero_catches_sword(){
		if (hero.getPosition().equals(sword.getPosition()) && !hero.isArmedSword()){
			hero.setIcon("A ");
			hero.setArmedSword(true);
			sword.setIcon("X ");
			sword.getPosition().setX(0);
			sword.getPosition().setY(0);
			message = "YOU PICKED UP THE SWORD!";
		}
	}
	
	public void heroCatchesSledgehammer(){
		if (hero.getPosition().equals(sledgehammer.getPosition()) && !hero.isArmedSledgehammer()){
			hero.setArmedSledgehammer(true);
			sledgehammer.setIcon("X ");
			sledgehammer.getPosition().setX(0);
			sledgehammer.getPosition().setY(0);
			message = "YOU PICKED UP THE SLEDGEHAMMER!";
		}
	}
	
	//LA
	//PARA A LANÇA
	public void heroCatchesSpear(){
		if (hero.getPosition().equals(spear.getPosition()) && !hero.isArmedSpear()){
			hero.setArmedSpear(true);
			if(HasDeadDragon(hero.getPosition().getX(),hero.getPosition().getY())){
				//isto muda a da lanca em vez da do dragao, nao entendo pq
				positionHasDeadDragon(hero.getPosition().getX(),hero.getPosition().getY()).setIcon("M ");
				//DragonClean(positionHasDeadDragon(hero.getPosition().getX(),hero.getPosition().getY()));		
				message = "YOU PICKED UP THE SPEAR AGAIN!";
			}
			//acho que este parenteses sao inuteis mas... 
			else
			{message = "YOU PICKED UP THE SPEAR!";}	
			spear.setIcon("X ");
			spear.getPosition().setX(0);
			spear.getPosition().setY(0);	
		}
	}

	public void heroKillsDragon(Dragon d1){
		if(hero.isArmedSword()){
			if((hero.getPosition().getX()==d1.getPosition().getX() && hero.getPosition().getY()==d1.getPosition().getY()) ||
					(hero.getPosition().getX()==d1.getPosition().getX()+1 && hero.getPosition().getY()==d1.getPosition().getY()) ||	
					(hero.getPosition().getX()==d1.getPosition().getX()-1 && hero.getPosition().getY()==d1.getPosition().getY()) ||
					(hero.getPosition().getX()==d1.getPosition().getX() && hero.getPosition().getY()==d1.getPosition().getY()+1) ||
					(hero.getPosition().getX()==d1.getPosition().getX() && hero.getPosition().getY()==d1.getPosition().getY()-1)){ 
				d1.setIcon("M ");
				d1.setDead(true);
				message = "YOU KILLED A DRAGON! This dungeon is a bit more secure now.";
			}
		}
	}
	
	//LA 
	public void spearKillsDragon(Dragon d1, String seta){				
				//System.out.println("a lanca esta tentando me matar");
				d1.setIcon(seta + "M");
				d1.setDead(true);
				getHero().setArmedSpear(false);
				message = "YOUR AIM IS AWESOME! One more dragon is gone.";
			}
	
	//LA
	public void DragonClean(Dragon d1){				
		//spear.setIcon(seta.substring(0,1) + positionHasDeadDragon(spear.getPosition().getX(),spear.getPosition().getY()).getIcon().substring(0,1));
		d1.setIcon("M ");
		d1.setDead(true);
	}
	
	public void dragonKillsHero(Dragon d1){
		if (!d1.isSleeping() && !d1.isDead()){
			if((hero.getPosition().getX()==d1.getPosition().getX() && hero.getPosition().getY()==d1.getPosition().getY()) ||
					(hero.getPosition().getX()==d1.getPosition().getX()+1 && hero.getPosition().getY()==d1.getPosition().getY()) ||	
					(hero.getPosition().getX()==d1.getPosition().getX()-1 && hero.getPosition().getY()==d1.getPosition().getY()) ||
					(hero.getPosition().getX()==d1.getPosition().getX() && hero.getPosition().getY()==d1.getPosition().getY()+1) ||
					(hero.getPosition().getX()==d1.getPosition().getX() && hero.getPosition().getY()==d1.getPosition().getY()-1)){
				hero.setDead(true);
				gameOver(false);
			}
		}
	}
	
	public int moveDragon(Dragon d1){
		int direction = -1; //-1 - same place, 0 - up, 1 - down, 2 - left, 3 - right
		if(!d1.isSleeping()){
			Random r = new Random();
			int pos = r.nextInt(5);

			switch (pos){
			case 0:
				if(validPosition(d1.getPosition().getX(),d1.getPosition().getY()-1)){
					if (positionHasHero(d1.getPosition().getX(),d1.getPosition().getY()-1))
						break;
					else{
						move_up(d1);
						direction = 0;
					}
				}
				else
					moveDragon(d1);
				break; 
			case 1:
				if(validPosition(d1.getPosition().getX(),d1.getPosition().getY()+1)){
					if (positionHasHero(d1.getPosition().getX(),d1.getPosition().getY()+1))
						break;
					else{
						move_down(d1);
						direction = 1;
					}
				}
				else
					moveDragon(d1);
				break;
			case 2:
				if(validPosition(d1.getPosition().getX()-1,d1.getPosition().getY())){
					if (positionHasHero(d1.getPosition().getX()-1,d1.getPosition().getY()))
						break;
					else{
						move_left(d1);
						direction = 2;
					}
				}
				else
					moveDragon(d1);
				break;
			case 3:
				if(validPosition(d1.getPosition().getX()+1,d1.getPosition().getY())){
					if (positionHasHero(d1.getPosition().getX()+1,d1.getPosition().getY()))
						break;
					else{
						move_right(d1);
						direction = 3;
					}
				}
				else
					moveDragon(d1);
				break;
			case 4:
				break;	
			}
		}
		//LA
		if (position_has_sword(d1.getPosition().getX(),d1.getPosition().getY()) || positionHasSledgehammer(d1.getPosition()) || positionHasSpear(d1.getPosition()) )
			d1.setIcon("F ");
		else{
			if (d1.isSleeping())
				d1.setIcon("d ");
			else {
				d1.setIcon("D ");
			}
		}
			
		if (positionHasDeadDragon(d1.getPosition().getX(),d1.getPosition().getY()) != null){
			//a rever quando forem criados mais dragões
			d1.setIcon(d1.getIcon().substring(0,1) + 
					positionHasDeadDragon(d1.getPosition().getX(),d1.getPosition().getY()).getIcon().substring(0,1));
		}
		else
			d1.setIcon(d1.getIcon().substring(0,1) + " ");
		
		return direction;
	}
	
	//LA 
	//Pensei que podia ser util
	public void PositionSplit(Item d1){
		
	if (positionHasDeadDragon(d1.getPosition().getX(),d1.getPosition().getY()) != null){
		d1.setIcon(d1.getIcon().substring(0,1) + 
				positionHasDeadDragon(d1.getPosition().getX(),d1.getPosition().getY()).getIcon().substring(0,1));
	}
	else
		d1.setIcon(d1.getIcon().substring(0,1) + " ");
	
	}
	
	public void gameOver(boolean win){
		if (win){
			endGame = 1;
			message = "Congratulations! You have beaten the mighty dragons and escaped the evil of this labyrinth!\nYou deserve the title of HERO!";
		}
		else{
			endGame = -1;
			message = "YOU DIED\nYou were eaten by a Dragon Senas.";
		}
	}
	
	public void addDragon(Dragon d1){
		dragons.add(d1);
	}

	public boolean useSledgehammer(int direction){
		//0 - up, 1 - down, 2 - left, 3 - right
		int originalUses = sledgehammer.getUses();
		if (hero.isArmedSledgehammer()){
			switch (direction){
			case 0:
				if(labyrinth[hero.getPosition().getY()-1][hero.getPosition().getX()] == "X "){
					//Verificar se e uma margem
					if(hero.getPosition().getY()-1 != 0){
						labyrinth[hero.getPosition().getY()-1][hero.getPosition().getX()] = "  ";
						sledgehammer.decrementUses();
					}
					else {
						message = "Hero, no matter how hard you try, that wall is indestructible!";
					}
					
				}
				else {
					message = "Hero, you can't use the sledgehammer that way!";
				}
				break;
			case 1:
				if(labyrinth[hero.getPosition().getY()+1][hero.getPosition().getX()] == "X "){
					if(hero.getPosition().getY()+1 != (labyrinth.length - 1)){
						labyrinth[hero.getPosition().getY()+1][hero.getPosition().getX()] = "  ";
						sledgehammer.decrementUses();
					}
					else {
						message = "Hero, no matter how hard you try, that wall is indestructible!";
					}
					
				}
				else {
					message = "Hero, you can't use the sledgehammer that way!";
				}
				break;
			case 2:
				if(labyrinth[hero.getPosition().getY()][hero.getPosition().getX()-1] == "X "){
					if(hero.getPosition().getX()-1 != 0){
						labyrinth[hero.getPosition().getY()][hero.getPosition().getX()-1] = "  ";
						sledgehammer.decrementUses();
					}
					else {
						message = "Hero, no matter how hard you try, that wall is indestructible!";
					}
					
				}
				else {
					message = "Hero, you can't use the sledgehammer that way!";
				}
				break;
			case 3:
				if(labyrinth[hero.getPosition().getY()][hero.getPosition().getX()+1] == "X "){
					if(hero.getPosition().getX()+1 != (labyrinth.length - 1)){
						labyrinth[hero.getPosition().getY()][hero.getPosition().getX()+1] = "  ";
						sledgehammer.decrementUses();
					}
					else {
						message = "Hero, no matter how hard you try, that wall is indestructible!";
					}
					
				}
				else {
					message = "Hero, you can't use the sledgehammer that way!";
				}
				break;
			}
		}
		if (originalUses != sledgehammer.getUses()){
			if (sledgehammer.getUses() <= 0){
				message = "YOU DESTROYED THE WALL WITH YOUR SLEDGEHAMMER\n"
						+ "But the sledgehammer broke!";
				hero.setArmedSledgehammer(false);
			}
			else {
				message = "YOU DESTROYED THE WALL WITH YOUR SLEDGEHAMMER\n"
						+ "Unfortunately, the sledgehammer is showing signs of weariness...";
			}
			return true;
		}
		
		return false;
	}
	
	
	//LA
	//IDEIA INICIAL
	public void useSpear(int direction){
		//0 - up, 1 - down, 2 - left, 3 - right
		if (hero.isArmedSpear()){
			switch (direction){
			//up
			case 0:
				spear.setIcon("^ ");
				spear.getPosition().setX(hero.getPosition().getX());
				spear.getPosition().setY(hero.getPosition().getY());
				while(labyrinth[spear.getPosition().getY()-1][spear.getPosition().getX()] != "X " && labyrinth[spear.getPosition().getY()-1][spear.getPosition().getX()] != "D " && labyrinth[spear.getPosition().getY()-1][spear.getPosition().getX()] != "S "){
						move_up(spear);
				}
				hero.setArmedSpear(false);
				break;
			//down 
			case 1:
				spear.setIcon("v ");
				spear.getPosition().setX(hero.getPosition().getX());
				spear.getPosition().setY(hero.getPosition().getY());
				while(labyrinth[spear.getPosition().getY()+1][spear.getPosition().getX()] != "X " && labyrinth[spear.getPosition().getY()+1][spear.getPosition().getX()] != "D " && labyrinth[spear.getPosition().getY()+1][spear.getPosition().getX()] != "S " ){
					move_down(spear);
				}
				hero.setArmedSpear(false);
				break;
			//leftuerda
			case 2:
				spear.setIcon("< ");
				spear.getPosition().setX(hero.getPosition().getX());
				spear.getPosition().setY(hero.getPosition().getY());
				
				while(labyrinth[spear.getPosition().getY()][spear.getPosition().getX()-1] != "X " || labyrinth[spear.getPosition().getY()][spear.getPosition().getX()-1] != "D " && labyrinth[spear.getPosition().getY()][spear.getPosition().getX()-1] != "S "){
					move_left(spear);
				}
				hero.setArmedSpear(false);
				break;
				//right
			case 3:
				spear.setIcon("> ");
				spear.getPosition().setX(hero.getPosition().getX());
				spear.getPosition().setY(hero.getPosition().getY());
				
				while(labyrinth[spear.getPosition().getY()][spear.getPosition().getX()+1] != "X " || labyrinth[spear.getPosition().getY()][spear.getPosition().getX()+1] != "D " && labyrinth[spear.getPosition().getY()+1][spear.getPosition().getX()] != "S "){
					move_left(spear);
				}
				hero.setArmedSpear(false);
				break;
			}
		}
	}
}
