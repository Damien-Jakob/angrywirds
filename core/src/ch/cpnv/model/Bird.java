package ch.cpnv.model;

import com.badlogic.gdx.math.Vector2;

public final class Bird extends MovingObject {
    static Vector2 g = new Vector2(0, (float) -9.81);

    @Override
    public void accelerate(float dt) {
        // v = v0 + a * t
        speed = speed.add(g.scl(dt));
    }
}
