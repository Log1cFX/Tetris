package logic.physics;

import java.awt.Point;

import logic.sprites.ColorPalette;

public class Square {

	public Point location;
	public ColorPalette color;

	public Square(Point location, ColorPalette color) {
		this.color = color;
		this.location = location;
	}
}
