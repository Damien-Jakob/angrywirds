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

import java.util.HashMap;

import ch.cpnv.angrybirds.AngryWirds;
import ch.cpnv.angrybirds.model.data.Vocabulary;
import ch.cpnv.angrybirds.providers.VocProvider;
import ch.cpnv.angrybirds.ui.IconButton;

// TODO find better icons (should take 100% of width and height of the png)

public class VocSelection extends Game implements InputProcessor {
    private static final String TITLE = "Vocabulaires";
    private static final int TITLE_SIZE = 6;
    private static final int LINE_SIZE = 3;

    private static final float TITLE_POSITION_Y = Play.WORLD_HEIGHT - 20f;
    private static final float BUTTON_DIMENSION = 100;
    private static final float VOC_POSITION_X = 200;
    private static final float VOC_START_Y = Play.WORLD_HEIGHT - 200;
    private static final float VOC_MARGIN = 50f;

    private Texture background;

    private BitmapFont titleFont;
    private float titlePositionX;

    private BitmapFont vocabularyFont;
    private BitmapFont randomVocFont;
    private float vocTextX;
    // used to align the text with the buttons, using the center
    private float textOffsetY;

    private IconButton randomVocButton;
    private HashMap<IconButton, Vocabulary> vocSelectionButtons;
    private HashMap<IconButton, Vocabulary> vocDetailButtons;
    private float vocDetailX;

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
        titleFont.setColor(Color.GOLD);
        titleFont.getData().setScale(TITLE_SIZE);
        GlyphLayout titleGlyphLayout = new GlyphLayout();
        titleGlyphLayout.setText(titleFont, TITLE);
        titlePositionX = Play.WORLD_WIDTH / 2f - titleGlyphLayout.width / 2f;

        vocDetailX = VOC_POSITION_X + BUTTON_DIMENSION + VOC_MARGIN;

        vocabularyFont = new BitmapFont();
        vocabularyFont.setColor(Color.BLUE);
        vocabularyFont.getData().setScale(LINE_SIZE);
        vocTextX = vocDetailX + BUTTON_DIMENSION + VOC_MARGIN;

        randomVocFont = new BitmapFont();
        randomVocFont.setColor(Color.RED);
        randomVocFont.getData().setScale(LINE_SIZE);

        float buttonY = VOC_START_Y;
        randomVocButton = new IconButton(
                new Vector2(VOC_POSITION_X, buttonY),
                BUTTON_DIMENSION, BUTTON_DIMENSION,
                "play-icon.png"
        );
        buttonY -= BUTTON_DIMENSION + VOC_MARGIN;
        vocSelectionButtons = new HashMap<>();
        vocDetailButtons = new HashMap<>();
        for (Vocabulary vocabulary : VocProvider.getInstance().vocabularies) {
            vocSelectionButtons.put(
                    new IconButton(
                            new Vector2(VOC_POSITION_X, buttonY),
                            BUTTON_DIMENSION, BUTTON_DIMENSION,
                            "play-icon.png"
                    ),
                    vocabulary
            );
            vocDetailButtons.put(
                    new IconButton(
                            new Vector2(vocDetailX, buttonY),
                            BUTTON_DIMENSION, BUTTON_DIMENSION,
                            "detail-icon.png"
                    ),
                    vocabulary
            );
            buttonY -= BUTTON_DIMENSION + VOC_MARGIN;
        }

        GlyphLayout vocGlyphLayout = new GlyphLayout();
        vocGlyphLayout.setText(vocabularyFont, TITLE);
        // We want to align the center of the button with the center of the text
        textOffsetY = vocGlyphLayout.height / 2f - BUTTON_DIMENSION / 2f;
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
        randomVocButton.draw(batch);
        randomVocFont.draw(batch, "Random", vocTextX, randomVocButton.getYTop() + textOffsetY);
        for (HashMap.Entry<IconButton, Vocabulary> entry : vocSelectionButtons.entrySet()) {
            IconButton button = entry.getKey();
            button.draw(batch);

            Vocabulary voc = entry.getValue();
            vocabularyFont.draw(batch, voc.getName(), vocTextX, button.getYTop() + textOffsetY);
        }
        for (HashMap.Entry<IconButton, Vocabulary> entry : vocDetailButtons.entrySet()) {
            IconButton button = entry.getKey();
            button.draw(batch);
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
        if (randomVocButton.contains(touchPoint)) {
            AngryWirds.popPage();
            AngryWirds.start();
        }
        for (HashMap.Entry<IconButton, Vocabulary> entry : vocSelectionButtons.entrySet()) {
            IconButton iconButton = entry.getKey();
            if (iconButton.contains(touchPoint)) {
                Vocabulary selectedVoc = entry.getValue();
                AngryWirds.popPage();
                AngryWirds.start(selectedVoc);
            }
        }
        for (HashMap.Entry<IconButton, Vocabulary> entry : vocDetailButtons.entrySet()) {
            IconButton iconButton = entry.getKey();
            if (iconButton.contains(touchPoint)) {
                Vocabulary selectedVoc = entry.getValue();
                AngryWirds.pushPage(new VocDetail(selectedVoc));
            }
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
