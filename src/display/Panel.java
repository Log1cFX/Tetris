package display;

import javax.swing.JPanel;

import logic.inputs.Inputs;
import logic.physics.GraphicsUpdate;
import logic.physics.PhysicsLoop;
import logic.physics.PhysicsLoopCaster;
import logic.physics.Square;
import settings.CurrentGameState;
import settings.Settings;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;

public class Panel extends JPanel implements KeyListener, GraphicsUpdate {

	Inputs input = new Inputs(this);

	Panel() {
		// System.out.println(getClass().getSimpleName());
		this.setBackground(Color.BLACK);
		this.setPreferredSize(new Dimension(Settings.Screen.SCREEN_WIDTH, Settings.Screen.SCREEN_HEIGHT));
		this.setFocusable(true);
		requestFocusInWindow();
		this.addKeyListener(this);
		PhysicsLoopCaster.addGraphicsUpdateLoop(this);

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawSquares(g);
		drawGrid(g);
		if (CurrentGameState.animation) {
			animation(g);
		}
		// System.out.println("PaintComponent: "+Thread.currentThread());
	}

	float i = 0; // Use float for smoother increments
	float availableFrames = Settings.Screen.FPS * (Settings.Animations.BURN_LINE_ANIMATION_SPEED_IN_MILLIS / 1000f); // Ensure division by 1000.0 for milliseconds to seconds conversion
	float increaseRectForAnimationPerFrame = Settings.Screen.SCREEN_WIDTH / (2 * availableFrames); // Calculate the expansion per frame more precisely

	// if called, draws an expanding white square on all the full lines
	// return true if rectangle expanded
	private boolean animation(Graphics g) {
	    g.setColor(Color.white);

	    if (i < availableFrames) { // x
	        i += 1.0f; // Increment by float
	        for (int j = 0; j < CurrentGameState.linesToBurn.get().length; j++) { // y
	            if (CurrentGameState.linesToBurn.get()[j]) { // Do animation only on full lines
	                int startX = (int)(-i * increaseRectForAnimationPerFrame + Settings.Screen.SCREEN_WIDTH / 2);
	                int width = (int)(i * increaseRectForAnimationPerFrame * 2);
	                g.fillRect(startX,
	                           j * Settings.Screen.UNIT_SIZE, 
	                           width,
	                           Settings.Screen.UNIT_SIZE);
	            }
	        }
	    } else { // Once finished, ends the animation
	        CurrentGameState.animation = false;
	        CurrentGameState.animationEnd = true;
	        i = 0.0f;
	    }
	    return true;
	}
	
	private boolean drawSquares(Graphics g) {
		if (CurrentGameState.placedSquares.get() != null) {
			for (Square square : CurrentGameState.placedSquares.get()) {
				g.setColor(square.color.color1);
				g.fillRect(square.location.x * Settings.Screen.UNIT_SIZE, square.location.y * Settings.Screen.UNIT_SIZE,
						Settings.Screen.UNIT_SIZE, Settings.Screen.UNIT_SIZE);
			}
		}
		if (CurrentGameState.currentBlock.get() != null) {
			for (Square square : CurrentGameState.currentBlock.get()) {
				g.setColor(square.color.color1);
				g.fillRect(square.location.x * Settings.Screen.UNIT_SIZE, square.location.y * Settings.Screen.UNIT_SIZE,
						Settings.Screen.UNIT_SIZE, Settings.Screen.UNIT_SIZE);
			}
		}
		return true;
	}

	private void drawGrid(Graphics g) {
		g.setColor(Color.gray);
		for (int i = 0; i < Settings.Screen.COLUMNS; i++) {
			g.drawLine(0, i * Settings.Screen.UNIT_SIZE, Settings.Screen.SCREEN_WIDTH, i * Settings.Screen.UNIT_SIZE);
		}
		for (int i = 0; i < Settings.Screen.ROWS; i++) {
			g.drawLine(i * Settings.Screen.UNIT_SIZE, 0, i * Settings.Screen.UNIT_SIZE, Settings.Screen.SCREEN_HEIGHT);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		input.keyPressed(e);

	}

	@Override
	public void keyReleased(KeyEvent e) {
		input.keyReleased(e);

	}

	@Override
	public void Update() {
		repaint();
	}
}