package ch.cpnv.angrybirds;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import ch.cpnv.model.Bird;
import ch.cpnv.model.Block;
import ch.cpnv.model.OutOfSceneryException;
import ch.cpnv.model.Pig;
import ch.cpnv.model.Tnt;
import ch.cpnv.model.Wasp;


public class AngryWirds extends ApplicationAdapter {
    public static final int WORLD_WIDTH = 1600;
    public static final int WORLD_HEIGHT = 900;

    public static final int FLOOR_HEIGHT = 120;

    public static final int BIRD_START_X = 200;
    public static final int BIRD_START_Y = 200;

    private static final int SWARM_SIZE = 5;
    private static final int HERD_SIZE = 12;
    private static final int TNT_QUANTITY = 50;
    private static final int BLOCKS_QUANTITY = 154;

    private Bird bird;
    private ArrayList<Wasp> swarm;
    private Scenery scenery;
    private Texture background;

    private OrthographicCamera camera;
    private SpriteBatch batch;

    public static Random alea; // random generator object. Static for app-wide use

    private Vector2 touchPoint;

    // Just for debug purpose
    private BitmapFont font;

    // Just for fun
    private ArrayList<Bird> babyBirds;

    @Override
    public void create() {
        alea = new Random();

        background = new Texture(Gdx.files.internal("background.jpg"));

        camera = new OrthographicCamera();
        camera.setToOrtho(false, WORLD_WIDTH, WORLD_HEIGHT);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        bird = new Bird();
        bird.setSpeed(new Vector2(150, 200));

        swarm = new ArrayList<Wasp>();
        for (int i = 0; i < SWARM_SIZE; i++) {
            Wasp wasp = new Wasp(new Vector2(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2f), new Vector2(40, 40));
            swarm.add(wasp);
        }

        scenery = new Scenery();
        scenery.addFloor();

        int maxTries = 3;
        for (int i = 0; i < BLOCKS_QUANTITY; i++) {
            for (int tryNumber = 0; tryNumber < maxTries ; tryNumber++) {
                try {
                    Block block = new Block(new Vector2(
                            alea.nextFloat() * (Scenery.MAX_X - Block.WIDTH - Scenery.MIN_X) + Scenery.MIN_X,
                            0
                    ));
                    scenery.addElement(block);
                    break;
                } catch (OutOfSceneryException ignored) {
                }
            }
        }
        for (int i = 0; i < TNT_QUANTITY; i++) {
            try {
                Tnt tnt = new Tnt(new Vector2(
                        alea.nextFloat() * (Scenery.MAX_X - Tnt.WIDTH - Scenery.MIN_X) + Scenery.MIN_X,
                        0
                ), 5);
                scenery.addElement(tnt);
            } catch (OutOfSceneryException ignored) {
            }
        }
        for (int i = 0; i < HERD_SIZE; i++) {
            try {
                Pig pig = new Pig(new Vector2(
                        alea.nextFloat() * (Scenery.MAX_X - Pig.WIDTH - Scenery.MIN_X) + Scenery.MIN_X,
                        0
                ), "?", 10);
                scenery.addElement(pig);
            } catch (OutOfSceneryException ignored) {
            }
        }

        batch = new SpriteBatch();

        touchPoint = new Vector2();

        // For debugging
        font = new BitmapFont();

        // For fun
        babyBirds = new ArrayList<Bird>();
        //babyBirds.add(bird);
        Timer birdsTimer = new Timer();

        // Test giving birth before flight
        if (false) {
            bird.freeze();
            haveFunWithBirds();
        }
        // Test giving birth during flight : glitched
        if (false) {
            birdsTimer.scheduleAtFixedRate(
                    new TimerTask() {
                        @Override
                        public void run() {
                            haveFunWithBirds();
                        }
                    },
                    1000,
                    1000
            );
        }
    }

    public void update() {
        float dt = Gdx.graphics.getDeltaTime(); // number of milliseconds elapsed since last render

        // TODO fix it, it doesn't do anything
        if (Gdx.input.justTouched()) {
            touchPoint.set(Gdx.input.getX(), Gdx.input.getY());
            if (bird.getBoundingRectangle().contains(touchPoint.x, touchPoint.y)) {
                bird.unFreeze();
            }
        }

        if (dt < 0.5f) { // Ignore big lapses, like the ones at the start of the game
            // --------- Bird
            // Apply changes to the bird. The magnitude of the changes depend on the time elapsed since last update !!!
            bird.move(dt);
            bird.accelerate(dt);

            // --------- Wasp
            // Apply changes to the wasp...
            for (Wasp wasp : swarm) {
                wasp.move(dt);
                wasp.accelerate(dt);
            }
            for (Bird bird : babyBirds) {
                bird.move(dt);
                bird.accelerate(dt);
            }
        }
    }

    @Override
    public void render() {
        update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(background, 0, 0, camera.viewportWidth, camera.viewportHeight);

        // Note that the order in which they are drawn matters, the last ones are on top of the previous ones
        scenery.draw(batch);
        for (Wasp wasp : swarm) {
            wasp.draw(batch);
        }
        for (Bird bird : babyBirds) {
            bird.draw(batch);
        }
        bird.draw(batch);

        // debug
        // draw bird speed
        //font.draw(batch, String.valueOf(bird.getSpeed().x) + ';' + String.valueOf(bird.getSpeed().y), 100, 100);
        font.draw(batch, String.valueOf(babyBirds.size()), 100, 100);

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    // TODO fix it, the image is displayed glitched when generated during runtime
    protected void haveFunWithBirds() {
        babyBirds.add(bird.giveBirth());
    }
}
