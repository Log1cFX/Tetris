package Physics;

import java.awt.Point;



public class Grid extends Block implements Update{
	
	
	Grid() {
		PhysicsLoopCaster.addUpdateLoop(this);
		//System.out.println("added");
	}
	
	public static void UpdateGrid(){
		CurrentBlock.getPosition().y+=1;
		CheckIfCollision();
	}
	
	public static void CheckIfCollision() {
		Point[] Squares = getSquaresRelativeToGrid();
		for(Point pos : Squares) {
			if(pos.y+1>=20 || Placed_Blocks[pos.x][pos.y+1]!=null) {
				PlaceBlocksOnGrid(Squares);
				NewBlock();
				break;
			}
		}
	}
	
	private static void PlaceBlocksOnGrid(Point[] Squares) {
		for(Point pos : Squares) {
			Placed_Blocks[pos.x][pos.y] = CurrentBlock.getColorPalette();
		}
	}

	@Override
	public void start() {
		NewBlock();
	}

	@Override
	public void execute() {
		UpdateGrid();
	}
}
