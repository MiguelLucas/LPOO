package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import logic.Labyrinth;
import logic.Item.Position;

public class TestTorch {

	private String [][] labyrinthtest = {{"X ","S ","X ","X ","X ","X ","X "},
										 {"X ","H ","  ","  ","  ","  ","X "},
										 {"X ","  ","X ","X ","X ","  ","X "},
										 {"X ","  ","  ","  ","X ","  ","X "},
										 {"X ","X ","X ","X ","X ","X ","X "}};
	private Labyrinth labyrinth = new Labyrinth(labyrinthtest);

	@Test 
	public void testPlaceTorch() {
		labyrinth.getHero().setTorches(1);
		Position p1 = new Position(1, 1);
		assertEquals(labyrinth.getTorches().size(), 0);
		labyrinth.useTorch();
		assertEquals(labyrinth.getTorches().size(), 1);
		assertEquals(labyrinth.getTorches().get(0).getPosition(), p1);
		assertEquals(labyrinth.getHero().getTorches(),0);
	}
	
	@Test 
	public void testNoMoreTorches() {
		labyrinth.getHero().setTorches(0);
		labyrinth.useTorch();
		assertEquals(labyrinth.getTorches().size(), 0);
		assertEquals(labyrinth.getHero().getTorches(),0);
	}
	
	@Test 
	public void testPassThroughTorch() {
		labyrinth.getHero().setTorches(1);
		Position p1 = new Position(1, 1);
		labyrinth.useTorch();
		labyrinth.move_right(labyrinth.getHero());
		labyrinth.move_left(labyrinth.getHero());
		labyrinth.move_down(labyrinth.getHero());
		assertEquals(labyrinth.getTorches().get(0).getPosition(), p1);
	}
}
