package cli;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import javax.xml.ws.handler.MessageContext;

import logic.Dragon;
import logic.GameState;
import logic.Hero;
import logic.Labyrinth;
import logic.Item.Position;

public class cli {
	public static void launchGame(GameState game){
		generateDragons(game);
		game.generateHero();
		game.generateSword();
		if (game.isSettingSledgehammer())
			game.generateSledgehammer();
		
		printLabyrinth(game);
		while (game.getLabyrinth().getEndGame() == 0){
			moveHeroCli(game);
			//opcao 2
			//heroi matar dragao antes E depois deste se mover
			//muito mais f�cil e leg�vel, mas talvez menos eficiente?
			//game.getLabyrinth().heroKillsDragon();
			for (int i=0;i<game.getLabyrinth().getDragons().size();i++){
				if(!game.getLabyrinth().getDragons().get(i).isDead()){
					if(game.isSettingMove()){

						game.getLabyrinth().moveDragon(game.getLabyrinth().getDragons().get(i));
					}
					game.getLabyrinth().heroKillsDragon(game.getLabyrinth().getDragons().get(i));
					game.getLabyrinth().dragonKillsHero(game.getLabyrinth().getDragons().get(i));
					if(game.isSettingSleep()){
						game.getLabyrinth().getDragons().get(i).goToSleep(game.getSleepRate());
					}
				}
			}
			printLabyrinth(game);
		}
		//printLabyrinth(game);
		GameState newGame = new GameState();
		MenuGenerator(newGame); 
	}

	public static void MenuGenerator(GameState game){
		System.out.println("Welcome to the Senas Kingdom");
		int opt = 0;
		//MENU opcoes jogo
		while(opt < 1 || opt > 4){
			try {
				System.out.println("Choose your option");
				System.out.println("[1] Standard game");
				// qual era a diferen�a entre este e o standard? 
				System.out.println("[2] Generated Labyrinth");
				System.out.println("[3] Settings");
				System.out.println("[4] Exit Game");
				Scanner sc = new Scanner(System.in);
				opt = sc.nextInt();
				if(opt < 1 || opt > 4)
					System.out.println("Please select an option between 1 and 4");
				//sc.close();
			} catch (InputMismatchException e) {
				System.out.println("Invalid value!");
			} 
			switch(opt){
			case 1:
				launchGame(game);
				break; 
			case 2: 
				buildMaze(game);
				launchGame(game);
				break; 
			case 3:
				menuSettings(game);
				break;
			case 4:
				System.out.println("Dragon Senas is waiting for you. See you soon!");
				System.exit(0);
				break; 
			}
		}
	}

	public static void buildMaze(GameState game){
		System.out.println("Choose labyrinth size (odd number greater than 6):");
		int size = 2;

		while (size%2 == 0 || size < 7){
			try {
			Scanner sc = new Scanner(System.in);
			size = sc.nextInt();
			if (size%2 == 0 || size < 7)
				System.out.println("Choose an odd number greater than 6! ");
			} catch (InputMismatchException e) {
				System.out.println("Invalid value!");
			} 
		}
		game.generateLabyrinth(size);
	}
	
	public static void menuSettings(GameState game){
		int opt = -1;
		while(opt < 0 || opt > 4){
			try {
				System.out.println("Choose your settings");
				//voltar atras no menu
				System.out.println("[0] Go back");
				//op�ao de mover
				if (game.isSettingMove())
					System.out.println("[1] Enable/disable dragon moves (Enabled)");
				else
					System.out.println("[1] Enable/disable dragon moves (Disabled)");
				//opcao de dormir
				if (game.isSettingSleep())
					System.out.println("[2] Enable/disable dragon sleep (Enabled)");
				else
					System.out.println("[2] Enable/disable dragon sleep (Disabled)");
				//opcao para percentagem de adormecer
				System.out.println("[3] Set dragon sleep rate (Currently " + game.getSleepRate() + "%)");
				if (game.isSettingSledgehammer())
					System.out.println("[4] Enable/disable sledgehammer (Enabled)");
				else
					System.out.println("[4] Enable/disable sledgehammer (Disabled)");
				/*
			restantes ficam aqui para futuro uso

			if (l.isSetting_dragon_fire())
				System.out.println("[1] Enable/disable dragon fire (Enabled)");
			else
				System.out.println("[1] Enable/disable dragon fire (Disabled)");

			if (l.isSetting_spear())
				System.out.println("[4] Enable/disable retrivable spears (Enabled)");
			else
				System.out.println("[4] Enable/disable retrivable spears (Disabled)");
			if (l.isSetting_shield_generate())
				System.out.println("[5] Enable/disable shield generation (Enabled)");
			else
				System.out.println("[5] Enable/disable shield generation (Disabled)");
			if (l.isSetting_shield_start())
				System.out.println("[6] Enable/disable starting shield (Enabled)");
			else
				System.out.println("[6] Enable/disable starting shield (Disabled)");
			if (l.isSetting_sledgehammer())
				System.out.println("[7] Enable/disable sledgehammer (Enabled)");
			else
				System.out.println("[7] Enable/disable sledgehammer (Disabled)");
				 */
				Scanner sc = new Scanner(System.in);
				opt = sc.nextInt();
				if(opt < 0 || opt > 4){
					System.out.println("Please select an option between 0 and 4");
				}
				//sc.close();
			} catch (InputMismatchException e) {
				System.out.println("Invalid value!");
			} 
			switch(opt){
			case 0:
				MenuGenerator(game); 
				break;
			case 1:
				if (game.isSettingMove())
					game.setSettingMove(false);
				else
					game.setSettingMove(true);
				break;
			case 2:
				if (game.isSettingSleep())
					game.setSettingSleep(false);
				else
					game.setSettingSleep(true);
				break;
			case 3:
				int sleepRate = -1;
				while (sleepRate < 0 || sleepRate > 100){
					try {
						System.out.println("Insert new sleep rate percentage (0-100)");
						Scanner sc = new Scanner(System.in);
						sleepRate = sc.nextInt();
						if(sleepRate < 0 || sleepRate > 100){
							System.out.println("Please select a value between 0 and 100");
						}
						//sc.close();
					} catch (InputMismatchException e) {
						System.out.println("Invalid value!");
					}
				}
				game.setSleepRate(sleepRate);
				System.out.println("Sleep rate successfully changed to " + sleepRate + "%!\n");
				break;
			case 4:
				if (game.isSettingSledgehammer())
					game.setSettingSledgehammer(false);
				else
					game.setSettingSledgehammer(true);
				break;
			}
			
			menuSettings(game);
		}
	}
	
	public static void generateDragons(GameState game){
		int size = game.getLabyrinth().getLabyrinth()[0].length;
		double maxDragons = Math.pow((size-1)/2,2);
		int nDragons = 0;
		System.out.println("Choose the number of dragons");
		while (nDragons < 1 || nDragons > maxDragons){
			try {
				Scanner sc = new Scanner(System.in);
				nDragons = sc.nextInt();
				if (nDragons < 1 || nDragons > maxDragons)
					System.out.println("Choose a number between 1 and " + maxDragons + "!");
			} catch (InputMismatchException e) {
				System.out.println("Invalid value!");
			} 
		}
		for (int i=1;i<nDragons;i++){
			Dragon d1 = new Dragon();
			int x = 0, y = 0;
			Random r = new Random();
			while (game.getLabyrinth().getLabyrinth()[y][x] != "  "){
				x = r.nextInt(size-1)+1;
				y = r.nextInt(size-1)+1;
			}
			d1.getPosition().setX(x);
			d1.getPosition().setY(y);
			game.getLabyrinth().addDragon(d1);
		}
		game.generateDragons();
	}

	public static void moveHeroCli(GameState game){
		if (game.getLabyrinth().getHero().isArmedSledgehammer())
			System.out.println("W-Up, S-Down, A-Left, D-Right, U-Use Sledgehammer, E-Exit Game");
		else {
			System.out.println("W-Up, S-Down, A-Left, D-Right, E-Exit Game");
		}
		Scanner sc = new Scanner(System.in);
		char move = Character.toUpperCase(sc.next().charAt(0));
		
		switch(move){
		case 'W':
			game.getLabyrinth().move_hero('W');
			break;
		case 'S':
			game.getLabyrinth().move_hero('S');
			break;
		case 'A':
			game.getLabyrinth().move_hero('A');
			break;
		case 'D':
			game.getLabyrinth().move_hero('D');
			break;
		case 'U':
			if (game.getLabyrinth().getHero().isArmedSledgehammer())
				useSledgehammerCli(game);
			else {
				System.out.println("Invalid direction.");
				moveHeroCli(game);
			}
			break;
		case 'E':
			System.out.println("Goodbye :(");
			sc.close();
			System.exit(0);
		default:
			System.out.println("Invalid direction.");
			moveHeroCli(game);
			break;
		}
	}
	
	public static void useSledgehammerCli(GameState game){
		System.out.println("Choose the direction you wish to use the sledgehammer\nW-Up, S-Down, A-Left, D-Right");
		Scanner sc = new Scanner(System.in);
		char move = Character.toUpperCase(sc.next().charAt(0));
		
		switch(move){
		case 'W':
			game.getLabyrinth().useSledgehammer(0);
			break;
		case 'S':
			game.getLabyrinth().useSledgehammer(1);
			break;
		case 'A':
			game.getLabyrinth().useSledgehammer(2);
			break;
		case 'D':
			game.getLabyrinth().useSledgehammer(3);
			break;
		default:
			System.out.println("Invalid direction.");
			useSledgehammerCli(game);
			break;
		}
	}
	
	public static void printLabyrinth(GameState game){
		game.getLabyrinth().getLabyrinth()[game.getLabyrinth().getSledgehammer().getPosition().getY()][game.getLabyrinth().getSledgehammer().getPosition().getX()] = game.getLabyrinth().getSledgehammer().getIcon();
		game.getLabyrinth().getLabyrinth()[game.getLabyrinth().getSword().getPosition().getY()][game.getLabyrinth().getSword().getPosition().getX()] = game.getLabyrinth().getSword().getIcon();
		for (int i=0;i<game.getLabyrinth().getDragons().size();i++){
			game.getLabyrinth().getLabyrinth()[game.getLabyrinth().getDragons().get(i).getPosition().getY()][game.getLabyrinth().getDragons().get(i).getPosition().getX()] = game.getLabyrinth().getDragons().get(i).getIcon();
		}
		game.getLabyrinth().getLabyrinth()[game.getLabyrinth().getHero().getPosition().getY()][game.getLabyrinth().getHero().getPosition().getX()] = game.getLabyrinth().getHero().getIcon();
		
		ArrayList<Position> positions = fogOfWar(game);
		//imprime o labirinto
		for(int i=0;i<game.getLabyrinth().getLabyrinth()[0].length;i++){
			for(int j=0;j<game.getLabyrinth().getLabyrinth()[1].length;j++){
				Position p1 = new Position(i, j);
				if (positions.contains(p1))
					System.out.print(game.getLabyrinth().getLabyrinth()[i][j]);
				else {
					System.out.print("  ");
				}
			}
			System.out.println();	
		}
		
		
		printInventory(game);
		printMessage(game);
	}
	
	public static ArrayList<Position> fogOfWar(GameState game){
		ArrayList<Position> positions = new ArrayList<Position>();
		for(int i=0;i<game.getLabyrinth().getLabyrinth()[0].length;i++){
			for(int j=0;j<game.getLabyrinth().getLabyrinth()[1].length;j++){
				if (Math.sqrt(Math.pow(i-game.getLabyrinth().getHero().getPosition().getX(),2)
						+ Math.pow(j-game.getLabyrinth().getHero().getPosition().getY(),2)) <= 5){
					Position p1 = new Position(j, i);
					positions.add(p1);
				}
					
			}	
		}
		return positions;
	}
	
	public static void printInventory(GameState game){
		System.out.println("----------------------");
		System.out.println("Hero's inventory: ");
		if (game.getLabyrinth().getHero().isArmedSword())
			System.out.println("1x Sword ");
		if (game.getLabyrinth().getHero().isArmedSledgehammer())
			System.out.println("1x Sledgehammer (" + game.getLabyrinth().getSledgehammer().getUses() + " uses left)");
		else
			System.out.println("Nothing");
		//a acrescentar mais items conforme se v�o criando
		System.out.println("----------------------");
	}
	
	public static void printMessage(GameState game){
		System.out.println("----------------------");
		System.out.println(game.getLabyrinth().getMessage() + "\n");
		game.getLabyrinth().setMessage("Hero,choose your action!");
	}
	
	public static void main(String[] args) {
		GameState newGame = new GameState();
		MenuGenerator(newGame); 
		
	}
}
