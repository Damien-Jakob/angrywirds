package ch.cpnv.angrybirds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

import ch.cpnv.model.Block;
import ch.cpnv.model.OutOfSceneryException;
import ch.cpnv.model.PhysicalObject;
import ch.cpnv.model.Pig;
import ch.cpnv.model.SceneCollapseException;
import ch.cpnv.model.data.Word;

public final class Scenery {
    public static final int MIN_X = AngryWirds.BIRD_START_X + 100;
    public static final int MAX_X = AngryWirds.WORLD_WIDTH;
    public static final int MIN_Y = AngryWirds.FLOOR_HEIGHT;
    public static final int MAX_Y = AngryWirds.WORLD_HEIGHT;

    private ArrayList<PhysicalObject> scene;
    private ArrayList<Sprite> decoy;

    public Scenery() {
        scene = new ArrayList<>();
        decoy = new ArrayList<>();
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

    // TODO test stability of objects
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

    public boolean handleTouchDown(Vector2 touchPoint) {
        for (PhysicalObject element : scene) {
            if (element instanceof Pig) {
                Pig pig = (Pig) element;
                if (pig.getBoundingRectangle().contains(touchPoint)) {
                    pig.sayWord();
                    return true;
                }
            }
        }
        return false;
    }

    public void handleTouchUp(Vector2 touchPoint) {
        for (PhysicalObject element : scene) {
            if (element instanceof Pig) {
                Pig pig = (Pig) element;
                pig.shutUp();
            }
        }
    }

    public Word pickAWord() {
        ArrayList<Pig> pigs = new ArrayList<Pig>();
        for(PhysicalObject pigCandidate : scene) {
            if(pigCandidate instanceof Pig) {
                pigs.add((Pig)pigCandidate);
            }
        }
        return pigs.get(AngryWirds.alea.nextInt(pigs.size())).getWord();
    }
}
