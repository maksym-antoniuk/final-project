package db.transaction;

@FunctionalInterface
public interface Transaction<T> {

    T execute();
}
