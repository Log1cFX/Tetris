package Screen;
import javax.swing.JPanel;

import Inputs.Inputs;
import Physics.Block;
import Physics.GraphicsUpdate;
import Physics.PhysicsLoopCaster;
import Physics.Update;
import ScreenSettings.Settings;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Panel extends JPanel implements KeyListener,GraphicsUpdate {

    Inputs input = new Inputs();
    int Pixel_Size = Settings.PIXEL_SIZE;
    int Screen_Height = Settings.SCREEN_HEIGHT;
    int Screen_Width = Settings.SCREEN_WIDTH;
    
    Panel() {
    	//System.out.println(getClass().getSimpleName());
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(Screen_Width,Screen_Height));
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
		for(int i=0;i<Settings.COLUMNS;i++) {
			for(int j=0;j<Settings.ROWS;j++) {
				if(Block.Placed_Blocks[i][j]!=null) {
					g.setColor(Block.Placed_Blocks[i][j].color1);
					g.fillRect(i*Pixel_Size, j*Pixel_Size, Pixel_Size, Pixel_Size);
				}
			}
		}
	}

	private void drawGrid(Graphics g) {
		g.setColor(Color.gray);
		for(int i=0;i<Settings.ROWS;i++) {
			g.drawLine(0, i*Pixel_Size, Screen_Width, i*Pixel_Size);
        }
		for(int i=0;i<Settings.COLUMNS;i++) {
			 g.drawLine(i*Pixel_Size, 0, i*Pixel_Size, Screen_Height);
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
    	input.keyReleased(e);

    }

	@Override
	public void Update() {
		repaint();
	}
}