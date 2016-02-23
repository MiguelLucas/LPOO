package logic;

import java.util.Scanner;

public class Labyrinth {

	public Labyrinth() {}
	
	private Hero hero = new Hero();
	private Sword sword = new Sword();
	
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
	public void move_hero(){
		System.out.println("-------------------------");
		System.out.println("W-Up, S-Down, A-Left, D-Right, E-Exit Game");
		Scanner sc = new Scanner(System.in);
		char move = Character.toUpperCase(sc.next().charAt(0));
		
		switch(move){
		case 'W':
			move_up();
			break;
		case 'S':
			move_down();
			break;
		case 'A':
			move_left();
			break;
		case 'D':
			move_right();
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
	
	public void move_up(){
		if(labyrinth[hero.getY()-1][hero.getX()] == "  " || position_has_sword(hero.getX(),hero.getY()-1)){
			labyrinth[hero.getY()][hero.getX()] = "  ";
			hero.setY(hero.getY()-1);
		}
	}
	
	public void move_down(){
		if(labyrinth[hero.getY()+1][hero.getX()] == "  " || position_has_sword(hero.getX(),hero.getY()+1)){
			labyrinth[hero.getY()][hero.getX()] = "  ";
			hero.setY(hero.getY()+1);
		}
	}
	
	public void move_left(){
		if(labyrinth[hero.getY()][hero.getX()-1] == "  " || position_has_sword(hero.getX()-1,hero.getY())){
			labyrinth[hero.getY()][hero.getX()] = "  ";
			hero.setX(hero.getX()-1);
		}
			
	}
	public void move_right(){
		if(labyrinth[hero.getY()][hero.getX()+1] == "  " || position_has_sword(hero.getX()+1,hero.getY())){
			labyrinth[hero.getY()][hero.getX()] = "  ";
			hero.setX(hero.getX()+1);
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
	
	public static void main(String[] args) {
		Labyrinth l = new Labyrinth();
		while (true){
			l.print_labyrinth();
			l.move_hero();
		}
	}

	public void print_labyrinth(){
		//coloca o heroi e a espada no labirinto
		this.labyrinth[hero.getY()][hero.getX()] = hero.getIcon();
		this.labyrinth[sword.getY()][sword.getX()] = sword.getIcon();
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
