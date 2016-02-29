package cli;

import java.util.Scanner;

import logic.Labyrinth;

public class cli {
	//adicionar o parametro DragonType? 
	public static void launch_game(int DragonType){
		/* DragonType = 1 parado sem dormir. 
		 * 				2 move-se mas nunca dorme. 
		 * 				3 tem sono como os mortais.
		 */
		Labyrinth l = new Labyrinth();
		l.print_labyrinth();
		while (true){
			l.move_hero();
			if(!l.getDragon().isDead()){
				if(DragonType == 2 || DragonType == 3){
					l.moveDragon();
				}
				l.heroKillsDragon();
				l.dragonKillsHero();
				if(DragonType == 3){
					l.getDragon().goToSleep();
				}
			}
			l.print_labyrinth();
		}
	}
	
	public static void MenuGenerator(){
		System.out.println("Welcome to the Senas Kingdom");
		int opt = 0;
		//MENU opcoes jogo
		while(opt < 1 || opt > 3){
			System.out.println("Choose your option");
			System.out.println("[1] Standard game");
			// qual era a diferença entre este e o standard? 
			System.out.println("[2] Generated Labyrinth");
			System.out.println("[3] Exit Game");
			Scanner sc = new Scanner(System.in);
			opt = sc.nextInt();
			if(opt < 1 || opt > 3)
				System.out.println("Invalid command.");
		  	} 
			switch(opt){
				case 1:
					int opt_d=0; 
					//MENU opcoes dragao
					while(opt_d < 1 || opt_d > 3){
					System.out.println("How do you want Dragon Senas?");
					System.out.println("[1] Always awake in the same place.");  
					System.out.println("[2] Always awake and sometimes walking around.");
					System.out.println("[3] Sometimes walking around and going to sleep.");			
					Scanner vai = new Scanner(System.in);
					opt_d = vai.nextInt();
					//e melhor testar com o < > como no do jogo certo?
					if(opt_d != 1 && opt_d != 2 && opt_d != 3)
						System.out.println("Invalid command."); 
			        }
					launch_game(opt_d);
					break; 
				case 2: 
					//
					break; 
				case 3:
					System.out.println("Dragon Senas is waiting for you. See you soon!");
					System.exit(0);
					break; 
			}			
			
	}
		public static void main(String[] args) {
			//nao sei se faz sentido o main só ter isto :P 
			MenuGenerator(); 
			
		}
}
