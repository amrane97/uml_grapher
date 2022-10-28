package fr.lernejo;

import fr.lernejo.umlgrapher.GraphType;
import fr.lernejo.umlgrapher.UmlGraph;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class UmlGraphTests {
    @Test
    void test_when_interface_with_no_relatio() {
        UmlGraph graph = new UmlGraph(new Class[]{
            Machin.class
        });
        String output = graph.as(GraphType.Mermaid);
        Assertions.assertThat(output).isEqualTo("""
            classDiagram
            class Machin{
                <<interface>>
            }
            """);
    }
    @Test
    void test_parent_relation() {
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
            class Living{
                <<interface>>
            }
            class Plant{
                <<interface>>
            }
            class Tree{
                <<interface>>
            }
            Alder  <|.. Tree : implements
            Plant  <|.. Living : implements
            Animal  <|.. Living : implements
            Ant  <|.. Animal : implements
            Cat  <|.. Animal : implements
            Tree  <|.. Plant : implements
            """);
    }

    @Test
    void test_parent_and_children_relation() {
        UmlGraph graph = new UmlGraph(new Class[]{
            Living.class
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
            class Living{
                <<interface>>
            }
            class Plant{
                <<interface>>
            }
            class Tree{
                <<interface>>
            }
            Alder  <|.. Tree : implements
            Plant  <|.. Living : implements
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

    public interface Machin {

    }
}
