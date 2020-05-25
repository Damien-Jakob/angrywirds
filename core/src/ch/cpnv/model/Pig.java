package ch.cpnv.model;

import com.badlogic.gdx.math.Vector2;

public final class Pig extends TextualObject {
    private static final String PICTURE_NAME = "pig.png";

    private static final int WIDTH = 60;
    private static final int HEIGHT = WIDTH;

    private int points;

    public Pig(Vector2 position, String word, int points) {
        super(position, WIDTH, HEIGHT, PICTURE_NAME, word);
        this.points = points;
    }

    public void sayWord() {
        // TODO implement comportment
    }
}
