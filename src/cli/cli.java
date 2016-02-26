package cli;

import java.util.Scanner;

import logic.Labyrinth;

public class cli {
	public static void MenuGenerator(Labyrinth l){
		int opt=0;
		System.out.println("Welcome to the Senas Kingdom");
		while (opt < 1 || opt > 3){
			System.out.println("Choose your option");
			System.out.println("[1] Standard game");
			System.out.println("[2] Generated Labyrinth");
			System.out.println("[3] Exit Game");
			Scanner sc = new Scanner(System.in);
			opt = sc.nextInt();
		}
		if (opt == 1){
			Dragon dragon = new Dragon();
			l.getDragons().add(dragon);
			launch_game(l);
		}
		else if (opt == 2)
			generate_game(l);
		else
			System.exit(0);
			
	}
	
	
	public void launch_game(){
		Labyrinth l = new Labyrinth();
		l.print_labyrinth();
		while (true){
			l.move_hero();
			if(!l.getDragon().isDead()){
				l.moveDragon();
				l.heroKillsDragon();
				l.dragonKillsHero();
				l.getDragon().goToSleep();
			}
			l.print_labyrinth();
		}
	}
	
	
	
	
	
	
		public static void main(String[] args) {
			
		}
		
	}


