package ch.cpnv.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

import ch.cpnv.model.data.Word;

// TODO store word ?

public final class Pig extends TextualObject {
    private static final String PICTURE_NAME = "pig.png";

    public static final int WIDTH = 60;
    public static final int HEIGHT = WIDTH;

    private Bubble bubble = null;

    private int points;

    public Pig(Vector2 position, Word word, int points) {
        super(position, WIDTH, HEIGHT, PICTURE_NAME, word.getQuestion());
        this.points = points;
    }

    @Override
    public void draw(Batch batch) {
        super.draw(batch);
        if (bubble != null) {
            bubble.draw(batch);
        }
    }

    public String getWord() {
        return getText();
    }

    public boolean getScreaming() {
        return bubble != null;
    }

    public void sayWord() {
        this.bubble = new Bubble(this);
    }

    public void shutUp() {
        this.bubble = null;
    }
}
