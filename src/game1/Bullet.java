/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game1;

import static game1.Constants.FRAME_HEIGHT;
import static game1.Constants.FRAME_WIDTH;
import java.awt.Color;
import java.awt.Graphics2D;
import utilities.Vector2D;

/**
 *
 * @author Corax
 */
class Bullet extends GameObject {

    public Bullet(Vector2D position, Vector2D velocity) {

        this.position = new Vector2D(position);
        this.velocity = new Vector2D(velocity);
        this.radius = 2;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.red);
        g.fillRect((int) position.x - (int) this.radius, (int) position.y - (int) this.radius, 2, 4);

    }

    public void update() {
        position.add(velocity);

        position.wrap(FRAME_WIDTH, FRAME_HEIGHT);

    }

    public void hit() {
        dead = true;
    }

}
