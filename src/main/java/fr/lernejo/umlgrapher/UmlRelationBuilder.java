package fr.lernejo.umlgrapher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UmlRelationBuilder {
    private final UmlType types;

    public UmlRelationBuilder(UmlType types) {
       this.types = types;
    }

    public Map<String, List<UmlRelation>> buildRelations() {
        Map<String, List<UmlRelation>> relations = new HashMap<>();

        for (Class<?> clazz : types.getClasses()) {
            List<UmlRelation> relatives = new ArrayList<>();

            Class<?> superClass = clazz.getSuperclass();
            if (superClass != null && !superClass.equals(Object.class)) {
                relatives.add(new UmlRelation(superClass, RelationType.EXTENDS));
            }
            for (Class<?> currInterface : clazz.getInterfaces()) {
                relatives.add(new UmlRelation(currInterface, RelationType.IMPLEMENTS));
            }

            relations.put(clazz.getSimpleName(), relatives);
        }

        return relations;
    }
}
