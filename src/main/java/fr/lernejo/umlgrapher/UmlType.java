package fr.lernejo.umlgrapher;

import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ConfigurationBuilder;

import java.util.*;

public class UmlType {

    private final TreeSet<Class<?>> classes = new TreeSet<>(Comparator.<Class<?>, String>comparing(Class::getSimpleName).thenComparing(Class::getPackageName));

    public TreeSet<Class<?>> getClasses() {
        return this.classes;
    }

    public UmlType(Class<?>[] inputClasses) {
        this.parseAndCacheClasses(inputClasses);
    }

    private void parseAndCacheClasses(Class<?>[] classes) {
        Queue<Class<?>> queue = new LinkedList<>(Arrays.asList((classes)));

        while(!queue.isEmpty()) {
            Class<?> clazz = queue.poll();
            this.classes.add(clazz);
            queue.addAll(Arrays.asList(clazz.getInterfaces()));
            if (clazz.getSuperclass() != null && !clazz.getSuperclass().equals(Object.class)) {
                queue.add(clazz.getSuperclass());
            }
            List<Class<?>> notAlreadyPresentClasses = this.getChildrenClasses(clazz)
                .stream().filter(cls -> !this.classes.contains(cls)).toList();
            queue.addAll(notAlreadyPresentClasses);
        }
    }

    private Set<Class<?>> getChildrenClasses(Class<?> clazz) {
        Reflections reflections = new Reflections(new ConfigurationBuilder()
            .forPackage("")
            .forPackage("", clazz.getClassLoader())
        );
        return reflections.get(
            Scanners.SubTypes
                .get(clazz)
                .asClass(this.getClass().getClassLoader(), clazz.getClassLoader())
        );
    }
}
