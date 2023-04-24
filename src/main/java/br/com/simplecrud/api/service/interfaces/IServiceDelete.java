package br.com.simplecrud.api.service.interfaces;

/**
 * E = Entity
 * I = ID
 * Methods:
 * - void delete(I id);
 */
public interface IServiceDelete<E, I> extends IServiceFindByKey<E, I> {

    default void delete(I id) {
        getRepository().delete(findByKey(id));
    }
}
