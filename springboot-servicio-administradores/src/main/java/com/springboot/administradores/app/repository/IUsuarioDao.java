package com.springboot.administradores.app.repository;



import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.administradores.app.entity.Usuario;

@Repository("usuarioDao")
public interface IUsuarioDao extends CrudRepository<Usuario, Long>{

	@Query(value = "select * from users", nativeQuery = true)
	public Usuario findbyUsername(String username);
	
	
}
