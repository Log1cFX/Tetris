package logic.physics;

import java.awt.Point;
import java.util.ArrayList;

import logic.inputs.Inputs;
import logic.sprites.ColorPalette;
import settings.CurrentGameState;
import settings.Settings;

// all the info and methods for the grid
public class Grid implements Update {

	long startUpdateTime = System.nanoTime();
	long finalUpdateTime;
	long startWaitTime;
	long finalWaitTime;
	volatile boolean waiting = false;
	public static volatile Square[][] grid = new Square[Settings.Screen.ROWS][Settings.Screen.COLUMNS];
	// Grid[x][y], !null means that there is a square
	protected BlockManager blockManager;

	Grid() {
		blockManager = new BlockManager(this);
		Inputs.addBlockManager(blockManager);
		PhysicsLoopCaster.addPhysicsUpdateLoop(this);
	}

	private void UpdateGrid() {
		CheckIfCollision();
		checkLines();
		// adding squares to settings.CurrentGameState.placed_Squares
		ArrayList<Square> newGrid = new ArrayList<>();
		for (Square[] column : grid) {
			for (Square s : column) {
				if (s != null) {
					newGrid.add(s);
				}
			}
		}
		CurrentGameState.placedSquares.set(newGrid);
	}
	
	// checks if collision under the block
	public boolean CheckIfCollision() {
		Square[] squares = blockManager.getSquaresRelativeToGrid();
		for (Square square : squares) {
			if (square.location.y + 1 >= Settings.Screen.COLUMNS
					|| grid[square.location.x][square.location.y + 1] != null) {
				Wait();
				if (!waiting) {
					PlaceBlockOnGrid(squares);
					blockManager.NewBlock();
				}
				return true;
			}
		}
		blockManager.currentBlock.getPosition().y += 1;
		return false;
	}

	private void PlaceBlockOnGrid(Square[] Squares) {
		for (Square square : Squares) {
			grid[square.location.x][square.location.y] = square;
		}
	}

	boolean[] isFullLine = new boolean[Settings.Screen.COLUMNS];
	
	private boolean checkLines() {
		
		for (int i = 0; i < isFullLine.length; i++) {
			isFullLine[i] = true;
		}
		for (int columns = 0; columns < Settings.Screen.COLUMNS; columns++) {
			for (int rows = 0; rows < Settings.Screen.ROWS; rows++) {
				if (grid[rows][columns] == null) {
					isFullLine[columns] = false;
					break;
				}
			}
		}
		
		int numOfLinesToBurn = 0;
		// checks if there are any lines to burn
		for (int i = 0; i < isFullLine.length; i++) {
			if (isFullLine[i] == true) {
				numOfLinesToBurn++;
			}
			
		}
		if (numOfLinesToBurn==0) {
			return false;
		}
		// if no lines need to be burned return false
		
		switch(numOfLinesToBurn) {
		case 1 : CurrentGameState.score = 100;
		break;
		case 2 : CurrentGameState.score = 400;
		break;
		case 3 : CurrentGameState.score = 800;
		}
		
		return true;
	}
	
	public void burnLines() {
		Square[][] newGrid = new Square[Settings.Screen.ROWS][Settings.Screen.COLUMNS];
		int i = 19;
		for (int columns = Settings.Screen.COLUMNS - 1; columns >= 0; columns--) {
			if (!isFullLine[columns]) {
				for (int rows = 0; rows < Settings.Screen.ROWS; rows++) {

					if (grid[rows][columns] != null) {
						grid[rows][columns].location = new Point(rows,i);
					}
					newGrid[rows][i] = grid[rows][columns];
				}
				i--;
			}
		}
		grid = newGrid;
		
		//CurrentGameState.score += addScore;<
	}

	public void Wait() {
		finalWaitTime = System.nanoTime();
		if (waiting != true) {
			waiting = true;
			startWaitTime = System.nanoTime();
		} else if (finalWaitTime - startWaitTime / 1000000 >= Settings.Animations.WAIT_AFTER_COLLISION_IN_MILLIS) {
			System.out.println("g");
			waiting = false;
		}
	}

	private void GameIsOver() {
		for (Square square : blockManager.getSquaresRelativeToGrid()) {
			if (square.location.y > 0) {
				CurrentGameState.isOver = true;
			}
		}
	}

	// checks if is a valid position
	protected boolean isValidPos(Point square) {
		if (square.x < 0 || square.x >= Settings.Screen.ROWS || square.y >= Settings.Screen.COLUMNS
				|| grid[square.x][square.y] != null) {
			return false;
		} else
			return true;
	}

	@Override
	public void start() {
		blockManager.NewBlock();
	}

	@Override
	public void execute() {
		finalUpdateTime = System.nanoTime();
		if ((finalUpdateTime - startUpdateTime) / 1000000 >= CurrentGameState.blockSpeedInMillis) {
			UpdateGrid();
			startUpdateTime = System.nanoTime();
		}
		ArrayList<Square> currentBlock = new ArrayList<Square>();
		for (Square square : blockManager.getSquaresRelativeToGrid()) {
			currentBlock.add(square);
		}
		CurrentGameState.currentBlock.set(currentBlock);
	}
}
