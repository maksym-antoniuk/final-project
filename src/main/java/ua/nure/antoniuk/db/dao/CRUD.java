package ua.nure.antoniuk.db.dao;

import java.util.Optional;


/**
 * @author Maksym Antoniuk
 * @param <E> the entity of which will be operated
 *
 * @apiNote Use this interface for extending in your DAO interfaces to implements standard CRUD operations
 */
public interface CRUD<E> {

    /**
     *
     * @param entity which we must to add
     * @return auto generated id by server
     */
    int create(E entity);

    /**
     *
     * @param id
     * @return entity by id, use Optional<T> to avoid NullPointerException
     */
    Optional<E> read(int id);

    /**
     *
     * @param entity which necessary update
     * @return true if update successful and false otherwise
     */
    boolean update(E entity);

    /**
     *
     * @param entity which necessary delete
     * @return true if delete successful and false otherwise
     */
    boolean delete(E entity);
}
