package game1;

import static game1.Constants.FRAME_HEIGHT;
import static game1.Constants.FRAME_WIDTH;
import java.awt.Color;
import java.awt.Graphics2D;
import utilities.Vector2D;

public class Asteroid extends GameObject {

    public static final double MAX_SPEED = 100;

    static Asteroid makeRandomAsteroid(double radius) {

        return (new Asteroid((new Vector2D(Math.random() * FRAME_WIDTH, Math.random() * FRAME_HEIGHT)), (new Vector2D(Math.random() * 3, Math.random() * 3)), radius));
    }

    public Asteroid(Vector2D position, Vector2D velocity, double radius) {
        super(position, velocity, radius);
    }

    @Override
    public void update() {
        position.add(velocity);

        position.wrap(FRAME_WIDTH, FRAME_HEIGHT);

    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.DARK_GRAY);
        g.fillOval((int) position.x - (int) this.radius, (int) position.y - (int) this.radius, 1 * (int) this.radius, 1 * (int) this.radius);
    }

    @Override
    public void hit() {
        dead = true;
    }

}
