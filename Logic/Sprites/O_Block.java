package Sprites;

import java.awt.Point;
import Physics.BlockBehavior;

public class O_Block implements BlockBehavior{
	
	public static String[] Rot1 = {"0,0","1,0","0,1","1,1"};
	Point Pos = new Point(0,0);
	ColorPalette colorPalette = new ColorPalette(this.getClass().getSimpleName());
	
	public O_Block(int Xpos){
		Pos.x = Xpos;
	}
	@Override
	public void NextRotationPos() {
	}
	@Override
	public void PreviousRotationPos() {
	}
	@Override
	public Point getPosition() {
		return Pos;
	}
	@Override
	public int getRotation() {
		return 1;
	}
	@Override
	public String[] getSquares() {
		return Rot1;
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
