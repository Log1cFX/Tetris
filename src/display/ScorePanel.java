package display;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class ScorePanel extends JPanel {
    private int score = 0;
    private Font font = new Font("Arial", Font.PLAIN, 20);

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Set rendering hints
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw score
        String scoreText = "Score: " + score;
        FontMetrics fontMetrics = g2d.getFontMetrics(font);
        Rectangle2D textBounds = fontMetrics.getStringBounds(scoreText, g2d);

        // Calculate position to center the text
        int x = (getWidth() - (int) textBounds.getWidth()) / 2;
        int y = (getHeight() - (int) textBounds.getHeight()) / 2 + fontMetrics.getAscent();

        // Draw score text centered in a rectangle
        g2d.setFont(font);
        g2d.setColor(Color.BLACK);
        g2d.drawString(scoreText, x, y);
    }

    public void setScore(int score) {
        this.score = score;
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Score Panel Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ScorePanel scorePanel = new ScorePanel();
        scorePanel.setPreferredSize(new Dimension(200, 100));

        frame.add(scorePanel);
        frame.pack();
        frame.setVisible(true);

        // Example of updating the score
        scorePanel.setScore(100);
    }
}
