package settings;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

import logic.physics.Square;

public class CurrentGameState {
	public static AtomicReference<ArrayList<Square>> placedSquares = new AtomicReference<>();
	public static AtomicReference<ArrayList<Square>> currentBlock = new AtomicReference<>();
	public static AtomicReference<boolean[]> linesToBurn = new AtomicReference<>();
	public static volatile boolean animation = false;
	public static volatile boolean animationEnd = false;
	public static int blockSpeedInMillis = 100;
	public static volatile int score = 0;
	public static volatile boolean isOver = false;
	public static volatile boolean burnLines = false;
}
