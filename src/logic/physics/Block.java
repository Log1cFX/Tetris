package logic.physics;

import java.awt.Point;
import java.util.Random;

import logic.sprites.*;
import settings.Settings;

// all the info and methods for the current block
public class Block {
	
	public static BlockBehavior CurrentBlock; // Current falling block
	public static ColorPalette[][] Placed_Blocks = new ColorPalette[Settings.ROWS][Settings.COLUMNS]; // Grid[x][y]
	public static String[] Blocks = { "I-Block", "J-Block", "L-Block", "O-Block", "S-Block", "T-Block", "Z-Block" };
	static Random rand = new Random();
	
	// get position of the square on the grid
	public static Point[] getSquaresRelativeToGrid() {
		Point[] Squares = new Point[CurrentBlock.getSquares().length];
		Point Pos = CurrentBlock.getPosition();
		int i = 0;
		for (Point p : convertStringsToPoints(CurrentBlock.getSquares())) {
			Squares[i] = new Point(p.x + Pos.x, p.y + Pos.y);
			i++;
		}
		return Squares;
	}
	// gets Array of coordinates of the current block's squares relative to the Grid

	// performs a translation if can*
	// return true if the translation was performed
	protected static boolean performTranslation(Point t) {
		// Point t = translation value
		for (Point square : Block.getSquaresRelativeToGrid()) {
			Point p = new Point(square);
			p.translate(t.x, t.y);
			
			if (!isValidPos(p)) {
				return false;
			}
			//checks if all the blocks are in correct positions
		}
		// checks if valid position
		CurrentBlock.getPosition().translate(t.x, t.y);
		return true;
	}

	// performs a rotation if can
	// return true if the translation was performed
	protected static boolean performRotation(int r) {
		// 1 clock 0 means counter-clock
		switch(r) {
		case 0 : CurrentBlock.PreviousRotationPos();
		break;
		case 1 : CurrentBlock.NextRotationPos();
		}
		for (Point square : Block.getSquaresRelativeToGrid()) {
			if (!isValidPos(square)) {
				System.out.println("gg");
				switch(r) {
				case 0 : CurrentBlock.NextRotationPos();
				break;
				case 1 : CurrentBlock.PreviousRotationPos();
				}
				return false;
			}
		}
		return true;
	}
	
	// checks if is a valid position
	private static boolean isValidPos(Point square) {
		if (square.x < 0 || square.x >= Settings.ROWS || square.y>=Settings.COLUMNS
				|| Placed_Blocks[square.x][square.y] != null) {
			return false;
		}
		else return true;
	}
	private static Point[] convertStringsToPoints(String[] stringPoints) {
		Point[] points = new Point[stringPoints.length];

		for (int i = 0; i < stringPoints.length; i++) {
			String[] parts = stringPoints[i].split(",");
			// Assuming the input format is correct and there are exactly 2 numbers
			int x = Integer.parseInt(parts[0].trim()); // Trim to remove any leading/trailing spaces
			int y = Integer.parseInt(parts[1].trim());
			points[i] = new Point(x, y);
		}
		return points;
	}

	public static void NewBlock() {

		int i = rand.nextInt(2);
		int pos = rand.nextInt(Settings.ROWS-1);
		System.out.println("new block");
		switch(i) {
		case 0 : CurrentBlock = new I_Block(pos);
		break;
		case 1 : CurrentBlock = new O_Block(pos);
		}
		/*
		 * switch (BlockNames[randomIndex]) { case "I-Block": CurrentBlock = new
		 * I_Block(randomPosX); break; case "J-Block": CurrentBlock = new
		 * J_Block(randomPosX); break; case "L-Block": CurrentBlock = new
		 * L_Block(randomPosX); break; case "O-Block": CurrentBlock = new
		 * O_Block(randomPosX); break; case "S-Block": CurrentBlock = new
		 * S_Block(randomPosX); break; case "T-Block": CurrentBlock = new
		 * T_Block(randomPosX); break; case "Z-Block": CurrentBlock = new
		 * Z_Block(randomPosX); break; }
		 */
	}
}
