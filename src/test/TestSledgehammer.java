package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import logic.Labyrinth;
import logic.Item.Position;

public class TestSledgehammer {

	private String [][] labyrinthtest = {{"X ","S ","X ","X ","X "},
										 {"X ","H ","  ","SL","X "},
										 {"X ","  ","X ","X ","X "},
										 {"X ","D ","  ","  ","X "},
										 {"X ","X ","X ","X ","X "}};
	private Labyrinth labyrinth = new Labyrinth(labyrinthtest);

	@Test 
	public void testBreakWallWithSledgehammer() {
		labyrinth.move_right(labyrinth.getHero());
		labyrinth.move_right(labyrinth.getHero());
		labyrinth.heroCatchesSledgehammer();
		Position p1 =  new Position(3,1);
		assertEquals(labyrinth.getHero().getPosition(), p1);
		assertTrue(labyrinth.getHero().isArmedSledgehammer());
		assertEquals(labyrinth.getLabyrinth()[2][3], "X ");
		labyrinth.useSledgehammer(1);
		assertEquals(labyrinth.getLabyrinth()[2][3], "  ");
		
		//test para verificar que nao parte parede quando os uses do sledgehammer acabam
		labyrinth.move_down(labyrinth.getHero());
		assertEquals(labyrinth.getSledgehammer().getUses(),0);
		assertEquals(labyrinth.getLabyrinth()[2][2], "X ");
		labyrinth.useSledgehammer(2);
		assertEquals(labyrinth.getLabyrinth()[2][2], "X ");
	}
	
	@Test
	public void testOuterWallUnbreakable() {
		labyrinth.move_right(labyrinth.getHero());
		labyrinth.move_right(labyrinth.getHero());
		labyrinth.heroCatchesSledgehammer();
		Position p1 =  new Position(3,1);
		assertEquals(labyrinth.getHero().getPosition(), p1);
		assertTrue(labyrinth.getHero().isArmedSledgehammer());
		assertEquals(labyrinth.getLabyrinth()[0][3], "X ");
		labyrinth.useSledgehammer(0);
		assertEquals(labyrinth.getLabyrinth()[0][3], "X ");
	}
	
	@Test
	public void testBreak2Walls() {
		labyrinth.getSledgehammer().setUses(2);
		labyrinth.move_right(labyrinth.getHero());
		labyrinth.move_right(labyrinth.getHero());
		labyrinth.heroCatchesSledgehammer();
		assertTrue(labyrinth.getHero().isArmedSledgehammer());
		assertEquals(labyrinth.getLabyrinth()[2][3], "X ");
		labyrinth.useSledgehammer(1);
		assertEquals(labyrinth.getLabyrinth()[2][3], "  ");
		
		//test segunda parede
		labyrinth.move_down(labyrinth.getHero());
		assertEquals(labyrinth.getSledgehammer().getUses(),1);
		assertEquals(labyrinth.getLabyrinth()[2][2], "X ");
		labyrinth.useSledgehammer(2);
		assertEquals(labyrinth.getLabyrinth()[2][2], "  ");
	}

}
