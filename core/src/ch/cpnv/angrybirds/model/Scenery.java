package ch.cpnv.angrybirds.model;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

import ch.cpnv.angrybirds.AngryWirds;
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
     * Add one piece of scenery, and fit its y position
     *
     * @param newElement Element to add to the scenery
     * @throws OutOfSceneryException
     * @throws SceneCollapseException
     */
    public void dropElement(PhysicalObject newElement) throws OutOfSceneryException, SceneCollapseException {
        // Check horizontal placement
        if (newElement.getXLeft() < MIN_X || newElement.getXRight() > MAX_X) {
            throw new OutOfSceneryException(newElement.toString());
        }
        Rectangle fallPath = new Rectangle(
                newElement.getX(), 0,
                newElement.getWidth(), Play.WORLD_HEIGHT);
        newElement.setY(MIN_Y);
        for (PhysicalObject element : scene) {
            if (element.getBoundingRectangle().overlaps(fallPath)
                    && newElement.getYBottom() < element.getYTop()) {
                // Check if the new element can stand
                if (newElement.getXCenter() < element.getXLeft()
                        || newElement.getXCenter() > element.getXRight()) {
                    throw new SceneCollapseException(newElement.toString());
                }
                newElement.setY(element.getYTop());
                if (newElement.getYTop() > MAX_Y) {
                    throw new OutOfSceneryException(newElement.toString());
                }
            }
        }
        scene.add(newElement);
    }

    /**
     * Remove an element of the scenery
     *
     * @param objectToRemove Element to remove
     */
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

    /**
     * Render the whole scenary
     *
     * @param batch Batch in which the scenery must be drawn
     */
    public void draw(Batch batch) {
        for (PhysicalObject element : scene) element.draw(batch);
        for (Sprite decoyElement : decoy) decoyElement.draw(batch);
    }

    /**
     * Handles the user interaction "touch down"
     *
     * @param touchPoint Point touched by the user
     * @return true if the action has been handled, false otherwise
     */
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

    /**
     * Handles the user interaction "touch up"
     *
     * @param touchPoint Point touched by the user
     * @return true if the action has been handled, false otherwise
     */
    public boolean handleTouchUp(Vector2 touchPoint) {
        for (PhysicalObject element : scene) {
            if (element instanceof Pig) {
                Pig pig = (Pig) element;
                pig.shutUp();
            }
        }
        return true;
    }

    /**
     * Get randomly the word of one of the pigs
     *
     * @return the word of one of the pigs
     */
    public Word pickAWord() {
        ArrayList<Pig> pigs = new ArrayList<Pig>();
        for (PhysicalObject pigCandidate : scene) {
            if (pigCandidate instanceof Pig) {
                pigs.add((Pig) pigCandidate);
            }
        }
        return pigs.get(AngryWirds.alea.nextInt(pigs.size())).getWord();
    }

    /**
     * Get the object hit by a moving object
     *
     * @param movingObject The object we want to test the collision
     * @return The object collided by the moving object, null if none
     */
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
