package ch.cpnv.angrybirds.activities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.HashMap;

import ch.cpnv.angrybirds.AngryWirds;
import ch.cpnv.angrybirds.model.data.Language;
import ch.cpnv.angrybirds.model.data.Vocabulary;
import ch.cpnv.angrybirds.providers.VocProvider;
import ch.cpnv.angrybirds.ui.IconButton;
import ch.cpnv.angrybirds.ui.TextButton;

public class Welcome extends BaseActivity {
    private static final String TITLE = "AngryWirds";
    private static final int TITLE_SCALE = 6;
    private static final int SUB_TITLE_SCALE = 4;

    private static final int COLUMN_1_X = 100;
    private static final int START_Y = Play.WORLD_HEIGHT - 200;
    private static final int LANGUAGE_OFFSET_Y = 100;
    private static final int BUTTON_WIDTH = 300;
    private static final int BUTTON_HEIGHT = 100;


    private BitmapFont titleFont;
    private float titlePositionX;
    private float titlePositionY;

    private BitmapFont subTitleFont;
    private float subTitlePositionX = 100;
    private float subTitlePositionY = 150;

    private HashMap<TextButton, Language> languagesFrom = new HashMap<>();
    private HashMap<TextButton, Language> languagesTo = new HashMap<>();

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
                    new TextButton(new Vector2(COLUMN_1_X, positionY), BUTTON_WIDTH, BUTTON_HEIGHT, language.getName()),
                    language
            );
            positionY -= LANGUAGE_OFFSET_Y;
        }
    }

    @Override
    public void render() {
        super.render();

        batch.begin();
        titleFont.draw(batch, TITLE, titlePositionX, titlePositionY);
        subTitleFont.draw(batch, "blabla", subTitlePositionX, subTitlePositionY);
        for (HashMap.Entry<TextButton, Language> entry : languagesFrom.entrySet()) {
            TextButton button = entry.getKey();
            button.draw(batch);
        }
        batch.end();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        AngryWirds.pushPage(new VocSelection());
        return true;
    }
}
