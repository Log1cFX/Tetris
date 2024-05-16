package display;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;

public class Frame extends JFrame {

    Frame() {
    	this.setLayout(new BorderLayout());
    	this.add(new Panel(), BorderLayout.WEST);
    	this.add(new StatsPanel(), BorderLayout.EAST);
		this.setTitle("Tetris");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
    }

}