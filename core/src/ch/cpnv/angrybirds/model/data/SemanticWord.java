package ch.cpnv.angrybirds.model.data;

import java.util.HashMap;

public class SemanticWord {
    private String question;
    private String solution;
    // tells if the word has been given to a Pig
    private boolean allocated;
    // tells if the player has already solved the translation
    private boolean found;

    private HashMap<Language, String> values = new HashMap<>();

    public SemanticWord(String solution, String question) {
        this.question = question;
        this.solution = solution;
        allocated = false;
        found = false;
    }

    // TODO add exception
    public String getValue(Language language) {
        return values.get(language);
    }

    // TODO add exception
    public void addTranslation(Language language, String value) {
        values.put(language, value);
    }

    // TODO remove deprecated
    public String getQuestion() {
        return question;
    }
    public String getSolution() {
        return solution;
    }

    public boolean isAllocated() {
        return allocated;
    }

    public void setAllocated(boolean allocated) {
        this.allocated = allocated;
    }

    public boolean isFound() {
        return found;
    }

    public void setFound(boolean found) {
        this.found = found;
    }
}
