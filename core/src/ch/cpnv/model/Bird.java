package ch.cpnv.model;

import com.badlogic.gdx.math.Vector2;

import ch.cpnv.angrybirds.AngryWirds;

public final class Bird extends MovingObject {
    public enum BirdState {READY, AIMING, FLYING}

    private static final String PICTURE_NAME = "bird.png";
    public static final int WIDTH = 60;
    public static final int HEIGHT = WIDTH;

    private BirdState state = BirdState.READY;
    // Remember where the aiming started
    private Vector2 aimOrigin;
    private Vector2 dragOffset; // Touch location "within" the bird, used to help keep the dragging animation clean

    public Bird() {
        super(new Vector2(AngryWirds.BIRD_START_X, AngryWirds.BIRD_START_Y), WIDTH, HEIGHT, PICTURE_NAME, new Vector2(0, 0));
    }

    public Bird(Vector2 position, int width, int height, Vector2 speed) {
        super(position, width, height, PICTURE_NAME, speed);
    }

    public BirdState getState() {
        return state;
    }

    public void setSpeed(Vector2 speed) {
        this.speed = speed;
    }

    public Vector2 getSpeed() {
        return speed;
    }

    public void startAim(Vector2 position) {
        if (state == BirdState.READY) {
            aimOrigin = position.cpy();
            // Attention : copy the position before modifying it
            dragOffset = position.sub(getX(), getY());
            state = BirdState.AIMING;
        }
    }

    public void drag(Vector2 position) {
        if (state == BirdState.AIMING) {
            setPosition(position.x - dragOffset.x, position.y - dragOffset.y);
        }
    }

    public void launchFrom(Vector2 position) {
        if (state == BirdState.AIMING) {
            state = BirdState.FLYING;
            speed = aimOrigin.sub(position).scl(AngryWirds.SLINGSHOT_POWER);
        }
    }

    @Override
    public void accelerate(float dt) {
        // y = y0 - g * t
        speed.y -= GRAVITY * dt;
    }

    // TODO fix it, the image is displayed glitched when generated during runtime
    public Bird giveBirth() {
        Bird child = new Bird(new Vector2(getX(), getY()), (int) getWidth(), (int) getHeight(), new Vector2(speed.x, 0));
        //Bird child = new Bird();
        return child;
    }
}
