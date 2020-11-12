package ch.cpnv.angrybirds.model.data;

import com.badlogic.gdx.Gdx;

import java.util.ArrayList;

import ch.cpnv.angrybirds.AngryWirds;

public class Vocabulary {
    private String name;
    private ArrayList<SemanticWord> words;

    public Vocabulary(String name) {
        this.name = name;
        words = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<SemanticWord> getWords() {
        return words;
    }

    public int size() {
        return words.size();
    }

    public void addWord(SemanticWord word) {
        words.add(word);
    }

    public boolean hasNotFoundWord() {
        for (SemanticWord word : words) {
            if (!word.isFound()) {
                return true;
            }
        }
        return false;
    }

    public boolean hasNotAllocatedWord() {
        for (SemanticWord word : words) {
            if (!word.isAllocated()) {
                return true;
            }
        }
        return false;
    }

    public void resetFoundWords() {
        for (SemanticWord word : words) {
            word.setFound(false);
        }
    }

    public void deallocateAll() {
        for (SemanticWord word : words) {
            word.setAllocated(false);
        }
    }

    public SemanticWord pickWord() throws NoPickableWordException {
        return pickWord(words);
    }

    public SemanticWord pickNotAllocatedWord() throws NoPickableWordException {
        return pickWord(notAllocatedWords());
    }

    public SemanticWord pickNotFoundWord() throws NoPickableWordException {
        return pickWord(notFoundWords());
    }

    public void print() {
        for (SemanticWord word : words) {
            Gdx.app.log("EXCEPTION", "Voc: ");
            Gdx.app.log("EXCEPTION",
                    word.getQuestion() + " / " + word.getSolution() + " :: "
            + "Allocated : " + word.isAllocated() + ", Found : " + word.isFound());
        }
    }

    private SemanticWord pickWord(ArrayList<SemanticWord> words) throws NoPickableWordException {
        int wordsCount = words.size();

        if (wordsCount <= 0) {
            throw new NoPickableWordException();
        }
        return words.get(AngryWirds.alea.nextInt(words.size()));
    }

    private ArrayList<SemanticWord> notAllocatedWords() {
        ArrayList<SemanticWord> notAllocatedWords = new ArrayList<>();
        for (SemanticWord word : words) {
            if (!word.isAllocated()) {
                notAllocatedWords.add(word);
            }
        }
        return notAllocatedWords;
    }

    private ArrayList<SemanticWord> notFoundWords() {
        ArrayList<SemanticWord> notFoundWords = new ArrayList<>();
        for (SemanticWord word : words) {
            if (!word.isFound()) {
                notFoundWords.add(word);
            }
        }
        return notFoundWords;
    }
}
