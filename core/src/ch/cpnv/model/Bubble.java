package ch.cpnv.model;

import com.badlogic.gdx.math.Vector2;

public final class Bubble extends TextualObject {
    private static final String PICTURE_NAME = "pig.png";

    private static final int WIDTH = 60;
    private static final int HEIGHT = 60;

    private int duration;

    // TODO implement self-destruction

    // TODO adapt width to the word
    // TODO find way to display the word
    public Bubble(Vector2 position, String word, int duration) {
        super(position, WIDTH, HEIGHT, PICTURE_NAME, word);
        this.duration = duration;
    }
}
