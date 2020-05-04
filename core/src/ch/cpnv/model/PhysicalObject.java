package ch.cpnv.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class PhysicalObject extends Sprite {
    public PhysicalObject(Texture texture, int x, int y, int width, int height) {
        super(texture, x, y, width, height);
    }
}
