package logic;

import java.util.ArrayList;

import logic.Item.Position;

public class FogOfWar {
	private ArrayList<Position> visiblePositions;

	public FogOfWar() {
		visiblePositions = new ArrayList<Position>();
	}
	
	public ArrayList<Position> getVisiblePositions() {
		return visiblePositions;
	}

	public void setVisiblePositions(ArrayList<Position> visiblePositions) {
		this.visiblePositions = visiblePositions;
	}

	public ArrayList<Position> updateFogOfWar(GameState game){
		
		Position posHero = new Position(game.getLabyrinth().getHero().getPosition().getY(), game.getLabyrinth().getHero().getPosition().getX());
		visiblePositions.add(posHero);
		
		updateFogOfWarLeft(game,game.getLabyrinth().getHero().getPosition().getX(),game.getLabyrinth().getHero().getPosition().getY(),game.getFogOfWarRadius(),true);
		updateFogOfWarRight(game,game.getLabyrinth().getHero().getPosition().getX(),game.getLabyrinth().getHero().getPosition().getY(),game.getFogOfWarRadius(),true);
		updateFogOfWarUp(game,game.getLabyrinth().getHero().getPosition().getX(),game.getLabyrinth().getHero().getPosition().getY(),game.getFogOfWarRadius(),true);
		updateFogOfWarDown(game,game.getLabyrinth().getHero().getPosition().getX(),game.getLabyrinth().getHero().getPosition().getY(),game.getFogOfWarRadius(),true);
		
		//System.out.println(game.getLabyrinth().getTorches().size());
		for (int i=0;i<game.getLabyrinth().getTorches().size();i++){
			Position posTorch = new Position(game.getLabyrinth().getTorches().get(i).getPosition().getY(), game.getLabyrinth().getTorches().get(i).getPosition().getX());
			visiblePositions.add(posTorch);
			visiblePositions.add(game.getLabyrinth().getTorches().get(i).getPosition());
			updateFogOfWarLeft(game,game.getLabyrinth().getTorches().get(i).getPosition().getX(),game.getLabyrinth().getTorches().get(i).getPosition().getY(),
					game.getTorchRadius(),true);
			updateFogOfWarRight(game,game.getLabyrinth().getTorches().get(i).getPosition().getX(),game.getLabyrinth().getTorches().get(i).getPosition().getY(),
					game.getTorchRadius(),true);
			updateFogOfWarUp(game,game.getLabyrinth().getTorches().get(i).getPosition().getX(),game.getLabyrinth().getTorches().get(i).getPosition().getY(),
					game.getTorchRadius(),true);
			updateFogOfWarDown(game,game.getLabyrinth().getTorches().get(i).getPosition().getX(),game.getLabyrinth().getTorches().get(i).getPosition().getY(),
					game.getTorchRadius(),true);

		}
		
		/*for(int i=0;i<visiblePositions.size();i++){
			System.out.println(visiblePositions.get(i).toString() + "\n");
		}*/
		return visiblePositions;
	}

	public void updateFogOfWarLeft(GameState game,int x, int y, int tiles, boolean call){
		for (int i=1;i<=tiles;i++){
			if (x-i < 0)
				break;
			
			for(int k=-1;k<=1;k++){
				Position p1 = new Position(y+k,x-i);
				visiblePositions.add(p1);
			}
			
			if (game.getLabyrinth().getLabyrinth()[y][x-i] == "X "){
				break;
			}
		}
		if (call){
			if (game.getLabyrinth().getLabyrinth()[y+1][x-1] != "X ")
				updateFogOfWarDown(game, x-1, y+1, 2, false);
			if (game.getLabyrinth().getLabyrinth()[y-1][x-1] != "X ")
				updateFogOfWarUp(game, x-1, y-1, 2, false);
		}
	}

	public void updateFogOfWarRight(GameState game, int x, int y, int tiles, boolean call){
		for (int i=1;i<=tiles;i++){
			if (x+i > game.getLabyrinth().getLabyrinth()[0].length-1)
				break;
			
			for(int k=-1;k<=1;k++){
				Position p1 = new Position(y+k,x+i);
				visiblePositions.add(p1);
			}
			
			if (game.getLabyrinth().getLabyrinth()[y][x+i] == "X "){
				break;
			}
		}
		if (call){
			if (game.getLabyrinth().getLabyrinth()[y+1][x+1] != "X ")
				updateFogOfWarDown(game, x+1, y+1, 2, false);
			if (game.getLabyrinth().getLabyrinth()[y-1][x+1] != "X ")
				updateFogOfWarUp(game, x+1, y-1, 2, false);
		}
	}

	public void updateFogOfWarUp(GameState game, int x, int y, int tiles, boolean call){
		for (int i=1;i<=tiles;i++){
			if (y-i < 0)
				break;
			
			for(int k=-1;k<=1;k++){
				Position p1 = new Position(y-i,x+k);
				visiblePositions.add(p1);
			}
			
			if (game.getLabyrinth().getLabyrinth()[y-i][x] == "X "){
				break;
			}
		}
		if (call){
			if (game.getLabyrinth().getLabyrinth()[y-1][x-1] != "X ")
				updateFogOfWarLeft(game, x-1, y-1, 2, false);
			if (game.getLabyrinth().getLabyrinth()[y-1][x+1] != "X ")
				updateFogOfWarRight(game, x+1, y-1, 2, false);
		}
	}

	public void updateFogOfWarDown(GameState game, int x, int y, int tiles, boolean call){
		for (int i=1;i<=tiles;i++){
			if (y+i > game.getLabyrinth().getLabyrinth()[0].length-1)
				break;
			
			for(int k=-1;k<=1;k++){
				Position p1 = new Position(y+i,x+k);
				visiblePositions.add(p1);
			}
			
			if (game.getLabyrinth().getLabyrinth()[y+i][x] == "X "){
				break;
			}
		}
		if (call){
			if (game.getLabyrinth().getLabyrinth()[y+1][x-1] != "X ")
				updateFogOfWarLeft(game, x-1, y+1, 2, false);
			if (game.getLabyrinth().getLabyrinth()[y+1][x+1] != "X ")
				updateFogOfWarRight(game, x+1, y+1, 2, false);
		}
	}

}
