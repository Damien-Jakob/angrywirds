package ch.cpnv.angrybirds.activities;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;

import ch.cpnv.angrybirds.AngryWirds;
import ch.cpnv.angrybirds.Scenery;
import ch.cpnv.angrybirds.model.Bird;
import ch.cpnv.angrybirds.model.Block;
import ch.cpnv.angrybirds.model.OutOfSceneryException;
import ch.cpnv.angrybirds.model.Panel;
import ch.cpnv.angrybirds.model.PhysicalObject;
import ch.cpnv.angrybirds.model.Pig;
import ch.cpnv.angrybirds.model.SceneCollapseException;
import ch.cpnv.angrybirds.model.Tnt;
import ch.cpnv.angrybirds.model.Wasp;
import ch.cpnv.angrybirds.model.data.Word;

// TODO prevent displaying the same word multiple times

// TODO keep in memory the words already found, prevent to reuse them
// TODO better score management : objects have points/negative points
// TODO see : Map !!!EXAMEN!!!
// TODO see : tables
// TODO save advancement

public class Play extends Game implements InputProcessor {
    public static final int WORLD_WIDTH = 1600;
    public static final int WORLD_HEIGHT = 900;

    public static final int FLOOR_HEIGHT = 120;

    public static final int BIRD_START_X = 200;
    public static final int BIRD_START_Y = 200;

    private static final int WASP_QUANTITY = 2;
    private static final int PIGS_QUANTITY = 5;
    private static final int TNT_QUANTITY = 3;
    private static final int BLOCKS_QUANTITY = 30;

    private static final int SCORE_POSITION_X = WORLD_WIDTH / 2;
    private static final int SCORE_POSITION_Y = WORLD_HEIGHT - 50;

    public static final float SLINGSHOT_POWER = 3f;

    public static final int AIMING_ZONE_WIDTH = WORLD_WIDTH;
    public static final int AIMING_ZONE_HEIGHT = WORLD_HEIGHT;

    public static final int PAUSE_ZONE_DIMENSIONS = 100;
    public static final int PAUSE_ZONE_X = WORLD_WIDTH - PAUSE_ZONE_DIMENSIONS;
    public static final int PAUSE_ZONE_Y = WORLD_HEIGHT - PAUSE_ZONE_DIMENSIONS;

    private Bird bird;
    private ArrayList<Wasp> wasps;
    private Scenery scenery;
    private Texture background;

    private BitmapFont scoreFont;

    private Rectangle pauseZone;
    private PhysicalObject pauseIcon;

    private Panel questionPanel;

    private Rectangle aimingzone;

    private OrthographicCamera camera;
    private SpriteBatch batch;

    public Play() {
        background = new Texture(Gdx.files.internal("background.jpg"));

        camera = new OrthographicCamera();
        camera.setToOrtho(false, WORLD_WIDTH, WORLD_HEIGHT);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        bird = new Bird();

        wasps = new ArrayList<>();
        for (int i = 0; i < WASP_QUANTITY; i++) {
            Wasp wasp = new Wasp(new Vector2(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2f), new Vector2(40, 40));
            wasps.add(wasp);
        }

        scenery = new Scenery();
        scenery.addFloor();

        int blocksLeft = BLOCKS_QUANTITY;
        while (blocksLeft > 0) {
            try {
                Block block = new Block(new Vector2(
                        AngryWirds.alea.nextFloat() * (Scenery.MAX_X - Block.WIDTH - Scenery.MIN_X) + Scenery.MIN_X,
                        0
                ));
                scenery.addElement(block);
                blocksLeft--;
            } catch (OutOfSceneryException exception) {
                Gdx.app.log("EXCEPTION", "Block out of bounds: " + exception.getMessage());
            } catch (SceneCollapseException exception) {
                Gdx.app.log("EXCEPTION", "Unstable block: " + exception.getMessage());
            }
        }

        int tntLeft = TNT_QUANTITY;
        while (tntLeft > 0) {
            try {
                Tnt tnt = new Tnt(new Vector2(
                        AngryWirds.alea.nextFloat() * (Scenery.MAX_X - Tnt.WIDTH - Scenery.MIN_X) + Scenery.MIN_X,
                        0
                ), 5);
                scenery.addElement(tnt);
                tntLeft--;
            } catch (OutOfSceneryException exception) {
                Gdx.app.log("EXCEPTION", "TNT out of bounds: " + exception.getMessage());
            } catch (SceneCollapseException exception) {
                Gdx.app.log("EXCEPTION", "Unstable TNT: " + exception.getMessage());
            }
        }

        int pigsLeft = Math.min(PIGS_QUANTITY, AngryWirds.voc.size());
        ArrayList<Word> selectedWords = new ArrayList<Word>();
        while (pigsLeft > 0) {
            try {
                Word word;
                do {
                    word = AngryWirds.voc.pickAWord();
                } while (selectedWords.contains(word));
                selectedWords.add(word);

                Pig pig = new Pig(new Vector2(
                        AngryWirds.alea.nextFloat() * (Scenery.MAX_X - Pig.WIDTH - Scenery.MIN_X) + Scenery.MIN_X,
                        0
                ), word);
                scenery.addElement(pig);
                pigsLeft--;
            } catch (OutOfSceneryException exception) {
                Gdx.app.log("EXCEPTION", "Pig out of bounds: " + exception.getMessage());
            } catch (SceneCollapseException exception) {
                Gdx.app.log("EXCEPTION", "Unstable pig: " + exception.getMessage());
            }
        }

        aimingzone = new Rectangle(0, 0, AIMING_ZONE_WIDTH, AIMING_ZONE_HEIGHT);

        pauseZone = new Rectangle(
                PAUSE_ZONE_X, PAUSE_ZONE_Y,
                PAUSE_ZONE_DIMENSIONS, PAUSE_ZONE_DIMENSIONS
        );
        pauseIcon = new PhysicalObject(
                new Vector2(PAUSE_ZONE_X, PAUSE_ZONE_Y),
                PAUSE_ZONE_DIMENSIONS, PAUSE_ZONE_DIMENSIONS,
                "pause-icon.png");

        questionPanel = new Panel(scenery.pickAWord());

        batch = new SpriteBatch();

        scoreFont = new BitmapFont();
        scoreFont.setColor(Color.BLACK);
        scoreFont.getData().setScale(2);

        // Set which InputProcessor does answer to the inputs
        Gdx.input.setInputProcessor(this);
    }

    public void update() {
        float dt = Gdx.graphics.getDeltaTime(); // number of milliseconds elapsed since last render

        if (dt < 0.5f) { // Ignore big lapses, like the ones at the start of the game
            // --------- Bird
            // Apply changes to the bird. The magnitude of the changes depend on the time elapsed since last update !!!
            if (bird.getState() == Bird.BirdState.FLYING) {
                bird.move(dt);
                bird.accelerate(dt);

                PhysicalObject objectHit = scenery.objectHitBy(bird);
                for (Wasp wasp : wasps) {
                    if (wasp.collidesWith(bird)) {
                        objectHit = wasp;
                    }
                }
                if (objectHit != null) {
                    if (objectHit instanceof Pig) {
                        Pig pig = (Pig) objectHit;
                        if (pig.getWord() == questionPanel.getWord()) {
                            AngryWirds.score++;
                            AngryWirds.popPage();
                            AngryWirds.pushPage(new Play());
                        } else {
                            AngryWirds.score--;
                            scenery.removeElement(objectHit);
                        }
                    }
                    scenery.removeElement(objectHit);
                    if(objectHit instanceof Wasp) {
                        wasps.remove(objectHit);
                    }
                    bird = new Bird();
                }
            }

            // If the bird has gone out of bound, it is time to stop that throw and start a new one
            if (bird.getXRight() < 0 || bird.getXLeft() > WORLD_WIDTH || bird.getYTop() < 0) {
                bird = new Bird();
            }

            // --------- Wasp
            // Apply changes to the wasp...
            for (Wasp wasp : wasps) {
                wasp.move(dt);
                wasp.accelerate(dt);
            }
        }
    }

    @Override
    public void create() {

    }

    @Override
    public void render() {
        update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(background, 0, 0, camera.viewportWidth, camera.viewportHeight);

        // Note that the order in which they are drawn matters, the last ones are on top of the previous ones
        for (Wasp wasp : wasps) {
            wasp.draw(batch);
        }
        scenery.draw(batch);
        questionPanel.draw(batch);
        bird.draw(batch);

        pauseIcon.draw(batch);
        scoreFont.draw(batch, "Score : " + AngryWirds.score, SCORE_POSITION_X, SCORE_POSITION_Y);

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    // InputProcessor interface implementation
    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 touchPoint = convertCoordinates(screenX, screenY);
        Gdx.app.log("ANGRY", "Touch at " + touchPoint.x + "," + touchPoint.y);

        if (pauseZone.contains(touchPoint)) {
            Gdx.app.log("ANGRY", "Pause touched");
            AngryWirds.pushPage(new Pause());
            return true;
        }

        boolean actionHandled = scenery.handleTouchDown(touchPoint);

        // We don't want to move the bird if the user wanted to display a Pig
        if (!actionHandled
                && aimingzone.contains(touchPoint)) {
            bird.startAim(touchPoint);
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Vector2 touchPoint = convertCoordinates(screenX, screenY);
        Gdx.app.log("ANGRY", "Touch up at " + touchPoint.x + "," + touchPoint.y);

        scenery.handleTouchUp(touchPoint);

        if (aimingzone.contains(touchPoint)) {
            bird.launchFrom(touchPoint);
        }
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Vector2 touchPoint = convertCoordinates(screenX, screenY);
        Gdx.app.log("ANGRY", "Drag at " + touchPoint.x + "," + touchPoint.y);

        if (aimingzone.contains(touchPoint)) {
            bird.drag(touchPoint);
        }
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    // convert screen coordinates to camera coordinates
    protected Vector2 convertCoordinates(int screenX, int screenY) {
        Vector3 point = new Vector3(screenX, screenY, 0);
        camera.unproject(point);
        return new Vector2(point.x, point.y);
    }
}
