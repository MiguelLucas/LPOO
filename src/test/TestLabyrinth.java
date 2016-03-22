package test;

import static org.junit.Assert.*;
import org.junit.Test;

import logic.*;
import logic.Item.Position;

public class TestLabyrinth {

	private String [][] labyrinthtest = {{"X ","S ","X ","X ","X "},
										 {"X ","H ","E ","SL","X "},
										 {"X ","  ","X ","  ","X "},
										 {"X ","D ","  ","  ","X "},
										 {"X ","X ","X ","X ","X "}};
	private Labyrinth labyrinth = new Labyrinth(labyrinthtest);

	//a) move-se para celula vazia 
	@Test
	public void testMoveHeroToFreeCell() {
		Position p1 = new Position(1, 1);
		assertEquals(labyrinth.getHero().getPosition(), p1);
		labyrinth.move_down(labyrinth.getHero());
		Position p2 = new Position(1, 2);
		assertEquals(labyrinth.getHero().getPosition(), p2);
	}

	//b) contra as paredes
	@Test
	public void testRetardedHero() {
		Position p1 = new Position(1, 1);
		assertEquals(labyrinth.getHero().getPosition(), p1);
		labyrinth.move_left(labyrinth.getHero());
		assertEquals(labyrinth.getHero().getPosition(), p1);
	}

	//c) verifica se apanha a espada 
	@Test
	public void testCatchesSword() {
		Position p_sword = new Position(2,1); 
		labyrinth.getSword().setPosition(p_sword); 
		assertTrue(!labyrinth.getHero().isArmedSword());
		labyrinth.move_right(labyrinth.getHero());
		labyrinth.hero_catches_sword();
		assertTrue(labyrinth.getHero().isArmedSword());
	}
			
	//d) se o heroi morre 
	@Test
	public void testHeroDies() {
		Position p_sword = new Position(1, 2);
		labyrinth.getSword().setPosition(p_sword); 
		//labyrinth.getSword().setX(2);
		//labyrinth.getSword().setY(1);
		Position p_lab = new Position(1, 1);
		assertEquals(labyrinth.getHero().getPosition(), p_lab);
		//assertEquals(labyrinth.getLabyrinth()[labyrinth.getHero().getY()][labyrinth.getHero().getX()], labyrinthtest[1][1]);
		assertTrue(!labyrinth.getHero().isArmedSword());
		labyrinth.move_down(labyrinth.getHero());
		p_lab.setY(2);			
		assertEquals(labyrinth.getHero().getPosition(), p_lab);
		labyrinth.dragonKillsHero(labyrinth.getDragons().get(0));
		assertTrue(labyrinth.getHero().isDead());
	}


	//e) heroi mata o dragao  
	@Test
	public void testDragonDies() {
		Position p_sword = new Position(2,1);
		Position p_dragon =  new Position(1,3);
		labyrinth.getSword().setPosition(p_sword);
		labyrinth.getDragons().get(0).setPosition(p_dragon);
		labyrinth.move_right(labyrinth.getHero());
		labyrinth.hero_catches_sword();
		labyrinth.move_left(labyrinth.getHero());
		labyrinth.move_down(labyrinth.getHero());
		labyrinth.heroKillsDragon(labyrinth.getDragons().get(0));
		assertTrue(labyrinth.getDragons().get(0).isDead());
	}

	//f) testar mover para a saida e passar nesse labirinto 
	@Test
	public void testHeroWins() {
		Position p_sword = new Position(2,1);
		Position p_dragon =  new Position(1,3);
		labyrinth.getSword().setPosition(p_sword);
		labyrinth.getDragons().get(0).setPosition(p_dragon);
		labyrinth.move_right(labyrinth.getHero()); 	
		labyrinth.hero_catches_sword();
		//esta armado
		labyrinth.move_left(labyrinth.getHero());
		labyrinth.move_down(labyrinth.getHero());
		labyrinth.move_down(labyrinth.getHero());
		labyrinth.heroKillsDragon(labyrinth.getDragons().get(0));
		assertTrue(labyrinth.getDragons().get(0).isDead());
		labyrinth.move_up(labyrinth.getHero());
		labyrinth.move_up(labyrinth.getHero());
		assertEquals(labyrinth.getEndGame(), 1);
	}

	//g) testar ir para a saida sem a espada 
	@Test
	public void testOutwithnoSword() {
		assertTrue(!labyrinth.getHero().isArmedSword());
		labyrinth.move_up(labyrinth.getHero());
		assertEquals(labyrinth.getEndGame(), 0); 
	}

	//h) testar vitoria sem ter morto o dragao 
	@Test 
	public void testOutwithDragonAlive() {
		Position p_dragon =  new Position(1,3);
		labyrinth.getDragons().get(0).setPosition(p_dragon);
		labyrinth.move_up(labyrinth.getHero());
		assertEquals(labyrinth.getEndGame(), 0);
	}

}