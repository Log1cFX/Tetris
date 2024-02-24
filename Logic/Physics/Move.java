package Physics;

public class Move extends Block{
	public static void down() {
		CurrentBlock.getPosition().y+=1;
	}
	public static void right() {
		CurrentBlock.getPosition().x+=1;
	}
	public static void left() {
		CurrentBlock.getPosition().x-=1;
	}
	public static void turnClock() {
		CurrentBlock.NextRotationPos();
	}
	public static void turnCounterClock() {
		CurrentBlock.PreviousRotationPos();
	}
}
