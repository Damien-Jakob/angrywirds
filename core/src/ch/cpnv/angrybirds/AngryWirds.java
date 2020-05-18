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

import ch.cpnv.model.Bird;
import ch.cpnv.model.Block;
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
    private static final int HERD_SIZE = 40;
    private static final int TNT_QUANTITY = 3;

    private Bird bird;
    private ArrayList<Wasp> swarm;
    private Scenery scenery;
    private Texture background;

    private OrthographicCamera camera;
    private SpriteBatch batch;

    public static Random alea; // random generator object. Static for app-wide use

    // Just for debug purpose
    private BitmapFont font;

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
        for (int i = 0; i < HERD_SIZE; i++) {
            Pig pig = new Pig(new Vector2(alea.nextFloat() * WORLD_WIDTH, FLOOR_HEIGHT + Block.HEIGHT), "?", 10);
            scenery.addElement(pig);
        }
        for (int i = 0; i < TNT_QUANTITY; i++) {
            Tnt tnt = new Tnt(new Vector2(alea.nextFloat() * WORLD_WIDTH, FLOOR_HEIGHT + Block.HEIGHT), 5);
            scenery.addElement(tnt);
        }

        batch = new SpriteBatch();

        // For debugging
        font = new BitmapFont();
    }

    public void update() {
        float dt = Gdx.graphics.getDeltaTime(); // number of milliseconds elapsed since last render
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
        }
    }

    @Override
    public void render() {
        update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(background, 0, 0, camera.viewportWidth, camera.viewportHeight);

        // Note that the order in which they are drawn matters, the last ones are on top of the previous ones
        bird.draw(batch);
        scenery.draw(batch);
        for (Wasp wasp : swarm) {
            wasp.draw(batch);
        }

        // debug
        // draw bird speed
        //font.draw(batch, String.valueOf(bird.getSpeed().x) + ';' + String.valueOf(bird.getSpeed().y), 100, 100);

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
