package br.com.simplecrud.mock.interfaces;

import java.util.LinkedList;
import java.util.List;

public interface IMock<E>{

    E mockEntity(Integer number);
    
    default List<E> mockEntityList() {
        List<E> entitys = new LinkedList<>();
        for (int i = 1; i <= 14; i++) {
            entitys.add(mockEntity(i));
        }
        return entitys;
    }
}
