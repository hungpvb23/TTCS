package com.ptit.repo;

import com.ptit.model.StudentClass;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentClassRepository extends CrudRepository<StudentClass, Integer> {
    Optional<StudentClass> findByClassIdAndStudentId(int classId, int studentId);
}