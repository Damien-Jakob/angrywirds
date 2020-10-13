package ch.cpnv.angrybirds.activities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

import ch.cpnv.angrybirds.AngryWirds;
import ch.cpnv.angrybirds.model.Scenery;
import ch.cpnv.angrybirds.model.Bird;
import ch.cpnv.angrybirds.model.Block;
import ch.cpnv.angrybirds.model.OutOfSceneryException;
import ch.cpnv.angrybirds.model.Panel;
import ch.cpnv.angrybirds.model.PhysicalObject;
import ch.cpnv.angrybirds.model.Pig;
import ch.cpnv.angrybirds.model.SceneCollapseException;
import ch.cpnv.angrybirds.model.ScoreInfluencer;
import ch.cpnv.angrybirds.model.Tnt;
import ch.cpnv.angrybirds.model.Wasp;
import ch.cpnv.angrybirds.model.data.NoPickableWordException;
import ch.cpnv.angrybirds.model.data.Word;
import ch.cpnv.angrybirds.ui.IconButton;

// TODO add rubber once implemented

public class Play extends BaseActivity implements InputProcessor {
    private static final float MAX_DT = 0.5f;

    public static final int FLOOR_HEIGHT = 120;

    public static final int BIRD_START_X = 200;
    public static final int BIRD_START_Y = 200;
    private static final int SLINGSHOT_WIDTH = 75;
    private static final int SLINGSHOT_HEIGHT = BIRD_START_Y + Bird.HEIGHT - FLOOR_HEIGHT;

    private static final int WASP_QUANTITY = 2;
    private static final int PIGS_QUANTITY = 5;
    private static final int TNT_QUANTITY = 3;
    private static final int BLOCKS_QUANTITY = 50;

    private static final int SCORE_POSITION_X = WORLD_WIDTH / 2;
    private static final int SCORE_POSITION_Y = WORLD_HEIGHT - 50;
    private static final int VOC_POSITION_X = SCORE_POSITION_X;
    private static final int VOC_POSITION_Y = WORLD_HEIGHT - 10;

    public static final int SUCCESS_POINTS = 100;

    public static final float SLINGSHOT_POWER = 3f;

    private static final int AIMING_ZONE_X = BIRD_START_X + Bird.WIDTH;
    private static final int AIMING_ZONE_Y = BIRD_START_Y + Bird.HEIGHT;

    public static final int PAUSE_BUTTON_DIMENSIONS = 100;
    public static final int PAUSE_BUTTON_X = WORLD_WIDTH - PAUSE_BUTTON_DIMENSIONS - 10;
    public static final int PAUSE_BUTTON_Y = WORLD_HEIGHT - PAUSE_BUTTON_DIMENSIONS - 10;

    private Bird bird;
    private ArrayList<Wasp> wasps;
    private Scenery scenery;

    private BitmapFont infoFont;

    private IconButton pauseButton;

    private Panel questionPanel;

    private Rectangle aimingzone;

    private Texture slingshot1;
    private Texture slingshot2;
    // private RubberBand rubberBand1;
    // private RubberBand rubberBand2;

    public Play() {
        bird = new Bird();

        wasps = new ArrayList<>();
        for (int i = 0; i < WASP_QUANTITY; i++) {
            Wasp wasp = new Wasp(new Vector2(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2f), new Vector2(40, 40));
            wasps.add(wasp);
        }

        scenery = new Scenery();

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

        AngryWirds.voc.deallocateAll();
        int pigsLeft = PIGS_QUANTITY;
        boolean firstPig = true;
        while (pigsLeft > 0) {
            try {
                Word word;
                // The first pig will have a word that has never been found
                // It will be the word of the question panel
                if (firstPig) {
                    word = AngryWirds.voc.pickNotFoundWord();

                    questionPanel = new Panel(word);

                    firstPig = false;
                } else {
                    word = AngryWirds.voc.pickNotAllocatedWord();
                }

                Pig pig = new Pig(new Vector2(
                        AngryWirds.alea.nextFloat() * (Scenery.MAX_X - Pig.WIDTH - Scenery.MIN_X) + Scenery.MIN_X,
                        0),
                        word);
                scenery.addElement(pig);
                pigsLeft--;
            } catch (OutOfSceneryException exception) {
                Gdx.app.log("EXCEPTION", "Pig out of bounds: " + exception.getMessage());
            } catch (SceneCollapseException exception) {
                Gdx.app.log("EXCEPTION", "Unstable pig: " + exception.getMessage());
            } catch (NoPickableWordException exception) {
                Gdx.app.log("EXCEPTION", "No more available words: " + exception.getMessage());
                break;
            }
        }

        pauseButton = new IconButton(
                new Vector2(PAUSE_BUTTON_X, PAUSE_BUTTON_Y),
                PAUSE_BUTTON_DIMENSIONS, PAUSE_BUTTON_DIMENSIONS,
                "pause-icon.png"
        );

        slingshot1 = new Texture(Gdx.files.internal("slingshot1.png"));
        slingshot2 = new Texture(Gdx.files.internal("slingshot2.png"));

        infoFont = new BitmapFont();
        infoFont.setColor(Color.BLACK);
        infoFont.getData().setScale(2);

        aimingzone = new Rectangle(0, 0, AIMING_ZONE_X, AIMING_ZONE_Y);

    }

    public void update() {
        // number of milliseconds elapsed since last render
        float dt = Gdx.graphics.getDeltaTime();

        if (dt < MAX_DT) { // Ignore big lapses, like the ones at the start of the game
            // --------- Bird
            // Apply changes to the bird. The magnitude of the changes depend on the time elapsed since last update !!!
            if (bird.getState() == Bird.State.FLYING) {
                bird.move(dt);
                bird.accelerate(dt);

                PhysicalObject objectHit = scenery.objectHitBy(bird);
                for (Wasp wasp : wasps) {
                    if (wasp.collidesWith(bird)) {
                        objectHit = wasp;
                    }
                }
                if (objectHit != null) {
                    if (objectHit instanceof ScoreInfluencer) {
                        ScoreInfluencer scoreInfluencer = (ScoreInfluencer) objectHit;
                        AngryWirds.score += scoreInfluencer.getPoints();
                        scenery.removeElement(objectHit);
                    }
                    if (objectHit instanceof Pig) {
                        Pig pig = (Pig) objectHit;
                        if (pig.getWord() == questionPanel.getWord()) {
                            AngryWirds.score -= pig.getPoints();
                            AngryWirds.score += SUCCESS_POINTS;
                            questionPanel.getWord().setFound(true);
                            AngryWirds.popPage();
                            if (AngryWirds.voc.hasNotFoundWord()) {
                                AngryWirds.pushPage(new Play());
                            } else {
                                AngryWirds.pushPage(new GameOver());
                            }
                        }
                    }
                    scenery.removeElement(objectHit);
                    if (objectHit instanceof Wasp) {
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
    public void render() {
        update();

        super.render();

        batch.begin();

        // Note that the order in which they are drawn matters, the last ones are on top of the previous ones
        for (Wasp wasp : wasps) {
            wasp.draw(batch);
        }
        scenery.draw(batch);
        questionPanel.draw(batch);
        batch.draw(slingshot1, BIRD_START_X, FLOOR_HEIGHT, SLINGSHOT_WIDTH, SLINGSHOT_HEIGHT);
        bird.draw(batch);
        batch.draw(slingshot2, BIRD_START_X, FLOOR_HEIGHT, SLINGSHOT_WIDTH, SLINGSHOT_HEIGHT);
        pauseButton.draw(batch);
        drawGameInfo();
        batch.end();
    }

    private void drawGameInfo() {
        infoFont.draw(batch, "Voc : " + AngryWirds.voc.getName(), VOC_POSITION_X, VOC_POSITION_Y);
        infoFont.draw(batch, "Score : " + AngryWirds.score, SCORE_POSITION_X, SCORE_POSITION_Y);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 touchPoint = convertCoordinates(screenX, screenY);

        if (pauseButton.contains(touchPoint)) {
            AngryWirds.pushPage(new Pause());
            return true;
        }

        boolean actionHandled = scenery.handleTouchDown(touchPoint);

        if (!actionHandled
                && aimingzone.contains(touchPoint)) {
            bird.startAim(touchPoint);
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Vector2 touchPoint = convertCoordinates(screenX, screenY);

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
}
