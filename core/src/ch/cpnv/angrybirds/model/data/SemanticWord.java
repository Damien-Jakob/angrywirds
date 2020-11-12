package ch.cpnv.angrybirds.model.data;

import java.util.HashMap;

public class SemanticWord {
    // tells if the word has been given to a Pig
    private boolean allocated;
    // tells if the player has already solved the translation
    private boolean found;

    private HashMap<String, String> values = new HashMap<>();

    public SemanticWord() {
        allocated = false;
        found = false;
    }

    public String getValue(Language language) throws TranslationDoesNotExistException {
        return getValue(language.getShortname());
    }

    public String getValue(String languageShortName) throws TranslationDoesNotExistException {
        if (!values.containsKey(languageShortName)) {
            throw new TranslationDoesNotExistException();
        }
        return values.get(languageShortName);
    }

    public void addTranslation(Language language, String value) throws TranslationExistsException {
        addTranslation(language.getShortname(), value);
    }

    public void addTranslation(String languageShortName, String value) throws TranslationExistsException {
        if (values.containsKey(languageShortName)) {
            throw new TranslationExistsException();
        }
        values.put(languageShortName, value);
    }

    // TODO remove deprecated methods
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
