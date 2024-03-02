package settings;

import java.awt.event.KeyEvent;

public class Settings {
	public static class Controls {
		public static final int RIGHT = KeyEvent.VK_RIGHT;
		public static final int LEFT = KeyEvent.VK_LEFT;
		public static final int DOWN = KeyEvent.VK_DOWN;
		public static final int TURN_CLOCKWISE = KeyEvent.VK_X;
		public static final int TURN_COUNTER_CLOCKWISE = KeyEvent.VK_W;
	}

	public static class Screen {
		public static final int SCREEN_HEIGHT = 800;
		public static final int SCREEN_WIDTH = SCREEN_HEIGHT / 2;
		public static final int COLUMNS = 20;
		public static final int ROWS = 10;
		public static final int UNIT_SIZE = SCREEN_HEIGHT / COLUMNS;
		public static final int FPS = 165;
	}

	public static class Animations {
		public static final int WAIT_AFTER_COLLISION_IN_MILLIS = 1000;
		public static final int BURN_LINE_ANIMATION_SPEED_IN_MILLIS = 200;
	}
}
