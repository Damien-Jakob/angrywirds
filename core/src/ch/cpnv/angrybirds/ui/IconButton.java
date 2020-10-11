package ch.cpnv.angrybirds.ui;

import com.badlogic.gdx.math.Vector2;

import ch.cpnv.angrybirds.model.PhysicalObject;

public class IconButton extends PhysicalObject
{
    public IconButton(Vector2 position, float width, float height, String pictureName) {
        super(position, width, height, pictureName);
    }

    public boolean contains(Vector2 point)
    {
        return this.getRectangle().contains(point);
    }
}
