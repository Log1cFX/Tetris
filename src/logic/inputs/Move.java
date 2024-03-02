package logic.inputs;

import java.awt.Point;

import logic.physics.BlockManager;
import logic.physics.Grid;

// Performs the action
public class Move {
	BlockManager blockManager;

	public Move(BlockManager blockManager) {
		this.blockManager = blockManager;
	}

	public void down() {
		blockManager.performTranslation(new Point(0, 1));
	}

	public void right() {
		blockManager.performTranslation(new Point(1, 0));
	}

	public void left() {
		blockManager.performTranslation(new Point(-1, 0));
	}

	public void turnClock() {
		blockManager.performRotation(1);
	}

	public void turnCounterClock() {
		blockManager.performRotation(0);
	}
}
