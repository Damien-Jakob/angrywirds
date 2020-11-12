package ch.cpnv.angrybirds.activities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.HashMap;

import ch.cpnv.angrybirds.model.data.Language;
import ch.cpnv.angrybirds.providers.VocProvider;
import ch.cpnv.angrybirds.ui.IconButton;
import ch.cpnv.angrybirds.ui.Button;

public class Welcome extends BaseActivity {
    private static final String TITLE = "AngryWirds";
    private static final int TITLE_SCALE = 6;
    private static final int SUB_TITLE_SCALE = 4;

    private static final int COLUMN_1_X = 100;
    private static final int COLUMN_2_X = Play.WORLD_WIDTH / 2;
    private static final int START_Y = Play.WORLD_HEIGHT - 300;
    private static final int LANGUAGE_OFFSET_Y = 100;
    private static final int BUTTON_WIDTH = 300;
    private static final int BUTTON_HEIGHT = 100;

    private static final int PLAY_BUTTON_DIMENSION = 100;

    private BitmapFont titleFont;
    private float titlePositionX;
    private float titlePositionY;

    private BitmapFont subTitleFont;
    private float subTitlePositionX = 100;
    private float subTitlePositionY = 150;

    private HashMap<Button, Language> languagesFrom = new HashMap<>();
    private HashMap<Button, Language> languagesTo = new HashMap<>();

    private IconButton playButton;

    private Language languageFrom;
    private Language languageTo;

    public Welcome() {
        titleFont = new BitmapFont();
        titleFont.setColor(Color.ROYAL);
        titleFont.getData().setScale(TITLE_SCALE);

        subTitleFont = new BitmapFont();
        subTitleFont.setColor(Color.ROYAL);
        subTitleFont.getData().setScale(SUB_TITLE_SCALE);

        GlyphLayout titleGlyphLayout = new GlyphLayout();
        titleGlyphLayout.setText(titleFont, TITLE);
        titlePositionX = Play.WORLD_WIDTH / 2f - titleGlyphLayout.width / 2f;
        titlePositionY = Play.WORLD_HEIGHT - 10;

        subTitlePositionX = 100;
        subTitlePositionY = titlePositionY - 100;

        ArrayList<Language> languages = VocProvider.getInstance().getLanguages();
        int positionY = START_Y;
        for (Language language : languages) {
            languagesFrom.put(
                    new Button(new Vector2(COLUMN_1_X, positionY), BUTTON_WIDTH, BUTTON_HEIGHT, language.getName()),
                    language
            );
            languagesTo.put(
                    new Button(new Vector2(COLUMN_2_X, positionY), BUTTON_WIDTH, BUTTON_HEIGHT, language.getName()),
                    language
            );
            positionY -= LANGUAGE_OFFSET_Y;
        }

        playButton = new IconButton(new Vector2(Play.WORLD_WIDTH / 2f, Play.WORLD_HEIGHT / 2f), PLAY_BUTTON_DIMENSION, PLAY_BUTTON_DIMENSION, "play-icon.png");
    }

    @Override
    public void render() {
        super.render();

        batch.begin();
        titleFont.draw(batch, TITLE, titlePositionX, titlePositionY);
        String languageFromText = languageFrom != null ? languageFrom.getName() : "(choisir)";
        String languageToText = languageTo != null ? languageTo.getName() : "(choisir)";
        String subtitleText = "Exercice de " + languageFromText + " en " + languageToText;

        subTitleFont.draw(batch, subtitleText, subTitlePositionX, subTitlePositionY);
        for (HashMap.Entry<Button, Language> entry : languagesFrom.entrySet()) {
            Button button = entry.getKey();
            button.draw(batch);
        }
        for (HashMap.Entry<Button, Language> entry : languagesTo.entrySet()) {
            Button button = entry.getKey();
            button.draw(batch);
        }

        if (languagesSelected()) {
            playButton.draw(batch);
        }

        batch.end();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 touchPoint = convertCoordinates(screenX, screenY);
        for (HashMap.Entry<Button, Language> entry : languagesFrom.entrySet()) {
            Button textButton = entry.getKey();
            if (textButton.contains(touchPoint)) {
                languageFrom = entry.getValue();
                languagesFrom = new HashMap<>();
                return true;
            }
        }
        for (HashMap.Entry<Button, Language> entry : languagesTo.entrySet()) {
            Button textButton = entry.getKey();
            if (textButton.contains(touchPoint)) {
                languageTo = entry.getValue();
                languagesTo = new HashMap<>();
                return true;
            }
        }
        if (languagesSelected() && playButton.contains(touchPoint)) {
            // TODO go to next page
        }
        return true;
    }

    private boolean languagesSelected() {
        return languageFrom != null && languageTo != null;
    }
}
