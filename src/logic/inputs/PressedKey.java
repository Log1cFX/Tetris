package logic.inputs;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import settings.Settings;

// this a Timer, that calls the inputHandler method till it's shutdown
public class PressedKey {
	private final ScheduledExecutorService executor;
	int keyCode;
	boolean keyReleased = false;
	private Inputs inputs;

	public PressedKey(int KeyCode, Inputs inputs) {
		this.keyCode = KeyCode;
		this.inputs = inputs;
		// Initialize the ScheduledExecutorService
		executor = Executors.newScheduledThreadPool(1);
		startPeriodicTask();
	}

	private void startPeriodicTask() {
		Runnable task = () -> {
			if (!keyReleased) {
				inputs.InputHandler(keyCode);
			} else {
				shutdown();
			}
		};
		if (keyCode != Settings.Controls.TURN_CLOCKWISE && keyCode != Settings.Controls.TURN_COUNTER_CLOCKWISE) {
			executor.scheduleAtFixedRate(task, 0, inputs.throttleValueInMillis, TimeUnit.MILLISECONDS);
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
