package ua.nure.antoniuk.util;

@FunctionalInterface
public interface Extractor<T, V> {

    T extract(V value);
}
