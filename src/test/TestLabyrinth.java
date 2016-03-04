package test;

import static org.junit.Assert.*;
import org.junit.Test;

import logic.*;

public class TestLabyrinth {
		
		private String [][] labyrinthtest = {{"X ","S ","X ","X ","X "},
					 	  					 {"X ","H ","E "," "," "},
					 	  					 {"X "," ","X "," ","X "},
					 	  					 {"X ","D "," "," ","X "},
					 	  					 {"X ","X ","X ","X ","X "}};
		private Labyrinth labyrinth = new Labyrinth(labyrinthtest);
		
		
		
		/*private String[][] labyrinth = 
			{{"X ","X ","X ","X ","X ","X ","X ","X ","X ","X "},
			{"X ","H ","  ","  ","  ","  ","  ","  ","  ","X "},
			{"X ","  ","X ","X ","  ","X ","  ","X ","  ","X "},
			{"X ","D ","X ","X ","  ","X ","  ","X ","  ","X "},
			{"X ","  ","X ","X ","  ","X ","  ","X ","  ","X "},
			{"X ","  ","  ","  ","  ","  ","  ","X ","  ","S "},
			{"X ","  ","X ","X ","  ","X ","  ","X ","  ","X "},
			{"X ","  ","X ","X ","  ","X ","  ","X ","  ","X "},
			{"X ","E ","X ","X ","  ","  ","  ","  ","  ","X "},
			{"X ","X ","X ","X ","X ","X ","X ","X ","X ","X "}};
		*/
		
		@Test
		public void testMoveHeroToFreeCell() {
			assertEquals(labyrinth.getLabyrinth()[labyrinth.getHero().getY()][labyrinth.getHero().getX()], labyrinthtest[1][1]);
			labyrinth.move_down(labyrinth.getHero());
			assertEquals(labyrinth.getLabyrinth()[labyrinth.getHero().getY()][labyrinth.getHero().getX()], labyrinthtest[2][1]);
		}
		
	/*	@Test
		public void testRetardedHero() {
			assertEquals(labyrinth.getLabyrinth()[labyrinth.getHero().getY()][labyrinth.getHero().getX()], labyrinthtest[1][1]);
			labyrinth.move_left(labyrinth.getHero());
			assertEquals(labyrinth.getLabyrinth()[labyrinth.getHero().getY()][labyrinth.getHero().getX()], labyrinthtest[0][1]);
		}
		*/
		
		@Test
		public void testHeroDies() {
			labyrinth.getSword().setX(2);
			labyrinth.getSword().setY(1);
			assertTrue(!labyrinth.getHero().isArmedSword());
			labyrinth.move_down(labyrinth.getHero());
			labyrinth.dragonKillsHero();
			assertEquals(labyrinth.getLabyrinth()[labyrinth.getHero().getY()][labyrinth.getHero().getX()], labyrinthtest[2][1]);
			assertTrue(labyrinth.getHero().isDead());
		}
		/*
			
			assertEquals(MazeStatus.HeroUnarmed, maze.getStatus());
			maze.moveHeroDown();
			assertEquals(MazeStatus.HeroDied, maze.getStatus());
		fail("Not yet implemented");
	} */

}
