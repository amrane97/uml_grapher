package fr.lernejo.umlgrapher;

public class UmlGraph {

    Class c;

    public UmlGraph(Class<?> machinClass) {
        this.c = machinClass;
    }

    public String as(GraphType graphType) {

        //le code ici

        return """
            classDiagram
            class Machin {
                <<interface>>
            }
            """;
    }
}
