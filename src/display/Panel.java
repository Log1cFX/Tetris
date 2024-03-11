package display;

import javax.swing.JLabel;
import javax.swing.JPanel;

import logic.inputs.Inputs;
import logic.physics.BlockManager;
import logic.physics.GraphicsUpdate;
import logic.physics.PhysicsLoopCaster;
import logic.physics.Square;
import logic.physics.Update;
import settings.CurrentGameState;
import settings.Settings;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
		displayScore(g);
		// System.out.println("PaintComponent: "+Thread.currentThread());
	}

	private void displayScore(Graphics g) {
		Font font = new Font("Arial Black", Font.BOLD, 40);
		g.setFont(font);
		g.setColor(Color.white);

		FontMetrics metrics = g.getFontMetrics(font);
		int x = Settings.Screen.SCREEN_WIDTH
				+ (Settings.Screen.SCREEN_WIDTH - metrics.stringWidth(String.valueOf(CurrentGameState.score))) / 2;
		g.setFont(font);
		g.drawString(String.valueOf(CurrentGameState.score), x, 100);

		g.drawString(String.valueOf(CurrentGameState.score), Settings.Screen.SCREEN_WIDTH / 2 - 20, 100);
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