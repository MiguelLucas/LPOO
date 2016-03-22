package test;

import static org.junit.Assert.*;
import org.junit.Test;

import logic.GameState;
import logic.IMazeBuilder;
import logic.MazeBuilder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;

public class TestMazeBuilder {
	// Auxiliary class
	public static class Point {		
		private int x, y;
		
		public int getX() {
			return x;
		}
		
		public int getY() {
			return y;
		}
		
		public Point(int y, int x) {
			this.x = x;
			this.y = y;
		}

		public boolean adjacentTo(Point p) {
			return Math.abs(p.x - this.x) + Math.abs(p.y - this.y) == 1;
		}
	}

	// a) the maze boundaries must have exactly one exit and everything else walls
	// b) the exit cannot be a corner
	private boolean checkBoundaries(String[][] m) {
		int countExit = 0;
		int n = m.length;
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				if (i == 0 || j == 0 || i == n - 1 || j == n - 1)
					if (m[i][j] == "S "){
						if ((i == 0 || i == n-1) && (j == 0 || j == n-1))
							return false;
						else
							countExit++;
					}else if (m[i][j] != "X ")
						return false;
		return countExit == 1;
	}
	

	// d) there cannot exist 2x2 (or greater) squares with blanks only 
	// e) there cannot exit 2x2 (or greater) squares with blanks in one diagonal and walls in the other
	// f) there cannot exist 3x3 (or greater) squares with walls only
	private boolean hasSquare(String[][] m, String[][] badWalls) {
		for (int i = 0; i < m.length - badWalls.length; i++)
			for (int j = 0; j < m.length - badWalls.length; j++) {
				boolean match = true;
				for (int y = 0; y < badWalls.length; y++)
					for (int x = 0; x < badWalls.length; x++) {
						if (m[i+y][j+x] != badWalls[y][x])
							match = false;
					}
				if (match)
					return true;
			}		
		return false; 
	}

	private Point findPos(String[][] m, String string) {
		for (int x = 0; x < m.length; x++)			
			for (int y = 0; y < m.length; y++)
				if (m[y][x] == string)
					return new Point(y, x);
		return null;		
	}
	
	// c) there must exist a path between any blank cell and the maze exit 
	private boolean checkExitReachable(String[][] m) {
		Point p = findPos(m, "S ");
		boolean [][] visited = new boolean[m.length] [m.length];
		
		visit(m, p.getY(), p.getX(), visited);
		
		for (int i = 0; i < m.length; i++)
			for (int j = 0; j < m.length; j++)
				if (m[i][j] != "X " && ! visited[i][j] )
					return false;
		
		return true; 
	}
	
	// auxiliary method used by checkExitReachable
	// marks a cell as visited and proceeds recursively to its neighbors
	private void visit(String[][] m, int i, int j, boolean [][] visited) {
		if (i < 0 || i >= m.length || j < 0 || j >= m.length)
			return;
		if (m[i][j] == "X " || visited[i][j])
			return;
		visited[i][j] = true;
		visit(m, i-1, j, visited);
		visit(m, i+1, j, visited);
		visit(m, i, j-1, visited);
		visit(m, i, j+1, visited);
	}
	
	@Test
	public void testRandomMazeGenerator() throws IllegalArgumentException, IOException {
		int numMazes = 100; // number of mazes to generate and test
		int maxMazeSize = 101; // can change to any odd number >= 7
		int minMazeSize = 7;
		
		IMazeBuilder builder = new MazeBuilder();
		String[][]  badWalls  =  {
				{"X ",  "X ",  "X "},
				{"X ",  "X ",  "X "},
				{"X ",  "X ",  "X "}};
		String[][]  badSpaces  =  {
				{"  ",  "  "},
				{"  ",  "  "}};
		String[][]  badDiagonalDown  =  {
				{"X ",  "  "},
				{"  ",  "X "}};
		String[][]  badDiagonalUp  =  {
				{"  ",  "X "},
				{"X ",  "  "}};
		
		Random rand = new Random(); 
		
		for (int i = 0; i < numMazes; i++) {
			int size = maxMazeSize == minMazeSize? minMazeSize : minMazeSize + 2 * rand.nextInt((maxMazeSize - minMazeSize)/2);
			GameState game = new GameState();
			game.getLabyrinth().setLabyrinth(builder.buildMaze(size));
			game.generateDragons(1);
			game.generateHero();
			game.generateSword();
			
			game.getLabyrinth().getLabyrinth()[game.getLabyrinth().getSword().getPosition().getY()][game.getLabyrinth().getSword().getPosition().getX()] = game.getLabyrinth().getSword().getIcon();
			game.getLabyrinth().getLabyrinth()[game.getLabyrinth().getHero().getPosition().getY()][game.getLabyrinth().getHero().getPosition().getX()] = game.getLabyrinth().getHero().getIcon();
			game.getLabyrinth().getLabyrinth()[game.getLabyrinth().getDragons().get(0).getPosition().getY()][game.getLabyrinth().getDragons().get(0).getPosition().getX()] = game.getLabyrinth().getDragons().get(0).getIcon();
			
			String[][]m = game.getLabyrinth().getLabyrinth();
			assertTrue("Invalid maze boundaries in maze:\n" + m, checkBoundaries(m));			
			assertTrue("Invalid walls in maze:\n" + m, ! hasSquare(m, badWalls));
			assertTrue("Invalid spaces in maze:\n" + m, ! hasSquare(m, badSpaces));
			assertTrue("Invalid diagonals in maze:\n" + m, ! hasSquare(m, badDiagonalDown));
			assertTrue("Invalid diagonals in maze:\n" + m, ! hasSquare(m, badDiagonalUp));
			
			writeFile3(m);
			assertTrue("Maze exit not reachable in maze:\n" + m, checkExitReachable(m));			
			assertNotNull("Missing exit in maze:\n" + m, findPos(m, "S "));
			assertNotNull("Missing hero in maze:\n" + m, findPos(m, "H "));
			assertNotNull("Missing dragon in maze:\n" + m, findPos(m, "D "));
			assertNotNull("Missing sword in maze:\n" + m, findPos(m, "E "));
			assertFalse("Adjacent hero and dragon in maze:\n" + m, findPos(m, "H ").adjacentTo(findPos(m, "D " )));
		
		}	
	}
	
	public void writeFile3(String[][] m) throws IOException {
		PrintWriter pw = new PrintWriter(new FileWriter("out.txt"));
	 
		for(int i=0;i<m[0].length;i++){
			for(int j=0;j<m[1].length;j++){
				pw.print(m[i][j]);
			}
			pw.println();	
		}
	 
		pw.close(); 
	}
	
	public String str(char[][] m) {
		StringBuilder s = new StringBuilder();
		for (char [] line : m) {
			s.append(Arrays.toString(line));
			s.append("\n");
		}
		return s.toString();
	}
}