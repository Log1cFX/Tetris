package settings;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

import logic.physics.Square;

public class CurrentGameState {
	public static AtomicReference<ArrayList<Square>> placedSquares = new AtomicReference<>();
	public static AtomicReference<ArrayList<Square>> currentBlock = new AtomicReference<>();
	public static int BlockSpeedInMillis = 200;
	public static volatile String score = "0";
	public static volatile boolean isOver = false;
	public static volatile boolean burnLines = false;
	public static volatile int[] LinesToBurn = new int[Settings.Screen.COLUMNS];
}
