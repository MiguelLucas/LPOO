package logic;

import java.util.Random;
import java.util.Scanner;

public class Labyrinth {

	public Labyrinth() {}
	
	private Hero hero = new Hero();
	private Sword sword = new Sword();
	private Dragon dragon = new Dragon();
	
	
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
	public Dragon getDragon() {
		return dragon;
	}
	public void setDragon(Dragon dragon) {
		this.dragon = dragon;
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
		if (position_has_sword(hero.getX(),hero.getY()))
				hero_catches_sword();
		
		if (positionHasDeadDragon(hero.getX(),hero.getY())){
			//junta o 1º carater do icone do heroi com o 1º carater do dragao
			//é feito desta forma porque o heroi pode ter vários icones diferentes
			hero.setIcon(hero.getIcon().substring(0,1) + dragon.getIcon().substring(0,1));
		}
		else
			hero.setIcon(hero.getIcon().substring(0,1) + " ");
		
	}
	
	public boolean validPosition(int x, int y){
		if (labyrinth[y][x] == "  " || position_has_sword(x,y) 
				|| positionHasDeadDragon(x,y) || !positionHasHero(x,y)){
			return true;
		}
		else
			return false;
	}
	
	public void move_up(Item i1){
		if (validPosition(i1.getX(),i1.getY()-1)){
			labyrinth[i1.getY()][i1.getX()] = "  ";
			i1.setY(i1.getY()-1);
		}
		if (i1.getClass().getName() == "logic.Hero"){
			if (labyrinth[i1.getY()-1][i1.getX()] == "S " && dragon.isDead()){
				labyrinth[i1.getY()][i1.getX()] = "  ";
				i1.setX(i1.getY()-1);
				gameOver(true);
			}	
		}
	}
	
	public void move_down(Item i1){
		if(validPosition(i1.getX(),i1.getY()+1)){
			labyrinth[i1.getY()][i1.getX()] = "  ";
			i1.setY(i1.getY()+1);
		}
		if (i1.getClass().getName() == "logic.Hero"){
			if (labyrinth[i1.getY()+1][i1.getX()] == "S " && dragon.isDead()){
				labyrinth[i1.getY()][i1.getX()] = "  ";
				i1.setX(i1.getY()+1);
				gameOver(true);
			}	
		}
	}
	
	public void move_left(Item i1){
		if(validPosition(i1.getX()-1,i1.getY())){
			labyrinth[i1.getY()][i1.getX()] = "  ";
			i1.setX(i1.getX()-1);
		}
		if (i1.getClass().getName() == "logic.Hero"){
			if (labyrinth[i1.getY()][i1.getX()-1] == "S " && dragon.isDead()){
				labyrinth[i1.getY()][i1.getX()] = "  ";
				i1.setX(i1.getX()-1);
				gameOver(true);
			}	
		}	
	}
	public void move_right(Item i1){
		if(validPosition(i1.getX()+1,i1.getY())){
			labyrinth[i1.getY()][i1.getX()] = "  ";
			i1.setX(i1.getX()+1);
		}
		if (i1.getClass().getName() == "logic.Hero"){
			if (labyrinth[i1.getY()][i1.getX()+1] == "S " && dragon.isDead()){
				labyrinth[i1.getY()][i1.getX()] = "  ";
				i1.setX(i1.getX()+1);
				gameOver(true);
			}	
		}
	}
	
	
	public boolean position_has_sword(int x,int y){
		if (sword.getX() == x && sword.getY() == y)
			return true;
		else
			return false;
	}
	
	public boolean positionHasDeadDragon(int x, int y){
		if (dragon.getX() == x && dragon.getY() == y && dragon.isDead()){
			return true;
		}
		else
			return false;
	}
	
	public boolean positionHasHero(int x, int y){
		if (hero.getX() == x && hero.getY() == y)
			return true;
		else
			return false;
	}
	public void hero_catches_sword(){
		hero.setIcon("A ");
		hero.setArmedSword(true);
		sword.setIcon("X ");
		sword.setX(0);
		sword.setY(0);
		System.out.println("You picked up the Sword!");
	}
	
	
	public void heroKillsDragon(){
		if(hero.isArmedSword()){
			if((hero.getX()==dragon.getX() && hero.getY()==dragon.getY()) ||
					(hero.getX()==dragon.getX()+1 && hero.getY()==dragon.getY()) ||	
					(hero.getX()==dragon.getX()-1 && hero.getY()==dragon.getY()) ||
					(hero.getX()==dragon.getX() && hero.getY()==dragon.getY()+1) ||
					(hero.getX()==dragon.getX() && hero.getY()==dragon.getY()-1)){ 
				
				dragon.setIcon("M ");
				dragon.setDead(true);
				System.out.println("You killed the Dragon Senas.");
			}
		}
	}
	public void dragonKillsHero(){
		if (!dragon.isSleeping() && !dragon.isDead()){
			if((hero.getX()==dragon.getX() && hero.getY()==dragon.getY()) ||
					(hero.getX()==dragon.getX()+1 && hero.getY()==dragon.getY()) ||	
					(hero.getX()==dragon.getX()-1 && hero.getY()==dragon.getY()) ||
					(hero.getX()==dragon.getX() && hero.getY()==dragon.getY()+1) ||
					(hero.getX()==dragon.getX() && hero.getY()==dragon.getY()-1)){
				gameOver(false);
			}
		}
	}
	
	
	
	public void moveDragon(){
		if(!dragon.isSleeping()){
			Random direction = new Random();
			int pos = direction.nextInt(4);

			switch (pos){
			case 0:
				if(labyrinth[dragon.getY()-1][dragon.getX()] != "X "){
					//opcao 1
					//impedir o dragao de se mexer para a mesma posição do dragão
					//codigo torna-se menos legivel
					if (positionHasHero(dragon.getX(),dragon.getY()-1))
						break;
					else
						move_up(dragon);
				}
				else
					moveDragon();
				break; 
			case 1:
				if(labyrinth[dragon.getY()+1][dragon.getX()] != "X "){
					if (positionHasHero(dragon.getX(),dragon.getY()-1))
						break;
					else
						move_down(dragon);
				}
				else
					moveDragon();
				break;
			case 2:
				if(labyrinth[dragon.getY()][dragon.getX()-1] != "X "){
					if (positionHasHero(dragon.getX(),dragon.getY()-1))
						break;
					else
						move_left(dragon);
				}
				else
					moveDragon();
				break;
			case 3:
				if(labyrinth[dragon.getY()][dragon.getX()+1] != "X "){
					if (positionHasHero(dragon.getX(),dragon.getY()-1))
						break;
					else
						move_right(dragon);
				}
				else
					moveDragon();
				break;
			case 4:
				break;	
			}
		}
		if (position_has_sword(dragon.getX(),dragon.getY()))
			sword.setIcon("F ");
		else
			sword.setIcon("E ");
		
		if (positionHasDeadDragon(dragon.getX(),dragon.getY())){
			//a rever quando forem criados mais dragões
			dragon.setIcon(dragon.getIcon().substring(0,1) + dragon.getIcon().substring(0,1));
		}
		else
			dragon.setIcon(dragon.getIcon().substring(0,1) + " ");
	}

	public void print_labyrinth(){
		//coloca o heroi e a espada no labirinto
		this.labyrinth[dragon.getY()][dragon.getX()] = dragon.getIcon();
		this.labyrinth[hero.getY()][hero.getX()] = hero.getIcon();
		this.labyrinth[sword.getY()][sword.getX()] = sword.getIcon();
		//imprime o labirinto
		for(int i=0;i<10;i++){
			for(int j=0;j<10;j++){
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
			
		System.exit(0);
	}

}
