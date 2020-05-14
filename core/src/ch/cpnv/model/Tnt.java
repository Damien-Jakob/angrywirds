package ch.cpnv.model;

import com.badlogic.gdx.math.Vector2;

public final class Tnt extends PhysicalObject {
    private static final String PICTURE_NAME = "tnt.png";

    private static final int WIDTH = 60;
    private static final int HEIGHT = WIDTH;

    private int negativePoints;

    public Tnt(Vector2 position, int negativePoints) {
        super(position, WIDTH, HEIGHT, PICTURE_NAME);
        this.negativePoints = negativePoints;
    }
}
