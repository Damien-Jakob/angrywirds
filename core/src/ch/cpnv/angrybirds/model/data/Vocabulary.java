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

    public void addWord(Word word) {
        words.add(word);
    }

    public Word pickAWord() {
        return words.get(AngryWirds.alea.nextInt(words.size()));
    }

    public Word pickAWord(ArrayList<Word> banList) {
        ArrayList<Word> pickableWords = (ArrayList<Word>) words.clone();
        for(Word bannedWord : banList) {
            pickableWords.remove(bannedWord);
        }
        return pickableWords.get(AngryWirds.alea.nextInt(pickableWords.size()));
    }

    public int size() {
        return words.size();
    }
}
