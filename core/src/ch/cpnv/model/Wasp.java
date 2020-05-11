package ch.cpnv.model;

import com.badlogic.gdx.math.Vector2;

import ch.cpnv.angrybirds.AngryWirds;

public final class Wasp extends MovingObject {

    private static final int AGITATION = 15; // How sharply speed changes
    private static final int AGITATION_ANGLE = 15;
    private static final String PICTURE_NAME = "wasp.png";
    private static final int WIDTH = 60;
    private static final int HEIGHT = 60;

    public Wasp(Vector2 position, Vector2 speed) {
        super(position, WIDTH, HEIGHT, PICTURE_NAME, speed);
    }

    @Override
    public void accelerate(float dt) {
        // The wasp only slightly alters its speed at random. It is subject to gravity, but it counters it with its flight
        if (!frozen) {
            accelerateByRandom(dt);
        }
    }

    private void accelerateByRandom(float dt) {
        // update the speed by a random acceleration
        float acc_x_update = AngryWirds.alea.nextFloat() - 0.5f;
        float acc_y_update = AngryWirds.alea.nextFloat() - 0.5f;
        speed.add(new Vector2(acc_x_update, acc_y_update).scl(dt * AGITATION));
    }

    // Rotation of the speed by an angle of max AGITATION_ANGLE
    // Note : if wasp starts with a speed of (0,0), it won't change
    private void accelerateByRotation(float dt) {
        int rotationAngle = AngryWirds.alea.nextInt(AGITATION * 2 + 1) - 15;
        speed.rotate(rotationAngle);
    }
}
