package ch.cpnv.angrybirds.activities;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ch.cpnv.angrybirds.AngryWirds;

public class GameOver extends Game implements InputProcessor {
    private static final String TITLE = "Fin de la partie";
    private static final int TITLE_SIZE = 6;
    private static final int SCORE_SIZE = 2;

    private Texture background;

    private BitmapFont titleFont;
    private float titlePositionX;
    private float titlePositionY;

    private BitmapFont infoFont;
    private String scoreText;
    private float scorePositionX;
    private float scorePositionY;
    private String vocText;
    private float vocPositionX;
    private float vocPositionY;

    private SpriteBatch batch;

    private OrthographicCamera camera;

    public GameOver() {
        batch = new SpriteBatch();

        Gdx.input.setInputProcessor(this);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Play.WORLD_WIDTH, Play.WORLD_HEIGHT);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        background = new Texture(Gdx.files.internal("background.jpg"));

        titleFont = new BitmapFont();
        titleFont.setColor(Color.RED);
        titleFont.getData().setScale(TITLE_SIZE);
        GlyphLayout titleGlyphLayout = new GlyphLayout();
        titleGlyphLayout.setText(titleFont, TITLE);
        titlePositionX = Play.WORLD_WIDTH / 2f - titleGlyphLayout.width / 2f;
        titlePositionY = Play.WORLD_HEIGHT / 2f + titleGlyphLayout.height / 2f;

        scoreText = "Score : " + AngryWirds.score;
        vocText = "Voc : " + AngryWirds.voc.getName();

        infoFont = new BitmapFont();
        infoFont.setColor(Color.BLACK);
        infoFont.getData().setScale(SCORE_SIZE);

        GlyphLayout glyphLayout = new GlyphLayout();
        glyphLayout.setText(infoFont, scoreText);
        scorePositionX = Play.WORLD_WIDTH / 2f - glyphLayout.width / 2f;
        scorePositionY = titlePositionY - titleGlyphLayout.height - glyphLayout.height;
        glyphLayout.setText(infoFont, vocText);
        vocPositionX = Play.WORLD_WIDTH / 2f - glyphLayout.width / 2f;
        vocPositionY = scorePositionY - glyphLayout.height - 10;
    }

    @Override
    public void create() {

    }

    public void update() {
        float dt = Gdx.graphics.getDeltaTime();
    }

    @Override
    public void render() {
        update();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(background, 0, 0, camera.viewportWidth, camera.viewportHeight);
        titleFont.draw(batch, TITLE, titlePositionX, titlePositionY);
        infoFont.draw(batch, scoreText, scorePositionX, scorePositionY);
        infoFont.draw(batch, vocText, vocPositionX, vocPositionY);
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
        AngryWirds.popPage();
        return true;
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
