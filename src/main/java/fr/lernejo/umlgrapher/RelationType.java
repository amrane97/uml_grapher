package fr.lernejo.umlgrapher;

public enum RelationType {
    EXTENDS("extends"), IMPLEMENTS("implements");
    private String type;

    RelationType(String type) {
        this.type = type;
    }

    public String toString() {
        return this.type;
    }
}
