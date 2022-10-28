package fr.lernejo.umlgrapher;

public class UmlGraph {

    private Class[] classes;

    public UmlGraph(Class<?>[] classes) {
        this.classes = classes;
    }

    public String as(GraphType graphType) {

        InternalGraphRepresentation graphRepresentation = new InternalGraphRepresentation(this.classes);

        if (graphType == GraphType.Mermaid) {
            return MermaidFormatter.format(graphRepresentation);
        }else {
            throw new RuntimeException("graph type inconnu");
        }


    }
}
