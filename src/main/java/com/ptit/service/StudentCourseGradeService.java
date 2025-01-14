package com.ptit.service;

import com.ptit.model.StudentCourseGrade;
import com.ptit.repo.StudentCourseGradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentCourseGradeService {

    @Autowired
    private StudentCourseGradeRepository studentCourseGradeRepository;

    public boolean addGrade(StudentCourseGrade grade) {
        Optional<StudentCourseGrade> existingGrade = studentCourseGradeRepository.findByStudentIdAndCourseId(grade.getStudentId(), grade.getCourseId());
        if (existingGrade.isPresent()) {
            return false;
        }
        studentCourseGradeRepository.save(grade);
        return true;
    }

    public boolean updateGrade(Integer id, StudentCourseGrade grade) {
        Optional<StudentCourseGrade> existingGrade = studentCourseGradeRepository.findById(id);
        if (existingGrade.isPresent()) {
            grade.setId(id);
            studentCourseGradeRepository.save(grade);
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteGrade(Integer id) {
        Optional<StudentCourseGrade> existingGrade = studentCourseGradeRepository.findById(id);
        if (existingGrade.isPresent()) {
            studentCourseGradeRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}