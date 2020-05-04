package ch.cpnv.model;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class PhysicalObject extends Sprite {
    public void draw(Batch batch) {
        batch.draw(this, this.getX(), this.getY());
    }
}
