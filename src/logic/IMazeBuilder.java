package logic;

public interface IMazeBuilder {
	public String[][] buildMaze(int size) throws IllegalArgumentException;
}