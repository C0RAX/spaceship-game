package game1;

import java.awt.*;
import javax.swing.JComponent;

public class View extends JComponent {
    // background colour

    public static final Color BG_COLOR = Color.black;

    private Game game;

    public View(Game game) {
        this.game = game;
    }

    @Override
    public void paintComponent(Graphics g0) {
        Graphics2D g = (Graphics2D) g0;
        // paint the background
        g.setColor(BG_COLOR);
        g.fillRect(0, 0, getWidth(), getHeight());
        for (GameObject object : game.objects) {
            object.draw(g);
        }
        game.Playership.draw(g);
        g.setColor(Color.WHITE);
        g.drawString("SCORE: " + Integer.toString(game.getScore()), 40, 25);
        g.drawString("LIVES: " + Integer.toString(game.Playership.lives), 960, 25);

    }

    @Override
    public Dimension getPreferredSize() {
        return Constants.FRAME_SIZE;
    }
}
