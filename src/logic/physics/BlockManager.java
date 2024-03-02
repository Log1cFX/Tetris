package logic.physics;

import java.awt.Point;
import java.util.Random;

import logic.sprites.*;
import settings.Settings;

// Methods to manage the current block
public class BlockManager {
	
	static Random rand = new Random();
	Grid grid;
	public BlockBehavior currentBlock; // Current falling block
	
	public BlockManager(Grid grid) {
		this.grid = grid;
	}
	
	// get position of the square on the grid
	protected Square[] getSquaresRelativeToGrid() {
		Square[] squares = new Square[4];
		Point pos = currentBlock.getPosition();
		int i = 0;
		for (Square s : convertStringsToSquares(currentBlock.getSquares())) {
			s.location.translate(pos.x, pos.y);
			squares[i] = s;
			i++;
		}
		return squares;
	}

	// performs a translation if can*
	// return true if the translation was performed
	public boolean performTranslation(Point t) {
		// Point t = translation value
		for (Square square : getSquaresRelativeToGrid()) {
			Point p = new Point(square.location);
			p.translate(t.x, t.y);
			
			if (!grid.isValidPos(p)) {
				return false;
			}
			//checks if all the blocks are in correct positions
		}
		// checks if valid position
		currentBlock.getPosition().translate(t.x, t.y);
		return true;
	}

	// performs a rotation if can
	// return true if the translation was performed
	public boolean performRotation(int r) {
		// 1 clock 0 means counter-clock
		switch(r) {
		case 0 : currentBlock.PreviousRotationPos();
		break;
		case 1 : currentBlock.NextRotationPos();
		}
		for (Square square : getSquaresRelativeToGrid()) {
			if (!grid.isValidPos(square.location)) {
				System.out.println("gg");
				switch(r) {
				case 0 : currentBlock.NextRotationPos();
				break;
				case 1 : currentBlock.PreviousRotationPos();
				}
				return false;
			}
		}
		return true;
	}
	
	
	private Square[] convertStringsToSquares(String[] stringPoints) {
		Square[] Squares = new Square[stringPoints.length];

		for (int i = 0; i < stringPoints.length; i++) {
			String[] parts = stringPoints[i].split(",");
			// Assuming the input format is correct and there are exactly 2 numbers
			int x = Integer.parseInt(parts[0].trim()); // Trim to remove any leading/trailing spaces
			int y = Integer.parseInt(parts[1].trim());
			Squares[i] = new Square(new Point(x, y), currentBlock.getColorPalette());
		}
		return Squares;
	}

	protected void NewBlock() {

		int i = rand.nextInt(6);
		int pos = rand.nextInt(1,Settings.Screen.ROWS-1);
		System.out.println("new block");
		switch(i) {
		case 0 : currentBlock = new I_Block(pos);
		break;
		case 1 : currentBlock = new O_Block(pos);
		break;
		case 2 : currentBlock = new L_Block(pos);
		break;
		case 3 : currentBlock = new J_Block(pos);
		break;
		case 4 : currentBlock = new T_Block(pos);
		break;
		case 5 : currentBlock = new S_Block(pos);
		break;
		case 6 : currentBlock = new Z_Block(pos);
		break;
		}
	}
}
