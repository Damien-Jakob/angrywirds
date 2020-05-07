package ch.cpnv.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class PhysicalObject extends Sprite {
    public PhysicalObject(Vector2 position, int width, int height, String pictureName) {
        super(new Texture(pictureName), width, height, (int)position.x, (int)position.y);
        //setBounds(position.x, position.y, width, height);
    }
}
