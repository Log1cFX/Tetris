package Physics;

import java.util.ArrayList;

public class PhysicsLoopCaster {
	static private ThreadLocal<ArrayList<Update>> threadLocal = new ThreadLocal<>();
    static ArrayList<Update> EventQueue = new ArrayList<>();
    static ArrayList<GraphicsUpdate> GraphicUnits = new ArrayList<>();
    PhysicsLoopCaster(){
    	if (EventQueue == null) {
            threadLocal.set(EventQueue);
        }
    }
    public static void addPhysicsUpdateLoop(Update value) {
        EventQueue.add(value);
        value.start();
    }
    public static void addGraphicsUpdateLoop(GraphicsUpdate value) {
    	GraphicUnits.add(value);
    }
}