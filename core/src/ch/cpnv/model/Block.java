package ch.cpnv.model;

import com.badlogic.gdx.math.Vector2;

public final class Block extends PhysicalObject {
    private static final String PICTURE_NAME = "block.png";

    private static final int WIDTH = 40;
    private static final int HEIGHT = 40;

    public Block(Vector2 position) {
        super(position, WIDTH, HEIGHT, PICTURE_NAME);
    }
}
