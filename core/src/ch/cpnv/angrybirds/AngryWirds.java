package ch.cpnv.angrybirds;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import ch.cpnv.angrybirds.activities.Play;
import ch.cpnv.angrybirds.activities.Welcome;
import ch.cpnv.angrybirds.model.data.Vocabulary;
import ch.cpnv.angrybirds.model.data.Word;
import ch.cpnv.angrybirds.providers.VocProvider;

public class AngryWirds extends Game {
    public static Random alea;

    protected static Stack<Game> pages;

    public static Vocabulary voc;
    public static ArrayList<Word> foundWords;

    public static int score = 0;

    @Override
    public void create() {
        alea = new Random();

        pages = new Stack<Game>();
        pages.push(new Welcome());
    }

    @Override
    public void render() {
        pages.peek().render();
    }

    public static void start() {
        AngryWirds.score = 0;
        AngryWirds.voc = VocProvider.getInstance().pickAVoc();
        AngryWirds.foundWords = new ArrayList<Word>();
        AngryWirds.pushPage(new Play());
    }

    public static void pushPage(Game game) {
        // Since we will only push new pages, we don't need to initialize
        pages.push(game);
    }

    public static void popPage() {
        pages.pop();
        Gdx.input.setInputProcessor((InputProcessor) pages.peek());
    }
}
