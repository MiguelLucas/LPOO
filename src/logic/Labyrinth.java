package logic;

import java.util.Scanner;

public class Labyrinth {

	public Labyrinth() {
		}

	public static Hero hero = new Hero();
	
	static String[][] labyrinth = 
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
	
	public static void move_hero(){
		System.out.println("Choose direction (WASD): ");
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
		case '0':
			System.out.println("Goodbye :(");
			System.exit(0);
		default:
			System.out.println("Invalid direction.");
			move_hero();
			break;
		}
	}
	// apagar antiga e escrever a nova 
	public static void move_up(){
		if(labyrinth[hero.getY()-1][hero.getX()] == "  " || labyrinth[hero.getY()-1][hero.getX()] == " E"){
			labyrinth[hero.getY()][hero.getX()] = "  ";
			hero.setY(hero.getY()-1);
			labyrinth[hero.getY()][hero.getX()] = "H ";
		}
	}
	
	public static void move_down(){
		if(labyrinth[hero.getY()+1][hero.getX()] == "  " || labyrinth[hero.getY()+1][hero.getX()] == " E"){
			labyrinth[hero.getY()][hero.getX()] = "  ";
			hero.setY(hero.getY()+1);
			labyrinth[hero.getY()][hero.getX()] = "H ";
		}
	}
	
	public static void move_left(){
		if(labyrinth[hero.getY()][hero.getX()-1] == "  " || labyrinth[hero.getY()][hero.getX()-1] == " E"){
			labyrinth[hero.getY()][hero.getX()] = "  ";
			hero.setX(hero.getX()-1);
			labyrinth[hero.getY()][hero.getX()] = "H ";
		}
			
	}
	public static void move_right(){
		if(labyrinth[hero.getY()][hero.getX()+1] == "  " || labyrinth[hero.getY()][hero.getX()+1] == " E"){
			labyrinth[hero.getY()][hero.getX()] = "  ";
			hero.setX(hero.getX()+1);
			labyrinth[hero.getY()][hero.getX()] = "H ";
		}
	}
	public static void main(String[] args) {
		while (true){
			print_labyrinth(labyrinth);
			move_hero();
		}
		
		//System.out.println(labyrinth);
	}

	public static void print_labyrinth(String[][] lab){
		for(int i=0;i<10;i++){
			for(int j=0;j<10;j++){
				System.out.print(lab[i][j]);
			}
			System.out.println();	
		}
		
		
		
	}
	
	
	
}
