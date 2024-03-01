package logic.sprites;

import java.awt.Color;

public class ColorPalette{
	public Color color1;
	//public Color color2;
	//public Color color3;
	ColorPalette(String BlockName){
		System.out.println(BlockName);
		switch(BlockName) {
		case "I_Block" : color1 = new Color(173, 216, 230);
		break;
		case "O_Block" : color1 = Color.red;
		break;
		case "J_Block" : color1 = new Color(0,0,139);
		break;
		case "L_Block" : color1 = Color.orange;
		break;
		case "T_Block" : color1 = Color.magenta;
		break;
		case "S_Block" : color1 = Color.green;
		break;
		case "Z_Block" : color1 = Color.red;
		break;
		}
	}
}
