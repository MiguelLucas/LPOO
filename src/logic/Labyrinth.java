package logic;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import logic.Item.Position;

public class Labyrinth {

	public Labyrinth() {}
	
	public Labyrinth(String[][] labyrinth) { this.labyrinth = labyrinth;}
	
	private Hero hero = new Hero();
	private Sword sword = new Sword();
	private ArrayList<Dragon> dragons = new ArrayList<Dragon>(); 
	
	
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
	public void move_hero(){
		System.out.println("W-Up, S-Down, A-Left, D-Right, E-Exit Game");
		Scanner sc = new Scanner(System.in);
		char move = Character.toUpperCase(sc.next().charAt(0));
		
		switch(move){
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
		case 'E':
			System.out.println("Goodbye :(");
			sc.close();
			System.exit(0);
		default:
			System.out.println("Invalid direction.");
			move_hero();
			break;
		}
		if (position_has_sword(hero.getPosition().getX(),hero.getPosition().getY()))
				hero_catches_sword();
		
		
		if (positionHasDeadDragon(hero.getPosition().getX(),hero.getPosition().getY()) != null){
			//junta o 1º carater do icone do heroi com o 1º carater do dragao
			//é feito desta forma porque o heroi pode ter vários icones diferentes
			hero.setIcon(hero.getIcon().substring(0,1) +
					positionHasDeadDragon(hero.getPosition().getX(),hero.getPosition().getY()).getIcon().substring(0,1));
		}
		else
			hero.setIcon(hero.getIcon().substring(0,1) + " ");
		
	}
	
	public boolean validPosition(int x, int y){
		if (labyrinth[y][x] == "  " || position_has_sword(x,y) 
				|| positionHasDeadDragon(x,y) != null && !positionHasHero(x,y)){
			return true;
		}
		else
			return false;
	}
	
	public void move_up(Item i1){
		if (validPosition(i1.getPosition().getX(),i1.getPosition().getY()-1)){
			labyrinth[i1.getPosition().getY()][i1.getPosition().getX()] = "  ";
			i1.getPosition().setY(i1.getPosition().getY()-1);
		}
		if (i1.getClass().getName() == "logic.Hero"){
			boolean allDead = true;
			for (int i=0;i<dragons.size();i++){
				if (!dragons.get(i).isDead()){
					allDead = false;
					break;
				}	
			}
			if (allDead && labyrinth[i1.getPosition().getY()-1][i1.getPosition().getX()] == "S "){
				labyrinth[i1.getPosition().getY()][i1.getPosition().getX()] = "  ";
				i1.getPosition().setX(i1.getPosition().getY()-1);
				gameOver(true);
			}
		}
	}

	public void move_down(Item i1){
		if(validPosition(i1.getPosition().getX(),i1.getPosition().getY()+1)){
			labyrinth[i1.getPosition().getY()][i1.getPosition().getX()] = "  ";
			i1.getPosition().setY(i1.getPosition().getY()+1);
		}
		if (i1.getClass().getName() == "logic.Hero"){
			boolean allDead = true;
			for (int i=0;i<dragons.size();i++){
				if (!dragons.get(i).isDead()){
					allDead = false;
					break;
				}	
			}
			if (allDead && labyrinth[i1.getPosition().getY()+1][i1.getPosition().getX()] == "S "){
				labyrinth[i1.getPosition().getY()][i1.getPosition().getX()] = "  ";
				i1.getPosition().setX(i1.getPosition().getY()+1);
				gameOver(true);
			}
		}
	}
	
	public void move_left(Item i1){
		if(validPosition(i1.getPosition().getX()-1,i1.getPosition().getY())){
			labyrinth[i1.getPosition().getY()][i1.getPosition().getX()] = "  ";
			i1.getPosition().setX(i1.getPosition().getX()-1);
		}
		if (i1.getClass().getName() == "logic.Hero"){
			boolean allDead = true;
			for (int i=0;i<dragons.size();i++){
				if (!dragons.get(i).isDead()){
					allDead = false;
					break;
				}	
			}
			if (allDead && labyrinth[i1.getPosition().getY()][i1.getPosition().getX()-1] == "S "){
				labyrinth[i1.getPosition().getY()][i1.getPosition().getX()] = "  ";
				i1.getPosition().setX(i1.getPosition().getX()-1);
				gameOver(true);
			}
		}	
	}
	
	public void move_right(Item i1){
		if(validPosition(i1.getPosition().getX()+1,i1.getPosition().getY())){
			labyrinth[i1.getPosition().getY()][i1.getPosition().getX()] = "  ";
			i1.getPosition().setX(i1.getPosition().getX()+1);
		}
		if (i1.getClass().getName() == "logic.Hero"){
			boolean allDead = true;
			for (int i=0;i<dragons.size();i++){
				if (!dragons.get(i).isDead()){
					allDead = false;
					break;
				}	
			}
			if (allDead && labyrinth[i1.getPosition().getY()][i1.getPosition().getX()+1] == "S "){
				labyrinth[i1.getPosition().getY()][i1.getPosition().getX()] = "  ";
				i1.getPosition().setX(i1.getPosition().getX()+1);
				gameOver(true);
			}
		}
	}
	
	
	public boolean position_has_sword(int x,int y){
		if (sword.getPosition().getX() == x && sword.getPosition().getY() == y)
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
	
	public boolean positionHasHero(int x, int y){
		if (hero.getPosition().getX() == x && hero.getPosition().getY() == y)
			return true;
		else
			return false;
	}
	public void hero_catches_sword(){
		hero.setIcon("A ");
		hero.setArmedSword(true);
		sword.setIcon("X ");
		sword.getPosition().setX(0);
		sword.getPosition().setY(0);
		System.out.println("You picked up the Sword!");
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
				System.out.println("You killed the Dragon Senas.");
			}
		}
	}
<<<<<<< HEAD
	public void dragonKillsHero(Dragon d1){
		if (!d1.isSleeping() && !d1.isDead()){
			if((hero.getPosition().getX()==d1.getPosition().getX() && hero.getPosition().getY()==d1.getPosition().getY()) ||
					(hero.getPosition().getX()==d1.getPosition().getX()+1 && hero.getPosition().getY()==d1.getPosition().getY()) ||	
					(hero.getPosition().getX()==d1.getPosition().getX()-1 && hero.getPosition().getY()==d1.getPosition().getY()) ||
					(hero.getPosition().getX()==d1.getPosition().getX() && hero.getPosition().getY()==d1.getPosition().getY()+1) ||
					(hero.getPosition().getX()==d1.getPosition().getX() && hero.getPosition().getY()==d1.getPosition().getY()-1)){
=======
	public void dragonKillsHero(){
		if (!dragon.isSleeping() && !dragon.isDead()){
			if((hero.getX()==dragon.getX() && hero.getY()==dragon.getY()) ||
					(hero.getX()==dragon.getX()+1 && hero.getY()==dragon.getY()) ||	
					(hero.getX()==dragon.getX()-1 && hero.getY()==dragon.getY()) ||
					(hero.getX()==dragon.getX() && hero.getY()==dragon.getY()+1) ||
					(hero.getX()==dragon.getX() && hero.getY()==dragon.getY()-1)){
				hero.setDead(true);
>>>>>>> origin/master
				gameOver(false);
			}
		}
	}
	
	
	
	public void moveDragon(Dragon d1){
		if(!d1.isSleeping()){
			Random direction = new Random();
			int pos = direction.nextInt(4);

			switch (pos){
			case 0:
				if(labyrinth[d1.getPosition().getY()-1][d1.getPosition().getX()] != "X "){
					//opcao 1
					//impedir o dragao de se mexer para a mesma posição do dragão
					//codigo torna-se menos legivel
					if (positionHasHero(d1.getPosition().getX(),d1.getPosition().getY()-1))
						break;
					else
						move_up(d1);
				}
				else
					moveDragon(d1);
				break; 
			case 1:
				if(labyrinth[d1.getPosition().getY()+1][d1.getPosition().getX()] != "X "){
					if (positionHasHero(d1.getPosition().getX(),d1.getPosition().getY()-1))
						break;
					else
						move_down(d1);
				}
				else
					moveDragon(d1);
				break;
			case 2:
				if(labyrinth[d1.getPosition().getY()][d1.getPosition().getX()-1] != "X "){
					if (positionHasHero(d1.getPosition().getX(),d1.getPosition().getY()-1))
						break;
					else
						move_left(d1);
				}
				else
					moveDragon(d1);
				break;
			case 3:
				if(labyrinth[d1.getPosition().getY()][d1.getPosition().getX()+1] != "X "){
					if (positionHasHero(d1.getPosition().getX(),d1.getPosition().getY()-1))
						break;
					else
						move_right(d1);
				}
				else
					moveDragon(d1);
				break;
			case 4:
				break;	
			}
		}
		if (position_has_sword(d1.getPosition().getX(),d1.getPosition().getY()))
			sword.setIcon("F ");
		else
			sword.setIcon("E ");
		
		if (positionHasDeadDragon(d1.getPosition().getX(),d1.getPosition().getY()) != null){
			//a rever quando forem criados mais dragões
			d1.setIcon(d1.getIcon().substring(0,1) + 
					positionHasDeadDragon(d1.getPosition().getX(),d1.getPosition().getY()).getIcon().substring(0,1));
		}
		else
			d1.setIcon(d1.getIcon().substring(0,1) + " ");
	}

	public void print_labyrinth(){
		//coloca o heroi e a espada no labirinto
<<<<<<< HEAD
		for (int i=0;i<dragons.size();i++){
			this.labyrinth[dragons.get(i).getPosition().getY()][dragons.get(i).getPosition().getX()] = dragons.get(i).getIcon();
		}
		//this.labyrinth[dragon.getPosition().getY()][dragon.getPosition().getX()] = dragon.getIcon();
		this.labyrinth[hero.getPosition().getY()][hero.getPosition().getX()] = hero.getIcon();
		this.labyrinth[sword.getPosition().getY()][sword.getPosition().getX()] = sword.getIcon();
=======
		this.labyrinth[dragon.getY()][dragon.getX()] = dragon.getIcon();
		this.labyrinth[hero.getY()][hero.getX()] = hero.getIcon();
		//this.labyrinth[sword.getY()][sword.getX()] = sword.getIcon();
>>>>>>> origin/master
		//imprime o labirinto
		for(int i=0;i<labyrinth[0].length;i++){
			for(int j=0;j<labyrinth[1].length;j++){
				System.out.print(this.getLabyrinth()[i][j]);
			}
			System.out.println();	
		}
		print_inventory();
	}
	
	public void print_inventory(){
		System.out.println("----------------------");
		System.out.println("Hero's inventory: ");
		if (hero.isArmedSword())
			System.out.println("1x Sword ");
		else
			System.out.println("Nothing");
		//a acrescentar mais items conforme se vão criando
		System.out.println("----------------------");
	}
	
	public void gameOver(boolean win){
		print_labyrinth();
		if (win){
			System.out.println("You're winner!");
		}
		else
			System.out.println("YOU DIED\nYou were eaten by Dragon Senas.");
			
		//System.exit(0);
	}
	
	public void addDragon(Dragon d1){
		dragons.add(d1);
	}

	
}
