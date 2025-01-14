package com.ptit.repo;

import com.ptit.model.StudentCourseGrade;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentCourseGradeRepository extends CrudRepository<StudentCourseGrade, Integer> {
    Optional<StudentCourseGrade> findByStudentIdAndCourseId(Integer studentId, Integer courseId);
}
