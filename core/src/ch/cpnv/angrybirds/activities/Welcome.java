package ch.cpnv.angrybirds.activities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

import ch.cpnv.angrybirds.AngryWirds;

public class Welcome extends BaseActivity {
    private static final String TITLE = "AngryWirds";
    private static final int TITLE_SIZE = 6;

    private BitmapFont titleFont;
    private float titlePositionX;
    private float titlePositionY;

    public Welcome() {
        titleFont = new BitmapFont();
        titleFont.setColor(Color.ROYAL);
        titleFont.getData().setScale(TITLE_SIZE);
        GlyphLayout titleGlyphLayout = new GlyphLayout();
        titleGlyphLayout.setText(titleFont, TITLE);
        titlePositionX = Play.WORLD_WIDTH / 2f - titleGlyphLayout.width / 2f;
        titlePositionY = Play.WORLD_HEIGHT / 2f + titleGlyphLayout.height / 2f;
    }

    @Override
    public void render() {
        super.render();

        batch.begin();
        titleFont.draw(batch, TITLE, titlePositionX, titlePositionY);
        batch.end();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        AngryWirds.pushPage(new VocSelection());
        return true;
    }
}
