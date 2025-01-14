package com.ptit.repo;


import com.ptit.model.Cours;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursRepository extends CrudRepository<Cours, Integer> {
}
