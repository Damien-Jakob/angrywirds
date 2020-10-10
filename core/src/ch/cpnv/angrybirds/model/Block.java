package ch.cpnv.angrybirds.model;

import com.badlogic.gdx.math.Vector2;

public final class Block extends PhysicalObject implements ScoreInfluencer{
    private static final String PICTURE_NAME = "block.png";

    public static final int POINTS = -1;

    public static final int WIDTH = 30;
    public static final int HEIGHT = WIDTH;

    public Block(Vector2 position) {
        super(position, WIDTH, HEIGHT, PICTURE_NAME);
    }

    @Override
    public int getPoints() {
        return POINTS;
    }
}
