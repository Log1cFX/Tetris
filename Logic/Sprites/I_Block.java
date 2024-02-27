package Sprites;

import java.awt.Point;

public class I_Block implements BlockBehavior {
	public static String[] Rot1 = { "0,-1", "0,0", "0,1", "0,2" };
	public static String[] Rot2 = { "-1,0", "0,0", "1,0", "2,0" };
	int rotation = 1;
	static int maxRotation = 2;
	ColorPalette colorPalette = new ColorPalette(this.getClass().getSimpleName());

	Point Pos = new Point(0, 0);

	public I_Block(int Xpos) {
		Pos.x = Xpos;
	}

	@Override
	public void NextRotationPos() {
		if (rotation == maxRotation)
			rotation = 1;
		else
			rotation++;
	}

	@Override
	public void PreviousRotationPos() {
		if (rotation == 1)
			rotation = maxRotation;
		else
			rotation--;
	}

	@Override
	public Point getPosition() {
		return Pos;
	}

	@Override
	public int getRotation() {
		return rotation;
	}

	@Override
	public String[] getSquares() {
		switch (rotation) {
		case 1:
			return Rot1;
		case 2:
			return Rot2;
		}
		return null;
	}

	@Override
	public String getBlockName() {
		return this.getClass().getName();
	}

	@Override
	public ColorPalette getColorPalette() {
		return colorPalette;
	}

}
