package display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import logic.physics.GraphicsUpdate;
import settings.CurrentGameState;
import settings.Settings;

public class StatsPanel extends JPanel implements GraphicsUpdate{
	
	StatsPanel() {
		this.setBackground(Color.BLACK);
		this.setPreferredSize(new Dimension(Settings.Screen.SCREEN_WIDTH / 2, Settings.Screen.SCREEN_HEIGHT));
		this.setFocusable(true);
		requestFocusInWindow();
		
		
		
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.gray);
		g.drawLine(0,80,400,80); // Separate between the game panel and the statistics panel
		displayScore(g);
		g.setColor(Color.gray);
		g.drawLine(0,0,0,800); // create a case for the score
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
		g.drawString("Score : " + String.valueOf(CurrentGameState.score), x, 100);

		g.drawString("Score : " + String.valueOf(CurrentGameState.score), Settings.Screen.SCREEN_WIDTH / 3 - 20, 50);
	}


	@Override
	public void Update() {
		repaint();
		
	}

}
