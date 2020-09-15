package ch.cpnv.model.data;

import java.util.ArrayList;

public class Vocabulary {
    private String name;
    private ArrayList<Word> words;

    public Vocabulary(String name) {
        this.name = name;
        words = new ArrayList<Word>();
    }
}
