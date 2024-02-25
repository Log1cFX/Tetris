package Physics;

import java.awt.Point;
import java.util.Random;

import ScreenSettings.Settings;
import Sprites.*;

public class Block{
	
	public static BlockBehavior CurrentBlock; // Current falling block
	public static ColorPalette[][] Placed_Blocks = new ColorPalette[Settings.COLUMNS][Settings.ROWS]; // Grid[x][y]
	public static String[] BlockNames = { "I-Block", "J-Block", "L-Block", "O-Block", "S-Block", "T-Block", "Z-Block" };
    static Random rand = new Random();
	
	public static Point[] getSquaresRelativeToGrid() {
		Point[] Squares = new Point[4];
		Point Pos = CurrentBlock.getPosition();
		int i = 0;
		for(Point p : convertStringsToPoints(CurrentBlock.getSquares())){
			Squares[i] = new Point(p.x+Pos.x, p.y+Pos.y);
			i++;
		}
		return Squares;
	}
	// gets Array of coordinates of the current block's squares relative to the Grid
	
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
	
	private static void TurnBlock() {
		
	}
	
	public static void NewBlock() {
        
        int randomIndex = rand.nextInt(BlockNames.length);
        System.out.println("new block");
        //CurrentBlock = new I_Block(rand.nextInt(0,Settings.COLUMNS));
        CurrentBlock = new I_Block(9);
        /*
        switch (BlockNames[randomIndex]) {
            case "I-Block":
            	CurrentBlock = new I_Block(randomPosX);
            	break;
            case "J-Block":
            	CurrentBlock = new J_Block(randomPosX);
            	break;
            case "L-Block":
            	CurrentBlock = new L_Block(randomPosX);
            	break;
            case "O-Block":
            	CurrentBlock = new O_Block(randomPosX);
            	break;
            case "S-Block":
            	CurrentBlock = new S_Block(randomPosX);
            	break;
            case "T-Block":
            	CurrentBlock = new T_Block(randomPosX);
            	break;
            case "Z-Block":
            	CurrentBlock = new Z_Block(randomPosX);
            	break;
        }
        */
    }
}
