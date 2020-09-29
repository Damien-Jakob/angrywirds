package ch.cpnv.angrybirds.model.data;

public class Word {
    private String question;
    private String solution;
    // tells if the word has been given to a Pig
    private boolean allocated;
    // tells if the player has already solved the translation
    private boolean found;

    public Word(String solution, String question) {
        this.question = question;
        this.solution = solution;
        allocated = false;
        found = false;
    }

    public String getQuestion() {
        return question;
    }

    public String getSolution() {
        return solution;
    }
}
