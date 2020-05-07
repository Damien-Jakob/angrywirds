package ch.cpnv.model;

import com.badlogic.gdx.math.Vector2;

public final class Wasp extends MovingObject {

    private static final int AGITATION = 15; // How sharply speed changes
    private static final String PICTURE_NAME = "wasp.png";
    private static final int WIDTH = 60;
    private static final int HEIGHT = 60;

    public Wasp(Vector2 position, Vector2 speed) {
        super(position, WIDTH, HEIGHT, PICTURE_NAME, speed);
    }

    @Override
    public void accelerate(float dt) {
        // The wasp only slightly alters its speed at random. It is subject to gravity, but it counters it with its flight
    }
}
