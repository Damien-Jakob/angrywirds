package ch.cpnv.angrybirds;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

import ch.cpnv.model.Block;
import ch.cpnv.model.PhysicalObject;

public final class Scenery {
    private ArrayList<PhysicalObject> scene;

    public Scenery() {
        scene = new ArrayList<PhysicalObject>();
    }

    /**
     * Add one piece of scenery
     *
     * @param element
     */
    public void addElement(PhysicalObject element) {
        scene.add(element);
    }

    /**
     * Lay down a line of blocks to act as floor to the scene
     */
    public void addFloor() {
        for (float x = 0; x < AngryWirds.WORLD_WIDTH; x += Block.WIDTH) {
            scene.add(new Block(new Vector2(x, AngryWirds.FLOOR_HEIGHT)));
        }
    }

    /**
     * Render the whole scenary
     *
     * @param batch
     */
    public void draw(Batch batch) {
        for (PhysicalObject element : scene) element.draw(batch);
    }

}
