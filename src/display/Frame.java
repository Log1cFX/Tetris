package display;

import java.awt.GridLayout;

import javax.swing.JFrame;

public class Frame extends JFrame {

    Frame() {
    	this.setLayout(new GridLayout(1, 2));
    	this.add(new Panel());
    	this.add(new StatsPanel());
		this.setTitle("Tertrix");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
    }

}