package ch.cpnv.angrybirds;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

public class Welcome extends Game implements InputProcessor {
    public static Random alea;

    private static final String TITLE = "AngryWirds";
    private static final int TITLE_SIZE = 6;

    private Texture background;
    private BitmapFont titleFont;

    private SpriteBatch batch;

    private OrthographicCamera camera;

    @Override
    public void create() {
        batch = new SpriteBatch();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, AngryWirds.WORLD_WIDTH, AngryWirds.WORLD_HEIGHT);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        background = new Texture(Gdx.files.internal("background.jpg"));

        titleFont = new BitmapFont();
        titleFont.setColor(Color.ROYAL);
        titleFont.getData().setScale(TITLE_SIZE);
    }

    @Override
    public void render() {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(background, 0, 0, camera.viewportWidth, camera.viewportHeight);
        // TODO put title in the center
        titleFont.draw(batch, TITLE, AngryWirds.WORLD_WIDTH / 2.0f, AngryWirds.WORLD_HEIGHT / 2.0f);
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
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
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
