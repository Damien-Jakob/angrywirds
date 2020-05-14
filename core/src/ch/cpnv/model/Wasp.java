package ch.cpnv.model;

import com.badlogic.gdx.math.Vector2;

import ch.cpnv.angrybirds.AngryWirds;

public final class Wasp extends MovingObject {

    private static final int AGITATION = 30; // How sharply speed changes
    private static final int AGITATION_ANGLE = 360;
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
        // note : that 0 <= nextFloat() < 1
        // note : that the more the wasp is far from the center, the more it will be attracted to it
        // -0.5 <= nextFloat - 0.5 < 0.5, in this case the acceleration in all directions is equally probable
        // getX()/AngryWirds.WORLD_WIDTH == 0.5 if the wasp is at the center
        float acc_x_update = AngryWirds.alea.nextFloat() - getX()/AngryWirds.WORLD_WIDTH;
        float acc_y_update = AngryWirds.alea.nextFloat() - getY()/AngryWirds.WORLD_HEIGHT;
        speed.add(new Vector2(acc_x_update, acc_y_update).scl(dt * AGITATION));
    }

    // It was a good thought experiment, but it's not really useful
    // Rotation of the speed by an angle of max AGITATION_ANGLE
    // Note : if wasp starts with a speed of (0,0), it won't change
    private void accelerateByRotation(float dt) {
        int rotationAngle = AngryWirds.alea.nextInt(AGITATION_ANGLE * 2 + 1) - AGITATION_ANGLE;
        speed.rotate(rotationAngle * dt);
    }
}
