package ch.cpnv.angrybirds.model;

import com.badlogic.gdx.math.Vector2;

import ch.cpnv.angrybirds.AngryWirds;
import ch.cpnv.angrybirds.activities.Play;

public final class Wasp extends MovingObject implements ScoreInfluencer {
    private static final int AGITATION = 30; // How sharply speed changes
    private static final int AGITATION_ANGLE = 360;
    private static final String PICTURE_NAME = "wasp.png";
    private static final int WIDTH = 60;
    private static final int HEIGHT = WIDTH;

    public static final int POINTS = -20;

    public Wasp(Vector2 position, Vector2 speed) {
        super(position, WIDTH, HEIGHT, PICTURE_NAME, speed);
    }

    @Override
    public void accelerate(float dt) {
        // The wasp only slightly alters its speed at random. It is subject to gravity, but it counters it with its flight
        accelerateToCenter(dt);
    }

    private void accelerateToCenter(float dt) {
        // update the speed by a random acceleration, that tends to keep it in the middle
        // note : 0 <= nextFloat() < 1
        // note : the more the wasp is far from the center, the more it will be attracted to it
        // note : -0.5 <= nextFloat - 0.5 < 0.5, in this case the acceleration in all directions is equally probable
        // note : getX()/AngryWirds.WORLD_WIDTH == 0.5 if the wasp is at the center
        Vector2 folly = new Vector2(
                AngryWirds.alea.nextFloat() - getX() / Play.WORLD_WIDTH,
                AngryWirds.alea.nextFloat() - getY() / Play.WORLD_HEIGHT);
        speed.add(folly.scl(dt * AGITATION));
    }

    // It was a good thought experiment, but it's not really useful
    // Rotation of the speed by an angle of max AGITATION_ANGLE
    // Note : if wasp starts with a speed of (0,0), it won't change
    private void accelerateByRotation(float dt) {
        int rotationAngle = AngryWirds.alea.nextInt(AGITATION_ANGLE * 2 + 1) - AGITATION_ANGLE;
        speed.rotate(rotationAngle * dt);
    }

    @Override
    public int getPoints() {
        return POINTS;
    }
}
