package logic;

import java.util.Random;
import java.util.Stack;

public class MazeBuilder {

	public static class Position {		
		private int x, y;
		
		public int getX() {
			return x;
		}
		
		public int getY() {
			return y;
		}
		
		public Position(int y, int x) {
			this.x = x;
			this.y = y;
		}
	}
	
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
				boolean esq = true, dir = true, cima = true, baixo = true, semSaida = false;
				int direccao = r.nextInt(4);
				switch(direccao){
				case 0://movimenta para baixo
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
				case 1://movimenta para cima
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
				case 2://movimenta para a direita
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
				case 3://movimenta para a esquerda
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
				//descomentar para verificar conteúdo do arrayPositions
				/*for (int i=0;i<arrayPositions[0].length;i++){
						for(int k=0;k<arrayPositions[1].length;k++){
							System.out.print(arrayPositions[i][k]);
						}
						System.out.println("");
					}
					System.out.println("");*/
				if ((xIni+1)<arrayPositions[0].length){
					if (arrayPositions[xIni+1][yIni] == ". "){
						dir = false;
					}
				}

				if ((xIni-1)>=0){
					if (arrayPositions[xIni-1][yIni] == ". "){
						esq = false;
					}
				}

				if ((yIni+1)<arrayPositions[0].length){
					if (arrayPositions[xIni][yIni+1] == ". "){
						baixo = false;
					}	
				}

				if ((yIni-1)>=0){
					if (arrayPositions[xIni][yIni-1] == ". "){
						cima = false;
					}
				}

				if (cima==true && baixo==true && esq==true && dir==true)
					semSaida = true;

				if (verifyFinished(arrayPositions)){
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

}
