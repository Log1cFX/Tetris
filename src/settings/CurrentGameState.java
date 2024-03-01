package settings;

public class CurrentGameState {
	public static volatile int BlockSpeedInMillis = 200;
	public static volatile boolean isOver = false;
	public static volatile boolean burnLines = false;
	public static volatile int[] LinesToBurn = new int[Settings.Screen.COLUMNS];
}
