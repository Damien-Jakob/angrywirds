package ch.cpnv.angrybirds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

import ch.cpnv.model.Block;
import ch.cpnv.model.PhysicalObject;

public final class Scenery {
    private ArrayList<PhysicalObject> scene;
    private ArrayList<Sprite> decoy;

    private static final int PANEL_HEIGHT = 150;
    private static final int PANEL_WIDTH = 250;
    private static final int PANEL_X = 50;

    public Scenery() {
        scene = new ArrayList<PhysicalObject>();
        decoy = new ArrayList<Sprite>();
        Sprite panel = new Sprite(new Texture("panel.png"));
        panel.setBounds(PANEL_X, AngryWirds.WORLD_HEIGHT - PANEL_HEIGHT, PANEL_WIDTH, PANEL_HEIGHT);
        decoy.add(panel);
    }

    /**
     * Add one piece of scenery
     *
     * @param newObject
     */
    public void addElement(PhysicalObject newObject) {
        fitY(newObject);
        scene.add(newObject);
    }

    /**
     * Lay down a line of blocks to act as the floor of the scene
     */
    public void addFloor() {
        for (float x = 0; x < AngryWirds.WORLD_WIDTH; x += Block.WIDTH) {
            scene.add(new Block(new Vector2(x, AngryWirds.FLOOR_HEIGHT)));
        }
    }

    protected void fitY(PhysicalObject newObject) {
        float minAvailableAltitude = 0;
        for (PhysicalObject object : scene) {
            if (!(object.getXRight() < newObject.getXLeft() || newObject.getXRight() < object.getXLeft())
                    && minAvailableAltitude < object.getYTop()) {
                minAvailableAltitude = object.getYTop();
            }
        }
        newObject.setY(minAvailableAltitude);
    }

    /**
     * Render the whole scenary
     *
     * @param batch
     */
    public void draw(Batch batch) {

        for (PhysicalObject element : scene) element.draw(batch);
        for (Sprite decoyElement : decoy) decoyElement.draw(batch);
    }


    // TODO
    // exceptions for out-of-bound positions
    // (optional) exceptions for non-accepted
}
