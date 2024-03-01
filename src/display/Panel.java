package display;

import javax.swing.JPanel;

import logic.inputs.Inputs;
import logic.physics.Block;
import logic.physics.GraphicsUpdate;
import logic.physics.PhysicsLoopCaster;
import logic.physics.Update;
import settings.Settings;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Panel extends JPanel implements KeyListener, GraphicsUpdate {

	Inputs input = new Inputs();

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
		drawBlock(g);
		drawPlacedBlocks(g);
		drawGrid(g);
	}

	private void drawPlacedBlocks(Graphics g) {
		for (int i = 0; i < Settings.Screen.COLUMNS; i++) {
			for (int j = 0; j < Settings.Screen.ROWS; j++) {
				if (Block.Placed_Blocks[j][i] != null) {
					g.setColor(Block.Placed_Blocks[j][i].color1);
					g.fillRect(j * Settings.Screen.PIXEL_SIZE, i * Settings.Screen.PIXEL_SIZE,
							Settings.Screen.PIXEL_SIZE, Settings.Screen.PIXEL_SIZE);
				}
			}
		}
	}

	private void drawGrid(Graphics g) {
		g.setColor(Color.gray);
		for (int i = 0; i < Settings.Screen.COLUMNS; i++) {
			g.drawLine(0, i * Settings.Screen.PIXEL_SIZE, Settings.Screen.SCREEN_WIDTH, i * Settings.Screen.PIXEL_SIZE);
		}
		for (int i = 0; i < Settings.Screen.ROWS; i++) {
			g.drawLine(i * Settings.Screen.PIXEL_SIZE, 0, i * Settings.Screen.PIXEL_SIZE,
					Settings.Screen.SCREEN_HEIGHT);
		}
	}

	private void drawBlock(Graphics g) {
		if (Block.CurrentBlock != null) {
			g.setColor(Block.CurrentBlock.getColorPalette().color1);
			for (Point p : Block.getSquaresRelativeToGrid()) {
				g.fillRect(p.x * Settings.Screen.PIXEL_SIZE, p.y * Settings.Screen.PIXEL_SIZE,
						Settings.Screen.PIXEL_SIZE, Settings.Screen.PIXEL_SIZE); // draw squares on screen
			}
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