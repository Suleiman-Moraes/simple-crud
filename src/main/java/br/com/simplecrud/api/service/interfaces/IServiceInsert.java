package br.com.simplecrud.api.service.interfaces;

import br.com.simplecrud.api.model.interfaces.IModel;

/**
 * E = Entity
 * I = ID
 * Methods:
 * - I insert(E object);
 */
public interface IServiceInsert<E extends IModel<I>, I> extends IServiceGetRepository<E, I> {

    default I insert(E object) {
        return getRepository().save(object).getKey();
    }
}
