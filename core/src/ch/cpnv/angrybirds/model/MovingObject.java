package ch.cpnv.angrybirds.model;

import com.badlogic.gdx.math.Vector2;

public abstract class MovingObject extends PhysicalObject {
    // Gravity, for objects that fall
    public final static float GRAVITY = -100f;

    protected Vector2 speed;

    public MovingObject(Vector2 position, float width, float height, String pictureName) {
        this(position, width, height, pictureName, new Vector2(0, 0));
    }

    public MovingObject(Vector2 position, float width, float height, String pictureName, Vector2 speed) {
        super(position, width, height, pictureName);
        this.speed = speed;
    }

    public void move(float dt) {
        translate(speed.x * dt, speed.y * dt);
    }

    // the accelerate method implements the speed change,
    // which depends on the physics of the derived object, reason why it is abstract here
    public abstract void accelerate(float dt);
}
