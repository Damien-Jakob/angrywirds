package ch.cpnv.angrybirds;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

import ch.cpnv.model.Bird;
import ch.cpnv.model.Wasp;


public class AngryWirds extends ApplicationAdapter {
    public static Random alea; // random generator object. Static for app-wide use

    public static final int WORLD_WIDTH = 1600;
    public static final int WORLD_HEIGHT = 900;

    public static final int BIRD_START_X = 200;
    public static final int BIRD_START_Y = 200;

    private Texture background;

    private Bird bird;
    private Wasp wasp;

    private OrthographicCamera camera;

    private SpriteBatch batch;

    private BitmapFont font;

    @Override
    public void create() {
        alea = new Random();

        background = new Texture(Gdx.files.internal("background.jpg"));

        camera = new OrthographicCamera();
        camera.setToOrtho(false, WORLD_WIDTH, WORLD_HEIGHT);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        wasp = new Wasp(new Vector2(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2f), new Vector2(20, 20));
        bird = new Bird();
        bird.setSpeed(new Vector2(150, 200));

        batch = new SpriteBatch();


        // For debugging
        font = new BitmapFont();
    }

    public void update() {
        float dt = Gdx.graphics.getDeltaTime(); // number of milliseconds elapsed since last render

        // --------- Bird
        // Apply changes to the bird. The magnitude of the changes depend on the time elapsed since last update !!!
        bird.move(dt);
        bird.accelerate(dt);

        wasp.move(dt);
        wasp.accelerate(dt);

        // --------- Wasp
        // Apply changes to the wasp...
    }

    @Override
    public void render() {
        update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(background, 0, 0, camera.viewportWidth, camera.viewportHeight);
        bird.draw(batch);
        wasp.draw(batch);

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
