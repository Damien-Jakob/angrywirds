package ch.cpnv.model;

import com.badlogic.gdx.math.Vector2;

public abstract class MovingObject extends PhysicalObject {
    // Gravity, for objects that fall
    public final static float GRAVITY = 50f;

    protected Vector2 speed;
    protected boolean frozen; // Allows to temporarily freeze the movement

    public MovingObject(Vector2 position, float width, float height, String pictureName) {
        this(position, width, height, pictureName, new Vector2(0, 0));
    }

    public MovingObject(Vector2 position, float width, float height, String pictureName, Vector2 speed) {
        super(position, width, height, pictureName);
        this.speed = speed;
        this.frozen = false;
    }

    public void freeze() {
        frozen = true;
    }

    public void unFreeze() {
        frozen = false;
    }

    public boolean isFrozen() {
        return frozen;
    }

    public void move(float dt) {
        if (!isFrozen()) {
            translate(speed.x * dt, speed.y * dt);
        }
    }

    // the accelerate method implements the speed change,
    // which depends on the physics of the derived object, reason why it is abstract here
    public abstract void accelerate(float dt);
}
