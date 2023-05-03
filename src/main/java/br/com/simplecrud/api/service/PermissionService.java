package br.com.simplecrud.api.service;

import org.springframework.stereotype.Service;

import br.com.simplecrud.api.repository.IPermissionRepository;
import br.com.simplecrud.api.service.interfaces.IPermissionService;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Service
@AllArgsConstructor
public class PermissionService implements IPermissionService {

    @Getter
    private IPermissionRepository repository;
}
