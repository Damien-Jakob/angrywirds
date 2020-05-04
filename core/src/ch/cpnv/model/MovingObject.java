package ch.cpnv.model;

import com.badlogic.gdx.math.Vector2;

public abstract class MovingObject extends PhysicalObject {
    public Vector2 speed;

    public MovingObject(Vector2 speed) {
        this.speed = speed;
    }

    public MovingObject() {
        this(new Vector2());
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
