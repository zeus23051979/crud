package com.springboot.administradores.app.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.springboot.administradores.app.entity.Administrador;
import com.springboot.administradores.app.repository.IAdministradorDao;

@Service("administradorService")
public class AdministradorServiceImpl implements IAdministradorService {
	
	@Autowired
	@Qualifier("administradorDao")
	private IAdministradorDao administradorDao;

	
	@Transactional(readOnly = true)
	@Override
	public List<Administrador> findAll() {
		
		return (List<Administrador>) administradorDao.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Administrador findById(Long id) {
		
		return administradorDao.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public Administrador save(Administrador administrador) {
		
		return administradorDao.save(administrador);
	}

	@Transactional
	@Override
	public void delete(Administrador administrador) {
		administradorDao.delete(administrador);

	}

	@Transactional
	@Override
	public void deleteById(Long id) {
		administradorDao.deleteById(id);

	}

}
