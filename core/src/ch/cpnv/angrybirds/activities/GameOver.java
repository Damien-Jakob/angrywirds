package ch.cpnv.angrybirds.activities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

import ch.cpnv.angrybirds.AngryWirds;

public class GameOver extends BaseActivity implements InputProcessor {
    private static final String TITLE = "Fin de la partie";
    private static final int TITLE_SIZE = 6;
    private static final int SCORE_SIZE = 2;

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

    public GameOver() {
        Gdx.input.setInputProcessor(this);

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
    public void render() {
        super.render();

        batch.begin();
        titleFont.draw(batch, TITLE, titlePositionX, titlePositionY);
        infoFont.draw(batch, scoreText, scorePositionX, scorePositionY);
        infoFont.draw(batch, vocText, vocPositionX, vocPositionY);
        batch.end();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        AngryWirds.popPage();
        return true;
    }
}
