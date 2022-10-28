package fr.lernejo.umlgrapher;

import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MermaidFormatter {

    public static String format(InternalGraphRepresentation graphRepresentation) {
        StringBuilder sb = initStringBuilder(graphRepresentation.getTypes().getClasses());
        addRelation(sb,graphRepresentation.getRelations());
        return sb.toString();
    }

    private static StringBuilder initStringBuilder(Set<Class<?>> classes) {
        StringBuilder sb = new StringBuilder("classDiagram\n");

        for (Class<?> clazz : classes) {
            sb.append("class ").append(clazz.getSimpleName());
            if (Modifier.isInterface(clazz.getModifiers())) {
                sb.append("{\n    <<interface>>\n}");
            }
            sb.append("\n");
        }
        return sb;
    }

    private static void addRelation(StringBuilder sb, Map<String, List<UmlRelation>> relations) {
        relations.entrySet().forEach(entry -> {
            String className = entry.getKey();
            List<UmlRelation> relatives = entry.getValue();

            for (UmlRelation relative: relatives) {
                sb.append(className).append(" ");
                if (relative.relationType().equals(RelationType.EXTENDS)) {
                    sb.append(" <|-- ");
                } else {
                    sb.append(" <|.. ");
                }
                sb.append(relative.relatedClassName()).append(" : ").append(relative.relationType().toString());
                sb.append("\n");
            }
        });
    }
}
