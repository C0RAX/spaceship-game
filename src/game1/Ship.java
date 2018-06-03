package game1;

import static game1.Constants.DT;
import static game1.Constants.FRAME_HEIGHT;
import static game1.Constants.FRAME_WIDTH;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import utilities.Vector2D;

public class Ship extends GameObject {

    int lives = 10;

    public static final Color COLOR = Color.cyan;
    // direction in which the nose of the ship is pointing 
    // this will be the direction in which thrust is applied 
    // it is a unit vector representing the angle by which the ship has rotated 
    public Vector2D direction;

    // controller which provides an Action object in each frame 
    private Controller ctrl;
    public boolean thrusting;
    public Bullet bullet;

    public Ship(Controller ctrl,Vector2D pos) {
        this.ctrl = ctrl;
        this.direction = new Vector2D(0, 1);
        this.position = pos;
        this.velocity = new Vector2D(0, 0);
        this.radius = 6;
    }

    @Override
    public void update() {
        direction.rotate((DT * 3) * ((double) ctrl.action().turn));
        Vector2D a = new Vector2D(direction);
        a.mult(ctrl.action().thrust);
        velocity.add(a.mult(DT * 3));

        //maximum velocity of the ship
        if (velocity.mag() > 5) {
            velocity.normalise().mult(5);
        }

        position.add(velocity);

        position.wrap(FRAME_WIDTH, FRAME_HEIGHT);

        if (ctrl.action().shoot) {
            mkBullet();
            ctrl.action().shoot = false;
        }
    }

    private void mkBullet() {
        //bullets hit ship!!
        double x = position.x;
        double y = position.y;
        double xv = direction.x;
        double yv = direction.y;
        Vector2D p = new Vector2D(x, y);
        Vector2D v = new Vector2D(direction);
        p.addScaled(v, 5);

        bullet = new Bullet(new Vector2D(p), new Vector2D(v));

    }

    @Override
    public void draw(Graphics2D g) {

        int[] XP = {0, 3, 0, -3};
        int[] YP = {0, 2, -2, 2};
        int[] XPTHRUST = {0, -1, 0, 1};
        int[] YPTHRUST = {0, 0, 2, 0};

        AffineTransform at = g.getTransform();

        g.translate(position.x, position.y);
        double rot = direction.angle() + Math.PI / 2;

        g.rotate(rot);

        g.scale(10, 10);

        g.setColor(COLOR);

        g.fillPolygon(XP, YP, XP.length);
        if (ctrl.action().thrust > 0) {
            g.setColor(Color.red);
            g.fillPolygon(XPTHRUST, YPTHRUST, XPTHRUST.length);
        }

        g.setTransform(at);
    }

    public void hit() {
        lives -= 1;
        if (lives == 0) {
            dead = true;
        }
    }

    public void collisionHandling(GameObject other) {
        if (this.getClass() != other.getClass() && this.overlap(other)) {
            if (!(other instanceof Bullet)) {
                this.hit();
                other.hit();
            }

        }
    }
}
