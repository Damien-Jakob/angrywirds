package ch.cpnv.angrybirds.model.data;

public class TranslationDoesNotExistException extends Exception {
    public TranslationDoesNotExistException(String message) {
        super(message);
    }
}
