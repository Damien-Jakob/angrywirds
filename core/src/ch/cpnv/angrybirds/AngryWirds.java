package ch.cpnv.angrybirds;

import com.badlogic.gdx.Game;
import java.util.Random;

import ch.cpnv.angrybirds.activities.Welcome;

public class AngryWirds extends Game {
    public static Random alea;

    private Game page;

    @Override
    public void create() {
        alea = new Random();
        page = new Welcome();
    }

    @Override
    public void render() {
        page.render();
    }
}
