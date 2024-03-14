package display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;

import logic.physics.GraphicsUpdate;
import logic.physics.PhysicsLoopCaster;
import settings.CurrentGameState;
import settings.Settings;

public class StatsPanel extends JPanel implements GraphicsUpdate{
	
	StatsPanel() {
		this.setBackground(Color.BLACK);
		this.setPreferredSize(new Dimension(Settings.Screen.SCREEN_WIDTH / 2, Settings.Screen.SCREEN_HEIGHT));
		this.setFocusable(true);
		PhysicsLoopCaster.addGraphicsUpdateLoop(this);
		
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		displayScore(g);
		

		// System.out.println("PaintComponent: "+Thread.currentThread());
	}
	
	private void displayScore(Graphics g) { // Displaying the score

	}
	
	private void displayNextBlock(Graphics g) { // Displaying the next block

	}


	@Override
	public void Update() {
		repaint();
		
	}

}
