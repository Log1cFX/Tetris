package logic.inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JComponent;

import logic.physics.BlockManager;
import settings.Settings;

public class Inputs implements KeyListener {

	ArrayList<PressedKey> KeysArray = new ArrayList<>(10);
	int ThrottleValueInMillis = 100;
	private static Move move;

	public Inputs(JComponent component) {
		if (!(component instanceof KeyListener)) {
			System.out.println("JComponent doesn't extend a KeyListener");
			System.out.println("Fix this plz");
		}
	}
	
	public static void addBlockManager(BlockManager blockManager) {
		move = new Move(blockManager);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	// creates new instance of KeyPressed
	@Override
	public void keyPressed(KeyEvent e) {
		int KeyCode = e.getKeyCode();
		boolean addKey = true;
		for (PressedKey key : KeysArray) {
			if (key.KeyCode == KeyCode) {
				addKey = false;
				break;
			}
		}
		// checks all the existing instances of keyPressed to see if there is an
		// instance with the same button
		if (addKey)
			KeysArray.add(new PressedKey(e.getKeyCode(),this));
		// adds instance of keyPressed with the key code of the current button
	}

	@Override
	public void keyReleased(KeyEvent e) {
		for (int i = 0; i < KeysArray.size(); i++) {
			if (KeysArray.get(i).KeyCode == e.getKeyCode()) {
				KeysArray.get(i).shutdown();
				KeysArray.remove(i);
				break;
			}
		}
		// checks all the existing instances of keyPressed to see if there is an
		// instance with the same button
		// if there is, it shuts down the instance and deletes from the array
	}

	protected void InputHandler(int KeyCode) {
		switch (KeyCode) {
		case Settings.Controls.DOWN:
			move.down();
			break;
		case Settings.Controls.RIGHT:
			move.right();
			break;
		case Settings.Controls.LEFT:
			move.left();
			break;
		case Settings.Controls.TURN_CLOCKWISE:
			move.turnClock();
			break;
		case Settings.Controls.TURN_COUNTER_CLOCKWISE:
			move.turnCounterClock();
			break;
		}
	}

}
