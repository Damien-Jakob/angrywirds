package ch.cpnv.angrybirds.model.data;

import java.util.HashMap;

public class SemanticWord {
    // tells if the word has been given to a Pig
    private boolean allocated;
    // tells if the player has already solved the translation
    private boolean found;

    private HashMap<Language, String> values = new HashMap<>();

    public SemanticWord() {
        allocated = false;
        found = false;
    }

    public String getValue(Language language) throws TranslationDoesNotExistException {
        if (!values.containsKey(language)) {
            throw new TranslationDoesNotExistException();
        }
        return values.get(language);
    }

    public void addTranslation(Language language, String value) throws TranslationExistsException {
        if (values.containsKey(language)) {
            throw new TranslationExistsException();
        }
        values.put(language, value);
    }

    // TODO remove deprecated
    public String getQuestion() {
        return "";
    }
    public String getSolution() {
        return "";
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
