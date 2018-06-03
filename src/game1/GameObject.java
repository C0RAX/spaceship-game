package game1;

import static game1.Constants.DT;
import static game1.Constants.FRAME_HEIGHT;
import static game1.Constants.FRAME_WIDTH;
import java.awt.Graphics2D;
import utilities.Vector2D;

public abstract class GameObject {

    public Vector2D position;
    public Vector2D velocity;
    public boolean dead;
    public double radius;

    public GameObject() {
    }

    public GameObject(Vector2D position, Vector2D velocity, double radius) {
        this.position = new Vector2D(position);
        this.velocity = new Vector2D(velocity);
        this.radius = radius;
    }

    public void hit() {
        dead = true;
    }

    public void update() {
        position.addScaled(velocity, DT);
        position.wrap(FRAME_WIDTH, FRAME_HEIGHT);
    }

    public abstract void draw(Graphics2D g);

    public boolean overlap(GameObject other) {
        double xDif = this.position.x - other.position.x;
        double yDif = this.position.y - other.position.y;
        double disSqr= xDif * xDif + yDif * yDif;
        boolean collision = disSqr < (this.radius + other.radius) * (this.radius + other.radius);
        return collision;
        //return (this.intersects(other.getBounds()));
    }

    //public Rectangle getBounds() {
    //    return new Rectangle(x, Y, WIDTH, HEIGHT);
    //}
    public void collisionHandling(GameObject other) {
        if (this.getClass() != other.getClass() && this.overlap(other)) {
            this.hit();
            other.hit();
        }
    }


}
