package ch.cpnv.model;

import com.badlogic.gdx.math.Vector2;

import ch.cpnv.angrybirds.AngryWirds;

public final class Bird extends MovingObject {
    private enum BirdState {init, aim, fly}

    private static final String PICTURE_NAME = "bird.png";
    public static final int WIDTH = 60;
    public static final int HEIGHT = WIDTH;

    private BirdState state = BirdState.init;

    public Bird() {
        super(new Vector2(AngryWirds.BIRD_START_X, AngryWirds.BIRD_START_Y), WIDTH, HEIGHT, PICTURE_NAME, new Vector2(0, 0));
        //freeze();
    }

    public Bird(Vector2 position, int width, int height, Vector2 speed) {
        super(position, width, height, PICTURE_NAME, speed);
        //freeze();
    }

    public void setSpeed(Vector2 speed) {
        this.speed = speed;
    }

    public Vector2 getSpeed() {
        return speed;
    }

    @Override
    public void unFreeze() {
        super.unFreeze();
        state = BirdState.fly;
    }

    @Override
    public void accelerate(float dt) {
        if (!isFrozen()) {
            // y = y0 - g * t
            speed.y -= GRAVITY * dt;
        }
    }

    // TODO fix it, the image is displayed glitched when generated during runtime
    public Bird giveBirth() {
        Bird child = new Bird(new Vector2(getX(), getY()), (int) getWidth(), (int) getHeight(), new Vector2(speed.x, 0));
        //Bird child = new Bird();
        return child;
    }
}
