package ch.cpnv.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ch.cpnv.angrybirds.AngryWirds;

// TODO WASP random movements

public final class Bird extends MovingObject {
    private enum BirdState {init, aim, fly}

    private static final String PICTURE_NAME = "bird.png";
    public static final int WIDTH = 60;
    public static final int HEIGHT = 60;

    private BirdState state = BirdState.init;

    public Bird() {
        super(new Vector2(AngryWirds.BIRD_START_X, AngryWirds.BIRD_START_Y), WIDTH, HEIGHT, PICTURE_NAME, new Vector2(0, 0));
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
}
