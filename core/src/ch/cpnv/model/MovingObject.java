package ch.cpnv.model;

import com.badlogic.gdx.math.Vector2;

public abstract class MovingObject extends PhysicalObject {
    public final static float GRAVITY = 50f; // Gravity, for objects that fall
    // 250f

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

    // met la position égale à la vitesse
    public void move(float dt) {
        if (!isFrozen()) {
            /*
            Vector2 position = new Vector2(getX(), getY());
            // x = x0 + v * t
            position = position.add(speed.scl(dt));
            setPosition(position);

             */

            float position_x = getX() + speed.x * dt;
            float position_y = getY() + speed.y * dt;
            setPosition(position_x, position_y);
        }
    }

    // the accelerate method implements the speed change, which depends on the physics of the derived object, reason why it is abstract here
    public abstract void accelerate(float dt);

    protected void setPosition(Vector2 position) {
        if (!isFrozen()) {
            setPosition(position.x, position.y);
        }
    }
}
