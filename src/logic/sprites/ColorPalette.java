package logic.sprites;

import java.awt.Color;

public class ColorPalette{
	public Color color1;
	//public Color color2;
	//public Color color3;
	ColorPalette(String BlockName){
		System.out.println(BlockName);
		switch(BlockName) {
		case "I_Block" : color1 = Color.blue;
		break;
		case "O_Block" : color1 = Color.red;
		break;
		}
	}
}
