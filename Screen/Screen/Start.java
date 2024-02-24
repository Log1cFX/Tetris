package Screen;

import Physics.PhysicsLoop;

public class Start {
	public static void main(String[] args) {
		new Thread(()->{new PhysicsLoop().startLoop();}).start();
		new Thread(()->{new Frame();}).start();
	}
}
