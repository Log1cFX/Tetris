package Inputs;

import java.awt.Point;

import Physics.Block;

public class Move extends Block{
	public static void down() {
		performTranslation(new Point(0,1));
	}
	public static void right() {
		performTranslation(new Point(1,0));
	}
	public static void left() {
		performTranslation(new Point(-1,0));
	}
	public static void turnClock() {
		performRotation(1);
	}
	public static void turnCounterClock() {
		performRotation(0);
	}
}
