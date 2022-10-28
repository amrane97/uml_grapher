package fr.lernejo;

import fr.lernejo.umlgrapher.GraphType;
import fr.lernejo.umlgrapher.UmlGraph;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class UmlGraphTests {



    @Test
    void empty_interface_with_no_relation() {

        UmlGraph graph = new UmlGraph(new Class[]{
            Living.Plant.Tree.Alder.class,
            Living.Animal.Ant.class,
            Living.Animal.Cat.class
        });
        //System.out.println(Machin.class);

        String output = graph.as(GraphType.Mermaid);

        Assertions.assertThat(output).isEqualTo("""
            classDiagram
            class Alder
            class Animal{
                <<interface>>
            }
            class Ant
            class Cat
            class Tree{
                <<interface>>
            }
            Alder  <|.. Tree : implements
            Animal  <|.. Living : implements
            Ant  <|.. Animal : implements
            Cat  <|.. Animal : implements
            Tree  <|.. Plant : implements
            """);
    }

    public sealed interface Living {
        sealed interface Animal extends Living {
            final class Ant implements Animal {
            }

            final class Cat implements Animal {
            }
        }

        sealed interface Plant extends Living {
            sealed interface Tree extends Plant {
                final class Alder implements Tree {
                }
            }
        }
    }
}
