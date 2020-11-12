package ch.cpnv.angrybirds.model.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import ch.cpnv.angrybirds.model.TextualObject;

public class Button extends TextualObject {

    protected int textOffsetX = 10;
    protected int textOffsetY = 10;

    protected BitmapFont font;

    protected ShapeRenderer shapeRenderer = new ShapeRenderer();

    public Button(Vector2 position, float width, float height, String text, OrthographicCamera camera) {
        super(position, width, height, "bird.png", text);
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(2);

        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.setColor(Color.OLIVE);
    }

    public boolean contains(Vector2 point) {
        return this.getRectangle().contains(point);
    }

    @Override
    public void draw(Batch batch) {
        batch.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.rect(getX(), getY(), getWidth(), getHeight());
        shapeRenderer.end();

        batch.begin();
        font.draw(batch, text, getX() + textOffsetX, getY() + getHeight() - textOffsetY);
    }
}
