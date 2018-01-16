package ua.nure.antoniuk.db.transaction;

@FunctionalInterface
public interface Transaction<T> {

    T execute();
}
