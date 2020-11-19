package com.familytree.genealogic.repository;

import org.springframework.data.repository.CrudRepository;

import com.familytree.genealogic.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, String>{

	Usuario findById(int id);
}
