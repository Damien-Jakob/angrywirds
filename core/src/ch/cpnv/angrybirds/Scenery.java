package ch.cpnv.angrybirds;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

import ch.cpnv.angrybirds.activities.Play;
import ch.cpnv.angrybirds.model.Block;
import ch.cpnv.angrybirds.model.MovingObject;
import ch.cpnv.angrybirds.model.OutOfSceneryException;
import ch.cpnv.angrybirds.model.PhysicalObject;
import ch.cpnv.angrybirds.model.Pig;
import ch.cpnv.angrybirds.model.SceneCollapseException;
import ch.cpnv.angrybirds.model.data.Word;

public final class Scenery {
    public static final int MIN_X = Play.BIRD_START_X + 100;
    public static final int MAX_X = Play.WORLD_WIDTH;
    public static final int MIN_Y = Play.FLOOR_HEIGHT;
    public static final int MAX_Y = Play.WORLD_HEIGHT;

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

    public void removeElement(PhysicalObject objectToRemove) {
        scene.remove(objectToRemove);
    }

    /**
     * Lay down a line of blocks to act as the floor of the scene
     */
    public void addFloor() {
        for (float x = MIN_X; x < MAX_X; x += Block.WIDTH) {
            scene.add(new Block(new Vector2(x, Play.FLOOR_HEIGHT)));
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
        for (PhysicalObject pigCandidate : scene) {
            if (pigCandidate instanceof Pig) {
                pigs.add((Pig) pigCandidate);
            }
        }
        return pigs.get(AngryWirds.alea.nextInt(pigs.size())).getWord();
    }

    public PhysicalObject objectHitBy(MovingObject movingObject) {
        for (PhysicalObject collisionCandidate : scene) {
            if (collisionCandidate.getBoundingRectangle().overlaps(
                    movingObject.getBoundingRectangle())) {
                return collisionCandidate;
            }
        }
        return null;
    }
}
