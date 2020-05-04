package ch.cpnv.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public final class Bird extends MovingObject {
    static protected Texture texture = new Texture("bird.png");
    static protected Vector2 g = new Vector2(0, (float) -9.81);

    public Bird(int x, int y, int width, int height) {
        super(texture, x, y, width, height);
    }

    @Override
    public void accelerate(float dt) {
        // v = v0 + a * t
        speed = speed.add(g.scl(dt));
    }
}
