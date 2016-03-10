package logic;

import java.util.Random;
import java.util.Stack;

import logic.Item.Position;

public class MazeBuilder {
	
	public MazeBuilder() {}
	
	public String[][] buildMaze(int size) {
		String[][] labyrinth = new String[size][size];
		String[][] arrayPositions = new String[(size-1)/2][(size-1)/2];
		//Encher todo o labirinto com "X" com exceçao de todas as coordenadas impares
		for (int i=0;i<labyrinth[0].length;i++){
			for(int k=0;k<labyrinth[1].length;k++){
				labyrinth[i][k] = "X ";
				if (i%2 != 0 && k%2 != 0)
					labyrinth[i][k] = "  ";
			}
		}
		for (int i=0;i<arrayPositions[0].length;i++){
			for(int k=0;k<arrayPositions[1].length;k++){
				arrayPositions[i][k] = ". ";
			}
		}
		//--------------------------------------
		//Escolher uma posicao inicial livre aleatoriamente
		Random r = new Random();
		int xIni = r.nextInt(size);
		int yIni = r.nextInt(size);
		while (xIni%2 == 0)
			xIni = r.nextInt(size);
		while (yIni%2 == 0)
			yIni = r.nextInt(size);
		labyrinth[xIni][yIni] = "  ";
		xIni = (xIni-1)/2;
		yIni = (yIni-1)/2;
		Position p1 = new Position(xIni,yIni);

		Stack<Position> jaPercorrido = new Stack<Position>();
		arrayPositions[p1.getX()][p1.getY()] = "1 ";
		jaPercorrido.push(p1);
		//--------------------------------------
		//gerar o labirinto "apagando" as paredes aleatoriamente
		while (!jaPercorrido.empty()){
			xIni = jaPercorrido.lastElement().getX();
			yIni = jaPercorrido.lastElement().getY();
			while (true){
				boolean left = true, right = true, up = true, down = true, semSaida = false;
				int righteccao = r.nextInt(4);
				switch(righteccao){
				case 0://movimenta para down
					if(xIni+1 < arrayPositions[0].length){
						if(arrayPositions[xIni+1][yIni] == ". "){
							Position temp = new Position(xIni+1,yIni);
							jaPercorrido.push(temp);
							arrayPositions[xIni+1][yIni] = "X";
							labyrinth[2*xIni+2][yIni*2 +1] = "  ";
							xIni++;
						}
					}
					break;
				case 1://movimenta para up
					if(xIni-1 >= 0){
						if(arrayPositions[xIni-1][yIni] == ". "){
							Position temp = new Position(xIni-1,yIni);
							jaPercorrido.push(temp);
							arrayPositions[xIni-1][yIni] = "X";
							labyrinth[2*xIni][yIni*2 +1] = "  ";
							xIni--;
						}
					}
					break;
				case 2://movimenta para a righteita
					if(yIni+1 < arrayPositions[0].length){
						if(arrayPositions[xIni][yIni+1] == ". "){
							Position temp = new Position(xIni,yIni+1);
							jaPercorrido.push(temp);
							arrayPositions[xIni][yIni+1] = "X";
							labyrinth[2*xIni+1][yIni*2 +2] = "  ";
							yIni++;
						}
					}
					break;
				case 3://movimenta para a leftuerda
					if(yIni-1 >= 0){
						if(arrayPositions[xIni][yIni-1] == ". "){
							Position temp = new Position(xIni,yIni-1);
							jaPercorrido.push(temp);
							arrayPositions[xIni][yIni-1] = "X";
							labyrinth[2*xIni+1][yIni*2] = "  ";
							yIni--;
						}
					}
					break;
				}
				/* descomentar para verificar progressão do arrayPositions
				System.out.println("XIni: " + xIni);
				System.out.println("YIni: " + yIni);
				for (int i=0;i<arrayPositions[0].length;i++){
						for(int k=0;k<arrayPositions[1].length;k++){
							System.out.print(arrayPositions[i][k]);
						}
						System.out.println("");
					}
					System.out.println("");*/
				if ((xIni+1)<arrayPositions[0].length){
					if (arrayPositions[xIni+1][yIni] == ". "){
						right = false;
					}
				}

				if ((xIni-1)>=0){
					if (arrayPositions[xIni-1][yIni] == ". "){
						left = false;
					}
				}

				if ((yIni+1)<arrayPositions[0].length){
					if (arrayPositions[xIni][yIni+1] == ". "){
						down = false;
					}	
				}

				if ((yIni-1)>=0){
					if (arrayPositions[xIni][yIni-1] == ". "){
						up = false;
					}
				}

				if (up==true && down==true && left==true && right==true)
					semSaida = true;

				if (verifyFinished(arrayPositions)){
					improveLabyrinth(labyrinth);
					return labyrinth;
				}

				if (semSaida){
					jaPercorrido.pop();
					break;
				}
			}
		}
		return labyrinth;
	}

	public boolean verifyFinished(String[][] arrayPositions){
		for (int i = 0;i<arrayPositions[0].length;i++){
			for (int k = 0;k<arrayPositions[1].length;k++){
				if (arrayPositions[i][k] == ". ")
					return false;
			}
		}
		return true;
	}
	
	//torna o labirinto mais "labirintico"
	public void improveLabyrinth(String[][] labyrinth){
		int size = labyrinth[0].length;
		if (size < 10)	//se o tamanho do labirinto for muito pequeno, é possível que esta função falhe, daí ter esta condição de prevenção
			return;
		Random r = new Random();
		//formula escolhida para calculo de paredes a remover: n/5
		int openings = size/5;
		while (openings > 1){
			int x = r.nextInt(size-4)+2;
			int y = r.nextInt(size-4)+2;
			
			if (labyrinth[x][y] == "X "){
				if (adjacentWalls(labyrinth,x,y)){
					// descomentar para verificar paredes retiradas
					/*System.out.println("X retirado: " + x);
					System.out.println("Y retirado: " + y);*/
					labyrinth[x][y] = "  ";
					openings--;
				}
			}
			
		}
	}
	
	public boolean adjacentWalls(String[][]labyrinth, int x, int y){
		if (labyrinth[x+1][y] == "X " && labyrinth[x+2][y] == "X " &&
				labyrinth[x-1][y] == "X " && labyrinth[x-2][y] == "X " && 
				labyrinth[x][y+1] != "X " && labyrinth[x][y-1] != "X "){
			return true;
		}
		if (labyrinth[x][y+1] == "X " && labyrinth[x][y+2] == "X " &&
				labyrinth[x][y-1] == "X " && labyrinth[x][y-2] == "X " &&
				labyrinth[x+1][y] != "X " && labyrinth[x-1][y] != "X "){
			return true;
		}
		return false;
		
	}
}
