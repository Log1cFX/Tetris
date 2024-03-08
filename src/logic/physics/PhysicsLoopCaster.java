package logic.physics;

import java.util.ArrayList;

public class PhysicsLoopCaster {
    static ArrayList<Update> EventQueue = new ArrayList<>();
    static ArrayList<GraphicsUpdate> GraphicUnits = new ArrayList<>();
    
    public static void addPhysicsUpdateLoop(Update value) {
        EventQueue.add(value);
        value.start();
    }
    public static void addGraphicsUpdateLoop(GraphicsUpdate value) {
    	GraphicUnits.add(value);
    }
}