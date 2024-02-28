package display;
import javax.swing.JFrame;

public class Frame extends JFrame {
    Panel panel = new Panel();

    Frame() {
    	this.add(new Panel());
		this.setTitle("Tertrix");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
    }

}