package br.com.simplecrud.api.service.interfaces;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.util.CollectionUtils;

import br.com.simplecrud.api.controller.interfaces.IController;
import br.com.simplecrud.api.exception.ResourceNotFoundException;
import br.com.simplecrud.api.model.interfaces.IModel;
import br.com.simplecrud.mapper.Mapper;

public interface IConverterService<M extends IModel> {

    default <T extends RepresentationModel<T>> List<T> parseObjects(List<M> listM, Class<T> clazz,
            Class<? extends IController<?>> controllerClass) {
        if (CollectionUtils.isEmpty(listM)) {
            throw new ResourceNotFoundException();
        }
        return listM.stream().map((M m) -> parseObjectAfterValid(m, clazz, controllerClass))
                .collect(Collectors.toList());
    }

    default <T extends RepresentationModel<T>> T parseObject(M m, Class<T> clazz,
            Class<? extends IController<?>> controllerClass) {
        if (m == null || m.getKey() == null) {
            throw new ResourceNotFoundException();
        }
        return parseObjectAfterValid(m, clazz, controllerClass);
    }

    default <T extends RepresentationModel<T>> T parseObjectAfterValid(M m, Class<T> clazz,
            Class<? extends IController<?>> controllerClass) {
        T t = Mapper.parseObject(m, clazz);
        t.add(linkTo(methodOn(controllerClass).findByKey(m.getKey())).withSelfRel());
        return t;
    }
}
