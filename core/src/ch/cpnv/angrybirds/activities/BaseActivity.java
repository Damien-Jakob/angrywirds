package ch.cpnv.angrybirds.activities;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class BaseActivity extends Game implements InputProcessor {
    private Texture background;

    protected SpriteBatch batch;

    private OrthographicCamera camera;

    public BaseActivity() {
        batch = new SpriteBatch();

        // Set which InputProcessor does answer to the inputs
        Gdx.input.setInputProcessor(this);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Play.WORLD_WIDTH, Play.WORLD_HEIGHT);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        background = new Texture(Gdx.files.internal("background.jpg"));
    }

    @Override
    public void create() { }

    @Override
    public void render() {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(background, 0, 0, camera.viewportWidth, camera.viewportHeight);
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
}
