package ch.cpnv.angrybirds.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class PhysicalObject extends Sprite {
    public PhysicalObject(Vector2 position, float width, float height, String pictureName) {
        super(new Texture(pictureName));
        setBounds(position.x, position.y, width, height);
    }

    public float getXLeft() {
        return getX();
    }

    public float getXRight() {
        return getX() + getWidth();
    }

    public float getYBottom() {
        return getY();
    }

    public float getYTop() {
        return getY() + getHeight();
    }

    public boolean collidesWith(PhysicalObject o)
    {
        return this.getRectangle().overlaps(o.getRectangle());
    }

    private Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
}
