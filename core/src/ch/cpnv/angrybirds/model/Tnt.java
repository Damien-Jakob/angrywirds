package ch.cpnv.angrybirds.model;

import com.badlogic.gdx.math.Vector2;

public final class Tnt extends PhysicalObject implements ScoreInfluencer {
    public static final int WIDTH = 60;
    public static final int HEIGHT = WIDTH;

    public static final int POINTS = -20;

    private static final String PICTURE_NAME = "tnt.png";

    public Tnt(Vector2 position) {
        super(position, WIDTH, HEIGHT, PICTURE_NAME);
    }

    @Override
    public int getPoints() {
        return POINTS;
    }
}
