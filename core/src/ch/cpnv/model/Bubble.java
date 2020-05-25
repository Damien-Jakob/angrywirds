package ch.cpnv.model;

import com.badlogic.gdx.math.Vector2;

public final class Bubble extends TextualObject {
    private static final String PICTURE_NAME = "bubble.png";

    // Note that width depends probably on the word to display
    private static final int HEIGHT = 40;

    private int duration;

    // TODO implement self-destruction

    // TODO adapt width to the word
    // TODO find way to display the word
    public Bubble(Vector2 position, String word, int duration) {
        super(position, HEIGHT, HEIGHT, PICTURE_NAME, word);
        this.duration = duration;
    }
}
