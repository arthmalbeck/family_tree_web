package com.familytree.genealogic.repository;

import org.springframework.data.repository.CrudRepository;

import com.familytree.genealogic.model.Pessoa;

public interface PessoaRepository extends CrudRepository<Pessoa, String> {
//	Iterable<Atleta> findByDuplas(List<Dupla> dupla);
	Pessoa findById(int id);
	Pessoa removeById(int id);
}
