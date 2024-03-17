package logic.physics;

import settings.Settings;

public class PhysicsLoop extends PhysicsLoopCaster {

	private static final long TARGET_TIME = 1000 / Settings.Screen.FPS;;
	public static boolean isRunning;
	public static int totalFrames = 0;

	public PhysicsLoop() {
		isRunning = true;
		System.out.println(getClass().getSimpleName());
		System.out.println();
		new Grid();
	}

	public void startLoop() {
		while (isRunning) {

			long startTime = System.nanoTime();

			update(); // Updates every single frame

			totalFrames++;

			long elapsedTime = System.nanoTime() - startTime;

			long sleepTime = TARGET_TIME - elapsedTime / 1000000;

			if (sleepTime > 0) {
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static void update() {
		for (Update u : EventQueue) {
			u.execute();
		}
		for (GraphicsUpdate g : GraphicUnits) {
			g.Update();
		}
		// System.out.println("executed");
	}
}