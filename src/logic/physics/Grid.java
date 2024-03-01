package logic.physics;

import java.awt.Point;

import logic.sprites.ColorPalette;
import settings.CurrentGameState;
import settings.Settings;

// all the info and methods for the grid
public class Grid extends Block implements Update {

	long startUpdateTime = System.nanoTime();
	long finalUpdateTime;
	long startWaitTime;
	long finalWaitTime;
	volatile boolean waiting = false;

	Grid() {
		PhysicsLoopCaster.addPhysicsUpdateLoop(this);
		// System.out.println("added");
	}

	private void UpdateGrid() {
		CheckIfCollision();
		BurnLines();
	}

	public boolean CheckIfCollision() {
		Point[] Squares = getSquaresRelativeToGrid();
		for (Point pos : Squares) {
			if (pos.y + 1 >= Settings.Screen.COLUMNS || Placed_Blocks[pos.x][pos.y + 1] != null) {
				Wait();
				if(!waiting) {
					PlaceBlocksOnGrid(Squares);
					NewBlock();
				}
				return true;
			}
		}
		CurrentBlock.getPosition().y += 1;
		return false;
	}

	private void PlaceBlocksOnGrid(Point[] Squares) {
		for (Point pos : Squares) {
			Placed_Blocks[pos.x][pos.y] = CurrentBlock.getColorPalette();
		}
	}

	private boolean BurnLines() {
		boolean[] isFullLine = new boolean[Settings.Screen.COLUMNS];
		for (int i = 0; i < isFullLine.length; i++) {
			isFullLine[i] = true;
		}
		for (int columns = 0; columns < Settings.Screen.COLUMNS; columns++) {
			for (int rows = 0; rows < Settings.Screen.ROWS; rows++) {
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

		ColorPalette[][] newGrid = new ColorPalette[Settings.Screen.ROWS][Settings.Screen.COLUMNS];
		int i = 19;
		for (int columns = Settings.Screen.COLUMNS - 1; columns >= 0; columns--) {
			if (!isFullLine[columns]) {
				for (int rows = 0; rows < Settings.Screen.ROWS; rows++) {
					newGrid[rows][i] = Placed_Blocks[rows][columns];
				}
				i--;
			}
		}
		Placed_Blocks = newGrid;
		return true;
	}

	public void Wait() {
		finalWaitTime = System.nanoTime();
		if(waiting!=true) {
			waiting = true;
			startWaitTime = System.nanoTime();
		}
		else if (finalWaitTime-startWaitTime/1000000>=Settings.Animations.WAIT_AFTER_COLLISION_IN_MILLIS) {
			System.out.println("g");
			waiting = false;
		}
	}

	private void CheckIfGameOver() {
		for (Point p : Block.getSquaresRelativeToGrid()) {
			if (p.y > 0) {
				CurrentGameState.isOver = true;
			}
		}
	}

	@Override
	public void start() {
		NewBlock();
	}

	@Override
	public void execute() {
		finalUpdateTime = System.nanoTime();
		if ((finalUpdateTime - startUpdateTime) / 1000000 >= CurrentGameState.BlockSpeedInMillis) {
			UpdateGrid();
			startUpdateTime = System.nanoTime();
		}
	}
}
