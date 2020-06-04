package ch.cpnv.model;

import com.badlogic.gdx.math.Vector2;

public final class Pig extends TextualObject {
    public static final int WIDTH = 60;
    public static final int HEIGHT = WIDTH;

    private static final String PICTURE_NAME = "pig.png";

    private int points;

    public Pig(Vector2 position, String word, int points) {
        super(position, WIDTH, HEIGHT, PICTURE_NAME, word);
        this.points = points;
    }

    public void sayWord() {
        // TODO implement comportment
    }
}
