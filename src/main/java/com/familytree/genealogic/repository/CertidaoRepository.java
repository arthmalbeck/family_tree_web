package com.familytree.genealogic.repository;

import org.springframework.data.repository.CrudRepository;

import com.familytree.genealogic.model.Certidao;

public interface CertidaoRepository extends CrudRepository<Certidao, String> {
//	Iterable<Atleta> findByDuplas(List<Dupla> dupla);
	Certidao findById(int id);
	Certidao removeById(int id);
}
