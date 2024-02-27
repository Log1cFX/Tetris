package Inputs;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

// this a Timer, that calls the inputHandler method till it's shutdown
public class KeyPressed extends Inputs{
	private final ScheduledExecutorService executor;
	int KeyCode;
	boolean KeyReleased = false;

    public KeyPressed(int KeyCode) {
    	this.KeyCode = KeyCode;
        // Initialize the ScheduledExecutorService
        executor = Executors.newScheduledThreadPool(1);
        startPeriodicTask();
    }

    private void startPeriodicTask() {
        Runnable task = () -> {
            if(!KeyReleased) {
            	InputHandler(KeyCode);
            }
            else {
            	shutdown();
            }
        };

        // Schedule the task for repeated execution every 150ms
        executor.scheduleAtFixedRate(task, 0, ThrottleValueInMillis, TimeUnit.MILLISECONDS);
    }

    public void shutdown() {
        if (executor != null && !executor.isShutdown()) {
            executor.shutdown();
        }
    }
}
