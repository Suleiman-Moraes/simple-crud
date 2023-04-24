package br.com.simplecrud.api.service.interfaces;

import br.com.simplecrud.api.model.interfaces.IModel;

/**
 * E = Entity
 * I = ID
 * Methods:
 * - List<E> findAll();
 * - E findByKey(I id);
 * - I insert(E object);
 * - void delete(I id);
 */
public interface IServiceCRUD<E extends IModel<I>, I>
        extends IServiceFindAll<E, I>, IServiceInsert<E, I>, IServiceDelete<E, I> {

}
