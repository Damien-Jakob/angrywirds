package ch.cpnv.angrybirds.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;

import ch.cpnv.angrybirds.model.TextualObject;

public class TextButton extends TextualObject {
    protected BitmapFont font;

    public TextButton(Vector2 position, float width, float height, String text) {
        super(position, width, height, null, text);
        font = new BitmapFont();
        font.setColor(Color.GREEN);
    }

    public boolean contains(Vector2 point) {
        return this.getRectangle().contains(point);
    }

    public void draw(Batch batch) {
        font.draw(batch, text, getX(), getY());
    }
}
