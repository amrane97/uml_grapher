package fr.lernejo.umlgrapher;

import java.util.List;
import java.util.Map;

public class InternalGraphRepresentation {
    private final Map<String, List<UmlRelation>> relations;
    private final UmlType types;

    public InternalGraphRepresentation(Class<?>[] classes) {
        this.types = new UmlType(classes);
        this.relations = new UmlRelationBuilder(types).buildRelations();
    }

    public Map<String, List<UmlRelation>> getRelations() {
        return relations;
    }

    public UmlType getTypes() {
        return types;
    }
}
