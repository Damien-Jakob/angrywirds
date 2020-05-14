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
    public static Random alea; // random generator object. Static for app-wide use

    public static final int WORLD_WIDTH = 1600;
    public static final int WORLD_HEIGHT = 900;

    public static final int BOTTOM_HEIGHT = 120;

    public static final int BIRD_START_X = 200;
    public static final int BIRD_START_Y = 200;

    private static final int SWARM_SIZE = 3;

    private Texture background;

    private Bird bird;
    private ArrayList<Wasp> swarm;
    private ArrayList<Tnt> tnts;
    private ArrayList<Block> blocks;
    private ArrayList<Pig> pigs;

    private OrthographicCamera camera;

    private SpriteBatch batch;

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
            Wasp wasp = new Wasp(new Vector2(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2f), new Vector2(0, 0));
            swarm.add(wasp);
        }

        pigs = new ArrayList<Pig>();
        pigs.add(new Pig(new Vector2(200, BOTTOM_HEIGHT + 40), "maman", 10));
        pigs.add(new Pig(new Vector2(250, BOTTOM_HEIGHT + 40), "papa", 9));

        tnts = new ArrayList<Tnt>();
        tnts.add(new Tnt(new Vector2(1000, BOTTOM_HEIGHT), 10));
        tnts.add(new Tnt(new Vector2(1200, BOTTOM_HEIGHT), 10));

        blocks = new ArrayList<Block>();
        blocks.add(new Block(new Vector2(200, BOTTOM_HEIGHT)));
        blocks.add(new Block(new Vector2(250, BOTTOM_HEIGHT)));
        blocks.add(new Block(new Vector2(300, BOTTOM_HEIGHT)));

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

        for (Wasp wasp : swarm) {
            wasp.draw(batch);
        }
        for (Tnt tnt : tnts) {
            tnt.draw(batch);
        }
        for (Block block : blocks) {
            block.draw(batch);
        }
        for (Pig pig : pigs) {
            pig.draw(batch);
        }
        bird.draw(batch);

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
