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

import java.util.HashMap;

import ch.cpnv.angrybirds.AngryWirds;
import ch.cpnv.angrybirds.model.data.Vocabulary;
import ch.cpnv.angrybirds.providers.VocProvider;
import ch.cpnv.angrybirds.ui.IconButton;

public class VocSelection extends Game implements InputProcessor {
    private static final String TITLE = "Vocabulaires";
    private static final int TITLE_SIZE = 6;
    private static final int LINE_SIZE = 3;

    private static final float TITLE_POSITION_Y = Play.WORLD_HEIGHT - 20f;
    private static final float buttonDimension = 100;
    private static final float VOC_POSITION_X = 200;
    private static final float VOC_START_Y = Play.WORLD_HEIGHT - 200;
    private static final float VOC_MARGIN = 50f;
    // used to align the text with the buttons
    private static final float TEXT_OFFSET_Y = -30f;

    private Texture background;

    private BitmapFont titleFont;
    private float titlePositionX;

    private BitmapFont vocabularyFont;
    private float vocTextX;

    private HashMap<IconButton, Vocabulary> vocSelectionButtons;

    private SpriteBatch batch;

    private OrthographicCamera camera;

    public VocSelection() {
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

        vocabularyFont = new BitmapFont();
        Color vocabularyNameColor = Color.BLUE;
        vocabularyFont = new BitmapFont();
        vocabularyFont.setColor(vocabularyNameColor);
        vocabularyFont.getData().setScale(LINE_SIZE);
        vocTextX = VOC_POSITION_X + buttonDimension + VOC_MARGIN;

        vocSelectionButtons = new HashMap<IconButton, Vocabulary>();
        float buttonY = VOC_START_Y;
        for (Vocabulary vocabulary : VocProvider.getInstance().vocabularies) {
            vocSelectionButtons.put(
                    new IconButton(
                            new Vector2(VOC_POSITION_X, buttonY),
                            buttonDimension, buttonDimension,
                            "play-icon.png"
                    ),
                    vocabulary
            );
            buttonY -= buttonDimension + VOC_MARGIN;
        }
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
        titleFont.draw(batch, TITLE, titlePositionX, TITLE_POSITION_Y);
        for (HashMap.Entry<IconButton, Vocabulary> entry : vocSelectionButtons.entrySet()) {
            IconButton button = entry.getKey();
            button.draw(batch);

            Vocabulary voc = entry.getValue();
            vocabularyFont.draw(batch, voc.getName(), vocTextX, button.getYTop() + TEXT_OFFSET_Y);
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
        AngryWirds.popPage();
        // TODO give the voc selected
        AngryWirds.start();
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
