package game1;

import static game1.Constants.DELAY;
import static game1.Constants.FRAME_HEIGHT;
import static game1.Constants.FRAME_WIDTH;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilities.JEasyFrame;
import utilities.Vector2D;

public class Game {

    public static final int N_INITIAL_ASTEROIDS = 5;
    public List<GameObject> objects;
    public Ship Playership;
    int Score;

    public int getScore() {
        return Score;
    }

    public Ship getShip() {
        return Playership;
    }
    Controller ctrl;

    public Game() {
        objects = new ArrayList<GameObject>();
        ctrl = new Keys();
        Playership = new Ship(ctrl,new Vector2D(600,500));
        objects.add(Playership);
        for (int i = 0; i < N_INITIAL_ASTEROIDS; i++) {
            objects.add(Asteroid.makeRandomAsteroid(50));
        }
        objects.add(new Ship(new RotateNShoot(),(new Vector2D(Math.random() * FRAME_WIDTH, Math.random() * FRAME_HEIGHT))));
    }

    public void update() {
        synchronized (Game.class) {
            List<GameObject> alive = new ArrayList<>();
            for (GameObject object : objects) {
                object.update();
                for (GameObject other : objects) {
                    object.collisionHandling(other);
                }
                if (!(object.dead)) {
                    alive.add(object);
                } else if (object.dead && object instanceof Asteroid && object.radius > 9) {
                    addScrore(10);
                    this.Playership.velocity.mult(-1);
                    alive.add(new Asteroid((new Vector2D(object.position.x, object.position.y)), (new Vector2D(Math.random() * 2, Math.random() * 2)), (double) object.radius - 10));
                    alive.add(new Asteroid((new Vector2D(object.position.x, object.position.y)), (new Vector2D(Math.random() * 2, Math.random() * 2)), (double) object.radius - 10));
                    alive.add(new Asteroid((new Vector2D(object.position.x, object.position.y)), (new Vector2D(Math.random() * 2, Math.random() * 2)), (double) object.radius - 10));

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            }
            if (Playership.bullet != null) {
                alive.add(Playership.bullet);
                Playership.bullet = null;
            }
            objects.clear();
            objects.addAll(alive);
        }
    }

    public void addScrore(int n) {

        Score += n;
    }

    public static void main(String[] args) throws Exception {
        Game game = new Game();
        View view = new View(game);
        JEasyFrame f = new JEasyFrame(view, "Game");
        f.addKeyListener((KeyListener) game.ctrl);
        f.setResizable(false);
        f.setSize(1200, 1000);

        // run the game
        while (true) {
            game.update();
            view.repaint();
            Thread.sleep(DELAY);
        }
    }
}
