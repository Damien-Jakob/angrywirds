package ch.cpnv.model;

import com.badlogic.gdx.math.Vector2;

public class TextualObject extends PhysicalObject {
    protected String word;

    public TextualObject(Vector2 position, float width, float height, String pictureName, String word) {
        super(position, width, height, pictureName);
        this.word = word;
    }

    public String getWord() {
        return word;
    }
}
