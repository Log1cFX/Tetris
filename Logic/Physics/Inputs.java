package Physics;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Inputs implements KeyListener{

	@Override
	public void keyTyped(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_DOWN : Move.down();
		break;
		case KeyEvent.VK_RIGHT : Move.right();
		break;
		case KeyEvent.VK_LEFT : Move.left();
		break;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_DOWN : Move.down();
		break;
		case KeyEvent.VK_RIGHT : Move.right();
		break;
		case KeyEvent.VK_LEFT : Move.left();
		break;
		case KeyEvent.VK_X : Move.turnClock();
		break;
		case KeyEvent.VK_W : Move.turnCounterClock();
		break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
}
