package Inputs;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
        executor.scheduleAtFixedRate(task, 0, 150, TimeUnit.MILLISECONDS);
    }

    public void shutdown() {
        if (executor != null && !executor.isShutdown()) {
            executor.shutdown();
        }
    }
}
