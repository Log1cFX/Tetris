package Inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Inputs implements KeyListener {

	ArrayList<KeyPressed> KeysArray = new ArrayList<>(10);
	int ThrottleValueInMillis = 100;

	@Override
	public void keyTyped(KeyEvent e) {
	}

	// creates new instance of KeyPressed
	@Override
	public void keyPressed(KeyEvent e) {
		boolean addKey = true;
		for (KeyPressed key : KeysArray) {
			if (key.KeyCode == e.getKeyCode()) {
				addKey = false;
				break;
			}
		}
		// checks all the existing instances of keyPressed to see if there is an
		// instance with the same button
		if (addKey)
			KeysArray.add(new KeyPressed(e.getKeyCode()));
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
		case KeyEvent.VK_DOWN:
			Move.down();
			break;
		case KeyEvent.VK_RIGHT:
			Move.right();
			break;
		case KeyEvent.VK_LEFT:
			Move.left();
			break;
		case KeyEvent.VK_X:
			Move.turnClock();
			break;
		case KeyEvent.VK_W:
			Move.turnCounterClock();
			break;
		}
	}

}
