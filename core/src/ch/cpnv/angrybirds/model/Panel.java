package ch.cpnv.angrybirds.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;

import ch.cpnv.angrybirds.AngryWirds;
import ch.cpnv.angrybirds.activities.Play;
import ch.cpnv.angrybirds.model.data.Word;

public class Panel extends Sprite {
    private static final String PICTURE_NAME = "panel.png";

    private static final int WIDTH = 500;
    private static final int HEIGHT = 100;

    private static final int POSITION_X = 20;
    private static final int POSITION_Y = Play.WORLD_HEIGHT - HEIGHT;

    private static final int TEXT_OFFSET_X = 50;
    private static final int TEXT_OFFSET_Y = 50;

    private static final int FONT_SCALE = 2;

    private BitmapFont font;
    private Word word;

    public Panel(Word word) {
        super(new Texture(PICTURE_NAME));
        setBounds(POSITION_X, POSITION_Y, WIDTH, HEIGHT);
        this.word = word;
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.getData().setScale(FONT_SCALE);
    }

    @Override
    public void draw(Batch batch) {
        super.draw(batch);
        font.draw(batch, word.getQuestion(), getX() + TEXT_OFFSET_X, getY() + TEXT_OFFSET_Y);
    }

    public Word getWord() {
        return word;
    }
}
