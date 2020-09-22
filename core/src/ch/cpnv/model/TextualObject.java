package ch.cpnv.model;

import com.badlogic.gdx.math.Vector2;

public abstract class TextualObject extends PhysicalObject {
    protected String text;

    public TextualObject(Vector2 position, float width, float height, String pictureName, String text) {
        super(position, width, height, pictureName);
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
