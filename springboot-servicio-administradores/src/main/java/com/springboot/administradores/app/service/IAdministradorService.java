package com.springboot.administradores.app.service;

import java.util.List;

import com.springboot.administradores.app.entity.Administrador;

public interface IAdministradorService {

	public List<Administrador> findAll();
	
	public Administrador findById(Long id);
	
	public Administrador save(Administrador	administrador);
	
	public void delete(Administrador administrador);
	
	public void deleteById(Long id);
}
