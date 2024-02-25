package Physics;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

// fuck me sideways, this shit doesn't work at all, fucking 6 hours into this horse fucking shit, fuck fuck fuck fuck
public class Inputs implements KeyListener, Update {
	private final HashMap<Integer, Boolean> pressedKeys = new HashMap<>(); // <keyCode, throttle>
	long startThrottleTime = System.nanoTime();
	long finalThrottleTime;
	int AntiThrottlingTimeInMillis = 100;
	// Time for anti throttling
	long startTimeBetweenKeyPressAndRelease;
	long finalTimeBetweenKeyPressAndRelease;

	public Inputs() {
		PhysicsLoopCaster.addPhysicsUpdateLoop(this);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		pressedKeys.put(e.getKeyCode(), true);
		startTimeBetweenKeyPressAndRelease = System.nanoTime();
		System.out.println("keyPressed " + pressedKeys.get(e.getKeyCode()));
	}

	@Override
	public void keyReleased(KeyEvent e) {
		finalTimeBetweenKeyPressAndRelease = System.nanoTime();
		if (!CheckKeyPressTime() && pressedKeys.containsKey(e.getKeyCode())) {
			pressedKeys.put(e.getKeyCode(), false);
		}
		handleKeyActions();
		System.out.println("keyReleased " + pressedKeys.get(e.getKeyCode()));
		pressedKeys.remove(e.getKeyCode());
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	private void handleKeyActions() {
		if (pressedKeys.containsKey(KeyEvent.VK_DOWN)) {
			if (pressedKeys.get(KeyEvent.VK_DOWN) == false) {
				Move.down();
			} else if (CheckThrottlTime()) {
				Move.down();
				// pressedKeys.remove(KeyEvent.VK_DOWN);
				startThrottleTime = System.nanoTime();

			}
		}
		if (pressedKeys.containsKey(KeyEvent.VK_RIGHT)) {
			if (pressedKeys.get(KeyEvent.VK_RIGHT) == false) {
				Move.right();
			} else if (CheckThrottlTime()) {
				Move.right();
				// pressedKeys.remove(KeyEvent.VK_RIGHT);
				startThrottleTime = System.nanoTime();

			}
		}
		if (pressedKeys.containsKey(KeyEvent.VK_LEFT)) {
			if (pressedKeys.get(KeyEvent.VK_LEFT) == false) {
				Move.left();
			} else if (CheckThrottlTime()) {
				Move.left();
				// pressedKeys.remove(KeyEvent.VK_LEFT);
				startThrottleTime = System.nanoTime();

			}
		}
		if (pressedKeys.containsKey(KeyEvent.VK_X)) {
			if (pressedKeys.get(KeyEvent.VK_X) == false) {
				Move.turnClock();
			} else if (CheckThrottlTime()) {
				Move.turnClock();
				startThrottleTime = System.nanoTime();

			}
		}
		if (pressedKeys.containsKey(KeyEvent.VK_W)) {
			if (pressedKeys.get(KeyEvent.VK_W) == false) {
				Move.turnCounterClock();
			} else if (CheckThrottlTime()) {
				Move.turnCounterClock();
				// pressedKeys.remove(KeyEvent.VK_W);
				startThrottleTime = System.nanoTime();

			}
		}
		finalThrottleTime = System.nanoTime();
	}

	private boolean CheckThrottlTime() {
		return ((finalThrottleTime - startThrottleTime) / 1000000 >= AntiThrottlingTimeInMillis) ? true : false;
	}

	private boolean CheckKeyPressTime() {
		return ((finalTimeBetweenKeyPressAndRelease - startTimeBetweenKeyPressAndRelease)
				/ 1000000 >= AntiThrottlingTimeInMillis) ? true : false;
	}

	@Override
	public void start() {

	}

	@Override
	public void execute() {
		// System.out.println('!');
		 // Handle actions every time the key set changes
	}
}
