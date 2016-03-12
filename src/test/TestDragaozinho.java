package test;

import static org.junit.Assert.*;

//import javax.naming.spi.DirectoryManager;

import org.junit.Test;
import logic.*;

import logic.Item.Position;

public class TestDragaozinho {

	private String [][] labyrinth_dragon = {{"X ","S ","X ","X ","X "},
											{"X ","H ","E ","  "," "},
											{"X ","  ","X ","  ","X "},
											{"X ","D ","  ","  ","X "},
											{"X ","X ","X ","X ","X "}};
	private Labyrinth labyrinth = new Labyrinth(labyrinth_dragon);

	//ver se ele adormece
	@Test 
	public void testDragonSleeps(){	 
		Position p_dragon =  new Position(1,3);
		labyrinth.getDragons().get(0).setPosition(p_dragon);
		assertEquals(labyrinth.getDragons().get(0).getPosition(), p_dragon);
		boolean dorme = false; 
		while(!dorme){
			labyrinth.getDragons().get(0).goToSleep(20);
			if(labyrinth.getDragons().get(0).isSleeping())
				dorme = true; 
		}
		assertTrue(labyrinth.getDragons().get(0).isSleeping());
	}

	//teste dos moves
	@Test
	public void testDragonMoves(){	 
		boolean cima = false, baixo = false, direita = false, esquerda = false, noSitio = false;
		Position p_dragon1 =  new Position(1,3);
		labyrinth.getDragons().get(0).setPosition(p_dragon1);
		while (!cima || !baixo || !direita || !esquerda || !noSitio) {
			if (labyrinth.moveDragon(labyrinth.getDragons().get(0)) == 0){
				cima = true;
			}
			if (labyrinth.moveDragon(labyrinth.getDragons().get(0)) == 1){
				baixo = true;
			}
			if (labyrinth.moveDragon(labyrinth.getDragons().get(0)) == 2){
				esquerda = true;
			}
			if (labyrinth.moveDragon(labyrinth.getDragons().get(0)) == 3){
				direita = true;
			}
			if (labyrinth.moveDragon(labyrinth.getDragons().get(0)) == -1){
				noSitio = true;
			}
		}
		assertTrue(direita && esquerda && cima && baixo && noSitio);
	}

}

		





