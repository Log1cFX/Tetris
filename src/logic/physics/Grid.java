package logic.physics;

import java.awt.Point;

import logic.sprites.ColorPalette;
import settings.Settings;

// all the info and methods for the grid
public class Grid extends Block implements Update {

	long startTime = System.nanoTime();
	long finalTime;

	Grid() {
		PhysicsLoopCaster.addPhysicsUpdateLoop(this);
		// System.out.println("added");
	}

	private void UpdateGrid() {
		CheckIfCollision();
		BurnLines();
		CurrentBlock.getPosition().y += 1;
	}

	public boolean CheckIfCollision() {
		Point[] Squares = getSquaresRelativeToGrid();
		for (Point pos : Squares) {
			if (pos.y + 1 >= Settings.COLUMNS || Placed_Blocks[pos.x][pos.y + 1] != null) {
				PlaceBlocksOnGrid(Squares);
				NewBlock();
				return true;
			}
		}
		return false;
	}

	private void PlaceBlocksOnGrid(Point[] Squares) {
		for (Point pos : Squares) {
			Placed_Blocks[pos.x][pos.y] = CurrentBlock.getColorPalette();
		}
	}

	private boolean BurnLines() {
		boolean[] isFullLine = new boolean[Settings.COLUMNS];
		for (int i = 0; i < isFullLine.length; i++) {
			isFullLine[i] = true;
		}
		for (int columns = 0; columns < Settings.COLUMNS; columns++) {
			for (int rows = 0; rows < Settings.ROWS; rows++) {
				if (Placed_Blocks[rows][columns] == null) {
					isFullLine[columns] = false;
					break;
				}
			}
		}
		
		boolean temp1 = false;
		for (int i = 0; i < isFullLine.length; i++) {
			if (isFullLine[i] == true)
				temp1 = true;
		}
		if (!temp1) {
			return false;
		}
		
		ColorPalette[][] newGrid = new ColorPalette[Settings.ROWS][Settings.COLUMNS];
		int i = 19;
		for (int columns = Settings.COLUMNS - 2; columns >= 0; columns--) {
			if (!isFullLine[columns]) {
				for (int rows = 0; rows < Settings.ROWS; rows++) {
					newGrid[rows][i] = Placed_Blocks[rows][columns];
				}
				i--;
			}
		}
		Placed_Blocks = newGrid;
		return true;
	}
	
	private void CheckIfGameOver() {
		for(Point p : Block.getSquaresRelativeToGrid()) {
			if(p.y>0) {
				
			}
		}
	}

	@Override
	public void start() {
		NewBlock();
	}

	@Override
	public void execute() {
		finalTime = System.nanoTime();
		if ((finalTime - startTime) / 1000000 >= Settings.BlockSpeedInMillis) {
			UpdateGrid();
			startTime = System.nanoTime();
		}

	}
}
