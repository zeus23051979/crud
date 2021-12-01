package com.springboot.administradores.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.administradores.app.entity.Administrador;

@Repository("administradorDao")
public interface IAdministradorDao extends CrudRepository<Administrador, Long> {

}
