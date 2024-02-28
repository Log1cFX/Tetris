package logic.physics;

import settings.Settings;

public class PhysicsLoop extends PhysicsLoopCaster {

    private static final int FPS = Settings.FPS;
    private static final long TARGET_TIME = 1000 / FPS;
    public static boolean isRunning;
    
    public PhysicsLoop() {
        isRunning = true;
        System.out.println(getClass().getSimpleName());
        System.out.println();
        new Grid();
    }

    public void startLoop() {
        while (isRunning) {
        	
            long startTime = System.nanoTime();
            update();  // Updates every single frame
            long elapsedTime = System.nanoTime() - startTime;
            
            long sleepTime = TARGET_TIME - elapsedTime/1000000;
            
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
    	for(Update u : EventQueue) {
    		u.execute();
    	}
    	for(GraphicsUpdate g : GraphicUnits) {
    		g.Update();
    	}
    	//System.out.println("executed");
    }
}