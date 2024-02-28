package logic.sprites;

import java.awt.Point;

public interface BlockBehavior {
	void NextRotationPos();

	void PreviousRotationPos();

	Point getPosition();

	int getRotation();

	String[] getSquares();

	String getBlockName();

	ColorPalette getColorPalette();
}
