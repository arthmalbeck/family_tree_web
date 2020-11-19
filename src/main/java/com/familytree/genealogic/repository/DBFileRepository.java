package com.familytree.genealogic.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.familytree.genealogic.model.DBFile;

@Repository
public interface DBFileRepository extends JpaRepository<DBFile, String> {

}