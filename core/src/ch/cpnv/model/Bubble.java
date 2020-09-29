package ch.cpnv.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;

public final class Bubble extends TextualObject {
    private static final String PICTURE_NAME = "bubble.png";
    private static final int WIDTH = 260;
    private static final int HEIGHT = 160;
    // Used to center the spike on the head
    private static final int OFFSET = 20;

    // Text position
    private static final int TEXT_OFFSET_X = 40;
    private static final int TEXT_OFFSET_Y = 100;

    private BitmapFont font;

    public Bubble(Pig pig) {
        super(new Vector2(pig.getX(), pig.getY()), WIDTH, HEIGHT, PICTURE_NAME, pig.getText());
        setBounds(getX() - WIDTH / 2 - OFFSET, getY() + HEIGHT / 2, WIDTH, HEIGHT);
        font = new BitmapFont();

        font.setColor(Color.BLACK);
        font.getData().setScale(2);
    }

    public void draw(Batch batch) {
        super.draw(batch);
        font.draw(batch, getText(), getX() + TEXT_OFFSET_X, getY() + TEXT_OFFSET_Y);
    }
}
