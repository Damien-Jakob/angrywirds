package ch.cpnv.angrybirds.model.data;

import java.util.ArrayList;

import ch.cpnv.angrybirds.AngryWirds;

public class Vocabulary {
    private String name;
    private ArrayList<Word> words;

    public Vocabulary(String name) {
        this.name = name;
        words = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Word> getWords() {
        return words;
    }

    public int size() {
        return words.size();
    }

    public void addWord(Word word) {
        words.add(word);
    }

    public boolean hasNotFoundWord() {
        for (Word word : words) {
            if (!word.isFound()) {
                return true;
            }
        }
        return false;
    }

    public boolean hasNotAllocatedWord() {
        for (Word word : words) {
            if (!word.isAllocated()) {
                return true;
            }
        }
        return false;
    }

    public void resetFoundWords() {
        for (Word word : words) {
            word.setFound(false);
        }
    }

    public void deallocateAll() {
        for (Word word : words) {
            word.setAllocated(false);
        }
    }

    public Word pickWord() throws NoPickableWordException {
        return pickWord(words);
    }

    public Word pickNotAllocatedWord() throws NoPickableWordException {
        return pickWord(notAllocatedWords());
    }

    public Word pickNotFoundWord() throws NoPickableWordException {
        return pickWord(notAllocatedWords());
    }

    private Word pickWord(ArrayList<Word> words) throws NoPickableWordException {
        int wordsCount = words.size();

        if( wordsCount <= 0) {
            throw new NoPickableWordException();
        }
        return words.get(AngryWirds.alea.nextInt(words.size()));
    }

    private ArrayList<Word> notAllocatedWords() {
        ArrayList<Word> unallocatedWords = new ArrayList<>();
        for (Word word : words) {
            if (!word.isAllocated()) {
                unallocatedWords.add(word);
            }
        }
        return unallocatedWords;
    }

    private ArrayList<Word> notFoundWords() {
        ArrayList<Word> unallocatedWords = new ArrayList<>();
        for (Word word : words) {
            if (!word.isFound()) {
                unallocatedWords.add(word);
            }
        }
        return unallocatedWords;
    }
}
