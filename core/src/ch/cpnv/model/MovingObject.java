package ch.cpnv.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public abstract class MovingObject extends PhysicalObject {
    public Vector2 speed;

    public MovingObject(Texture texture, int x, int y, int width, int height) {
        this(texture, x, y, width, height, new Vector2());
    }

    public MovingObject(Texture texture, int x, int y, int width, int height, Vector2 speed) {
        super(texture, x, y, width, height);
        this.speed = speed;
    }

    protected void setPosition(Vector2 position) {
        setPosition(position.x, position.y);
    }

    public void move(float dt) {
        Vector2 position = new Vector2(getX(), getY());
        // x = x0 + v * t
        position = position.add(speed.scl(dt));
        setPosition(position);
    }

    public abstract void accelerate(float dt);
}
