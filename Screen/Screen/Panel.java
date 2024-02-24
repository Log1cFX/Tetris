package Screen;
import javax.swing.JPanel;

import Physics.Block;
import Physics.Inputs;
import Physics.PhysicsLoopCaster;
import Physics.Update;
import ScreenSettings.Settings;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Panel extends JPanel implements KeyListener,Update {

    Inputs input = new Inputs();
    Panel() {
    	//System.out.println(getClass().getSimpleName());
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(Settings.SCREEN_WIDTH,Settings.SCREEN_HEIGHT));
        this.setFocusable(true);
        requestFocusInWindow();
        this.addKeyListener(this);
        PhysicsLoopCaster.addUpdateLoop(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGrid(g);
        drawBlock(g);
        drawPlacedBlocks(g);
    }
    
    

	private void drawPlacedBlocks(Graphics g) {
		for(int i=0;i<Settings.COLUMNS;i++) {
			for(int j=0;j<Settings.COLUMNS;j++) {
				if(Block.Placed_Blocks[i][j]!=null) {
					g.setColor(Block.Placed_Blocks[i][j].color1);
					g.fillRect(i*Settings.PIXEL_SIZE, j*Settings.PIXEL_SIZE, Settings.PIXEL_SIZE, Settings.PIXEL_SIZE);
				}
			}
		}
	}

	private void drawGrid(Graphics g) {
    	 int width = getWidth();
         int height = getHeight();

         int rowHeight = height / Settings.COLUMNS;
         for (int i = 0; i < Settings.COLUMNS; i++) {
             int y = i * rowHeight;
             g.drawLine(0, y, width, y);
         }

         int colWidth = width / Settings.COLUMNS;
         for (int i = 0; i < Settings.COLUMNS; i++) {
             int x = i * colWidth;
             g.drawLine(x, 0, x, height);
         }
	}
	
	private void drawBlock(Graphics g) {
		if(Block.CurrentBlock!=null) {
			g.setColor(Block.CurrentBlock.getColorPalette().color1);
			for(Point p : Block.getSquaresRelativeToGrid()) {
				g.fillRect(p.x*Settings.PIXEL_SIZE, p.y*Settings.PIXEL_SIZE,
						Settings.PIXEL_SIZE, Settings.PIXEL_SIZE); // draw squares on screen
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
    	//input.keyReleased(e);

    }

	@Override
	public void start() {
	}

	@Override
	public void execute() {
		repaint();
	}

}