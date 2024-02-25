package Physics;

import java.awt.Point;

import ScreenSettings.Settings;



public class Grid extends Block implements Update{
	
	long startTime = System.nanoTime();
    long finalTime;
	
	Grid() {
		PhysicsLoopCaster.addPhysicsUpdateLoop(this);
		//System.out.println("added");
	}
	
	public static void UpdateGrid(){
		CheckIfCollision();
		//CurrentBlock.getPosition().y+=1;
	}
	
	public static void CheckIfCollision() {
		Point[] Squares = getSquaresRelativeToGrid();
		for(Point pos : Squares) {
			if(pos.y+1>=Settings.ROWS || Placed_Blocks[pos.x][pos.y+1]!=null) {
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
		finalTime=System.nanoTime();
		if((finalTime-startTime)/1000000>=Settings.BlockSpeedInMillis) {
			UpdateGrid();
			startTime = System.nanoTime();
		}
		
	}
}
