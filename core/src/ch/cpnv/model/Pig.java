package ch.cpnv.model;

import com.badlogic.gdx.math.Vector2;

import ch.cpnv.model.data.Word;

// TODO store word ?

public final class Pig extends TextualObject {
    public static final int WIDTH = 60;
    public static final int HEIGHT = WIDTH;

    private static final String PICTURE_NAME = "pig.png";

    private int points;

    public Pig(Vector2 position, Word word, int points) {
        super(position, WIDTH, HEIGHT, PICTURE_NAME, word.getQuestion());
        this.points = points;
    }

    public void sayWord() {
        // TODO implement comportment
    }
}
