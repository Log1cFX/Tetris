package Physics;

import java.awt.Point;

import ScreenSettings.Settings;
import Sprites.ColorPalette;


// all the info and methods for the grid
public class Grid extends Block implements Update{
	
	long startTime = System.nanoTime();
    long finalTime;
	
	Grid() {
		PhysicsLoopCaster.addPhysicsUpdateLoop(this);
		//System.out.println("added");
	}
	
	private static void UpdateGrid(){
		CheckIfCollision();
		CurrentBlock.getPosition().y+=1;
	}
	
	public static void CheckIfCollision() {
		Point[] Squares = getSquaresRelativeToGrid();
		for(Point pos : Squares) {
			if(pos.y+1>=Settings.COLUMNS || Placed_Blocks[pos.x][pos.y+1]!=null) {
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
	private static void BurnLines() {
		boolean[] isFullLine = new boolean[Settings.COLUMNS];
		for(int columns = 0;columns <= Settings.COLUMNS;columns++) {
			for(int rows = 0;rows <= Settings.ROWS;rows++) {
				if (Placed_Blocks[rows][columns] == null) {
					isFullLine[columns] = false;
					break;
				}
			}
			if (!isFullLine[columns]) {
				isFullLine[columns] = true;
			}
		}
		ColorPalette[][] newGrid = new ColorPalette[Settings.ROWS][Settings.COLUMNS];
		for (int columns = Settings.COLUMNS;columns >= 0;columns--) {
			
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
