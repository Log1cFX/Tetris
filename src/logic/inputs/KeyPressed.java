package logic.inputs;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import settings.Settings;

// this a Timer, that calls the inputHandler method till it's shutdown
public class KeyPressed extends Inputs {
	private final ScheduledExecutorService executor;
	int KeyCode;
	boolean KeyReleased = false;
	private boolean firstCycle = true;

	public KeyPressed(int KeyCode) {
		this.KeyCode = KeyCode;
		// Initialize the ScheduledExecutorService
		executor = Executors.newScheduledThreadPool(1);
		startPeriodicTask();
	}

	private void startPeriodicTask() {
		Runnable task = () -> {
			if (!KeyReleased) {
				InputHandler(KeyCode);
			} else {
				shutdown();
			}
		};
		if (KeyCode != Settings.TURN_CLOCKWISE && KeyCode != Settings.TURN_COUNTER_CLOCKWISE) {
			executor.scheduleAtFixedRate(task, 0, ThrottleValueInMillis, TimeUnit.MILLISECONDS);
		}
		else {
			task.run();
		}
		// Schedule the task for repeated execution every 150ms
	}

	public void shutdown() {
		if (executor != null && !executor.isShutdown()) {
			executor.shutdown();
		}
	}
}
