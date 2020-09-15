package ch.cpnv.model.data;

import java.util.ArrayList;

import ch.cpnv.angrybirds.AngryWirds;

public class Vocabulary {
    private String name;
    private ArrayList<Word> words;

    public Vocabulary(String name) {
        this.name = name;
        words = new ArrayList<Word>();
    }

    public void addWord(Word word) {
        words.add(word);
    }

    public Word pickAWord() {
        // TODO better selection
        return words.get(AngryWirds.alea.nextInt(words.size()));
    }
}
