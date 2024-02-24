package Physics;

import java.util.ArrayList;

public class PhysicsLoopCaster {
	static private ThreadLocal<ArrayList<Update>> threadLocal = new ThreadLocal<>();
    static protected ArrayList<Update> EventQueue = new ArrayList<>();
    PhysicsLoopCaster(){
    	if (EventQueue == null) {
            threadLocal.set(EventQueue);
        }
    }
    public static void addUpdateLoop(Update value) {
        EventQueue.add(value);
        value.start();
    }
}