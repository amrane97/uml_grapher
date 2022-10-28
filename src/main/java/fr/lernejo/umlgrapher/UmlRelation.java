package fr.lernejo.umlgrapher;

public class UmlRelation {
    private final Class<?> relatedClass;
    private final RelationType relationType;

    public UmlRelation(Class<?> clazz, RelationType type) {
        this.relatedClass = clazz;
        this.relationType = type;
    }

    public String relatedClassName() {
        return this.relatedClass.getSimpleName();
    }

    public RelationType relationType() {
        return this.relationType;
    }
}
