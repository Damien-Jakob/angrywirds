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
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import ch.cpnv.angrybirds.AngryWirds;
import ch.cpnv.angrybirds.model.data.Vocabulary;
import ch.cpnv.angrybirds.model.data.Word;
import ch.cpnv.angrybirds.ui.IconButton;

public class VocDetail extends Game implements InputProcessor {
    private static final int TITLE_SIZE = 6;
    private static final int WORD_SIZE = 2;

    private static final float TITLE_POSITION_Y = Play.WORLD_HEIGHT - 20f;
    private static final float BUTTON_DIMENSION = 100;
    private static final float COLUMN1_X = 200;
    private static final float COLUMN2_X = Play.WORLD_WIDTH / 2f + COLUMN1_X;
    private static final float VOC_START_Y = Play.WORLD_HEIGHT - 200;
    private static final float WORD_MARGIN = 25f;

    private Texture background;

    private Vocabulary voc;

    private BitmapFont titleFont;
    private String title;
    private float titlePositionX;

    private BitmapFont wordFont;
    private float wordHeight;

    private IconButton returnButton;

    private SpriteBatch batch;

    private OrthographicCamera camera;

    public VocDetail(Vocabulary voc) {
        this.voc = voc;

        batch = new SpriteBatch();

        Gdx.input.setInputProcessor(this);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Play.WORLD_WIDTH, Play.WORLD_HEIGHT);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        background = new Texture(Gdx.files.internal("background.jpg"));

        title = this.voc.getName();
        titleFont = new BitmapFont();
        titleFont.setColor(Color.GOLD);
        titleFont.getData().setScale(TITLE_SIZE);
        GlyphLayout titleGlyphLayout = new GlyphLayout();
        titleGlyphLayout.setText(titleFont, title);
        titlePositionX = Play.WORLD_WIDTH / 2f - titleGlyphLayout.width / 2f;

        wordFont = new BitmapFont();
        wordFont.setColor(Color.BLACK);
        wordFont.getData().setScale(WORD_SIZE);

        returnButton = new IconButton(
                new Vector2(Play.WORLD_WIDTH - BUTTON_DIMENSION - 10f,
                        Play.WORLD_HEIGHT - BUTTON_DIMENSION - 10f),
                BUTTON_DIMENSION, BUTTON_DIMENSION,
                "return-icon.png"
        );

        GlyphLayout vocGlyphLayout = new GlyphLayout();
        vocGlyphLayout.setText(wordFont, title);
        wordHeight = vocGlyphLayout.height;
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
        titleFont.draw(batch, title, titlePositionX, TITLE_POSITION_Y);
        returnButton.draw(batch);
        float wordY = VOC_START_Y;
        for (Word word : voc.getWords()) {
            wordFont.draw(batch, word.getQuestion(), COLUMN1_X, wordY);
            wordFont.draw(batch, word.getSolution(), COLUMN2_X, wordY);
            wordY -= wordHeight + WORD_MARGIN;
        }
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
        if (returnButton.contains(touchPoint)) {
            AngryWirds.popPage();
        }
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

    // convert screen coordinates to camera coordinates
    protected Vector2 convertCoordinates(int screenX, int screenY) {
        Vector3 point = new Vector3(screenX, screenY, 0);
        camera.unproject(point);
        return new Vector2(point.x, point.y);
    }
}
