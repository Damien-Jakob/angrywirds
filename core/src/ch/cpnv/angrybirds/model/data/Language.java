package ch.cpnv.angrybirds.model.data;

public class Language {
    private String name;
    private String shortName;

    public Language(String shortName, String name) {
        this.name = name;
        this.shortName = name;
    }

    public String getShortname() {
        return shortName;
    }

    public String getName() {
        return name;
    }
}
