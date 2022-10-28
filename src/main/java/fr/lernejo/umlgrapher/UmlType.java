package fr.lernejo.umlgrapher;

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
            this.classes.addAll(Arrays.asList(clazz.getInterfaces()));
            if (clazz.getSuperclass() != null && !clazz.getSuperclass().equals(Object.class)) {
                queue.add(clazz.getSuperclass());
            }
        }
    }
}
