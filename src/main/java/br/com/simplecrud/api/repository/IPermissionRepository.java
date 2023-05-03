package br.com.simplecrud.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.simplecrud.api.model.Permission;

public interface IPermissionRepository extends JpaRepository<Permission, Long> {

}
