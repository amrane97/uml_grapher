package fr.lernejo.umlgrapher;

import picocli.CommandLine;
import picocli.CommandLine.Option;
import java.util.concurrent.Callable;


class Launcher implements Callable<Integer> {

    @Option(names = {"-c", "--classes"},required = true, description = "description de classe")
    private final Class<?>[] c = null;

    @Option(names = {"-g", "--graphe"}, description = "type de graphe")
    private final GraphType graphType = GraphType.Mermaid;

    @Override
    public Integer call() throws Exception { // your business logic goes here..

        UmlGraph umlGraph = new UmlGraph(c);
        String sortie = umlGraph.as(graphType);
        System.out.println(sortie);
        return 0;
    }

    // this example implements Callable, so parsing, error handling and handling user
    // requests for usage help or version help can be done with one line of code.
    public static void main(String... args) {
        int exitCode = new CommandLine(new Launcher()).execute(args);
        System.exit(exitCode);
    }
}
