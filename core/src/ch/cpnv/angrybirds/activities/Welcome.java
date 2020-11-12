package ch.cpnv.angrybirds.activities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

import ch.cpnv.angrybirds.AngryWirds;

public class Welcome extends BaseActivity {
    private static final String TITLE = "AngryWirds";
    private static final int TITLE_SCALE = 6;
    private static final int SUB_TITLE_SCALE = 4;

    private BitmapFont titleFont;
    private float titlePositionX;
    private float titlePositionY;

    private BitmapFont subTitleFont;
    private float subTitlePositionX = 100;
    private float subTitlePositionY = 150;

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
        titlePositionY = Play.WORLD_HEIGHT -10;

        subTitlePositionX = 100;
        subTitlePositionY = titlePositionY - 100;
    }

    @Override
    public void render() {
        super.render();

        batch.begin();
        titleFont.draw(batch, TITLE, titlePositionX, titlePositionY);
        subTitleFont.draw(batch, "blabla", subTitlePositionX, subTitlePositionY);
        batch.end();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        AngryWirds.pushPage(new VocSelection());
        return true;
    }
}
