package ch.cpnv.angrybirds.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class RubberBand extends Sprite {
    private static final String PICTURE_NAME = "rubber.png";
    private static final float THICKNESS = 12f;

    public RubberBand() {
        super(new Texture(PICTURE_NAME));
    }

    public void putBetween(Vector2 origin, Vector2 destination) {
        // sub is done in place, and we don't want to modify the destination vector
        Vector2 difference = destination.cpy().sub(origin);
        setBounds(origin.x, origin.y, difference.len(), THICKNESS);
        setOrigin(0, 0);
        setRotation(difference.angle());
    }
}
