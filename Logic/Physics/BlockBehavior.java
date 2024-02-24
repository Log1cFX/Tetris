package Physics;

import java.awt.Point;

import Sprites.ColorPalette;

public interface BlockBehavior {
	public void NextRotationPos();
	public void PreviousRotationPos();
	public Point getPosition();
	public int getRotation();
	public String[] getSquares();
	public String getBlockName();
	public ColorPalette getColorPalette();
}
