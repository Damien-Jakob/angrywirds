package ch.cpnv.model;

import com.badlogic.gdx.math.Vector2;

public final class Block extends PhysicalObject {
    private static final String PICTURE_NAME = "block.png";

    private static final int WIDTH = 40;
    private static final int HEIGHT = 40;

    public static int height() {
        return HEIGHT;
    }

    public static int width() {
        return WIDTH;
    }

    public Block(Vector2 position) {
        super(position, WIDTH, HEIGHT, PICTURE_NAME);
    }
}
