package ch.cpnv.angrybirds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

import ch.cpnv.model.Block;
import ch.cpnv.model.OutOfSceneryException;
import ch.cpnv.model.PhysicalObject;
import ch.cpnv.model.SceneCollapseException;

// TODO (optional) exceptions for non-stable objects

public final class Scenery {
    public static final int MIN_X = AngryWirds.BIRD_START_X + 100;
    public static final int MAX_X = AngryWirds.WORLD_WIDTH;
    public static final int MIN_Y = AngryWirds.FLOOR_HEIGHT;
    public static final int MAX_Y = AngryWirds.WORLD_HEIGHT;

    private ArrayList<PhysicalObject> scene;
    private ArrayList<Sprite> decoy;

    private static final int PANEL_HEIGHT = 150;
    private static final int PANEL_WIDTH = 250;
    private static final int PANEL_X = 50;

    public Scenery() {
        scene = new ArrayList<>();
        decoy = new ArrayList<>();
        Sprite panel = new Sprite(new Texture("panel.png"));
        panel.setBounds(PANEL_X, AngryWirds.WORLD_HEIGHT - PANEL_HEIGHT, PANEL_WIDTH, PANEL_HEIGHT);
        decoy.add(panel);
    }

    /**
     * Add one piece of scenery
     *
     * @param newObject element to add to the scenery
     */
    public void addElement(PhysicalObject newObject) throws OutOfSceneryException, SceneCollapseException {
        if (newObject.getXLeft() < MIN_X || newObject.getXRight() > MAX_X) {
            throw new OutOfSceneryException();
        }
        fitY(newObject);
        scene.add(newObject);
    }

    /**
     * Lay down a line of blocks to act as the floor of the scene
     */
    public void addFloor() {
        for (float x = MIN_X; x < MAX_X; x += Block.WIDTH) {
            scene.add(new Block(new Vector2(x, AngryWirds.FLOOR_HEIGHT)));
        }
    }

    protected void fitY(PhysicalObject newObject) throws OutOfSceneryException, SceneCollapseException {
        float minAvailableAltitude = MIN_Y;
        for (PhysicalObject object : scene) {
            if (!(object.getXRight() < newObject.getXLeft() || newObject.getXRight() < object.getXLeft())
                    && minAvailableAltitude < object.getYTop()) {
                minAvailableAltitude = object.getYTop();
            }
        }
        if (minAvailableAltitude + newObject.getHeight() > MAX_Y) {
            throw new OutOfSceneryException();
        }
        newObject.setY(minAvailableAltitude);
    }

    /**
     * Render the whole scenary
     *
     * @param batch batch in which the scenery must be drawn
     */
    public void draw(Batch batch) {
        for (PhysicalObject element : scene) element.draw(batch);
        for (Sprite decoyElement : decoy) decoyElement.draw(batch);
    }
}
