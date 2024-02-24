package Physics;

public class PhysicsLoop extends PhysicsLoopCaster {

    private static final int FPS = 10;
    private static final long TARGET_TIME = 1000 / FPS;
    public static boolean isRunning;
    
    public PhysicsLoop() {
        isRunning = true;
        System.out.println(getClass().getSimpleName());
        System.out.println();
        new Grid();
    }

    public static void startLoop() {
        while (isRunning) {
        	
            long startTime = System.currentTimeMillis();
            update();  // Updates every single frame
            long elapsedTime = System.currentTimeMillis() - startTime;
            
            long sleepTime = TARGET_TIME - elapsedTime;
            
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
    	System.out.println("executed");
    }
}