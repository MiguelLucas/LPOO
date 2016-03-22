package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import logic.FogOfWar;
import logic.GameState;
import logic.Labyrinth;
import logic.Item.Position;

public class TestFogOfWar {

	private String [][] labyrinthtest = {{"X ","S ","X ","X ","X ","X ","X "},
										 {"X ","H ","  ","  ","  ","  ","X "},
										 {"X ","  ","X ","X ","X ","  ","X "},
										 {"X ","  ","  ","  ","X ","  ","X "},
										 {"X ","X ","X ","X ","X ","X ","X "}};
	private Labyrinth labyrinth = new Labyrinth(labyrinthtest);

	public boolean compareFogs(ArrayList<Position> fog1, ArrayList<Position> fog2){
		boolean equalTiles = true;
		//verifica se todos as posições de "tiles" existem no fog of war
		for (int i = 0; i < fog1.size(); i++) {
			if (!fog2.contains(fog1.get(i)))
				equalTiles = false;
		}
		//verifica se todos as posições do fog of war existem no "tiles"
		for (int i = 0; i < fog2.size(); i++) {
			if (!fog1.contains(fog2.get(i)))
				equalTiles = false;
		}
		return equalTiles;
	}
	
	@Test 
	public void testVisibleTiles() {
		ArrayList<Position> tiles = new ArrayList<Position>();
		for (int i=0;i<7;i++){
			Position p1 = new Position(0, i);
			Position p2 = new Position(1, i);
			Position p3 = new Position(2, i);
			tiles.add(p1);
			tiles.add(p2);
			tiles.add(p3);
		}
		for (int i=0;i<5;i++){
			Position p1 = new Position(i, 0);
			Position p2 = new Position(i, 1);
			Position p3 = new Position(i, 2);
			tiles.add(p1);
			tiles.add(p2);
			tiles.add(p3);
		}
		FogOfWar fog = new FogOfWar();
		GameState game = new GameState();
		game.setLabyrinth(labyrinth);
		fog.updateFogOfWar(game);
		boolean equalTiles = compareFogs(fog.getVisiblePositions(), tiles);
		assertTrue(equalTiles);
	}

	@Test 
	public void testCornerTiles() {
		ArrayList<Position> tiles = new ArrayList<Position>();
		labyrinth.move_down(labyrinth.getHero());
		assertEquals(labyrinth.getHero().getPosition(), new Position(1, 2));
		for (int i=0;i<5;i++){
			Position p1 = new Position(i, 0);
			Position p2 = new Position(i, 1);
			Position p3 = new Position(i, 2);
			Position p4 = new Position(i, 3);
			Position p5 = new Position(i, 4);
			tiles.add(p1);
			tiles.add(p2);
			tiles.add(p3);
			tiles.add(p4);
			tiles.add(p5);
		}
		
		FogOfWar fog = new FogOfWar();
		GameState game = new GameState();
		game.setLabyrinth(labyrinth);
		fog.updateFogOfWar(game);
		boolean equalTiles = compareFogs(fog.getVisiblePositions(), tiles);
		assertTrue(equalTiles);
		
	}
	
	@Test 
	public void testTorchFogOfWar() {
		labyrinth.move_down(labyrinth.getHero());
		labyrinth.move_down(labyrinth.getHero());
		labyrinth.getHero().setTorches(1);
		Position p1 = new Position(1, 3);
		assertEquals(labyrinth.getTorches().size(), 0);
		labyrinth.useTorch();
		assertEquals(labyrinth.getTorches().size(), 1);
		assertEquals(labyrinth.getTorches().get(0).getPosition(), p1);
		
		labyrinth.move_up(labyrinth.getHero());
		labyrinth.move_up(labyrinth.getHero());
		labyrinth.move_right(labyrinth.getHero());
		labyrinth.move_right(labyrinth.getHero());
		labyrinth.move_right(labyrinth.getHero());
		labyrinth.move_right(labyrinth.getHero());
		
		
		ArrayList<Position> tiles = new ArrayList<Position>();
		FogOfWar fog = new FogOfWar();
		GameState game = new GameState();
		game.setLabyrinth(labyrinth);
		fog.updateFogOfWar(game);
		
		for (int i=0;i<7;i++){
			for (int k=0;k<5;k++){
				Position pfog = new Position(k, i);
				tiles.add(pfog);
			}
		}
		boolean equalTiles = compareFogs(fog.getVisiblePositions(), tiles);
		assertTrue(equalTiles);
		
	}
}
