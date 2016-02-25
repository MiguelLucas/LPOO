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
		System.out.println("-------------------------");
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
		
	}
	
	public void move_up(Item i1){
		if(labyrinth[i1.getY()-1][i1.getX()] == "  " || position_has_sword(i1.getX(),i1.getY()-1)){
			labyrinth[i1.getY()][i1.getX()] = "  ";
			i1.setY(i1.getY()-1);
		}
	}
	
	public void move_down(Item i1){
		if(labyrinth[i1.getY()+1][i1.getX()] == "  " || position_has_sword(i1.getX(),i1.getY()+1)){
			labyrinth[i1.getY()][i1.getX()] = "  ";
			i1.setY(i1.getY()+1);
		}
	}
	
	public void move_left(Item i1){
		if(labyrinth[i1.getY()][i1.getX()-1] == "  " || position_has_sword(i1.getX()-1,i1.getY())){
			labyrinth[i1.getY()][i1.getX()] = "  ";
			i1.setX(i1.getX()-1);
		}

			
	}
	public void move_right(Item i1){
		if(labyrinth[i1.getY()][i1.getX()+1] == "  " || position_has_sword(i1.getX()+1,i1.getY())){
			labyrinth[i1.getY()][i1.getX()] = "  ";
			i1.setX(i1.getX()+1);
		}
		
	}
	
	
	public boolean position_has_sword(int x,int y){
		if (sword.getX() == x && sword.getY() == y)
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
	}
	
	public void dragonKillsHero(){
		if (!dragon.isSleeping()){
			if((hero.getX()==dragon.getX() && hero.getY()==dragon.getY()) ||
					(hero.getX()==dragon.getX()+1 && hero.getY()==dragon.getY()) ||	
					(hero.getX()==dragon.getX()-1 && hero.getY()==dragon.getY()) ||
					(hero.getX()==dragon.getX() && hero.getY()==dragon.getY()+1) ||
					(hero.getX()==dragon.getX() && hero.getY()==dragon.getY()-1)){
				System.out.println("You were eaten by Dragon Cenas.");
				System.exit(0);
			}
		}
	}
	
	
	
	public void moveDragon(){
	
	Random direction = new Random();
	int pos = direction.nextInt(4);
	
	switch (pos){
	case 0:
		move_up(dragon);
		break; 
	case 1:
		move_down(dragon);
		break;
	case 2:
		move_left(dragon);
		break;
	case 3:
		move_right(dragon);
		break;
	case 4:
		break;	
	}
	
	}
	

	
	public static void main(String[] args) {
		Labyrinth l = new Labyrinth();
		while (true){
			l.print_labyrinth();
			l.move_hero();
			l.getDragon().goToSleep();
			l.moveDragon();
			l.dragonKillsHero();
		}
	}

	public void print_labyrinth(){
		//coloca o heroi e a espada no labirinto
		this.labyrinth[hero.getY()][hero.getX()] = hero.getIcon();
		this.labyrinth[sword.getY()][sword.getX()] = sword.getIcon();
		this.labyrinth[dragon.getY()][dragon.getX()] = dragon.getIcon();
		//nao tenho a certeza qual a forma mais correta de fazer isto. a de cima ou de baixo
		//this.labyrinth[this.getSword().getY()][this.getSword().getX()] = this.getSword().getIcon();
		
		//imprime o labirinto
		for(int i=0;i<10;i++){
			for(int j=0;j<10;j++){
				System.out.print(this.getLabyrinth()[i][j]);
			}
			System.out.println();	
		}
	}
	
	
	
	
	
}
