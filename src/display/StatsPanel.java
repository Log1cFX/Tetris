package display;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import logic.physics.GraphicsUpdate;
import logic.physics.PhysicsLoopCaster;
import settings.CurrentGameState;
import settings.Settings;

public class StatsPanel extends JPanel implements GraphicsUpdate {

	private Font font = new Font("Arial", Font.PLAIN, 50);
	Rectangle scoreRect = new Rectangle(0, (Settings.Screen.SCREEN_HEIGHT / 3) * 0, Settings.Screen.SCREEN_WIDTH / 2,
			Settings.Screen.SCREEN_HEIGHT / 3);
	Rectangle nextBlockRect = new Rectangle(0, (Settings.Screen.SCREEN_HEIGHT / 3) * 1,
			Settings.Screen.SCREEN_WIDTH / 2, Settings.Screen.SCREEN_HEIGHT / 3);
	Rectangle levelRect = new Rectangle(0, (Settings.Screen.SCREEN_HEIGHT / 3) * 2,
			Settings.Screen.SCREEN_WIDTH / 2, Settings.Screen.SCREEN_HEIGHT / 3);

	StatsPanel() {
		
		this.setBackground(Color.BLACK);
		this.setPreferredSize(new Dimension(Settings.Screen.SCREEN_WIDTH / 2, Settings.Screen.SCREEN_HEIGHT));
		this.setFocusable(true);
		PhysicsLoopCaster.addGraphicsUpdateLoop(this);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		drawStatFrames(g2d);
		g.setFont(font);
		g.drawString(String.valueOf(CurrentGameState.score), scoreRect.width / 2, scoreRect.height / 2);

		// System.out.println("PaintComponent: "+Thread.currentThread());
	}

	// Drawing Frames (decorative)
	private void drawStatFrames(Graphics2D g2d) { 
		g2d.setStroke(new BasicStroke(3));
		g2d.setColor(Color.white);
		g2d.draw(scoreRect);
		g2d.draw(levelRect);
		g2d.draw(nextBlockRect);
	}

	private void displayNextBlock(Graphics2D g2d) { // Displaying the next block

	}

	@Override
	public void Update() {
		repaint();

	}

}
